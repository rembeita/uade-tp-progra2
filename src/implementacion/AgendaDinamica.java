package implementacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tda.ABBTDATurnos;
import tda.IAgenda;
import tda.TDACola;
import tda.TDAConjunto;

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
		
		while ( aux != null && aux.fecha != fecha) 
		{
			aux = aux.sigFecha;
		}
		
		if (aux == null)
		{
			NodoValor nodovalor = new NodoValor ();
			nodovalor.fecha		= fecha;
			nodovalor.sigFecha 	= nodoclave.valores;
			//agregar el arbol a valor
			nodoclave.valores 	= nodovalor ;
			ABBTDATurnos arbol = new ABBTurnos();
			nodovalor.turnos = arbol;
			arbol.agregar(paciente, turno);
		}
		else 
		{
			aux.turnos.agregar(paciente, turno);
		}
		
	}


	public void eliminar(String medico)
	{
			if(primero!= null) 
			{
				if(primero.clave == medico) 
				{
					primero = primero.siguiente;
				}
				else 
				{
					NodoClave aux = primero;
					while (aux.siguiente != null && aux.siguiente.clave != medico) 
					{
						aux = aux.siguiente;
					}
					if(aux.siguiente != null ) 
					{
						aux.siguiente= aux.siguiente.siguiente;
					}
				}
			}
	}
	
	public NodoClave mostrarMedico(String medico)
	{
		NodoClave nodoclave = buscarClave(medico);

		return nodoclave;
	}
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Elimina una fecha determinada a un medico determinado. Si no existe no hace nada.
	 * @param medico   : Cadena de caracteres con el nombre del m�dico
	 * @param fecha    : Cadena de caracteres con la fecha del turno. El formato es <B>YYYYMMDD<B>
	 * @return void
	 * 
	 * */
	public void eliminarFecha(String medico, String fecha)
	{
			if ( primero != null ) 
			{
				if ( primero.clave == medico) 
				{					
					EliminarValorEnNodo(primero, fecha);
					if( primero.valores == null ) 
					{
						primero = primero.siguiente;
					}
				}
				else 
				{
					NodoClave aux = primero;
					while (aux.siguiente != null && aux.siguiente.clave != medico)
					{
						aux = aux.siguiente;
					}
					if( aux.siguiente != null ) 
					{
						EliminarValorEnNodo(aux. siguiente , fecha);
						if (aux.siguiente.valores == null ) 
						{
							aux.siguiente = aux.siguiente.siguiente;
						}
					}
				}
			}
		}
		
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Elimina el o los turnos de un paciente determinado en una fecha determinada a un medico determinado. Si 
	 * el m�dico o la fecha no existe no hace nada
	 * @param medico   : Cadena de caracteres con el nombre del m�dico
	 * @param fecha    : Cadena de caracteres con la fecha del turno. El formato es <B>YYYYMMDD</B>
	 * @param paciente : Cadena de caracteres con el nombre del paciente
	 * @return void
	 * 
	 * */
	public void eliminarTurno(String medico, String fecha, String paciente)
	{
		NodoClave nodoclave = buscarClave(medico);
		
		NodoValor aux = nodoclave.valores;
		
		while ( aux != null && aux.fecha != fecha) 
		{
			aux = aux.sigFecha;
		}
		
		aux.turnos.eliminar(paciente);
    }
	
	private void EliminarValorEnNodo( NodoClave nodo , int valor) 
	{
		i f ( nodo . valores!= nul l ) {
			i f (nodo . valores. valor == valor) {
				nodo . valores = nodo . valores. sigValor;
			}
			e l s e {
				NodoValor aux = nodo . valores;
				whi le (aux . sigValor != nul l && aux. sigValor.valor
						!= valor){
					aux = aux. sigValor;
				}
				i f ( aux. sigValor!= nul l ) {
					aux. sigValor= aux. sigValor. sigValor;
				}
			}
		}
	}

	public TDAConjunto obtenerMedicos()
	{
		TDAConjunto resultado_conjunto = new ConjuntoEstaticoString();
		resultado_conjunto.inicializar();
		
		NodoClave aux = primero;
		
		while (aux != null )
		{
			resultado_conjunto.agregar(aux.clave);
			aux = aux.siguiente;
		}
		return resultado_conjunto;
	}
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Obtiene el conjunto de todas las fechas que tienen turnos en el consultorio ordenadas de menor a mayor.
	 * @return TDACola con las fechas en las que hay turnos 
	 * 
	 * */
	public TDACola obtenerFechas()
	{
		
	}
	
	
	public TDACola obtenerFechasMedico(String medico)
	{
		TDACola respuesta_cola = new ColaEstaticaString();
		respuesta_cola.inicializar();
		
		NodoClave nodoclave = buscarClave(medico);
		NodoValor aux = nodoclave.valores;
		List<String> lista = new ArrayList<String>();
		
		while ( aux != null) 
		{
			lista.add(aux.fecha);
			aux = aux.sigFecha;
		}
		Collections.sort(lista);
		
		for (int i=0; i < lista.size(); i++)
		{
			respuesta_cola.acolar(lista.get(i));
		}
				
		return respuesta_cola;
		
	}
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Obtiene todos los turnos que tiene el consultorio para una 
	 * fecha determinada, ordenados de menor a mayor.
	 * @param fecha : Cadena de caracteres con la fecha del turno. El formato es <B>YYYYMMDD</B>
	 * @return Arreglo bidimensional que tiene en cada fila la fecha, el medico, el paciente y el turno,
	 * ordenado por fecha, medico y turno 
	 * 			  
	 * */
	public String[][] obtenerTurnosFecha(String fecha)
	{}
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Obtiene el conjunto de todos los turnos que tienen en el consultorio para un 
	 * m�dico determinado, ordenados de menor a mayor.
	 * @param medico : Cadena de caracteres con el nombre del m�dico
	 * @return Arreglo bidimensional que tiene en cada fila la fecha, el paciente y el turno,
	 * ordenado por fecha y turno 
	 * 			  
	 * */
	public String[][] obtenerTurnosMedico(String medico)
	{}
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Obtiene el conjunto de todos los turnos que tienen en el consultorio para un 
	 * m�dico determinado en una fecha determinada, ordenados de menor a mayor.
	 * @param medico : Cadena de caracteres con el nombre del m�dico
	 * @param fecha : Cadena de caracteres con la fecha del turno. El formato es <B>YYYYMMDD</B>
	 * @return Arreglo bidimensional que tiene en cada fila la el paciente y el turno,
	 * ordenado por turno 
	 * 			  
	 * */
	public String[][] obtenerTurnosMedicoEnFecha(String medico, String fecha)
	{}
	
	
	
}
