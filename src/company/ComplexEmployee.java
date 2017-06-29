package company;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

public class ComplexEmployee implements Serializable {
	private String name;

	private int salary;

	/** Creates a new instance of ComplexEmployee */
	public ComplexEmployee(String name, int salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public int getSalary() {
		return this.salary;
	}
}
