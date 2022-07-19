package ficha;

public class AvaliacaoOO2022 {
    private String nome;
    private int matricula;
    private float nota;
    
    public AvaliacaoOO2022(String nome, int matricula, float nota) {
        this.nome = nome;
        this.matricula = matricula;
        this.nota = nota;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    public int getMatricula() {
        return this.matricula;
    }
    
    public void setNota(float nota) {
        this.nota = nota;
    }
    public float getNota() {
        return this.nota;
    }
}
