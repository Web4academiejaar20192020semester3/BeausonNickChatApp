package controller;

import domain.ConversationService;
import domain.PersonService;

public class ControllerFactory {
	
    public RequestHandler getController(String key, PersonService model, ConversationService conservationModel) {
        return createHandler(key, model,conservationModel);
    }
    
	private RequestHandler createHandler(String handlerName, PersonService model, ConversationService conversationModel) {
		RequestHandler handler = null;
		try {
			Class<?> handlerClass = Class.forName("controller."+ handlerName);
			Object handlerObject = handlerClass.newInstance();
			handler = (RequestHandler) handlerObject;
	    	handler.setModel(model);
	    	handler.setConversationModel(conversationModel);
		} catch (Exception e) {
			throw new RuntimeException("Deze pagina bestaat niet!!!!");
		}
		return handler;
	}


}
