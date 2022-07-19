package racasClasses;

import java.io.Serializable;


public abstract class RacasClasses implements Serializable {
    protected String nome;
    protected int poder;
    
    public RacasClasses(String nome, int poder) {
        this.nome = nome;
        this.poder = poder;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public int getPoder() {
        return this.poder;
    }
}
