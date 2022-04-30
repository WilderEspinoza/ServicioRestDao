package com.idat.idatapirest.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.idat.idatapirest.model.Usuario;
import com.idat.idatapirest.repository.UsuarioRepository;

@Service
public class JWTUserDetailService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repository.findByUsuario(username);
		
		if(usuario != null) {
			List<GrantedAuthority> listaGranted =new ArrayList<GrantedAuthority>();
			GrantedAuthority  granted = new SimpleGrantedAuthority(usuario.getRol());
			
			listaGranted.add(granted);
			return new User(usuario.getUsuario(), new BCryptPasswordEncoder().encode(usuario.getContrasenia()) , listaGranted);
			
		}else {
			throw new UsernameNotFoundException("El usuario no existe");
		}
	}


}
