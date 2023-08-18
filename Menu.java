package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.model.contaCorrente;
import conta.controller.ContaController;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static Scanner leia = new Scanner(System.in);

	public static void main(String[] args) {

		ContaController contas = new ContaController();
		int opcao,numero,agencia,tipo,aniversario,numeroDestino;
		String titular = null;
		float saldo,limite,valor;
	

		System.out.println("\nCriar Contas\n");
		contaCorrente cc1 = new contaCorrente(contas.gerarNumero(), 123, 1, "Richard Campos", 2000f, 100.0f);
		contaCorrente cc2 = new contaCorrente(contas.gerarNumero(), 125, 1, "Joao Gomes", 5000f, 100.0f);
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 126 , 2, "Guilherme Rocha", 4000f, 12);
		
		contas.listarTodas();

		while (true) {

			System.out.println(Cores.TEXT_PURPLE + Cores.ANSI_BLACK_BACKGROUND
					+ "**************************************************************");
			System.out.println("                                                     ");
			System.out.println("                   RC BANK                           ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);
			
			try {
				opcao = leia.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao=0;
			}

			if (opcao == 9) {
				System.out.println("\nRC Bank agradece !");
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println("\n Criar Conta");
				System.out.println("\n Digite o numero da Agência:");
				agencia = leia.nextInt();
				System.out.println("\n Digite o nome do Titular da Conta:");
				leia.skip("\\R?");
				titular = leia.nextLine();
				
				do {
				System.out.println("\nDigite o tipo da conta: (1-CC ou 2-CP) :");
				tipo = leia.nextInt();	
				}while (tipo <1 && tipo>2);
				
				System.out.println("\n Digite o saldo da conyta: ");
				saldo = leia.nextFloat();
				
				switch(tipo) {
				case 1 -> {
				System.out.println("\nDigite o limite de crédito (R$) :");
				limite = leia.nextFloat();
				contas.cadastrar(new contaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
				System.out.println("\n Digite o dia do aniversário da conta: ");
				aniversario = leia.nextInt();
				contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}
				
				keyPress();
				break;
			case 2:
				System.out.println("\n Listar todas as Contas");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println("\n Buscar Conta por número");
				System.out.println("\n Digite o numero da conta: ");
				numero = leia.nextInt();
				
				contas.procurarPorNumero(numero);
		
				keyPress();
				break;
			case 4:
				System.out.println("\n Atualizar dados da Conta");
				System.out.println("\n Digite o numero da conta:");
				numero = leia.nextInt();
				
			if (contas.buscarNaCollection(numero) != null) {
				System.out.println("\n Digite o numero da agencia: ");
				agencia = leia.nextInt();
				System.out.println("\nDigite o nome do titutal:");
				leia.skip("\\R?");
				
			System.out.println("\n Digite o saldo da conta (R$)");
			saldo = leia.nextFloat();
			
			tipo = contas.retornaTipo(numero);
			
			switch (tipo) {
			case 1 -> {
				System.out.println("\n Digite o limite de crédito (R$)");
				limite = leia.nextFloat();
				contas.atualizar(new contaCorrente(numero,agencia,tipo,titular,saldo,limite));
			}
			case 2 -> {
				System.out.println("\nDigite o dia do aniversário da conta");
				aniversario = leia.nextInt();
				contas.atualizar(new ContaPoupanca(numero,agencia,tipo,titular,saldo,aniversario));
			}
			default -> {
				System.out.println("\n Tipo de conta inválido");
			}
			}
			
			}else 
				System.out.println("\n Conta não encontrada!");
				
				keyPress();
				break;
			case 5:
				System.out.println("\n Apagar Conta");
				
				System.out.println("\n Digite o numero da conta");
				numero = leia.nextInt();
				
				contas.deletar(numero);

				keyPress();
				break;
			case 6:
				System.out.println("\n Sacar");
				System.out.println("\n Digite o numero da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("\n Digite o valor do saque: ");
					valor = leia.nextFloat();
				}while (valor <=0);
				
				contas.sacar(numero, valor);
				keyPress();
				break;
			case 7:
				System.out.println("\n Depositar");
				System.out.println("\n Digite o numero da conta:");
				numero = leia.nextInt();
				
				do {
					System.out.println("\nDigite o valor do deposito:");
					valor = leia.nextFloat();
				}while (valor <=0);
				
				contas.depositar(numero, valor);

				keyPress();
				break;
			case 8:
				System.out.println("\n Transferir");
				System.out.println("\n Digite o numero da conta de Origem: ");
				numero = leia.nextInt();
				System.out.println("\nDigite o numero da conta de Destino: ");
				numeroDestino = leia.nextInt();
				
				do {
					System.out.println("\n Digite o valor da Transferência: ");
					valor = leia.nextFloat();
				} while (valor <= 0);
				
				contas.transferir(numero, numeroDestino, valor);

				keyPress();
				break;
			default:
				System.out.println("\nOpção Inválida" + Cores.TEXT_RESET);
				
				keyPress();
				break;
			}
		}
	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}
}