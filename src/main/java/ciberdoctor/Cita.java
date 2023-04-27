package ciberdoctor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_citas")


public class Cita {

	public Cita() {}
	
	 public Cita(int num_cita, Date fecha_cita, String nom_paciente_cita, int id_doctor) {
		super();
		this.num_cita = num_cita;
		this.fecha_cita = fecha_cita;
		this.nom_paciente_cita = nom_paciente_cita;
		this.id_doctor = id_doctor;
	}

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id_cita")
     public int id_cita;

     @Column(name = "num_cita")
     public int num_cita;

     @Column(name = "fecha_cita")
     public Date fecha_cita;

     @Column(name = "nom_paciente_cita")
     public String nom_paciente_cita	;
     
     @Column(name = "id_doctor")
     public int id_doctor;

}
