package ficha;

import java.io.Serializable;

public class AvaliacaoOO2022 implements Serializable {
    private String nome;
    private int matricula;
    private float nota;
    
    public AvaliacaoOO2022() {
        this.matricula = -1; //indica que o cadastro ainda não foi realizado
    }
    
    public void setNome(String nome) throws DadoInvalidoException {
        if (nome.length() == 0) throw new DadoInvalidoException("\n---O NOME NAO PODE ESTAR VAZIO---");
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setMatricula(int matricula) throws DadoInvalidoException {
        if (matricula < 0) throw new DadoInvalidoException("\n---A MATRICULA NAO PODE SER NEGATIVA---");
        if (Integer.toString(matricula).length() == 0) throw new DadoInvalidoException("\n---A MATRICULA NAO PODE ESTAR VAZIA---");
        this.matricula = matricula;
    }
    public int getMatricula() {
        return this.matricula;
    }
    
    public void setNota(float nota) throws DadoInvalidoException {
        if (nota < 0) throw new DadoInvalidoException("\n---A NOTA NAO PODE SER NEGATIVA---");
        if (Float.toString(nota).length() == 0) throw new DadoInvalidoException("\n---A NOTA NAO PODE ESTAR VAZIA---");
        this.nota = nota;
    }
    public float getNota() {
        return this.nota;
    }
}
