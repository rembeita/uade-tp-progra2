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
		
		while (aux != null && aux.clave != medico)
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
		
		if(validarhora(turno) != true)
		{
			return;
		}
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
		NodoClave nodoclave = buscarClave(medico);
		if (nodoclave == null)
		{
			return;
		}
		NodoValor nodovalor = nodoclave.valores;
		NodoValor nodovaloraux;
		
		if (nodovalor.fecha == fecha)
		{
			nodoclave.valores = nodovalor.sigFecha;
			return;
		}
		else
		{
			nodovaloraux = nodovalor.sigFecha;
		}
		
		while (nodovaloraux != null && !(nodovaloraux.fecha == fecha))
		{
			nodovaloraux = nodovaloraux.sigFecha;
			nodovalor = nodovalor.sigFecha;
		}
		nodovalor.sigFecha = nodovaloraux.sigFecha;
			
		
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
		
	public TDACola obtenerFechas()
	{
		NodoClave auxclave = primero;
		NodoValor auxvalor;
		TDACola respuesta_cola = new ColaEstaticaString();
		respuesta_cola.inicializar();
		List<String> lista = new ArrayList<String>();
		while(auxclave != null)
		{
			auxvalor = auxclave.valores;
			while (auxvalor != null)
			{
				lista.add(auxvalor.fecha);
				auxvalor=auxvalor.sigFecha;
			}
			auxclave = auxclave.siguiente;
		}
		Collections.sort(lista);
		
		for (int i=0; i < lista.size(); i++)
		{
			respuesta_cola.acolar(lista.get(i));
		}
				
		return respuesta_cola;
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
		
	public String[][] obtenerTurnosFecha(String fecha)
	{
		NodoClave aux = primero;
		NodoValor auxvalor; 

		String [][] resultado = new String[100][4];
		String [][] resultado_final = null;
		
		while (aux != null)
		{
			auxvalor = aux.valores;
			while ( auxvalor != null && auxvalor.fecha != fecha )
			{
				auxvalor = auxvalor.sigFecha;
			}
			if (auxvalor != null)
			{
				int contfilas = 0;
				this.cargaArbolFecha(auxvalor.turnos, resultado, contfilas, fecha, aux.clave);
			}
			aux = aux.siguiente;
			
		}
		
		resultado_final = this.ordenarArrayFecha(resultado);
		return resultado_final;
		
	}
	
	public String[][] obtenerTurnosMedico(String medico)
	{
		NodoClave auxmedico = this.buscarClave(medico);
		NodoValor auxvalor = auxmedico.valores;
		String [][] resultado = new String[100][4];
		String [][] resultado_final = null;
		int contfilas = 0;
		while (auxvalor != null)
		{
			this.cargaArbolMedico(auxvalor.turnos, resultado, contfilas, auxvalor.fecha);
			auxvalor = auxvalor.sigFecha;
		}
	    resultado_final = this.ordenarArrayMedico(resultado);
		//System.out.println("filas " + resultado_final.length);
		//System.out.println("cls " + resultado_final[0].length);
		return resultado_final;
	}

	public String[][] obtenerTurnosMedicoEnFecha(String medico, String fecha)
	{
		NodoClave auxmedico = this.buscarClave(medico);
		NodoValor auxvalor = auxmedico.valores;
		String [][] resultado = new String[100][2];
		String [][] resultado_final = null;
		//System.out.println("Este es fecha parametro: " + fecha);
		//System.out.println("Este es fecha valor: " + auxvalor.fecha);
		
		
		while (  auxvalor.fecha != fecha && auxvalor != null)
		{
			auxvalor = auxvalor.sigFecha;
		}
		
		//System.out.println("encontreee");
		
		if (auxvalor != null)
		{
			int contfilas = 0;
			this.cargaArbol(auxvalor.turnos, resultado, contfilas);

			//System.out.println("filas: " + contfilas);
			resultado_final = this.ordenarArray(resultado);
		}
		return resultado_final;
	}
	
	private void cargaArbol(ABBTDATurnos arbol, String[][] resultado, int contfilas)
	{
		if(!arbol.arbolVacio())
		{
			while (resultado[contfilas][0] != null)
			{
				contfilas++;
			}
			
			resultado[contfilas][0] = arbol.paciente();
			resultado[contfilas][1] = arbol.turno();
			this.cargaArbol(arbol.hijoIzquierdo(), resultado, contfilas);
			this.cargaArbol(arbol.hijoDerecho(), resultado, contfilas);
		}
	}
	
	private void cargaArbolMedico(ABBTDATurnos arbol, String[][] resultado, int contfilas, String fecha)

	{

		if(!arbol.arbolVacio())
		{

			while (resultado[contfilas][0] != null)
			{
				contfilas++;
			}
			//System.out.println("valores: " + arbol.paciente() + " " + arbol.turno() + " " + fecha);
			//System.out.println("valores: " + arbol.paciente() + " " + arbol.turno() + " " + contfilas);
			resultado[contfilas][0] = arbol.paciente();
			resultado[contfilas][1] = arbol.turno();
			resultado[contfilas][2] = fecha;
			this.cargaArbolMedico(arbol.hijoIzquierdo(), resultado, contfilas, fecha);
			this.cargaArbolMedico(arbol.hijoDerecho(), resultado, contfilas, fecha);
		}
	}

	private void cargaArbolFecha(ABBTDATurnos arbol, String[][] resultado, int contfilas, String fecha, String medico)
	{
		if(!arbol.arbolVacio())
		{
			while (resultado[contfilas][0] != null)
			{
				contfilas++;
			}
			resultado[contfilas][0] = arbol.paciente();
			resultado[contfilas][1] = arbol.turno();
			resultado[contfilas][2] = fecha;
			resultado[contfilas][3] = medico;

			this.cargaArbolFecha(arbol.hijoIzquierdo(), resultado, contfilas, fecha, medico);
			this.cargaArbolFecha(arbol.hijoDerecho(), resultado, contfilas, fecha, medico);
		}
	}
	
	private String[][] ordenarArray(String[][] procesar)
	{
		int j=0, longitud, i;
		longitud=filas_mularray(procesar);
		String[][] auxresultado = new String[0][2];
		String[][] resultadofinal = new String[longitud][2];

		for (i=0; i < longitud; i++)
		{
			for (j=0; i < longitud-1; j++)
			{
				if (procesar[j][1].compareTo(procesar[j+1][1]) > 0 )
				{
					//System.out.println("cambio");
					//System.out.println("DALEE " + procesar[i][0]);
										
					auxresultado[0][0] = procesar[j+1][0];
					auxresultado[0][1] = procesar[j+1][1];
					
					procesar[j+1][0] = procesar[j][0];
					procesar[j+1][1] = procesar[j][1];
					
					procesar[j][0] = auxresultado[0][0];
					procesar[j][1] = auxresultado[0][1];
					
				}
			}
		}
		
		for (i=0; i < resultadofinal.length; i++)
		{
			for (j=0; j < resultadofinal[0].length;j++)
			{
				resultadofinal[i][j] = procesar[i][j];
			}
		}
			
		
		return resultadofinal;
	}
	
	private String[][] ordenarArrayMedico(String[][] procesar)
	{
		int longitud, i, j;
		longitud=filas_mularray(procesar);
	//	System.out.println("LONGITUD: " + longitud);
		String[][] auxresultado = new String[longitud][3];
		String[][] resultadofinal = new String[longitud][3];
		
		for (i=0; i < longitud; i++)
		{
			for (j=0; j < longitud-1; j++)
			{
				if (procesar[j][2].compareTo(procesar[j+1][2]) > 0 )
				{
					//System.out.println("DALEE " + procesar[i][0]);
					auxresultado[0][0] = procesar[j+1][0];
					auxresultado[0][1] = procesar[j+1][1];
					auxresultado[0][2] = procesar[j+1][2];
					
					procesar[j+1][0] = procesar[j][0];
					procesar[j+1][1] = procesar[j][1];
					procesar[j+1][2] = procesar[j][2];
					
					procesar[j][0] = auxresultado[0][0];
					procesar[j][1] = auxresultado[0][1];
					procesar[j][2] = auxresultado[0][2];
				}
							
			}
		}
		
		for (i=0; i < longitud; i++)
		{
			for (j=0; j < longitud-1; j++)
			{
				if (procesar[j][1].compareTo(procesar[j+1][1]) > 0 && procesar[j][2].equals(procesar[j+1][2]))
				{
					//System.out.println("DALEE " + procesar[i][0]);
					auxresultado[0][0] = procesar[j+1][0];
					auxresultado[0][1] = procesar[j+1][1];
					auxresultado[0][2] = procesar[j+1][2];
					
					procesar[j+1][0] = procesar[j][0];
					procesar[j+1][1] = procesar[j][1];
					procesar[j+1][2] = procesar[j][2];
				
					procesar[j][0] = auxresultado[0][0];
					procesar[j][1] = auxresultado[0][1];
					procesar[j][2] = auxresultado[0][2];
				
				}
							
			}
		}
		
		for (i=0; i < resultadofinal.length; i++)
		{
			for (j=0; j < resultadofinal[0].length;j++)
			{
				resultadofinal[i][j] = procesar[i][j];
			}
		}
			
		
		return resultadofinal;
	}
	
	private String[][] ordenarArrayFecha(String[][] procesar)
	{
		int longitud, i, j;
		longitud=filas_mularray(procesar);
		String[][] auxresultado = new String[1][4];
		String[][] resultadofinal = new String[longitud][4];
		for (i=0; i < longitud; i++)
		{
			for (j=0; j < longitud-1; j++)
			{
				if (procesar[j][3].compareTo(procesar[j+1][3]) > 0 )
				{
					//System.out.println("DALEE " + procesar[i][0]);
					auxresultado[0][0] = procesar[j+1][0];
					auxresultado[0][1] = procesar[j+1][1];
					auxresultado[0][2] = procesar[j+1][2];
					auxresultado[0][3] = procesar[j+1][3];
					
					procesar[j+1][0] = procesar[j][0];
					procesar[j+1][1] = procesar[j][1];
					procesar[j+1][2] = procesar[j][2];
					procesar[j+1][3] = procesar[j][3];

					procesar[j][0] = auxresultado[0][0];
					procesar[j][1] = auxresultado[0][1];
					procesar[j][2] = auxresultado[0][2];
					procesar[j][3] = auxresultado[0][3];

				}
							
			}
		}
		
		for (i=0; i < longitud; i++)
		{
			for (j=0; j < longitud-1; j++)
			{
				if (procesar[j][1].compareTo(procesar[j+1][1]) > 0 && procesar[j][3].equals(procesar[j+1][3]))
				{
					//System.out.println("DALEE " + procesar[i][0]);
					auxresultado[0][0] = procesar[j+1][0];
					auxresultado[0][1] = procesar[j+1][1];
					auxresultado[0][2] = procesar[j+1][2];
					auxresultado[0][3] = procesar[j+1][3];
					
					procesar[j+1][0] = procesar[j][0];
					procesar[j+1][1] = procesar[j][1];
					procesar[j+1][2] = procesar[j][2];
					procesar[j+1][3] = procesar[j][3];

					procesar[j][0] = auxresultado[0][0];
					procesar[j][1] = auxresultado[0][1];
					procesar[j][2] = auxresultado[0][2];
					procesar[j][3] = auxresultado[0][3];

				}
							
			}
		}
		
		for (i=0; i < resultadofinal.length; i++)
		{
			for (j=0; j < resultadofinal[0].length;j++)
			{
				resultadofinal[i][j] = procesar[i][j];
			}
		}
			
		
		return resultadofinal;
		
	}

	private int filas_mularray(String[][] procesar)
	{
		int longitud;
		for (longitud=0; procesar[longitud][0] != null; longitud++)
		{}
		return longitud;
	}
	
	private boolean validarhora(String hora)
	{
		boolean resultado=true;
	    //09:05
		String[] partes = hora.split(":");
		//System.out.println("partes" + partes[1]);
		int parte1 = Integer.parseInt(partes[0]); 
		int parte2 = Integer.parseInt(partes[1]);  
		
		if (!(( parte1 < 18 && parte1 >= 8 && (parte2 == 0 || parte2 == 15 
				|| parte2 == 30 || parte2 == 45))))
		{
			resultado= false;
		}
		
		return resultado;
	}
	
		

}
