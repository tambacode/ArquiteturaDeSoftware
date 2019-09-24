package CompositePoc;

import java.util.ArrayList;

public class Casal {
	private ArrayList<Pessoa> listaDeFilhos;
	protected Homem pai;
	protected Mulher mae;

	protected Casal(Homem pai, Mulher mae) {
		this.listaDeFilhos = new ArrayList<Pessoa>();
		this.pai = pai;
		this.mae = mae;
	}
	
	public void addDescendente(Pessoa pessoa) {
		this.listaDeFilhos.add(pessoa);
	}
	
	
	public void getNomeFilhos() {
		System.out.println(pai.getNome() + " + " + mae.getNome());
		for (Pessoa pessoa : listaDeFilhos) {
			System.out.println(pessoa.getNome());
		}
	}
}
