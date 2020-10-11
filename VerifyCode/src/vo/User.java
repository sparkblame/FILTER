package vo;

public class User {
	private String userName;
	private String password;
	private String chrName;
	private String role;
	public User(String userName, String password, String chrName, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.chrName = chrName;
		this.role = role;
	}
	public String getChrName() {
		return chrName;
	}
	public void setChrName(String chrName) {
		this.chrName = chrName;
	}
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", chrName=" + chrName + ", role=" + role + "]";
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
