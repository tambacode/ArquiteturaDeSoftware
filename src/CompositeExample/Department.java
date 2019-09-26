package CompositeExample;

/*
 * BASE COMPONENT
 */

public abstract class Department {
	protected Integer id;
	protected String name;
	
    public abstract void printDepartmentName();
    
    public Department(Integer id, String name) {
    	this.id = id;
    	this.name = name;
    }
}