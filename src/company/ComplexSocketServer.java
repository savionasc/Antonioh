package company;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ComplexSocketServer {
	public static void main(String args[]) throws Exception {
		ServerSocket servSocket;
		Socket fromClientSocket;
		Socket fromSocketClient;
		int cTosPortNumber = 1777;
		
                //Esperando o primeiro cliente
		servSocket = new ServerSocket(cTosPortNumber);
		System.out.println("Waiting the player 1 for a connection on " + cTosPortNumber);

		fromClientSocket = servSocket.accept();

                //Esperando o segundo cliente
                //servSocket = new ServerSocket(cTosPortNumber);
		System.out.println("Waiting the player 2 for a connection on " + cTosPortNumber);

		fromSocketClient = servSocket.accept();
                
                //Criando os inputs e os outputs
                
                //Do player 1
		ObjectOutputStream oos1 = new ObjectOutputStream(fromClientSocket.getOutputStream());
		ObjectInputStream ois1 = new ObjectInputStream(fromClientSocket.getInputStream());                
                oos1.writeObject("bye bye");
                
                ComplexCompany comp = null;
                Car car = null;
                String op;
                int size = Integer.parseInt(ois1.readLine());

                while (size > 0){
                    op = ois1.readLine();
                    if (op.equals("Company")) {
                        while ((comp = (ComplexCompany) ois1.readObject()) != null) {
                            //comp.printCompanyObject();
                            break;
                        }
                    } else {
                        while ((car = (Car) ois1.readObject()) != null) {
                            //car.printCarObject();
                            break;
                        }
                    }
                    size--;
                }

                
                //Do player 2
		ObjectOutputStream oos2 = new ObjectOutputStream(fromSocketClient.getOutputStream());
		ObjectInputStream ois2 = new ObjectInputStream(fromSocketClient.getInputStream());
                //oos2.write("1".getBytes());
                //oos2.writeObject("hahaha");
                
                oos2.write("2\n".getBytes());
                oos2.write("Company".getBytes());
                oos2.writeObject(comp);
                oos2.write("Car".getBytes());
                oos2.writeObject(car);
                
                oos1.writeObject("bye bye");
                
		oos1.close();
		oos2.close();
		fromClientSocket.close();
                fromSocketClient.close();
	}
}
