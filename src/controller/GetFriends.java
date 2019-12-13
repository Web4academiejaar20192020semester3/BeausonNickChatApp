package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class GetFriends extends AsyncRequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*  Get user out of session */
        HttpSession session = request.getSession();
       // Person person = (Person) session.getAttribute("user");
        String loggedInUserEmail = session.getAttribute("userEmail") + "";

        PersonService personService = getPersonService();
        Person user = personService.getPerson(loggedInUserEmail);

        ArrayList<Person>  friends = user.getFriends();

        return toJSON(friends);



    }

    public String toJSON (ArrayList<Person> friends) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(friends);
    }
}
