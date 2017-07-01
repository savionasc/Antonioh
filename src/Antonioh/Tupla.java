package Antonioh;

import java.io.Serializable;

/**
 *
 * @author savio
 */
public class Tupla implements Serializable{
    private String botao;
    private Carta carta;

    public Tupla() {
    }

    public Tupla(String botao, Carta nome) {
        this.botao = botao;
        this.carta = nome;
    }
    
    public String getBotao() {
        return botao;
    }

    public void setBotao(String botao) {
        this.botao = botao;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }
}
