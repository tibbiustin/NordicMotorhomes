
package motorhome;

public class Customer {
	private int id;
	private String name;
	private String email;
	private String password;
	private long cpr;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public long getCpr() {
		return cpr;
	}
	public void setCpr(long cpr) {
		this.cpr = cpr;
	}

	public Customer(){
		super();
	}
	
	public Customer(String name, long cpr, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpr = cpr;
	}
	
	public Customer(int id, String name, long cpr,String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpr = cpr;
	}
	

}
