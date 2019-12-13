package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;
import domain.PersonService;
import domain.Role;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "GetUsers", urlPatterns = {"/GetUsers"})
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PersonService personService;

    public UserController(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("usercontroller put");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserController");


        Person person = new Person("jan@ucll.be","t","Jan","Peters",35,"M", Role.LID,"online");
        Person person1 = new Person("nick@ucll.be","t","Nick","Beauson",27,"M",Role.LID,"online");
        ArrayList<Person> users = new ArrayList<>();
        users.add(person);
        users.add(person1);

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        // setting the CORS request, CrossOriginResourceSharing
        // so that this url/response is accessible in another domain (angular application for example)


        response.setHeader("Access-Control-Allow-Origin","*");
        /*response.setHeader("Access-Control-Allow-Methods","GET");
        response.setStatus(HttpServletResponse.SC_OK);*/


        String output = toJSON(users);

        //response.setHeader("Access-Control-Allow-Origin", "*");
        //response.getWriter().write(heroes);
        out.print(output);
    }

    public String toJSON (ArrayList<Person> users) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(users);
    }
}
