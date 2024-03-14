package DTO_Tuto;

public class UserDTO {
    private int id;
    private String username;
    private String email;
    private String state;
    private String zipCode;
	public UserDTO(int id, String username, String email, String state, String zipCode) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.state = state;
		this.zipCode = zipCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
    
}
