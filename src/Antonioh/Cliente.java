package Antonioh;

import company.Car;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author savio
 */
public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Socket socket1;
        int portNumber = 1777;
        String str;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {//InetAddress.getLocalHost()
            socket1 = new Socket(InetAddress.getLoopbackAddress(), portNumber);
            ois = new ObjectInputStream(socket1.getInputStream());
            oos = new ObjectOutputStream(socket1.getOutputStream());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Recebendo o numero do player (1 ou 2)
        int vez = (int) ois.readObject();
        System.out.println("Player "+vez);
        try {
            //Numero referente ao deck
            oos.writeObject(2);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Esperando as cartas
        System.out.println("Antonioh.Cliente.main()");
        //numero de cartas
        int numCartas = 0;
        try {
            numCartas = (int) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("numero de cartas da mao "+numCartas);
        List<Carta> cartas = new ArrayList<>();

        System.out.println("Cartas da mao: ");
        //Car c = (Car) ois.readObject();
        //System.out.println("marca: "+c.getMarca());
        for (int i = 0; i < numCartas; i++) {
            Carta ca = null;
            int exep = 0;
            while (exep != 1) {
                try {
                    ca = (Carta) ois.readObject();
                    oos.writeObject("Deu bom");
                    exep = 1;
                } catch (IOException ex) {
                    System.out.println("deu ruim, escutar novamente");
                    oos.writeObject("Deu ruim");
                }            
            }
            System.out.println("Carta: "+ca.getNome());
        }
        //ois.close();
        //oos.close();
        //socket1.close();

    }
}
