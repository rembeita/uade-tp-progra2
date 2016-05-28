package implementacion;

import tda.ABBTDATurnos;
import tda.IAgenda;

public class AgendaDinamica implements IAgenda 
{	
	NodoClave primero;
	
	private NodoClave buscarClave(String medico)
	{	
		NodoClave aux = primero;
		
		while (aux != null && aux.clave!= medico)
		{
			aux = aux.siguiente;
		}
		return aux ;
	}
	
	public void inicializar(){
		
		primero = null;
	}

	public void agregar(String medico, String fecha, String paciente, String turno)
	{
		NodoClave nodoclave = buscarClave(medico);
		
		if (nodoclave==null)
		{ 
			nodoclave = new NodoClave() ;
			nodoclave.clave = medico;
			nodoclave.valores = null;
			
			nodoclave.siguiente = primero;
			primero = nodoclave;
		}
	
		NodoValor aux = nodoclave.valores;
		
		while ( aux != null && aux.fecha!= fecha) {
			aux = aux.sigFecha;
		}
		
		if (aux == null)
		{
			NodoValor nodovalor = new NodoValor ();
			nodovalor.fecha		= fecha;
			nodovalor.sigFecha 	= nodoclave.valores;
			//agregar el arbol a valor
			nodoclave.valores 	= nodovalor ;
			NodoABB arbol = new ABBTurnos();
			nodovalor.turnos = arbol;
		}
		
		 
		
		
		
		
		
	}
}
