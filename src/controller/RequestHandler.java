package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import domain.*;

import java.io.IOException;

public abstract class RequestHandler {
	
	private PersonService personService;
	private ConversationService conversationService;
	
	public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	public void setModel (PersonService personService) {
		this.personService = personService;
	}
	public void setConversationModel(ConversationService conversationService){
		this.conversationService=conversationService;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public ConversationService getConversationService(){
		return this.conversationService;
	}


	protected boolean isFromUserWithRole (HttpServletRequest request, Role role) {
		Person person = (Person) request.getSession().getAttribute("user");
		if (person != null && person.getRole().equals(role)) {
			return true;
		}
		return false;
	}

}
