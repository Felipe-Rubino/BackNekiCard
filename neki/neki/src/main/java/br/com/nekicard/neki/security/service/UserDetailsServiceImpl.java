package br.com.nekicard.neki.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nekicard.neki.security.domain.User;
import br.com.nekicard.neki.security.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Empresa Not Found with email: " + email));

		return UserDetailsImpl.build(user);

	}
}
