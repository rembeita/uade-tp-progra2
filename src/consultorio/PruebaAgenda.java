package consultorio;

import implementacion.AgendaDinamica;
import tda.IAgenda;
import tda.TDACola;
import tda.TDAConjunto;

public class PruebaAgenda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String medico;
		TDACola fechas;
		IAgenda agenda = new AgendaDinamica();
		agenda.inicializar();
		/* Agrego datos a la estructura */

		/* Obtener Medicos */
		TDAConjunto medicos = agenda.obtenerMedicos();
		System.out.println("Turnos del Consultorio\n");
		/* Para cada uno de los medicos */
		while(!medicos.conjuntoVacio())
		{
			medico = medicos.elegir();
			medicos.sacar(medico);
			System.out.println("\tMedico : " + medico + "\n");
			/* Obtener Fechas */
			fechas = agenda.obtenerFechasMedico(medico);
			while(!fechas.colaVacia())
			{
				System.out.println("\t\tFecha : " + fechas.primero() + "\n");
				/* Obtener turnos */
				String[][] turnos = agenda.obtenerTurnosMedicoEnFecha(medico, fechas.primero());
				for(int i = 0; i < turnos.length; i++)
					System.out.println("\t\t\tHorario : " + turnos[i][0] + " Paciente : " + turnos[i][0]);
				fechas.desacolar();
			}
		}
	}
}
