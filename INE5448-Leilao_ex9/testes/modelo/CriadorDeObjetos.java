package modelo;

import interfaces.IMercadoLeilao;
import modelo.FabricaDeMercado;
import modelo.MercadoLeilao;

public class CriadorDeObjetos {

	public static IMercadoLeilao mercado() {
		return new MercadoLeilao();
	}

	public static FabricaDeMercado fabrica() {
		return new FabricaDeMercado();
	}

}
