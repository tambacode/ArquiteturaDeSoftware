package CompositePoc;

public abstract class Pessoa {
	protected String Nome;
	
	public Pessoa(String nome) {
		this.Nome = nome;
	}

	public String getNome() {
		return this.Nome;
	}

}
