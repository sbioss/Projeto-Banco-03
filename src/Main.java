import java.util.HashMap;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static Hashtable<String, Cliente> clientes = new Hashtable<>();
	public static Hashtable<String, Banco> ClienteBanco = new Hashtable<>();
	public static Hashtable<String, Conta> ClienteConta = new Hashtable<>();
	public static Map<String, Extrato> mapaNomesUsuarios = new HashMap<>();
	
	
	
	
	
	public static Scanner scanner = new Scanner(System.in);
	

	public static void main(String[] args) {	
		System.out.println("Bem vindo ao banco do leozão");
			lobby();}	
		static void lobby(){
			try {					
		System.out.println("1 - Cadastrar Cliente \n2 - Consultar Cliente \n3 - Imprimir todos os clientes cadastrado no banco \n4 - Movimentação bancaria");
			int numero = scanner.nextInt();		
		switch (numero) {
			case 1:
				Cadastrar();			
					break;
			case 2:
				ColsultarCliente();
					break;	
			case 3:
				imprimirTodosClientes();
					break;
			case 4:
			MovimentacaoBancaria();
					break;	
			default:
				break;}
		} catch (InputMismatchException e) {
			System.out.println("Apenas numeros!");
			scanner.nextLine();
			
			lobby();}	
		//Conta cc = new ContaCorrente(venilton);
		//Conta poupanca = new ContaPoupanca(venilton);

		//cc.depositar(100);
		//cc.transferir(100, poupanca);
		
		
       // cc.imprimirExtrato();
       // poupanca.imprimirExtrato();
	}
	
	static void Cadastrar(){
		
		System.out.println("Digiti o nome que deseja cadastrar");
			String nome = scanner.next();
		System.out.println("Digiti o cpf que deseja cadastrar");
			String cpf = scanner.next();
		System.out.println("Digiti o telefone que deseja cadastrar");
			String telefone = scanner.next();
		System.out.println("Digiti o numero da conta");
			String conta = scanner.next();
		
			clientes.put(nome, new Cliente(nome, cpf, telefone, conta));
			ClienteBanco.put(nome, new Banco(nome, conta));
			ClienteConta.put(nome,new Conta(nome, conta));
			
			lobby();
		
		


	}
	static void ColsultarCliente(){
		System.out.println("Digiti o nome que deseja consultar");
			String nome = scanner.next();
		Cliente cliente = clientes.get(nome);
		if(cliente != null){
			System.out.println("Cliente : " +nome+ "\nCpf : " + cliente.getCpf() + "\nTelefone : " + cliente.getTelefone() + "\nNumero da conta : " + cliente.getConta());		
		} 
		lobby();	
	}
	static void depositar(){

	}
	static void imprimirTodosClientes() {
		System.out.println("Lista de Todos os Clientes:");
	
		for (String nome : ClienteBanco.keySet()) {
			Banco cliente = ClienteBanco.get(nome);
			System.out.println("Nome: " + nome + "\nNumero da conta: " + cliente.getConta() + "\n");
		}
	
		lobby(); 
	}
	static void MovimentacaoBancaria(){
		try {	
		System.out.println("1 - Depositar \n2 - Sacar \n3 - Transferencia \n4 - Extrato \n5 - Voltar ao menu inicial");
			int numero = scanner.nextInt();
		switch (numero) {
			case 1:
				Depositar();				
				break;
			case 2:
				sacar();				
				break;
			case 3:
				Transferencia();				
				break;
			case 4:
				System.out.print("Digite o nome do usuário para imprimir suas informações: \n ");
                    String nomeParaImprimir = scanner.next();

                    Extrato usuarioParaImprimir = mapaNomesUsuarios.get(nomeParaImprimir);
                    if (usuarioParaImprimir != null) {
                        System.out.println("Extrato do Cliente " + nomeParaImprimir + ":");
                        for (String item : usuarioParaImprimir.getInformacoes()) {
                            System.out.println(item);
                        }
                    } else {
                        System.out.println("Usuário não encontrado.\n");
                    }				
				break;
			case 5:
			lobby();
                    break;
		
			default:
				break;
	}
} 			catch (InputMismatchException e) {
					System.out.println("Apenas numeros!");
					scanner.nextLine();
					MovimentacaoBancaria();		
	}
}
	static void Depositar(){
		System.out.println("Digite o nome do usuário que vai receber o dinheiro.");
    		String nome = scanner.next();        
			Conta cliente = ClienteConta.get(nome);
			Extrato usuario = mapaNomesUsuarios.get(nome);
			if (usuario == null) {
			usuario = new Extrato(nome);		
			mapaNomesUsuarios.put(nome, usuario);
	}
		if (cliente != null) {
    	System.out.println("Digite o valor que deseja depositar.");
    		double ValorDeposito = scanner.nextDouble();    
			String ValorNoExtrato = String.valueOf(ValorDeposito);    
        		cliente.depositar(ValorDeposito);
			usuario.adicionarInformacao("+" + ValorNoExtrato);
		

        System.out.println("Saldo na conta de " + nome + " é: " + cliente.getSaldo());
		
    } 	
	
	
	else {
        System.out.println("Conta não encontrada para o cliente " + nome);
    }

    lobby();
}
	static void sacar(){
		System.out.println("Digite o nome do usuário que vai receber o dinheiro.");
    		String nome = scanner.next();
			Conta cliente = ClienteConta.get(nome);
			Extrato usuario = mapaNomesUsuarios.get(nome);
			if (usuario == null) {
			usuario = new Extrato(nome);		
			mapaNomesUsuarios.put(nome, usuario);
	}
		if (cliente != null) {        
    	System.out.println("Digite o valor que deseja depositar.");
    		double ValorSaque = scanner.nextDouble();        
        		cliente.saque(ValorSaque);
        System.out.println("Saldo na conta de " + nome + " é: " + cliente.getSaldo());
		String ValorNoExtrato = String.valueOf(ValorSaque);    
        		
			usuario.adicionarInformacao("-" + ValorNoExtrato);
			
		
    } else {
        System.out.println("Conta não encontrada para o cliente " + nome);
    }

    lobby();
}
	static void Transferencia(){
		System.out.println("Nome do proprietario da conta");
		String nome = scanner.next();
		System.out.println("Valor da transferencia");
		double valor = scanner.nextDouble();
		Conta cliente = ClienteConta.get(nome);
		double ValorNaConta = cliente.getSaldo();
		
		
		if(cliente != null && (ValorNaConta - valor) > 0){
			cliente.saque(valor);
		}
		else{
			System.out.println("Saldo insuficiente");
			MovimentacaoBancaria();
		}
		System.out.println("Nome do destinatario");
		String nome2 = scanner.next();
		Conta destinatario = ClienteConta.get(nome2);
			Extrato usuario = mapaNomesUsuarios.get(nome);
			if (usuario == null) {
			usuario = new Extrato(nome);		
			mapaNomesUsuarios.put(nome, usuario);
				Extrato usuario1 = mapaNomesUsuarios.get(nome2);
			if (usuario1 == null) {
			usuario1 = new Extrato(nome2);		
			mapaNomesUsuarios.put(nome2, usuario1);
				String ValorNoExtrato = String.valueOf(valor);            		
				usuario.adicionarInformacao("-" + ValorNoExtrato);
				usuario1.adicionarInformacao("-" + ValorNoExtrato);
	}
	}
		if(destinatario != null){
			destinatario.depositar(valor);
		System.out.println("Saldo da conta do " + cliente.getNome() + " : " + cliente.getSaldo());
		System.out.println("Saldo da conta do " + destinatario.getNome() + " : " + destinatario.getSaldo());
		
		}
		lobby();
			
		
		}

		
	}




	
