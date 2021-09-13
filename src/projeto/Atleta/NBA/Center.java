package projeto.Atleta.NBA;

public class Center extends Atleta{

    private String especialidade;

    public Center (String nome, String numero, String especialidade) {
        super (nome, numero, "Center");
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public void setAltura(String especialidade){
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return super.toString() + "Especialidade: " + this.getEspecialidade();
    }
}
