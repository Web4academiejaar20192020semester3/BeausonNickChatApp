package db;

import domain.Conversation;
import domain.DomainException;
import domain.Person;

import java.util.ArrayList;
import java.util.List;

public class ConversationRepository {

    List<Conversation> conversations = new ArrayList<Conversation>();

    public ConversationRepository(){

    }

    public boolean checkConversationAlreadyExists(Person user, Person friend){
        boolean exists = false;
        if(this.getConversation(user,friend) != null){
            exists = true;
        }
        return exists;
    }

    public void addConversation(Person user, Person friend){
        Conversation conversation = null;
        if(!checkConversationAlreadyExists(user,friend)) {
            conversation = new Conversation(user,friend);
            this.conversations.add(conversation);
        }else{
            throw new DbException("Conversation with users already made!");
        }
    }

    public Conversation getConversation(Person person1, Person person2){
        Conversation conversation = null;
        for(Conversation conv : conversations){
            //if conv.p1 = p1 || conv.p1=p2  && conv.p2 = p1 ||conv.p2=p2
            if((conv.getPerson1().getEmail().equals(person1.getEmail()) || conv.getPerson1().getEmail().equals(person2.getEmail())) &&
                    (conv.getPerson2().getEmail()).equals(person1.getEmail()) || conv.getPerson2().getEmail().equals(person2.getEmail())){
                conversation=conv;
            }

        }
        return conversation;
    }


    public List<Conversation> getAllConversations(){
        return this.conversations;
    }

}

