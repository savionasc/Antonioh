package company;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ComplexSocketClientSocket {
    public static void main(String args[]) throws Exception {
        Socket socket1;
        int portNumber = 1777;
        socket1 = new Socket(InetAddress.getLocalHost(), portNumber);

        ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());
        
        ComplexCompany comp;
        Car car;
        String op;
        int size = Integer.parseInt(ois.readLine());

        while (size > 0){
            op = ois.readLine();
            if (op.equals("Company")) {
                while ((comp = (ComplexCompany) ois.readObject()) != null) {
                    comp.printCompanyObject();
                    break;
                }
            } else {
                while ((car = (Car) ois.readObject()) != null) {
                    car.printCarObject();
                    break;
                }
            }
            size--;
        }
    }
}
