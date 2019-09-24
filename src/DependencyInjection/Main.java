package DependencyInjection;

import java.util.Map;
public class Main {

	public static void main(String[] args) {
				
		Container container = new Container();
		Escola escola = (Escola) container.mPico.getComponent(Escola.class);
		
		escola.addAluno(new Aluno(1, "Silvandro", "123"));
		escola.addAluno(new Aluno(2, "Pablo", "023"));
		escola.addAluno(new Aluno(3, "Rafael", "223"));

		escola.removeAluno(2);

		Map<Integer, Aluno> listaAlunos = escola.getAllAlunos();

		for (Map.Entry<Integer, Aluno> entry : listaAlunos.entrySet()) {
			System.out.println("Matricula: " + entry.getValue().matricula + " Nome: " + entry.getValue().nome
					+ " Telefone: " + entry.getValue().telefone);
		}

	}
}
