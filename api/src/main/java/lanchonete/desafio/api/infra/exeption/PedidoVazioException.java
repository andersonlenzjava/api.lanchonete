package lanchonete.desafio.api.infra.exeption;

public class PedidoVazioException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public PedidoVazioException(String msg) {
		super(msg);
	}
}
