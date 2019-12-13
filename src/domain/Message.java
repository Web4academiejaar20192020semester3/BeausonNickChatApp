package domain;

public class Message {

    Person person;
    String message;

    public Message(Person person, String message){
        this.setPerson(person);
        this.setMessage(message);
    }

    public Person getPerson(){
        return this.person;
    }

    protected void setPerson(Person person) {
        this.person = person;
    }

    public String getMessage() {
        return message;
    }

    protected void setMessage(String message) {
        this.message = message;
    }

}
