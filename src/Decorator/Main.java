package Decorator;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		Escola escola = new Escola();

		escola.addAluno(new Aluno(1, "Silvandro", "123"));
		escola.addAluno(new Aluno(2, "SiPablo", "023"));
		escola.addAluno(new Aluno(3, "Rafael", "223"));
		
		EscolaInvertidaDecorator escolaInvertidaDecorator = new EscolaInvertidaDecorator(escola);
		
		
		EscolaComBuscaDecorator escolaComBusca = new EscolaComBuscaDecorator(escolaInvertidaDecorator);
		
		
		System.out.println("------------");
		escolaComBusca.PrintAlunos(escolaComBusca.getAllAlunosWithLetter("S"));

	}
}
