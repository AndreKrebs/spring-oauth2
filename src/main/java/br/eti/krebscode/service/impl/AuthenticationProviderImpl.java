package br.eti.krebscode.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.eti.krebscode.domain.User;
import br.eti.krebscode.repository.UserRepository;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder cryptPassword;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();

		User user = userRepository.findByUsername(username);
		Collection<? extends GrantedAuthority> authorities;
		
//		if(user == null){
//			throw new UsernameNotFoundException("Invalid username or password...");
//		}
		
		if (user.getUsername().equals(username) && cryptPassword.matches(password, user.getPassword())) {
			authorities = user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
			
			return new UsernamePasswordAuthenticationToken(username, password, authorities);
		} else {
			throw new BadCredentialsException("External system authentication failed...");
		}
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
