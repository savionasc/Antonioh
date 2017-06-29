package company;
import java.io.Serializable;

public class ComplexDepartment implements Serializable {
	private String name;

	private ComplexEmployee manager;

	public ComplexDepartment(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public ComplexEmployee getManager() {
		return this.manager;
	}

	public void addManager(ComplexEmployee e) {
		manager = e;
	}
}
