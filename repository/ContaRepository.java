package conta.repository;

import conta.model.conta;

public interface ContaRepository {


	// CRUD
	public void procurarPorNumero(int numero); // READ
	public void listarTodas();  // READ
	
	public void cadastrar(conta conta); // CREATE
	public void atualizar(conta conta); // UPDATE
	public void deletar(int numero); // DELETE
	
	
	public void sacar(int numero, float valor);
	public void depositar(int numero, float valor);
	public void transferir(int numeroOrigem, int numeroDestino, float valor);
	
}