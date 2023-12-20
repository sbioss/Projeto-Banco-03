
public class Cliente {

	private String nome;
	private String cpf;
	private String telefone;
	private String conta;

	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getConta() {
		return conta;
	}

	public Cliente(String nome, String cpf, String telefone, String conta){
		this.telefone = telefone;
		this.nome = nome;
		this.cpf = cpf;
		this.conta = conta;


	}
}