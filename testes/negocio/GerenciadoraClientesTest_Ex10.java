package negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operacoes
 * sobre clientes, realizadas pela classe {@link GerenciadoraClientes}.
 * 
 * @author Renan Rodrigues
 * @date 06/08/2024
 */
public class GerenciadoraClientesTest_Ex10 {

	private GerenciadoraClientes gerClientes;
	private int idCLiente01 = 1;
	private int idCLiente02 = 2;

	@Before
	public void setUp() {

		/* ========== Montagem do cenario ========== */

		// Criando alguns clientes
		Cliente cliente01 = new Cliente(idCLiente01, "Diego Alan", 31, "diegoalan@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCLiente02, "Patrick Jose", 34, "patrickjose@gmail.com", 1, true);

		// Inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		gerClientes.limpa();
	}

	/**
	 * Teste basico da pesquisa de um cliente a partir do seu ID.
	 * 
	 * @author Renan Rodrigues
	 * @date 06/08/2024
	 */
	@Test
	public void testPesquisaCliente() {

		/* ========== Execucao ========== */
		Cliente cliente = gerClientes.pesquisaCliente(idCLiente01);

		/* ========== Verificacoes ========== */
		assertThat(cliente.getId(), is(idCLiente01));

	}

	/**
	 * Teste basico da pesquisa por um cliente que nao existe.
	 * 
	 * @author Renan Rodrigues
	 * @date 06/08/2024
	 */
	@Test
	public void testPesquisaClienteInexistente() {

		/* ========== Execucao ========== */
		Cliente cliente = gerClientes.pesquisaCliente(1001);

		/* ========== Verificacoes ========== */
		assertNull(cliente);

	}

	/**
	 * Teste basico da remocao de um cliente a partir do seu ID.
	 * 
	 * @author Renan Rodrigues
	 * @date 06/08/2024
	 */
	@Test
	public void testRemoveCliente() {

		/* ========== Execucao ========== */
		boolean clienteRemovido = gerClientes.removeCliente(idCLiente02);

		/* ========== Verificacoes ========== */
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCLiente02));

	}

	/**
	 * Teste da tentativa de remocao de um cliente inexistente.
	 * 
	 * @author Renan Rodrigues
	 * @date 06/08/2024
	 */
	@Test
	public void testRemoveClienteInexistente() {

		/* ========== Execucao ========== */
		boolean clienteRemovido = gerClientes.removeCliente(1001);

		/* ========== Verificacoes ========== */
		assertThat(clienteRemovido, is(false));
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));

	}

	/**
	 * Validacao da idade de um cliente quando a mesma esta no intervalo permitido.
	 * 
	 * @throws IdadeNaoPermitidaException
	 * @author Renan Rodrigues
	 * @date 06/08/2024
	 */
	@Test
	public void testClienteIdadeAceitavel() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenario ========== */
		Cliente cliente = new Cliente(1, "Gustavo", 25, "guga@gmail.com", 1, true);

		/* ========== Execucao ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* ========== Verificacoes ========== */
		assertTrue(idadeValida);
	}

	/**
	 * Validacao da idade de um cliente quando a mesma esta no intervalo permitido.
	 * 
	 * @throws IdadeNaoPermitidaException
	 * @author Renan Rodrigues
	 * @date 06/08/2024
	 */
	@Test
	public void testClienteIdadeAceitavel_02() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenario ========== */
		Cliente cliente = new Cliente(1, "Gustavo", 18, "guga@gmail.com", 1, true);

		/* ========== Execucao ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* ========== Verificacoes ========== */
		assertTrue(idadeValida);
	}

	/**
	 * Validacao da idade de um cliente quando a mesma esta no intervalo permitido.
	 * 
	 * @throws IdadeNaoPermitidaException
	 * @author Renan Rodrigues
	 * @date 06/08/2024
	 */
	@Test
	public void testClienteIdadeAceitavel_03() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenario ========== */
		Cliente cliente = new Cliente(1, "Gustavo", 65, "guga@gmail.com", 1, true);

		/* ========== Execucao ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* ========== Verificacoes ========== */
		assertTrue(idadeValida);
	}

	/**
	 * Validacao da idade de um cliente quando a mesma esta abaixo intervalo
	 * permitido.
	 * 
	 * @throws IdadeNaoPermitidaException
	 * @author Renan Rodrigues
	 * @date 06/08/2024
	 */
	@Test
	public void testClienteIdadeAceitavel_04() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenario ========== */
		Cliente cliente = new Cliente(1, "Gustavo", 17, "guga@gmail.com", 1, true);

		/* ========== Execucao ========== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* ========== Verificacoes ========== */
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	}

	/**
	 * Validacao da idade de um cliente quando a mesma esta acima intervalo
	 * permitido.
	 * 
	 * @throws IdadeNaoPermitidaException
	 * @author Renan Rodrigues
	 * @date 06/08/2024
	 */
	@Test
	public void testClienteIdadeAceitavel_05() throws IdadeNaoPermitidaException {

		/* ========== Montagem do Cenario ========== */
		Cliente cliente = new Cliente(1, "Gustavo", 66, "guga@gmail.com", 1, true);
		/* ========== Execucao ========== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* ========== Verificacoes ========== */
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	}

}