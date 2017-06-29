package Antonioh;

import java.io.Serializable;

/**
 *
 * @author savio
 */
public class CartaMagica extends Carta implements Serializable{

    private int especialidade;    
    private int number = 0;
    public CartaMagica() {
        this.tipo = tipoC.MAGICA;
    }
    
    public CartaMagica(String nome, String efeito, int especialidade, int number) {
        this.setNome(nome);
        this.setEfeito(efeito);

        this.tipo = tipoC.MAGICA;
        this.especialidade = especialidade;
        this.number = number;
    }
    
    enum propriedade{NORMAL, RITUAL, CONTINUO, CAMPO, EQUIPAMENTO, RAPIDA};
    private propriedade propriedade;
    public propriedade getPropriedade() {
        return propriedade;
    }
    public void setPropriedade(propriedade propriedade) {
        this.propriedade = propriedade;
    } 

    public int getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(int especialidade) {
        this.especialidade = especialidade;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}