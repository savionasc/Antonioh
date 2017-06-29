package company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Testt {
    public static void main(String[] args) throws Exception{
        Socket socket1;
        int portNumber = 1777;
        socket1 = new Socket(InetAddress.getLocalHost(), portNumber);

        ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());
        
        String size = ois.readLine();
        JOptionPane.showMessageDialog(null, size);
    }
}
