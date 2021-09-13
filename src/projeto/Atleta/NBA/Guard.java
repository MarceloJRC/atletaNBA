package projeto.Atleta.NBA;

public class Guard extends Atleta{

    private String especialidade;

    public Guard (String nome, String numero, String especialidade) {
        super (nome, numero, "Guard");
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public void setEstilo(String especialidade){
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return super.toString() + "Especialidade: " + this.getEspecialidade();
    }
}
