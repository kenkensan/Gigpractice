package dto;

public class Kadaidto {
	private String name;
	private int age;
	private String gender;
	private int tell;
	private String mail;
	private String salt;
	private String password;
	private String hashedPassword;
	public Kadaidto(String name, int age, String gender, int tell, String mail, String salt, String password,
			String hashedPassword) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.tell = tell;
		this.mail = mail;
		this.salt = salt;
		this.password = password;
		this.hashedPassword = hashedPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getTell() {
		return tell;
	}
	public void setTell(int tell) {
		this.tell = tell;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
}
