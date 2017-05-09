package br.ufsc.es.projetoPoquer.modelo.colecaoMapa;

import java.util.Collection;

public interface TipoColecaoMapa<K, V, F, C extends Collection<F>> {
	
	public void adicionar(K chave, V valor);
	
	public V pegar(K chave);
	
	public boolean contém(K chave);
	
	public void remover(K chave);
	
	public C fornecerObjetosFeijão();
	
	public int fornecerQuantidade();
}
