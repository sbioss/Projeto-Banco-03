

public class Conta {	
	
		private String nome;
		private String conta;
		private double saldo;
		public double getSaldo(){
			return saldo;
		}
	
		public Conta(String nome, String conta) {
			this.nome = nome;
			this.conta = conta;
        }
        public String getNome() {
			return nome;
		}
		public String getConta() {
			return conta;
		}
	
		
		public void depositar(double ValorDeposito){
			saldo += ValorDeposito;
		}
		public void saque(double ValorSaque){
			
			saldo -= ValorSaque;
		}
		
		

		
	}

	
