package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa;


public class FinalizadorDeRodada implements Runnable {
	
	private TorneioDeUmaMesa torneioDeUmaMesa;
	private Thread rodada;
	
	public FinalizadorDeRodada(TorneioDeUmaMesa torneioDeUmaMesa, Thread rodada) {
		this.torneioDeUmaMesa = torneioDeUmaMesa;
		this.rodada = rodada;
	}

	@Override
	public synchronized void run() {
		try {
			rodada.join();
		} catch (InterruptedException erro) {
			erro.printStackTrace();
		}
		torneioDeUmaMesa.terminarRodada();
	}
}