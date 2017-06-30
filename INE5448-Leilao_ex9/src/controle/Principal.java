package controle;

import visao.LeilaoGUI;
import modelo.MercadoLeilao;

public class Principal {

	public static MercadoLeilao mercado = new MercadoLeilao();
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				iniciarComInterfaceGrafica();
			}
		});
	}
	
	private static void iniciarComInterfaceGrafica() {
		new LeilaoGUI(mercado);
	}
}