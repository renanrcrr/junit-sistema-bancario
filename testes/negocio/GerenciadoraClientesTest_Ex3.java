package negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GerenciadoraClientesTest_Ex3 {

	private GerenciadoraClientes gerClientes;

	@Test
	public void testPesquisaCliente() {

		/* ========== Montagem do cenario ========== */
		
		// Criando alguns clientes
		Cliente cliente01 = new Cliente(1, "Diego Alan", 31, "diegoalan@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Patrick Jose", 34, "patrickjose@gmail.com", 1, true);
		
		// Inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);

		/* ========== Execucao ========== */
		Cliente cliente = gerClientes.pesquisaCliente(1);
		
		/* ========== Verificacoes ========== */
		assertThat(cliente.getId(), is(1));
		
	}
	
	@Test
	public void testRemoveCliente() {

		/* ========== Montagem do cenario ========== */
		
		// Criando alguns clientes
		Cliente cliente01 = new Cliente(1, "Diego Alan", 31, "diegoalan@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Patrick Jose", 34, "patrickjose@gmail.com", 1, true);
		
		// Inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
		
		/* ========== Execucoo ========== */
		boolean clienteRemovido = gerClientes.removeCliente(2);
		
		/* ========== Verificacoes ========== */
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(2));
		
	}
	
}
