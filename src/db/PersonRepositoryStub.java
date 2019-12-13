package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Person;
import domain.Role;

import javax.servlet.http.HttpSession;

public class PersonRepositoryStub implements PersonRepository {
	private Map<String, Person> persons = new HashMap<String, Person>();
	
	public PersonRepositoryStub () {
		Person administrator = new Person("bib@ucll.be", "t", "Bib", "Liothekaris",35,"Male", Role.BIB, "online");
		add(administrator);
		Person jan = new Person("jan@ucll.be", "t", "Jan", "Janssens", 35,"Male", Role.LID, "online");
		add(jan);
		Person an = new Person("an@ucll.be", "t", "An", "Cornelissen",34, "Female", Role.LID, "online");
		add(an);
		Person sofie = new Person ("sofie@ucll.be", "t", "Sofie","Van de Vonder",22,"Female", Role.LID, "online");
		add(sofie);
		Person nick = new Person ("nick@ucll.be", "t", "Nick","Beauson",27,"Male", Role.LID, "offline");
		add(nick);

		persons.put(administrator.getEmail(),administrator);
		persons.put(jan.getEmail(),jan);
		persons.put(an.getEmail(),an);
		persons.put(sofie.getEmail(),sofie);
		persons.put(nick.getEmail(), nick);

        jan.addFriend(an);
       /* jan.addFriend(sofie);
		jan.addFriend(Nick);*/
        an.addFriend(jan);
       /* an.addFriend(sofie);
		an.addFriend(Nick);
        sofie.addFriend(jan);
        sofie.addFriend(an);
        sofie.addFriend(Nick);
        Nick.addFriend(jan);
        Nick.addFriend(sofie);
        Nick.addFriend(an);*/
	}
	
	public Person get(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		return persons.get(personId);
	}
	
	public ArrayList<Person> getAll(){
		return new ArrayList<Person>(persons.values());

	}

	public void add(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		if (persons.containsKey(person.getEmail())) {
			throw new IllegalArgumentException("User already exists");
		}
		this.persons.put(person.getEmail(), person);
	}
	
	public void update(Person person){

		if(person == null){
			throw new IllegalArgumentException("No person given");
		}

		this.persons.put(person.getEmail(),person);
		for(Person user: this.persons.values()){
		    ArrayList<Person> friendList = user.getFriends();
            for(Person friend: friendList){
                if(friend.getEmail().equals(person.getEmail())){
                    friend.setStatus(person.getStatus());
                    friend.setFirstName(person.getFirstName());
                    friend.setLastName(person.getLastName());
                    friend.setAge(person.getAge());
                    friend.setSex(person.getSex());
                }
            }
        }


/*
		System.out.println("update person: " + person.getEmail());
		Person person1 = this.get(person.getEmail());
		System.out.println("Person voor update: " + person1.getFirstName() + " " + person1.getStatus() + " " + person1.getPassword());
		persons.put(person.getEmail(), person);
		Person person2 = this.get(person.getEmail());
		System.out.println("Person na update: " + person2.getFirstName() + " " + person2.getStatus() + " " + person2.getPassword());

		for(Person friend: person1.getFriends()){

        }*/
	}
	
	public void delete(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		persons.remove(personId);
	}
	
	public Person getAuthenticatedUser(String email, String password) {
		Person person = get(email);
		
		if (person != null && person.isCorrectPassword(password)) {
			return person;
		}
		else {
			return null;
		}
	}
}
