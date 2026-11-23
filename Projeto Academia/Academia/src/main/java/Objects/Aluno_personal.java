package Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name="aluno_personal")
public class Aluno_personal {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;

    @OneToOne
    @JoinColumn(name = "id_aluno")
	private Aluno aluno;
    @OneToOne
    @JoinColumn(name = "id_personal")
	private Personal personal;
    
    public Aluno_personal(Aluno aluno, Personal personal) {
    	this.aluno = aluno;
    	this.personal = personal;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
    
    

}