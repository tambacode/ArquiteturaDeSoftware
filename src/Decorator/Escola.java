package Decorator;
import java.util.HashMap;
import java.util.Map;

public class Escola implements InterfaceEscola {
	private Map<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();

	public void addAluno(Aluno aluno) {
		alunos.put(aluno.matricula, aluno);
	}

	public void removeAluno(Integer matricula) {
		alunos.remove(matricula);
	}

	public Map<Integer, Aluno> getAllAlunos() {
		return alunos;
	}

}
