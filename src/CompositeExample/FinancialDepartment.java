package CompositeExample;

/*
 * LEAF
 */

public class FinancialDepartment extends Department {
	public FinancialDepartment(Integer id, String name) {
    	super(id, name);
    }
    
    public void printDepartmentName() {
        System.out.println(getClass().getSimpleName());
    }
}