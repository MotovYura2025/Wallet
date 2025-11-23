package com.example.walletapi.controller;

import com.example.walletapi.entity.User;
import com.example.walletapi.repository.UserRepository;
import com.example.walletapi.service.TwoFactorAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final TwoFactorAuthService twoFactorAuthService;

    public AuthController(UserRepository userRepository, TwoFactorAuthService twoFactorAuthService) {
        this.userRepository = userRepository;
        this.twoFactorAuthService = twoFactorAuthService;
    }

    @PostMapping("/2fa/setup")
    public ResponseEntity<String> setup2fa(@AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> userOpt = userRepository.findByEmail(userDetails.getUsername());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOpt.get();

        String secret = twoFactorAuthService.generateSecret();
        user.setTwoFactorSecret(secret);
        user.setTwoFactorEnabled(true);
        userRepository.save(user);

        return ResponseEntity.ok(secret);
    }

    @PostMapping("/2fa/verify")
    public ResponseEntity<String> verify2fa(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int code) {
        Optional<User> userOpt = userRepository.findByEmail(userDetails.getUsername());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOpt.get();

        if (!user.isTwoFactorEnabled()) {
            return ResponseEntity.badRequest().body("2FA is not enabled");
        }

        boolean verified = twoFactorAuthService.verifyCode(user.getTwoFactorSecret(), code);
        if (verified) {
            return ResponseEntity.ok("2FA verification successful");
        } else {
            return ResponseEntity.status(401).body("Invalid 2FA code");
        }
    }
}
