package br.eti.krebscode.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/pagamentos")
public class PagamentosController {

	@GetMapping
    public String getRequest() {
        return "GET pagamentos " + UUID.randomUUID().toString();
    }
	
	@PostMapping
    public String postRequest() {
        return "POST pagamentos " + UUID.randomUUID().toString();
    }
	
	@PutMapping
    public String putRequest() {
        return "PUT pagamentos " + UUID.randomUUID().toString();
    }
	
	@DeleteMapping
    public String deleteRequest() {
        return "DELETE pagamentos " + UUID.randomUUID().toString();
    }
}
