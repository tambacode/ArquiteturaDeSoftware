package Decorator;
import java.util.HashMap;
import java.util.Map;

public class EscolaComBuscaDecorator extends EscolaDecorator implements InterfaceEscola {
	
	public EscolaComBuscaDecorator(InterfaceEscola interfaceescola) {
		super(interfaceescola);
	}
	

	
	public Map<Integer, Aluno> getAllAlunosWithLetter(String letter) {
		Map<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();
		
		for (Map.Entry<Integer, Aluno> aluno : interfaceescola.getAllAlunos().entrySet()) {
			if(aluno.getValue().nome.subSequence(0, 1).equals(letter)) {
				alunos.put(aluno.getValue().matricula, aluno.getValue());
			}

		}
		
		return alunos;
	}

	
}
