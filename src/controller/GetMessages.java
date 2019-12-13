package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class GetMessages extends AsyncRequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response)throws IOException {

        //System.out.println("GetMessages");

        ConversationService conversationModel = getConversationService();
        PersonService personService = getPersonService();

        /*  Friend */
        String friendEmail = request.getParameter("userEmail");
        //System.out.println("GetMessages friendEmail: " + friendEmail);
        Person friend = personService.getPerson(friendEmail);
        //System.out.println(friend.getFirstName());


        /* Logged in user */
        HttpSession session = request.getSession();
        String loggedInUserEmail = session.getAttribute("userEmail") + "";
        Person user = personService.getPerson(loggedInUserEmail);

        Conversation conversation = null;
        boolean test = conversationModel.checkConversationAlreadyExists(user,friend);
        if(!conversationModel.checkConversationAlreadyExists(user,friend)){
            System.out.println("Conversation for " + user.getFirstName() + " & " + friend.getFirstName() + " added");
            conversationModel.addConversation(user,friend);
        }
        conversation = conversationModel.getConversation(user,friend);

        ArrayList<Message> messages = conversation.getMessages();
        if(messages == null){
            System.out.println("No messages");
            return null;
        }else {
            //System.out.println(toJSON(messages));
            return toJSON(messages);
        }
    }

    public String toJSON (ArrayList<Message> friends) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(friends);
    }
}
