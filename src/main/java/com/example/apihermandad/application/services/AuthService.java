package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.JwtData;
import com.example.apihermandad.application.dto.LoginRequestDto;
import com.example.apihermandad.application.dto.LoginResponseDto;
import com.example.apihermandad.application.mapper.UsuarioMapper;
import com.example.apihermandad.domain.entity.Sesion;
import com.example.apihermandad.domain.entity.Usuario;
import com.example.apihermandad.domain.repository.SesionRepository;
import com.example.apihermandad.domain.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class AuthService {

    private final UsuarioRepository userRepository;
    private final UsuarioMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final SesionRepository sesionRepository;

    public AuthService(UsuarioRepository userRepository, UsuarioMapper userMapper,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       SesionRepository sesionRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.sesionRepository = sesionRepository;
    }

    public LoginResponseDto login(LoginRequestDto request, HttpServletRequest httpRequest) {

        Usuario user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        JwtData jwtData = jwtService.generateToken(user);
        String fingerprint=buildFingerprint(httpRequest);

        Sesion sesion = new Sesion();
        sesion.setUsuario(user);
        sesion.setToken(jwtData.getToken());
        sesion.setFechaEmision(jwtData.getIssuedAt());
        sesion.setFechaExpiracion(jwtData.getExpiresAt());
        sesion.setFingerPrint(fingerprint);
        sesion.setActive(true);

        sesionRepository.save(sesion);

        return new LoginResponseDto(
                jwtData.getToken(),
                userMapper.toDto(user)
        );
    }
    private String buildFingerprint(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String raw = ip + "|" + userAgent;
        return Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }
}