package Decorator;

import java.util.Map;

public interface InterfaceEscola {
	public void addAluno(Aluno aluno);
	public void removeAluno(Integer matricula);
	public Map<Integer, Aluno> getAllAlunos();
}
