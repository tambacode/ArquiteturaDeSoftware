package Decorator;
import java.util.Map;

public abstract class EscolaDecorator implements InterfaceEscola {
	
	protected InterfaceEscola interfaceescola;
	
	public EscolaDecorator(InterfaceEscola interfaceescola) {
		this.interfaceescola = interfaceescola;
	}
	
	public void addAluno(Aluno aluno) {
		interfaceescola.addAluno(aluno);
	}

	public void removeAluno(Integer matricula) {
		interfaceescola.removeAluno(matricula);
	}

	public Map<Integer, Aluno> getAllAlunos() {
		return interfaceescola.getAllAlunos();
	} 
	
	public void PrintAlunos(Map<Integer, Aluno> listaAlunos) {
		for (Map.Entry<Integer, Aluno> entry : listaAlunos.entrySet()) {
			System.out.println("Matricula: " + entry.getValue().matricula + " Nome: " + entry.getValue().nome
					+ " Telefone: " + entry.getValue().telefone);
		}

	}
	
}
