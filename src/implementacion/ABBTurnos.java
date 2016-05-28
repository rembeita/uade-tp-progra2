package implementacion;

import tda.ABBTDATurnos;

public class ABBTurnos implements ABBTDATurnos {

		NodoABB raiz ;
		
		public void inicializar()
		{
			raiz = null;
		}

		public NodoABB Raiz() 
		{
			return raiz;
		}

		public void agregar(String paciente, String turno)
		{
			if ( raiz == null ) 
			{
				raiz = new NodoABB() ;
				raiz.paciente = paciente;
				raiz.turno = turno;
				raiz.hijoI = new ABBTurnos();
				raiz.hijoI.inicializar();
				raiz.hijoD = new ABBTurnos ();
				raiz.hijoD.inicializar();
			}
			else if(turno.compareTo(raiz.turno) < 0 )
				raiz.hijoI.agregar(paciente, turno);

			else if ( turno.compareTo(raiz.turno) > 0)
				raiz.hijoD.agregar(paciente, turno);
			
			else if ( turno.compareTo(raiz.turno) == 0)
				System.out.println("El turno YA esta tomado");
		}

		
		public void eliminar(String paciente)
		{
			if( raiz != null ) 
			{
				if(raiz.paciente == paciente && raiz.hijoI.arbolVacio() && raiz.hijoD.arbolVacio() ) 
				{
					raiz = null;
				}
				else if(raiz.paciente == paciente && !raiz.hijoI.arbolVacio()) 
				{
					raiz = this.mayor(raiz.hijoI);
					raiz.hijoI.eliminar(raiz.paciente);
				}
				else if(raiz.paciente == paciente && !raiz.hijoD.arbolVacio())
				{
					raiz = this.menor( raiz.hijoD);
					raiz.hijoD.eliminar(raiz.paciente);
				}
				else if(paciente.compareTo(raiz.paciente) < 0 )
				{
					raiz.hijoI.eliminar(paciente) ;
				}
				else 
				{
					raiz.hijoD.eliminar(paciente) ;
				}
				
			}
		}
		
		private NodoABB mayor(ABBTDATurnos a) //El mayor es el valor del hijo derecho que no tiene hijo derecho
		{
			if(a.hijoDerecho().arbolVacio())
				return raiz;
			else
				return mayor(a.hijoDerecho());
		}
		
		private NodoABB menor(ABBTDATurnos a) //El menor es el valor del hijo derecho que no tiene hijo derecho
		{
			if(a.hijoIzquierdo().arbolVacio())
				return raiz;
			else
				return mayor(a.hijoIzquierdo());
		}
		
		public boolean arbolVacio() 
		{
			return raiz == null;
		}


		public String paciente()
		{
			return raiz.paciente;
		}
		
		public String turno()
		{
			return raiz.turno;
		}
		

		public ABBTDATurnos hijoIzquierdo()
		{
			return raiz.hijoI;
		}
		
		public ABBTDATurnos hijoDerecho()
		{
			return raiz.hijoD;
		}
		
			
}
