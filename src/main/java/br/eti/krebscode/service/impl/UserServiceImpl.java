package br.eti.krebscode.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.eti.krebscode.domain.User;
import br.eti.krebscode.repository.UserRepository;
import br.eti.krebscode.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		Collection<? extends GrantedAuthority> authorities;
		
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		authorities = user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	/*private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_CLIENT"));
	}*/

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userRepository.delete((int) id);
	}

	@Override
    public User save(User user) {
        return userRepository.save(user);
    }
	
}
