package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

	private String email;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String salt;
	private String firstName;
	private String lastName;
	private int age;
    private String sex;
	private String status;
	private Role role;

	@JsonIgnore  //json moet negeren, anders infinite loop -> ophalen vrienden van vrienden van vrienden van ...
	private ArrayList<Person> friends = new ArrayList<Person>();

	public Person(String email, String password, String firstName,
			String lastName,int age, String sex, Role role, String status) {
	    setEmail(email);
		setHashedPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setAge(age);
		setSex(sex);
		setStatus(status);
		setRole(role);
	}

	public Person(String email, String password, String salt,
			String firstName, String lastName,int age, String sex, Role role,String status) {
		setEmail(email);
		setPassword(password);
		setSalt(salt);
		setFirstName(firstName);
		setLastName(lastName);
		setAge(age);
		setSex(sex);
		setStatus(status);
		setRole(role);
	}

	public Person() {
	}

	public void addFriend(Person person){
	    if(person == null){
	        throw new DomainException("No person is given");
        }
        this.friends.add(person);
    }
    @JsonIgnore
	public ArrayList<Person> getFriends(){
	    return this.friends;
    }

    public void setFriends(ArrayList<Person> friends){
		if(friends == null){
			throw new IllegalArgumentException("No friends given");
		}
		this.friends=friends;
	}

	public void setStatus(String status){
	    if(status == null ||status.trim().isEmpty()){
	        throw new IllegalArgumentException("No valid status");
        }
        this.status=status;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex(){
	    return this.sex;
    }

    public String getStatus(){
	    return this.status;
    }

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role=role;
	}

	public void setAge(int age){
	    this.age = age;
    }

    public int getAge(){
	    return this.age;
    }
	

	public void setEmail(String email) {
		if (email.isEmpty()) {
			throw new IllegalArgumentException("No email given");
		}
		String USERID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public boolean isCorrectPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		return getPassword().equals(hashPassword(password, getSalt()));
	}

	public void setPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	public void setHashedPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		this.password = hashPassword(password);
	}

	private String hashPassword(String password) {
		SecureRandom random = new SecureRandom();
		byte[] seed = random.generateSeed(20);

		String salt = new BigInteger(1, seed).toString(16);
		this.setSalt(salt);

		return hashPassword(password, salt);
	}

	private String hashPassword(String password, String seed) {
		String hashedPassword = null;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(salt.getBytes("UTF-8"));
			crypt.update(password.getBytes("UTF-8"));
			hashedPassword = new BigInteger(1, crypt.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new DomainException(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			throw new DomainException(e.getMessage(), e);
		}
		return hashedPassword;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSalt() {
		return salt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName.isEmpty()) {
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;// firstName;

	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName.isEmpty()) {
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}

}
