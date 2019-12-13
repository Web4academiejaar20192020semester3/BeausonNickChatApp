package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeStatus extends AsyncRequestHandler {


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

/*
        String status = request.getParameter("status");
        System.out.println("ChangeStatus getparam: " + status);
        String statuss;
        if(status == null) {
            statuss = request.getAttribute("status") + "";
            System.out.println("status == null & statuss: " + statuss);
        }
        */

        String status = request.getParameter("status"); //doorgestuurd vanuit .js
        //System.out.println("ChangeStatus: " + status);

        HttpSession session = request.getSession(); //Ophalen user
        String loggedInUserEmail =  session.getAttribute("userEmail") + "";
        //System.out.println("Logged in user email: " + loggedInUserEmail);

        PersonService personService = getPersonService();
        Person user = personService.getPerson(loggedInUserEmail);
        //System.out.println("Logged in user before set: " + user.getFirstName() + " " + user.getStatus());

        user.setStatus(status);
        //System.out.println("Logged in user after set: " + user.getFirstName() + " " + user.getStatus());


        //person.setStatus(status);


        return status;
    }
}
