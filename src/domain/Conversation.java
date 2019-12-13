package domain;

import java.util.ArrayList;
import java.util.List;

public class Conversation {

    ArrayList<Message> messages = new ArrayList<Message>();
    Person person1;
    Person person2;

    public Conversation(Person person1, Person person2){
        this.setPerson1(person1);
        this.setPerson2(person2);
    }

    public void addMessageToConversation(Message message){
        if(message == null){
            throw new DomainException("Invalid message given!");
        }
        this.messages.add(message);
    }

    public void clearConversation(){
        this.messages.clear();
    }

    public ArrayList<Message> getMessages(){
        return this.messages;
    }

    public Person getPerson1() {
        return person1;
    }

    protected void setPerson1(Person person1) {
        this.person1 = person1;
    }

    public Person getPerson2() {
        return person2;
    }

    protected void setPerson2(Person person2) {
        this.person2 = person2;
    }


}
