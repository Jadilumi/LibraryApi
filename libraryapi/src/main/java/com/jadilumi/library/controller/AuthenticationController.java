package com.jadilumi.library.controller;

import com.jadilumi.library.config.security.TokenService;
import com.jadilumi.library.domain.entities.users.User;
import com.jadilumi.library.domain.entities.users.dto.AuthenticationDTO;
import com.jadilumi.library.domain.entities.users.dto.LoginResponseDTO;
import com.jadilumi.library.domain.entities.users.dto.RegisterDTO;
import com.jadilumi.library.domain.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        String url = "https://viacep.com.br/ws/" + data.cep() + "/json/";
        User newUser = new User(data.email(), encryptedPassword, data.role(), getAddress(url));

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(this.tokenService.validateToken(token.replace("Bearer ", "")) != null, HttpStatus.ACCEPTED);
    }

    private String getAddress(String url) {
        Map dadosCep = restTemplate.getForObject(url, Map.class);
        String address = dadosCep.get("logradouro") + ", "
                + dadosCep.get("bairro") + ", "
                + dadosCep.get("localidade") + ", "
                +dadosCep.get("uf");
        return address;
    }

}
