package domain;

import db.ConversationRepository;

import java.util.List;

public class ConversationService {
    private ConversationRepository conversationRepository = new ConversationRepository();

    public ConversationService(){
    }

    public Conversation getConversation(Person person1,Person person2){
        return conversationRepository.getConversation(person1,person2);
    }

    public List<Conversation> getAllConversations(){
        return conversationRepository.getAllConversations();
    }

    public void addConversation(Person user,Person friend){
        conversationRepository.addConversation(user,friend);
    }

    public boolean checkConversationAlreadyExists(Person user, Person friend){
       return conversationRepository.checkConversationAlreadyExists(user,friend);
    }
}
