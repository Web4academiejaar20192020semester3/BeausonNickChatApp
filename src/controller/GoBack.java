package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GoBack extends SyncRequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.removeAttribute("topic");

        System.out.println("Session topic removed");
        return "index.jsp";
    }
}
