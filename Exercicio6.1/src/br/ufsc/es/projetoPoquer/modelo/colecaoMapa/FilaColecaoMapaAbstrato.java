package br.ufsc.es.projetoPoquer.modelo.colecaoMapa;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class FilaColecaoMapaAbstrato<K, V, F> implements TipoColecaoMapa<K, V, F, Queue<F>>, Iterable<K> {
	
	private Map<K, V> valores;
	private Queue<K> chavesDosValores;
	
	public FilaColecaoMapaAbstrato() {
		valores = new HashMap<K, V>();
		chavesDosValores = new LinkedList<K>();
	}
	
	@Override
	public final void adicionar(K chave, V valor) {
		valores.put(chave, valor);
		chavesDosValores.add(chave);
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
	public final void remover(K chave) {
		chavesDosValores.remove(valores.get(chave));
		valores.remove(chave);
	}

	@Override
	public final Queue<F> fornecerObjetosFeijão() {
		return fornecerObjetosFeijãoConcreto(chavesDosValores);
	}
	
	protected abstract Queue<F> fornecerObjetosFeijãoConcreto(Queue<K> chavesDosValores);
	
	public V tirarDaFila() {
		V valor = valores.remove(chavesDosValores.peek());
		chavesDosValores.remove();
		
		return valor;
	}
	
	public V reentrarNaFila() {
		K chave = chavesDosValores.remove();
		chavesDosValores.add(chave);
		
		return valores.get(chave);
	}
	
	public K verPrimeiro() {
		return chavesDosValores.peek();
	}
	
	public K verÚltimo() {
		K chaveDoÚltimo = null;
		for (K chave : chavesDosValores) {
			chaveDoÚltimo = chave;
		}
		return chaveDoÚltimo;
	}
	
	@Override
	public Iterator<K> iterator() {
		return chavesDosValores.iterator();
	}
	
	@Override
	public int fornecerQuantidade() {
		return chavesDosValores.size();
	}
}
