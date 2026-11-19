package Objects;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Horarios {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="horaInicio")
	private Date horaInicio;
	@Column(name="horaFim")
	private Date horaFim;
	@Column(name="diaSemana")
	private String diaSemana;
	
	public Horarios(Date horaInicio, Date horaFim, String diaSemana) {
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.diaSemana = diaSemana;
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

	public void setHoraInicio(String horaString) {
		try {
		SimpleDateFormat sdf = new SimpleDateFormat();
		this.horaInicio = sdf.parse(horaString);
		} catch(Exception err) {
			System.err.println("Erro: " + err);
		}
	}

	public String getHoraFim() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		return sdf.format(horaFim);
	}

	public void setHoraFim(String horaString) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			this.horaFim = sdf.parse(horaString);
			} catch(Exception err) {
				System.err.println("Erro: " + err);
			}
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

}
