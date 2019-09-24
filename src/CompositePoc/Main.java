package CompositePoc;

public class Main {

	public static void main(String[] args) {
		Homem jeferson = new Homem("jeferson");
		Mulher suzane = new Mulher("suzane");
		Mulher suany = new Mulher("suany");
		Mulher pandora = new Mulher("pandora");
		Mulher meg = new Mulher("meg");
		Homem paquera = new Homem("paquera");
		Homem prince = new Homem("prince");
		
		Casal casal = new Casal(jeferson, suzane);
		casal.addDescendente(suany);
		casal.addDescendente(pandora);
		casal.addDescendente(meg);
		casal.addDescendente(paquera);
		casal.addDescendente(prince);
		
		try {
			casal.getNomeFilhos();
			/*
			pessoa2.getNome();
			System.out.println("====================");
			((Casal) pessoa1).addDescendente(pessoa2);
			((Casal) pessoa1).addDescendente(pessoa3);
			((Casal) pessoa1).addDescendente(pessoa4);
			((Casal) pessoa1).addDescendente(pessoa5);
			((Casal) pessoa1).addDescendente(pessoa6);
			pessoa1.getNome();
			((Casal) pessoa4).addDescendente(pessoa5);
			pessoa4.getNome();
			*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
