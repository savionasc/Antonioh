package Backup;

import Antonioh.*;
import java.io.Serializable;

/**
 *
 * @author savio
 */
public class CartaMonstro extends Carta implements Serializable{
    private int ataque = 0;
    private int defesa = 0;
    private boolean atk = true;
    
    public CartaMonstro() {
//        this.tipo = tipoC.MONSTRO;
    }
    
    public CartaMonstro(String nome, String efeito, int ataque, int defesa) {
        this.setNome(nome);
        this.setEfeito(efeito);
  //      this.tipo = tipoC.MONSTRO;
        this.ataque = ataque;
        this.defesa = defesa;
    }
    
    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public boolean isAtk() {
        return atk;
    }

    public void setAtk(boolean atk) {
        this.atk = atk;
    }
}
