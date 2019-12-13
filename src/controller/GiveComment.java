package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GiveComment extends SyncRequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        int topicnr = Integer.parseInt(request.getParameter("topic"));
        System.out.println(topicnr);
        String topic;
        switch(topicnr){
            case 1: topic = "Was het een interessante projectweek?";
                    break;
            case 2: topic = "Wat ben je van plan om te doen vandaag?";
                    break;
            case 3: topic = "Naar welke muziek ben je momenteel aan het luisteren?";
                    break;
            case 4: topic = "Wat zijn mogelijke examenvagen voor het vak Web4?";
                    break;
            case 5: topic = "Komt er ooit een Web5?";
                    break;
            case 6: topic = "Wat is je favoriete muziek?";
                    break;
            case 7: topic = "Waar ben je van plan om stage te doen?";
                    break;
            case 8: topic = "Is CORS je vriend?";
                    break;
            default: topic = "Error";
        }
        System.out.println(topic);
        HttpSession session = request.getSession();
        session.setAttribute("topic",topic); //Doorsturen om als titel mee te geven

        System.out.println("Session topic created");


        return "giveComment.jsp";
    }
}