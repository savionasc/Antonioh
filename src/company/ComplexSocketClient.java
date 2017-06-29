package company;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ComplexSocketClient {
    public static void main(String args[]) throws Exception {
        Socket socket1;
        int portNumber = 1777;
        String str;

        socket1 = new Socket(InetAddress.getLocalHost(), portNumber);

        ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());

        ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());

        ComplexCompany comp = new ComplexCompany("A");
        ComplexEmployee emp0 = new ComplexEmployee("B", 1000);
        comp.addPresident(emp0);

        ComplexDepartment sales = new ComplexDepartment("C");
        ComplexEmployee emp1 = new ComplexEmployee("D", 1200);
        sales.addManager(emp1);
        comp.addDepartment(sales);

        ComplexDepartment accounting = new ComplexDepartment("E");
        ComplexEmployee emp2 = new ComplexEmployee("F", 1230);
        comp.addDepartment(accounting);
        accounting.addManager(emp2);

        ComplexDepartment maintenance = new ComplexDepartment("Maintenance");
        ComplexEmployee emp3 = new ComplexEmployee("Greg Hladlick", 1020);
        maintenance.addManager(emp3);
        comp.addDepartment(maintenance);

        Car car = new Car();
        car.setCor("Green");
        car.setMarca("Ford");

        oos.write("2\n".getBytes());
        oos.write("Company".getBytes());
        oos.writeObject(comp);
        oos.write("Car".getBytes());
        oos.writeObject(car);

        try {
            while ((str = (String) ois.readObject()) != null) {
                System.out.println(str);
                oos.writeObject("bye");
                
                if (str.equals("bye"))
                    break;
            }

            ois.close();
            oos.close();
            socket1.close();
        } catch (Exception e) {
            System.out.println("Conexao terminada pelo servidor.");
        }finally {

        }

    }
}
