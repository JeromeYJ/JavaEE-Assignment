package edu.cn.demo.security;

import edu.cn.demo.domain.Role;
import edu.cn.demo.domain.UserDto;
import edu.cn.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBUserDetailService implements UserDetailsService {

    @Autowired
    UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userService.getUserById(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }

        return User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(getAuthorities(user))
                .build();
    }

    private static GrantedAuthority[] getAuthorities(UserDto user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            String[] authorityStrs = role.getAuthorities().split(",");
            for (String auth : authorityStrs) {
                authorities.add(new SimpleGrantedAuthority(auth));
            }
        }
        return authorities.toArray(new GrantedAuthority[authorities.size()]);
    }

}
