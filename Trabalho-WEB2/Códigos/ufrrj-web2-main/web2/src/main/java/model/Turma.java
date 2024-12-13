package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTurma;

    private String disciplina;
    private Integer ano;
    private Integer periodo;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "Turma_Aluno",
            joinColumns = @JoinColumn(name = "idTurma"),
            inverseJoinColumns = @JoinColumn(name = "idAluno")
    )
    private List<Aluno> alunos = new ArrayList<>();

    @OneToMany(mappedBy = "turma")
    private List<Aula> aulas = new ArrayList<>();
    
    public Turma() {
    }

	public Turma(Integer idTurma, String disciplina, Integer ano, Integer periodo, List<Aluno> alunos,
			List<Aula> aulas) {
		super();
		this.idTurma = idTurma;
		this.disciplina = disciplina;
		this.ano = ano;
		this.periodo = periodo;
		this.alunos = alunos;
		this.aulas = aulas;
	}

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}
    
}
