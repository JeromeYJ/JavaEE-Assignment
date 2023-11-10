package edu.cn.demo.controller;

import edu.cn.demo.domain.User;
import edu.cn.demo.security.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("authentication")
public class AuthenticationController {

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getId());
        if(userDetails == null)
        {
            return ResponseEntity.badRequest().body("用户不存在!");
        }
        if (user.getPassword().equals(userDetails.getPassword()))
        {
            String token = jwtTokenUtil.generateToken(userDetails);
            System.out.println("Token:" + token);
            return ResponseEntity.ok(token);
        }
        else
        {
            return ResponseEntity.badRequest().body("用户认证未通过!");
        }
    }
}
