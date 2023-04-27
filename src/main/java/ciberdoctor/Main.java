package ciberdoctor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	private static Scanner s = new Scanner(System.in);
	private static EntityManager em;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("CL1_HuanacoQuispe");
         em = emf.createEntityManager();
         
         //Creación de los 3 primeros registros
		 //Comentarlo despues de ejecutarlo por primera vez
		  /*
         em.getTransaction().begin();

         Doctor doc1 = new Doctor("Juan", "Otorrino");
         Doctor doc2 = new Doctor("Samuel", "Dentista");
         Doctor doc3 = new Doctor("Alberto", "Dermatologo");

         em.persist(doc1);
         em.persist(doc2);
         em.persist(doc3);
         em.getTransaction().commit();
*/
         
         EjecutarPrograma();
		
	}
	
	public static void EjecutarPrograma() throws InterruptedException {
		
		while(true) {
			System.out.println("Bienvenido al hospital Buena Salud");
			System.out.println("----------------------------------------");
			System.out.println("- Ingresar Cita [1]");
			System.out.println("- Mostrar Citas del Doctor [2]");
			System.out.println("- Salir [3]");
			System.out.println("Elija una opción: ");
			

			switch(Integer.parseInt(s.nextLine())) {
			
				case 1:
					IngresarCita();
					break;
				case 2:
					MostrarCitaDeDoctor();
					break;
				case 3:
					System.out.println("Gracias por usar el sistema");
					Thread.sleep(2000);
					System.exit(0);
					
				default:
					System.out.println("Error: Ingrese un valor válido");
					Thread.sleep(1000);
					break;
			}
			
		}
	}
	
	public static void MostrarCitaDeDoctor() throws InterruptedException {
		
		String nombreDoctor;
		
		System.out.println("Ingrese nombre del Doctor:");
		nombreDoctor = s.nextLine();
		
		int doctorId = obtenerDoctorId(nombreDoctor);
	
		@SuppressWarnings("unchecked")
		List<Cita> citasDelDoctor = (List<Cita>) em.createQuery("from Cita where id_doctor="+doctorId).getResultList();

		System.out.println("Citas:");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		for (Cita cita : citasDelDoctor) {
			
			System.out.println("Nro de Cita: "+cita.num_cita+" | Fecha: "+ formato.format(cita.fecha_cita)+" | Paciente: "+cita.nom_paciente_cita);
			
		}
		
		System.out.println("Regresando a menú principal en");
		for(int i=5;i>0;i--) {
			System.out.println(i);
			Thread.sleep(1000);
		}
		
	}
	
	public static void IngresarCita() throws InterruptedException {
		
		String nombrePaciente, nombreDoctor;
		int numCita;
		Date fechaCita;
		
		System.out.println("Ingrese el nro de cita:");
		numCita = Integer.parseInt(s.nextLine());
		
		
		while(true) {
			System.out.println("Ingrese fecha de la cita: [dd/MM/yyyy]");
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
			try {
				fechaCita = formato.parse(s.nextLine());
				break;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("No has colocado una fecha válida. Intenta de nuevo");
			}
		}
		

		System.out.println("Ingrese nombre del paciente");
		nombrePaciente = s.nextLine();
		
		
		while(true) {
			System.out.println("Ingrese nombre del doctor");
			nombreDoctor = s.nextLine();
			try {
				System.out.println(obtenerDoctorId(nombreDoctor));
				break;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("No existe tal Doctor ó hay múltiples doctores con ese nombre [La creacion de los 3 doctores solo debe hacerse una vez]");
			}
		}
		crearCita(numCita, fechaCita, nombrePaciente, nombreDoctor);
		
		System.out.println("Cita registrada, regresando a menú principal en");
		for(int i=3;i>0;i--) {
			System.out.println(i);
			Thread.sleep(1000);
		}
		
	}
	
	
	private static void crearCita(int num_cita, Date fecha_cita, String nom_paciente_cita, String nom_doctor ) throws InterruptedException {

        em.getTransaction().begin();

        
        int doctorId = obtenerDoctorId(nom_doctor);
        Cita cita = new Cita(num_cita, fecha_cita, nom_paciente_cita, doctorId);

        em.persist(cita);
        em.getTransaction().commit();

	}
	
	
	private static int obtenerDoctorId(String nombre) {
		return (Integer) em.createQuery("select id from Doctor where nom_doctor='"+nombre+"'").getResultList().get(0);
	}

}
