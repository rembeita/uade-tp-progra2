package tda;

public interface TDACola {

	public void inicializar();
	
	/** inicializada */
	public void acolar(String valor);
	
	/** inicializada y no vacía */
	public void desacolar();
	
	/** inicializada y no vacía */
	public String primero();
	
	/** inicializada */
	public boolean colaVacia();
	
}
