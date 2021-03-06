package Antonioh;

import java.io.Serializable;

/**
 *
 * @author savio
 */
public class Carta implements Serializable {
    
    public String id;
    private String nome;
    protected enum tipoC{MONSTRO, MAGICA, ARMADILHA};
    protected tipoC tipo;
    private String efeito;
    private String imagem;
    
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
