package container.model;

public class Employee {
	private String name;
	private String job;
	private int id;
	private String joining_date;
	private float salary;
	private int deptno;
	public Employee() {
		
	}
	public Employee(String name, String job, int id, String joining_date, float salary, int deptno) {
		
		this.name = name;
		this.job = job;
		this.id = id;
		this.joining_date = joining_date;
		this.salary = salary;
		this.deptno = deptno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJoining_date() {
		return joining_date;
	}
	public void setJoining_date(String joining_date) {
		this.joining_date = joining_date;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	

}
