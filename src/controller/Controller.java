package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.RemoteEndpoint;
import javax.xml.crypto.Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.*;

//@WebServlet("/Controller")
@WebServlet(urlPatterns={"/Controller"}, asyncSupported=true)
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public PersonService model = new PersonService();


	private ConversationService conversationModel = new ConversationService();
	private ControllerFactory controllerFactory = new ControllerFactory();
	//private UserController usercontroller = new UserController(model);

	public Controller() {
		super();
	}

	public PersonService getPersonService(){
		return this.model;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPut(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

	    //1 ophalen data
        //2 omzetten data naar object
        //3 zorgen asynchroon aangepast


	    //System.out.println("DoPut: ");
	    BufferedReader br = new BufferedReader( new InputStreamReader(request.getInputStream()));
	    String data = br.readLine();

	    ObjectMapper mapper = new ObjectMapper();
	    Map<String, Object> map = mapper.readValue(data, Map.class);

	    try{
	        Person person = mapper.readValue(data, Person.class);

	        /* retrieve password, salt & friendslist: */
            String email = person.getEmail();
            Person oldPerson =  this.model.getPerson(email);
            person.setPassword(oldPerson.getPassword());
            person.setSalt(oldPerson.getSalt());
            person.setFriends(oldPerson.getFriends());

            this.model.updatePersons(person);

        }catch( IOException e){

        }
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String result = "index.jsp";
        RequestHandler handler = null;
        if (action != null) {

            try {
                handler = controllerFactory.getController(action, model, conversationModel);
                result = handler.handleRequest(request, response);
            } catch (NotAuthorizedException exc) {
                List<String> errors = new ArrayList<String>();
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                result = "index.jsp";
            }
        } else {  /* localhost:8080/Controller ->  returns all users als json */
            response.setContentType("application/json;charset=UTF-8");
            ServletOutputStream out = response.getOutputStream();
            response.setHeader("Access-Control-Allow-Origin","*");
            ArrayList<Person> users = this.model.getPersons();
            String output = toJSON(users);
            out.print(output);
            //request.getRequestDispatcher(result).forward(request, response);
        }

        if (handler instanceof SyncRequestHandler) {
            RequestDispatcher view = request.getRequestDispatcher(result);
            view.forward(request, response); //Toont heel nieuwe pagina
        } else if(handler instanceof AsyncRequestHandler){
            if (result != null) {
                response.setContentType("application/json"); //laat weten dat string json object is
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.getWriter().write(result); //response sturen
            }
        }
		/*
    2 soorten requesthandlers: synch & asynch
    -> 2 subklasses van requesthandler
    -> sync geeft jsp terug
    -> asynch geeft json terug aan .js
    */
    }

    private String toJSON (ArrayList< Person > users) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(users);
    }

    private static String inputStreamToString(InputStream inputStream){
	    Scanner scanner = new Scanner(inputStream, "UTF-8");
	    return scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
    }

}
