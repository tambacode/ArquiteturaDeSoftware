package CompositeExample;

import java.util.ArrayList;
import java.util.List;

/*
 * COMPOSITE
 */

public class HeadDepartment extends Department {
    private List<Department> childDepartments;
 
    public HeadDepartment(Integer id, String name) {
    	super(id, name);
        this.childDepartments = new ArrayList<>();
    }
 
    public void printDepartmentName() {
        childDepartments.forEach(Department::printDepartmentName);
    }
 
    public void addDepartment(Department department) {
        childDepartments.add(department);
    }
 
    public void removeDepartment(Department department) {
        childDepartments.remove(department);
    }
}