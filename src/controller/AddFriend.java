package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddFriend extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PersonService personService = getPersonService();

        String newFriendEmail = request.getParameter("newFriend");

        HttpSession session = request.getSession(); //Ophalen user
        String loggedInUserEmail = session.getAttribute("userEmail") + ""; // Eigen user

        /* Get Logged in user as Person object */
        Person LoggedInUser = personService.getPerson(loggedInUserEmail);

        /* Get Friend as Person object */
        Person newFriend = personService.getPerson(newFriendEmail);

        LoggedInUser.addFriend(newFriend);
        newFriend.addFriend(LoggedInUser);
        return null;
    }
}
