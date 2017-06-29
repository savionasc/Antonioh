package company;
import java.io.Serializable;

public class Car implements Serializable {
	
	private String marca;
	private String cor;
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public void printCarObject() {
		System.out.println("Carro: ");
		System.out.println("   A marca do carro eh: " + getMarca());
		System.out.println("   A cor do carro eh:  " + getCor());
	}

	
}
