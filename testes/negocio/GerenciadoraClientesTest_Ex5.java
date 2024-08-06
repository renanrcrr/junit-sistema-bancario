package negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operacoes
 * sobre clientes, realizadas pela classe {@link GerenciadoraClientes}.
 * 
 * @author Renan Rodrigues
 * @date 06/08/2024
 */
public class GerenciadoraClientesTest_Ex5 {

	private GerenciadoraClientes gerClientes;

	/**
	 * Teste basico da pesquisa de um cliente a partir do seu ID.
	 * 
	 * @author Renan Rodrigues
	 * @date 06/08/2024
	 */
	@Test
	public void testPesquisaCliente() {

		/* ========== Montagem do cen�rio ========== */

		// Criando alguns clientes
		int idCLiente01 = 1;
		int idCLiente02 = 2;
		Cliente cliente01 = new Cliente(idCLiente01, "Diego Alan", 31, "diegoalan@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCLiente02, "Patrick Jose", 34, "patrickjose@gmail.com", 1, true);

		// Inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);

		/* ========== Execucao ========== */
		Cliente cliente = gerClientes.pesquisaCliente(idCLiente01);

		/* ========== Verificacoes ========== */
		assertThat(cliente.getId(), is(idCLiente01));

	}

	/**
	 * Teste basico da remocao de um cliente a partir do seu ID.
	 * 
	 * @author Renan Rodrigues
	 * @date 06/08/2024
	 */
	@Test
	public void testRemoveCliente() {

		/* ========== Montagem do cenario ========== */

		// Criando alguns clientes
		int idCLiente01 = 1;
		int idCLiente02 = 2;
		Cliente cliente01 = new Cliente(idCLiente01, "Diego Alan", 31, "diegoalan@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCLiente02, "Patrick Jose", 34, "patrickjose@gmail.com", 1, true);

		// Inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);

		/* ========== Execucao ========== */
		boolean clienteRemovido = gerClientes.removeCliente(idCLiente02);

		/* ========== Verificacoes ========== */
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCLiente02));

	}

}
