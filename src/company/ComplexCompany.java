package company;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

public class ComplexCompany implements Serializable {
	private String name;

	private ComplexEmployee president;

	private Vector departments;

	public ComplexCompany(String name) {
		this.name = name;
		departments = new Vector();
	}

	public String getName() {
		return this.name;
	}

	public void addDepartment(ComplexDepartment dept) {
		departments.addElement(dept);
	}

	public ComplexEmployee getPresident() {
		return this.president;
	}

	public void addPresident(ComplexEmployee e) {
		this.president = e;
	}

	public Iterator getDepartmentIterator() {
		return departments.iterator();
	}

	public void printCompanyObject() {
		System.out.println("The company name is " + getName());
		System.out.println("The company president is " + getPresident().getName());
		System.out.println(" ");

		Iterator i = getDepartmentIterator();
		while (i.hasNext()) {
			ComplexDepartment d = (ComplexDepartment) i.next();
			System.out.println("   The department name is " + d.getName());
			System.out.println("   The department manager is " + d.getManager().getName());
			System.out.println(" ");
		}
	}
}
