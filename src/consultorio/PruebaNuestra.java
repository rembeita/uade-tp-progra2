package consultorio;

import implementacion.AgendaDinamica;
import implementacion.NodoClave;
import tda.IAgenda;

public class PruebaNuestra {

	public static void main(String[] args) 
	{
		System.out.println("Prueba del programa");
		System.out.println("Creando Agenda");
		IAgenda agenda = new AgendaDinamica();
		agenda.inicializar();
		agenda.agregar("Yesica", "20160528", "Josefo", "10:00");
		agenda.agregar("Claudio", "20160528", "Roberto", "10:00");
		//agenda.agregar("Yesica", "20160528", "Josefo2", "11:00");
		//agenda.agregar("Yesica", "20160528", "Josefo3", "09:00");
		agenda.agregar("Yesica", "20160528", "Josefo4", "14:00");
		agenda.agregar("Yesica", "20160528", "Josefo4", "09:00");
		agenda.agregar("Yesica", "20160528", "Josefo4", "15:00");

		//agenda.eliminar("Yesica");
		
		NodoClave rtanodoclave;
		rtanodoclave = agenda.mostrarMedico("Yesica");
		if (rtanodoclave != null)
		{
			//System.out.println("entre!!");
			System.out.println("Nombre: " + rtanodoclave.clave);
			System.out.println("Valores: " + rtanodoclave.valores.fecha);
			System.out.println("Valores: " + rtanodoclave.valores.turnos.paciente());
			System.out.println("Nombre: " + rtanodoclave.clave);
			System.out.println("Valores: " + rtanodoclave.valores.fecha);
			System.out.println("Valores: " + rtanodoclave.valores.turnos.hijoDerecho().paciente());
			System.out.println("Valores: " + rtanodoclave.valores.turnos.hijoIzquierdo().paciente());
			System.out.println("Valores: " + rtanodoclave.valores.turnos.hijoDerecho().hijoDerecho().paciente());
		}
		else
		{
			System.out.println("No Existe");
		}
		
		agenda.eliminarTurno("Yesica", "20160528", "Josefo4");
		
		rtanodoclave = agenda.mostrarMedico("Yesica");
		if (rtanodoclave != null)
		{
			System.out.println("####################!!");
			System.out.println("Nombre: " + rtanodoclave.clave);
			System.out.println("Valores: " + rtanodoclave.valores.fecha);
			System.out.println("Valores: " + rtanodoclave.valores.turnos.paciente());
		//	System.out.println("Nombre: " + rtanodoclave.clave);
		//	System.out.println("Valores: " + rtanodoclave.valores.fecha);
			System.out.println("Valores: " + rtanodoclave.valores.turnos.hijoDerecho().paciente());
		//	System.out.println("Valores: " + rtanodoclave.valores.turnos.hijoIzquierdo().paciente());
		//	System.out.println("Valores: " + rtanodoclave.valores.turnos.hijoDerecho().hijoDerecho().paciente());
		}
		else
		{
			System.out.println("No Existe");
		}
		
	}

}
