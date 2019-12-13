package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetChatPage extends SyncRequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        return "chatPage.jsp";
    }
}
