package Antonioh;

/**
 *
 * @author savio
 */
public class CartaArmadilha extends Carta{
    
    public CartaArmadilha() {
        this.tipo = tipoC.ARMADILHA;
    }
    
    public CartaArmadilha(String nome, String efeito, int especialidade) {
        this.nome = nome;
        this.tipo = tipoC.ARMADILHA;
        this.efeito = efeito;
        this.especialidade = especialidade;
    }
    private int especialidade;
    /*enum propriedade{NORMAL, CONTINUO, RESPOSTA};
    propriedade propriedade;

    public propriedade getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(propriedade propriedade) {
        this.propriedade = propriedade;
    }*/

    public int getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(int especialidade) {
        this.especialidade = especialidade;
    }
}