package implementacion;

import tda.TDAConjunto;

public class ConjuntoEstaticoString implements TDAConjunto 
{
	String [] nombre;
	int cant;
	public void inicializar()
	{
		nombre = new String[100];
		cant = 0;
	}
	
	public void agregar(String valor)
	{
		if (!this.pertenece(valor))
		{
			nombre[cant] = valor;
			cant++;
		}
		
	}
	
	public boolean conjuntoVacio()
	{
		return cant == 0;
	}
	
	public String elegir()
	{
		return nombre[cant - 1];
	}

	public boolean pertenece(String valor)
	{
		int i = 0;
		while (i < cant && nombre[i] != valor)
		{
			i++;
		}
		return ( i < cant);
	}


	public void sacar(String valor)
	{
		int i = 0;
		while (i < cant && nombre[i] != valor)
		{
			i++;
		}
		if (i < cant)
		{
			nombre[i] = nombre[cant-1];
			cant--;
		}
	}
	

}
