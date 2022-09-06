package br.com.borba.clientes;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.borba.clientes.aplication.ClienteService;
import br.com.borba.clientes.aplication.representation.ClienteSaveRequest;
import br.com.borba.clientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
@Slf4j
public class ClientesController {

	private final ClienteService service;

	@GetMapping
	public String status() {
		log.info("Entrou no método");
		return "OK";
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody ClienteSaveRequest request) {

		Cliente cliente = request.toModel();
		service.save(cliente);

		URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={cpf}")
				.buildAndExpand(cliente.getCpf()).toUri();

		return ResponseEntity.created(headerLocation).build();
	}

	@GetMapping(params = "cpf")
	public ResponseEntity<Cliente> dadosCliente(@RequestParam String cpf) {

		Optional<Cliente> cliente = service.getByCPF(cpf);

		if (cliente.isEmpty()) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(cliente.get());

	}

}
