package br.com.borba.clientes.aplication;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.borba.clientes.domain.Cliente;
import br.com.borba.clientes.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository repository;

	@Transactional
	public Cliente save(Cliente cliente) {

		return repository.save(cliente);

	}

	public Optional<Cliente> getByCPF(String cpf) {

		return repository.findByCpf(cpf);

	}

}
