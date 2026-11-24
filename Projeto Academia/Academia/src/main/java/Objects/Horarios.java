package Objects;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "horarios")
public class Horarios {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="horaInicio")
	private String horaInicio;
	@Column(name="horaFim")
	private String horaFim;
	@Column(name="diaSemana")
	private String diaSemana;
	
	@OneToOne
	@JoinColumn(name = "id_aluno_personal")
	private Aluno_personal aluno_personal;
	
	public Horarios(String horaInicio, String horaFim, String diaSemana) {
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.diaSemana = diaSemana;
	}
	
	public Horarios() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHoraInicio() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		return sdf.format(horaInicio);
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	public Aluno_personal getAluno_Personal() {
		return aluno_personal;
	}

	public void setAluno_Personal(Aluno_personal aluno_personal) {
		this.aluno_personal = aluno_personal;
	}

}
