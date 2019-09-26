package CompositeExample;

/*
 * LEAF
 */

public class SalesDepartment extends Department {
	public SalesDepartment(Integer id, String name) {
    	super(id, name);
    }
	
	public void printDepartmentName() {
        System.out.println(getClass().getSimpleName());
    }
}