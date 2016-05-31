package implementacion;

import tda.TDACola;

public class ColaEstaticaString implements TDACola 
{
	
	String[] nombre;
	int indice;
	
	public void inicializar()
	{
		nombre = new String[100];
		indice = 0;
	}
	
	public void acolar(String valor)
	{
		nombre[indice] = valor;
		indice++;
	}
	
	public void desacolar()
	{
		for (int i =0; i < indice-1; i++)
		{
			nombre[i] = nombre[i+1];
		}
		indice--;
	}
	
	public String primero()
	{
		return nombre[0];
	}
		
	public boolean colaVacia()
	{
		return (indice == 0);
	}
}
