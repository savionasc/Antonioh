package Antonioh;

/**
 *
 * @author savio
 */
public class CartaMonstro extends Carta{
    int ataque = 0;
    int defesa = 0;
    boolean atk = true;
    
    public CartaMonstro() {
        this.tipo = tipoC.MONSTRO;
    }
    
    public CartaMonstro(String nome, String efeito, int ataque, int defesa) {
        this.nome = nome;
        this.tipo = tipoC.MONSTRO;
        this.efeito = efeito;
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
