package br.com.borba.clientes.aplication.representation;

import br.com.borba.clientes.domain.Cliente;
import lombok.Data;

@Data
public class ClienteSaveRequest {

	private String nome;
	private Integer idade;
	private String cpf;

	public Cliente toModel() {
		return new Cliente(nome, idade, cpf);
	}

}
