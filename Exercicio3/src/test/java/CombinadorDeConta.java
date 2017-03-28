import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Conta;

public class CombinadorDeConta extends BaseMatcher<Conta> implements Matcher<Conta> {
	
	private String identificador;
	private String titular;
	private String nomeDoBanco;
	private String nomeDaAgencia;
	
	public CombinadorDeConta(String identificador, String titular, String nomeDoBanco, String nomeDaAgencia) {
		this.identificador = identificador;
		this.titular = titular;
		this.nomeDoBanco = nomeDoBanco;
		this.nomeDaAgencia = nomeDaAgencia;
	}
	
	@Override
	public boolean matches(Object objeto) {
		if(objeto != null && objeto instanceof Conta){
			Conta outra = (Conta) objeto;
			Agencia agencia = outra.obterAgencia();
			return this.identificador.equals(outra.obterIdentificador()) &&
					this.titular.equals(outra.obterTitular()) &&
					this.nomeDoBanco.equals(agencia.obterBanco().obterNome()) &&
					this.nomeDaAgencia.equals(agencia.obterNome());
		}
		return false;
	}
	
	@Override
	public void describeTo(Description descricao) {
		descricao.appendText("Conta de identificador ");
		descricao.appendValue(identificador);
		descricao.appendText(" do cliente ");
		descricao.appendValue(titular);
		descricao.appendText(" da agencia ");
		descricao.appendValue(nomeDaAgencia);
		descricao.appendText(" do banco ");
		descricao.appendValue(nomeDoBanco);
	}

	@Override
	public void describeMismatch(Object objeto, Description descricao){
		if(objeto != null & objeto instanceof Conta){
			Conta outra = (Conta) objeto;
			Agencia agencia = outra.obterAgencia();
			descricao.appendText("Conta de identificador ");
			descricao.appendValue(outra.obterIdentificador());
			descricao.appendText(" do cliente ");
			descricao.appendValue(outra.obterTitular());
			descricao.appendText(" da agencia ");
			descricao.appendValue(agencia.obterNome());
			descricao.appendText(" do banco ");
			descricao.appendValue(agencia.obterBanco().obterNome());
		}
	}
	
}
