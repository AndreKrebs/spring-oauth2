package br.eti.krebscode;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.eti.krebscode.domain.User;
import br.eti.krebscode.domain.UserRole;
import br.eti.krebscode.repository.UserRepository;

@SpringBootApplication
public class DemoJwtOauth2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoJwtOauth2Application.class, args);
	}
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
    @Override
	public void run(String... args) throws Exception {
    	
		System.err.println("###################### ADICIONANDO USUÁRIOS ####################");
		User user1 = new User(1, "alex.adm", passwordEncoder.encode("Alex1234"), 3456, 33, UserRole.ADMIN); // "$2a$04$MpCBI3rw1KjqFvka1BSjNe.ohgVSXxkMQ.rgX/q8RnNF6phSiPXSe"
		User user2 = new User(2, "tom.clt", passwordEncoder.encode("Tom1234"), 7823, 23, UserRole.CLIENT);
		User user3 = new User(3, "adam.clt", passwordEncoder.encode("Adam1234"), 4234, 45, UserRole.CLIENT);
		
		userRepository.save(Arrays.asList(user1, user2, user3));
	}
}
