package projeto.Atleta.NBA;

public class Forward extends Atleta{

    private String especialidade;

    public Forward (String nome, String numero, String especialidade) {
        super (nome, numero, "Forwards");
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
        return super.toString() + "Epecialidade: " + this.getEspecialidade();
    }
}
