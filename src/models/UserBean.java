package models;

public class UserBean {
	
	private String password;
	private String email;
	private String firstName;
	private String lastName;

	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	@Override
	public String toString() {
		return "UserBean [password=" + password + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}
	public UserBean(String password, String email, String firstName, String lastName) {
		super();
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	
	

}
