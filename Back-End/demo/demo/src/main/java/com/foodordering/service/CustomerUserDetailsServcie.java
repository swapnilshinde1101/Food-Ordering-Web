package com.foodordering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.*;

import com.foodordering.model.USER_ROLE;
import com.foodordering.model.User;
import com.foodordering.repository.UserRepository;
@Service
public class CustomerUserDetailsServcie implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; 
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
		
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
		
		USER_ROLE role=USER_ROLE.getRole();
		List<GrantedAuthority> authorities=new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(role.toString()));
		
		
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
	
	

}