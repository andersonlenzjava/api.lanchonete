package lanchonete.desafio.api.infra.exeption;

public class ItemVencidoException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ItemVencidoException(String msg) {
		super(msg);
	}
}
