package com.olmez.coremicro.springsecurity;

import java.rmi.UnexpectedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.coremicro.springsecurity.AuthService.AuthResponse;
import com.olmez.coremicro.springsecurity.securityutiliy.AuthRequest;
import com.olmez.coremicro.springsecurity.securityutiliy.RegisterRequest;
import com.olmez.coremicro.utility.StringUtility;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // This allows to talk to port:5000 (ui-backend)
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signupUser(@RequestBody RegisterRequest request) {
        boolean res = authService.register(request);
        return (res) ? new ResponseEntity<>(HttpStatus.CREATED) : ResponseEntity.badRequest().body(false);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody AuthRequest request) throws UnexpectedException {
        AuthResponse res = authService.authenticate(request);
        return (!StringUtility.isEmpty(res.getToken())) ? ResponseEntity.ok(res)
                : ResponseEntity.badRequest().body(null);
    }

}