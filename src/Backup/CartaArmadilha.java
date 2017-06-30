package Backup;

import Antonioh.*;
import java.io.Serializable;

/**
 *
 * @author savio
 */
public class CartaArmadilha extends Carta implements Serializable{
    
    public CartaArmadilha() {
//        this.tipo = tipoC.ARMADILHA;
    }
    
    public CartaArmadilha(String nome, String efeito, int especialidade) {
        this.setNome(nome);
        this.setEfeito(efeito);
        this.tipo = tipoC.ARMADILHA;
        this.especialidade = especialidade;
    }
    private int especialidade;
    public enum propriedade{NORMAL, CONTINUO, RESPOSTA};
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
}