package Antonioh;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import company.Car;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author savio
 */
public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket servSocket;
        Socket fromClientSocket;
        int cTosPortNumber = 1777;
        
        //Esperando o primeiro cliente
        servSocket = new ServerSocket(cTosPortNumber);
        System.out.println("Esperando o primeiro player pela porta " + cTosPortNumber);

        fromClientSocket = servSocket.accept();
        
        ObjectOutputStream oos1 = new ObjectOutputStream(fromClientSocket.getOutputStream());
        ObjectInputStream ois1 = new ObjectInputStream(fromClientSocket.getInputStream());                
        oos1.writeObject(1);
        
        System.out.println("Deck recebido: "+ois1.readObject());
        oos1.writeObject(4);
        
        CartaMonstro magoNegro = new CartaMonstro("Mago Negro", "", 2500, 2100);
        CartaMonstro dragaoBranco = new CartaMonstro("Dragão Branco", "", 3000, 2500);
        CartaMonstro obelisk = new CartaMonstro("Obelisk", "", 4000, 4000);
        CartaMagica raigeki = new CartaMagica("Raigeki", "", 1, 1);
        CartaMagica lendarySword = new CartaMagica("Lendary Sword", "", 3, 300);
        
        //oos1.writeObject(magoNegro);
        oos1.writeObject(magoNegro);
        if((ois1.readObject().toString()).equals("Deu ruim")){
            System.out.println("Enviando novamente");
            oos1.writeObject(magoNegro);
        }else{
            System.out.println("Deu bom");
        }
        oos1.writeObject(dragaoBranco);
        if((ois1.readObject().toString()).equals("Deu ruim")){
            System.out.println("Enviando novamente");
            oos1.writeObject(magoNegro);
        }else{
            System.out.println("Deu bom");
        }
        oos1.writeObject(obelisk);
        if((ois1.readObject().toString()).equals("Deu ruim")){
            System.out.println("Enviando novamente");
            oos1.writeObject(magoNegro);
        }else{
            System.out.println("Deu bom");
        }
        oos1.writeObject(raigeki);
        if((ois1.readObject().toString()).equals("Deu ruim")){
            System.out.println("Enviando novamente");
            oos1.writeObject(magoNegro);
        }else{
            System.out.println("Deu bom");
        }
        //oos1.writeObject(c);
    }
    
}
