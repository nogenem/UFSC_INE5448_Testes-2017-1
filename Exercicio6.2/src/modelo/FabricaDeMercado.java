package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import interfaces.IMercadoLeilao;

public class FabricaDeMercado implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	public FabricaDeMercado() {
		
	}

	
	public IMercadoLeilao montar() {
		MercadoLeilao mercado = null;
		try {
			FileInputStream arquivo = new FileInputStream("arquivoDoMercado");
			ObjectInputStream objLeitura = new ObjectInputStream(arquivo);
			mercado = (MercadoLeilao) objLeitura.readObject();
			objLeitura.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return mercado;
	}

	
	public void desmontar(IMercadoLeilao mercado) {
		try {
			FileOutputStream arquivo = new FileOutputStream("arquivoDoMercado");
			ObjectOutputStream objGravacao = new ObjectOutputStream(arquivo);
			objGravacao.writeObject(mercado);
			objGravacao.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
