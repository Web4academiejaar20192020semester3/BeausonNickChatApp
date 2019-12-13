package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetStatus extends AsyncRequestHandler{


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        //Person person = (Person) session.getAttribute("user");
        String loggedInUserEmail = session.getAttribute("userEmail") + "";
        //System.out.println("GetStatus logged in user mail: " + loggedInUserEmail);

        PersonService personService = getPersonService();
        Person user = personService.getPerson(loggedInUserEmail);

        String status = user.getStatus();

        // OF   Person person = (Person) request.getSesstion().getAttribute("user");
        return status;
    }
}

