package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAluno;

    private String nome;

    @ManyToMany(mappedBy = "alunos", fetch=FetchType.EAGER)
    private List<Turma> turmas = new ArrayList<>();
    
    public Aluno() {
    }

	public Aluno(Integer idAluno, String nome, List<Turma> turmas) {
		super();
		this.idAluno = idAluno;
		this.nome = nome;
		this.turmas = turmas;
	}

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}    
}
