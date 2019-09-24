package Decorator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EscolaInvertidaDecorator extends EscolaDecorator implements InterfaceEscola {
	
	private InterfaceEscola interfaceescola;
	
	public EscolaInvertidaDecorator(InterfaceEscola interfaceescola) {
		super(interfaceescola);
	}
	
	public Map<Integer, Aluno> getAllAlunos() {
		List<Aluno> alunos = new ArrayList<>();
		Map<Integer, Aluno> alunosInvertido = new HashMap<Integer, Aluno>();
		
		for (Map.Entry<Integer, Aluno> entry : interfaceescola.getAllAlunos().entrySet()) {
			alunos.add(entry.getValue());
		}
		
		for (int i = alunos.size()-1; i >= 0; i--) {
			alunosInvertido.put(alunos.get(i).matricula, alunos.get(i));
		}
		return alunosInvertido;
	} 

	
}
