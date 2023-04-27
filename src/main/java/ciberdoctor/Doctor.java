package ciberdoctor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_doctores")

public class Doctor {

	 public Doctor(String nom_doctor, String especialidad_doctor) {
		super();
		this.nom_doctor = nom_doctor;
		this.especialidad_doctor = especialidad_doctor;
	}



	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id_doctor")
	 private int id_doctor;
	
	 @Column(name = "nom_doctor")
	 private String nom_doctor;
	
	 @Column(name = "especialidad_doctor")
	 public String especialidad_doctor;

	
}
