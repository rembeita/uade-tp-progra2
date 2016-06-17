package consultorio;

import javax.swing.JOptionPane;

import implementacion.AgendaDinamica;
import tda.IAgenda;
import tda.TDACola;
import tda.TDAConjunto;

public class TestAgenda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IAgenda agenda = new AgendaDinamica();
		agenda.inicializar();
		agenda.agregar("Juan"	,	"20160620"	,	"Carlos"	,"05:45");
		agenda.agregar("Juan"	,	"20160620"	,	"Jose"		,"12:45");
		agenda.agregar("Juan"	,	"20160620"	,	"Juan"		,"15:45");
		agenda.agregar("Juan"	,	"20160620"	,	"Jeronimo"	,"11:45");
		agenda.agregar("Juan"	,	"20160620"	,	"Jacinto"	,"10:45");
		agenda.agregar("Juan"	,	"20160620"	,	"Joaquin"	,"13:45");
		agenda.agregar("Juan"	,	"20160620"	,	"Javier"	,"08:30");
		agenda.agregar("Juan"	,	"20160620"	,	"Julieta"	,"16:15");
		agenda.agregar("Juan"	,	"20160620"	,	"Juliana"	,"09:00");
		agenda.agregar("Juan"	,	"20160623"	,	"Antonia"	,"05:45");
		agenda.agregar("Juan"	,	"20160623"	,	"Carlos"	,"10:45");
		agenda.agregar("Juan"	,	"20160623"	,	"Ana"		,"13:45");
		agenda.agregar("Juan"	,	"20160623"	,	"Alma"		,"08:30");
		agenda.agregar("Juan"	,	"20160623"	,	"Aldana"	,"16:15");
		agenda.agregar("Juan"	,	"20160624"	,	"Carlos"	,"09:00");
		agenda.agregar("Juan"	,	"20160624"	,	"Maria"		,"10:00");
		agenda.agregar("Juan"	,	"20160624"	,	"Carlos"	,"11:00");
		agenda.agregar("Juan"	,	"20160624"	,	"Maria"		,"12:00");
		agenda.agregar("Juan"	,	"20160624"	,	"Carlos"	,"13:00");
		agenda.agregar("Juan"	,	"20160624"	,	"Maria"		,"14:00");
		agenda.agregar("Juan"	,	"20160624"	,	"Carlos"	,"15:00");
/** Juan */
		agenda.agregar(	"Marcela"	,	"20160620"	,	"Jose"		,	"12:45"	);
		agenda.agregar(	"Marcela"	,	"20160620"	,	"Marcela"	,	"15:45"	);
		agenda.agregar(	"Marcela"	,	"20160620"	,	"Jeronimo"	,	"11:45"	);
		agenda.agregar(	"Marcela"	,	"20160620"	,	"Jacinto"	,	"10:45"	);
		agenda.agregar(	"Marcela"	,	"20160622"	,	"Joaquin"	,	"13:45"	);
		agenda.agregar(	"Marcela"	,	"20160622"	,	"Javier"	,	"08:30"	);
		agenda.agregar(	"Marcela"	,	"20160620"	,	"Julieta"	,	"16:15"	);
		agenda.agregar(	"Marcela"	,	"20160620"	,	"Juliana"	,	"09:00"	);
		agenda.agregar(	"Marcela"	,	"20160623"	,	"Antonia"	,	"05:45"	);
		agenda.agregar(	"Marcela"	,	"20160623"	,	"Carlos"	,	"10:45"	);
		agenda.agregar(	"Marcela"	,	"20160623"	,	"Ana"		,	"13:45"	);
		agenda.agregar(	"Marcela"	,	"20160623"	,	"Alma"		,	"08:30"	);
		agenda.agregar(	"Marcela"	,	"20160623"	,	"Aldana"	,	"16:15"	);
		agenda.agregar(	"Marcela"	,	"20160624"	,	"Carlos"	,	"09:00"	);
		agenda.agregar(	"Marcela"	,	"20160624"	,	"Maria"		,	"10:00"	);
		agenda.agregar(	"Marcela"	,	"20160624"	,	"Carlos"	,	"11:00"	);
		agenda.agregar(	"Marcela"	,	"20160625"	,	"Maria"		,	"12:00"	);
		agenda.agregar(	"Marcela"	,	"20160625"	,	"Carlos"	,	"13:00"	);
		agenda.agregar(	"Marcela"	,	"20160625"	,	"Maria"		,	"11:00"	);
		agenda.agregar(	"Marcela"	,	"20160625"	,	"Carlos"	,	"10:00"	);
/** Marcela */
		agenda.agregar(	"Evaristo"	,	"20160630"	,	"Eustaquio"	,	"12:00"	);
/** Evaristo */
		
		recorrerAgenda(agenda);
		
		System.out.println("\n\n-----------------------------------------------------------\n\n");
		agenda.eliminarTurno("Evaristo", "20160630", "Eustaquio");
		recorrerAgenda(agenda);
		System.out.println("\n\n-----------------------------------------------------------\n\n");
		agenda.eliminarTurno("Marcela", "20160625", "Carlos");
		recorrerAgenda(agenda);
		System.out.println("\n\n-----------------------------------------------------------\n\n");
		agenda.eliminar("Juan");
		recorrerAgenda(agenda);
	}

	private static void recorrerAgenda(IAgenda agenda) {
		// TODO Auto-generated method stub
		String medico;
		/* Recorro la estructura por médicos */
		TDAConjunto medicos = agenda.obtenerMedicos();
		while(!medicos.conjuntoVacio())
		{	
			medico = medicos.elegir();
			System.out.println("medico : " + medico);
			System.out.println(" fecha \t paciente \t turno");
			String[][] turnos = agenda.obtenerTurnosMedico(medico);
			muestroDatos(turnos);
			medicos.sacar(medico);
		}
				
		/* Recorro la estructura por fechas */	
		TDACola fechas = agenda.obtenerFechas();
		System.out.println(" fecha \t medico\t paciente \t turno");
		while(!fechas.colaVacia())
		{
			String[][] turnos = agenda.obtenerTurnosFecha(fechas.primero());
			muestroDatos(turnos);
			fechas.desacolar();
		}
	}

	private static void muestroDatos(String[][] turnos) {
		// TODO Auto-generated method stub
		int rows = turnos.length;
		int cols = turnos[0].length;
		//JOptionPane.showMessageDialog(null, "cols " + cols + " rows " + rows);
		for(int j = 0; j<rows; j++){
			for(int i = 0; i<cols; i++)
			{
				System.out.print(turnos[j][i] + "\t");
			}
			System.out.println("");
		}
	}

}
