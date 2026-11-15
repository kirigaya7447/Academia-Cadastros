package Objects;

public class Personal {
	private int id;
	private String nome;
	private double valorAula;

	public Personal(String nome, double valorAula) {
		this.nome = nome;
		this.valorAula = valorAula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorAula() {
		return valorAula;
	}

	public void setValorAula(double valorAula) {
		this.valorAula = valorAula;
	}

}
