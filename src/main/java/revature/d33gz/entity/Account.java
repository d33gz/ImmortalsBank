package revature.d33gz.entity;

public class Account {
	private int id;
	private int owner;
	private String name;
	private int balance;
	
	public Account() {
		super();
	}
	public Account(int id, int owner, String name, int balance) {
		super();
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", owner=" + owner + ", name=" + name + ", balance=" + balance + "]";
	}
}
