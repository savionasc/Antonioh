package Antonioh;

/**
 *
 * @author savio
 */
public class Carta {
    String nome;
    protected enum tipoC{MONSTRO, MAGICA, ARMADILHA};
    protected tipoC tipo;
    String efeito;    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public tipoC getTipo() {
        return tipo;
    }

    public void setTipo(tipoC tipo) {
        this.tipo = tipo;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }
}
