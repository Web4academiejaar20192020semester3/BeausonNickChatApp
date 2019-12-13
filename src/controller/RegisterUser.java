package controller;

import domain.Person;
import domain.PersonService;
import domain.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUser extends SyncRequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userId = request.getParameter("userId");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        //Foutenafhandeling
        if(!password1.equals(password2)){
            //arraylist errors -> in session steken en ophalen op register.jsp
        }

        PersonService model = getPersonService();
        Person newUser = new Person(userId,password1,firstName,lastName,age, sex, Role.LID,"online");
        model.addPerson(newUser);
        System.out.println("New user " + firstName + " created");

        return "index.jsp";
    }
}
