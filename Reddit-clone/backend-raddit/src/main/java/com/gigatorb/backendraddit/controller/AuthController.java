package com.gigatorb.backendraddit.controller;
import com.gigatorb.backendraddit.dto.LoginRequest;
import com.gigatorb.backendraddit.dto.AuthenticationResponse;
import com.gigatorb.backendraddit.dto.RefreshTokenRequest;
import com.gigatorb.backendraddit.dto.RegisterRequest;
import com.gigatorb.backendraddit.service.AuthService;
import com.gigatorb.backendraddit.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
@CrossOrigin(origins="*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest){
            authService.signup(registerRequest);
            return new ResponseEntity(OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);

    }
    @PostMapping("refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }



    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh token deleted successfully!!");
    }
}
