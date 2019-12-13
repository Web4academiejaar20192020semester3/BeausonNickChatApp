package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Message;
import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class GetOnOffCount extends AsyncRequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String status = request.getParameter("");

        PersonService personService = getPersonService();

        HttpSession session = request.getSession();
        String loggedInUserEmail = session.getAttribute("userEmail") + "";
        Person person = personService.getPerson(loggedInUserEmail);

        int offline = 0;
        int online = 0;
        if(person != null) {
            ArrayList<Person> friends = person.getFriends();


            for (Person friend : friends) {
                if (friend.getStatus().equals("offline")) {
                    offline++;
                } else {
                    online++;
                }
            }
        }
        ArrayList<Integer> count = new ArrayList<>();
        count.add(online);
        count.add(offline);


        return toJSON(count);


    }

    public String toJSON (ArrayList<Integer> count) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(count);
    }
}
