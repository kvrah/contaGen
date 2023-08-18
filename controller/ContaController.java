package conta.controller;

import java.util.ArrayList;

import conta.model.conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {
	
	private ArrayList<conta> listaContas = new ArrayList<conta>();
	int numero =0;

	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null)
			conta.visualizar();
		else
			System.out.println("\nA Conta numero :" + numero+ " não foi encontrada");
		
	}

	@Override
	public void listarTodas() {
	 for (var conta: listaContas) {
		 conta.visualizar();
	     }
		
	}

	@Override
	public void cadastrar(conta conta) {
	listaContas.add(conta);
	System.out.println("\n A conta numero :"+ conta.getNumero() + " foi criada com sucesso!");
	}

	@Override
	public void atualizar(conta conta) {
		var buscarConta = buscarNaCollection(conta.getNumero());
		
		if (buscarConta != null) {
			listaContas.set(listaContas.indexOf(buscarConta), conta);
			System.out.println("\nA conta numero :"+ conta.getNumero()+ " foi atualizada com sucesso");
		}else
		System.out.println("\n A conta numero: "+ conta.getNumero()+ " não foi encontrada!");
		
	}

	@Override
	public void deletar(int numero) {
	var conta = buscarNaCollection(numero);
	
	if (conta != null) {
		if (listaContas.remove(conta) == true)
			System.out.println("\n A conta numero :" + numero + " foi deletada com sucesso");
	}  else 
		System.out.println("\n A conta numero" + numero + "não foi encontrada!");
		
	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			
			if (conta.sacar(valor) == true)
				System.out.println("\n o Saque na conta numero: "+ numero + "foi efetuado com sucesso");
		}else
			System.out.println("\nA conta numero"+ numero + "não foi encontrada!");
		
	
	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			conta.depositar(valor);
			System.out.println("\nO deposito na conta numero :"+ numero+ " foi efetuado com sucesso!");
		} else
			System.out.println("\nA conta numero: "+  numero + "não foi encontrada ou a conta não é uma conta corrente!");
		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);
		
		if (contaOrigem != null && contaDestino != null) {
			if (contaOrigem.sacar(valor)==true) {
				contaDestino.depositar(valor);
				System.out.println("\n Transferência efetuada com sucesso!");
			}
		}else 
			System.out.println("\nA conta de Origem ou Destino não encontrada!");
		
	}
 public int gerarNumero() {
	return ++ numero;
	 
 }
 
 public conta buscarNaCollection (int numero) {
	 for (var conta : listaContas) {
		 if (conta.getNumero() == numero) {
			 return conta;
		 }
	 }
	return null;
 }


public int retornaTipo(int numero) {
	for (var conta: listaContas) {
		if (conta.getNumero() == numero) {
			return conta.getTipo();
		}
	}
	
	return 0;
}
}