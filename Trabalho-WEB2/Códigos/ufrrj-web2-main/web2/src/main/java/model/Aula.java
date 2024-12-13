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
import javax.persistence.ManyToOne;

@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAula;

    private String data;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "Aula_Aluno",
            joinColumns = @JoinColumn(name = "idAula"),
            inverseJoinColumns = @JoinColumn(name = "idAluno")
    )
    private List<Aluno> alunosPresentes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idTurma")
    private Turma turma;
    
    public Aula() {
    }

	public Aula(Integer idAula, String data, List<Aluno> alunosPresentes, Turma turma) {
		super();
		this.idAula = idAula;
		this.data = data;
		this.alunosPresentes = alunosPresentes;
		this.turma = turma;
	}

	public Integer getIdAula() {
		return idAula;
	}

	public void setIdAula(Integer idAula) {
		this.idAula = idAula;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Aluno> getAlunosPresentes() {
		return alunosPresentes;
	}

	public void setAlunosPresentes(List<Aluno> alunosPresentes) {
		this.alunosPresentes = alunosPresentes;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
    
    
}