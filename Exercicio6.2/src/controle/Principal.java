package controle;

import visao.LeilaoGUI;
import modelo.MercadoLeilao;

public class Principal {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				iniciarComInterfaceGrafica();
			}
		});
	}
	
	private static void iniciarComInterfaceGrafica() {
		MercadoLeilao mercado = new MercadoLeilao();
		new LeilaoGUI(mercado);
	}
}