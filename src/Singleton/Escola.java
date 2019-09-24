package Singleton;
import java.util.HashMap;
import java.util.Map;

public class Escola {
	/* A classe escola deve ser um Singleton */

	private static Escola escolaSingleton;

	private Escola() {
	}

	public static synchronized Escola getInstance() {
		if (escolaSingleton == null)
			escolaSingleton = new Escola();

		return escolaSingleton;
	}

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
