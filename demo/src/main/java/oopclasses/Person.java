package oopclasses;

public abstract class Person {
    private int id;
    private String phone_numb;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private String username;


    public Person(int id, String phone_numb, String password, String first_name, String last_name, String email, String username) {
        super();
        this.id = id;
        this.phone_numb = phone_numb;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
    }
    public int getId() { 
    	return id; }
    public String getPhone_numb() { 
    	return phone_numb; }
    public String getPassword() { 
    	return password; }
    public String getFirst_name() { 
    	return first_name; }
    public String getLast_name() { 
    	return last_name; }
    public String getEmail() { 
    	return email; }
    public String getUsername() { 
    	return username; }

    public void setFirst_name(String first_name) {
        while (!first_name.matches("[a-zA-Z]+")) {
            System.out.print("Invalid letters only");
        }
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        while (!last_name.matches("[a-zA-Z]")) {
            System.out.print("Invalid letters only");
        }
        this.last_name = last_name;
    }

    public void setPhone_numb(String phone_numb) {
        while (phone_numb == null || phone_numb.isBlank() || !phone_numb.matches("^[0-9]$")) {
            System.out.print("Invalid phone,");
        }
        this.phone_numb = phone_numb;
    }

    public void setId(int id) {
        if (id <= 0)
            System.out.printf("ID must be positive.");
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
	@Override
	public String toString() {
		return "Person [id=" + id + ", phone_numb=" + phone_numb + ", password=" + password + ", first_name="
				+ first_name + ", last_name=" + last_name + ", email=" + email + ", username=" + username + "]";
	}
    
    
}