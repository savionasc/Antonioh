package Antonioh;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
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
        oos1.writeObject(5);
        
        CartaMonstro magoNegro = new CartaMonstro("Mago Negro", "", 2500, 2100);
        magoNegro.setImagem("C:\\Users\\savio\\Desktop\\Cards\\200x295\\5138517_t.jpg");
        CartaMonstro dragaoBranco = new CartaMonstro("Dragão Branco", "", 3000, 2500);
        dragaoBranco.setImagem("C:\\Users\\savio\\Desktop\\Cards\\200x295\\Dragão_branco_de_olhos_azuis.jpg");
        CartaMonstro obelisk = new CartaMonstro("Obelisk", "", 4000, 4000);
        obelisk.setImagem("C:\\Users\\savio\\Desktop\\Cards\\200x295\\obilesk.jpg");
        CartaMagica raigeki = new CartaMagica("Raigeki", "", 1, 1);
        raigeki.setImagem("C:\\Users\\savio\\Desktop\\Cards\\200x295\\Redimencionar\\LegendarySword-LOB-NA-SP-UE-Reprint.png");
        CartaMagica lendarySword = new CartaMagica("Lendary Sword", "", 3, 300);
        lendarySword.setImagem("C:\\Users\\savio\\Desktop\\Cards\\200x295\\Untitled design.jpg");
        List<Carta> cartas = new ArrayList<>();
        cartas.add(magoNegro);
        cartas.add(dragaoBranco);
        cartas.add(obelisk);
        cartas.add(raigeki);
        cartas.add(lendarySword);
        
        try {
            for (Carta carta : cartas) {
                oos1.writeObject(carta);
                if((ois1.readObject().toString()).equals("Deu ruim")){
                    System.out.println("Enviando novamente");
                    oos1.writeObject(magoNegro);
                }else{
                    System.out.println("Deu bom");
                }            
            }
        } catch (Exception e) {
        }
        
        cartas = null;
        HashMap cartasMaoP1 = new HashMap();
        
        //oos1.writeObject(magoNegro);
        /*oos1.writeObject(magoNegro);
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
        }*/
        
        System.out.println("Aguardando carta para invocar");
        System.out.println(((Carta) ois1.readObject()).getNome());
    }
}