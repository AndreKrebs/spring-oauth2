package br.eti.krebscode.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {

	@GetMapping
    public String getRequest() {
        return "GET produtos " + UUID.randomUUID().toString();
    }
	
	@PostMapping
    public String postRequest() {
        return "POST produtos " + UUID.randomUUID().toString();
    }
	
	@PutMapping
    public String putRequest() {
        return "PUT produtos " + UUID.randomUUID().toString();
    }
	
	@DeleteMapping
    public String deleteRequest() {
        return "DELETE produtos " + UUID.randomUUID().toString();
    }
}
