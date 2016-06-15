package consultorio;

import implementacion.AgendaDinamica;
import implementacion.ConjuntoEstaticoString;
import implementacion.NodoClave;
import tda.IAgenda;
import tda.TDAConjunto;

public class PruebaNuestra {

	public static void main(String[] args) 
	{
		System.out.println("Prueba del programa");
		System.out.println("Creando Agenda");
		IAgenda agenda = new AgendaDinamica();
		agenda.inicializar();
		agenda.agregar("Yesica", "20160528", "paciente1", "10:00");
		agenda.agregar("Yesica", "20160528", "Josefo2", "11:00");
		agenda.agregar("Yesica", "20160528", "Josefo3", "09:00");
		agenda.agregar("Yesica", "20160528", "paciente2", "14:00");
		agenda.agregar("Yesica", "20160528", "paciente3", "09:00");
		agenda.agregar("Yesica", "20160528", "paciente4", "15:00");
		agenda.agregar("Yesica", "20160528", "paciente4", "08:00");
		agenda.agregar("Yesica", "20160530", "paciente2", "17:00");
		agenda.agregar("Yesica", "20160514", "paciente3", "18:00");
		agenda.agregar("Yesica", "20160511", "paciente4", "16:30");
		agenda.agregar("Yesica", "20160523", "paciente4", "14:00");
		agenda.agregar("Claudio", "20160528", "pacienteclau1", "10:00");
		agenda.agregar("Claudio", "20160528", "pacienteclau2", "11:00");
		agenda.agregar("Claudio", "20160528", "pacienteclau3", "12:00");
		agenda.agregar("Claudio", "20160528", "pacienteclau4", "13:00");

		//agenda.eliminar("Yesica");
		/*
		//agenda.eliminarTurno("Yesica", "20160528", "Josefo4");
		
		rtanodoclave = agenda.mostrarMedico("Yesica");
		
				
		TDAConjunto test = new ConjuntoEstaticoString();
		test.inicializar();
		test = agenda.obtenerMedicos();
		System.out.println("Conjunto: " + test.elegir());
		test.sacar("Yesica");
		System.out.println("Conjunto2: " + test.elegir());
		System.out.println("Conjunto3: " + test.elegir());
		
		System.out.println("####################!!");
		String [][] teststring = new String[100][2];
		teststring = agenda.obtenerTurnosMedicoEnFecha("Yesica", "20160528");
		System.out.println("#########OBTENER TURnOS MEDICO en FECHA Yesica 20160528");
		for (int i=0 ; teststring[i][0] != null; i++)
		{
			System.out.println("obtenerTurnosMedico en FECHA: " + teststring[i][0] + " " + teststring[i][1] );
		}
		
		System.out.println("####################OBTENER TURNOS MEDICO Yesica");
		String [][] teststring2;
		teststring2 = agenda.obtenerTurnosMedico("Yesica");
		for (int l=0 ; teststring2[l][0] != null; l++)
		{
			System.out.println("obtenerTurnosMedico: " + teststring2[l][0] + " " + teststring2[l][1] + "  " + teststring2[l][2]);
		}
		
		System.out.println("####################ELIMINANDOOOOOOOO");
		agenda.eliminarFecha("Yesica", "20160528");
		System.out.println("####################ELIMINANDOOOOO OBTENER TURNOS MEDICO Yesica");
		teststring2 = agenda.obtenerTurnosMedico("Yesica");
		for (int l=0 ; teststring2[l][0] != null; l++)
		{
			System.out.println("obtenerTurnosMedico: " + teststring2[l][0] + " " + teststring2[l][1] + "  " + teststring2[l][2]);
		}
		*/
		String [][] teststring2;
		System.out.println("####################CONSULTANDO POR FECHA");
		teststring2 = agenda.obtenerTurnosFecha("20160528");
		for (int l=0 ; teststring2[l][0] != null; l++)
		{
			System.out.println("obtenerTurnosFecha: " + teststring2[l][0] + " " + teststring2[l][1] + " " + teststring2[l][2] + "  " + teststring2[l][3]);
		}
	}
}
