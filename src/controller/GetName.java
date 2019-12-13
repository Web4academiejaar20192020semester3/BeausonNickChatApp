package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetName extends AsyncRequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        //Person person = (Person) session.getAttribute("user");
        String loggedInUserEmail = session.getAttribute("userEmail") + "";

        PersonService personService = getPersonService();
        Person user = personService.getPerson(loggedInUserEmail);

        if(user == null){
            return "";
        }else{
            return user.getFirstName();
        }
    }
}
