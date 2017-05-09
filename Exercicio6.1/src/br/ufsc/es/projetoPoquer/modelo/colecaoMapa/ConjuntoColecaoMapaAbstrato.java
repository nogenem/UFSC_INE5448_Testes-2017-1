package br.ufsc.es.projetoPoquer.modelo.colecaoMapa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class ConjuntoColecaoMapaAbstrato<K, V, F> implements TipoColecaoMapa<K, V, F, Set<F>>, Iterable<K> {
	
	private Map<K, V> valores;
	private Set<K> chavesDosValores;
	
	public ConjuntoColecaoMapaAbstrato() {
		this.valores = new HashMap<K, V>();
		this.chavesDosValores = new HashSet<K>();
	}
	
	@Override
	public final void adicionar(K chave, V valor) {
		valores.put(chave, valor);
		chavesDosValores.add(chave);
	}
	
	@Override
	public final void remover(K chave) {
		chavesDosValores.remove(valores.get(chave));
		valores.remove(chave);
	}

	@Override
	public final V pegar(K chave) {
		return valores.get(chave);
	}

	@Override
	public final boolean contém(K chave) {
		return valores.containsKey(chave);
	}
	
	@Override
	public final Set<F> fornecerObjetosFeijão() {
		return fornecerObjetosFeijãoConcreto(chavesDosValores);
	}
	
	@Override
	public Iterator<K> iterator() {
		return chavesDosValores.iterator();
	}
	
	@Override
	public int fornecerQuantidade() {
		return chavesDosValores.size();
	}
	
	protected abstract Set<F> fornecerObjetosFeijãoConcreto(Set<K> chavesDosValores);
}