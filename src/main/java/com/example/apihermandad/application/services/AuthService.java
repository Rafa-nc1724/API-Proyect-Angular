package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.JwtData;
import com.example.apihermandad.application.dto.LoginRequestDto;
import com.example.apihermandad.application.dto.LoginResponseDto;
import com.example.apihermandad.application.mapper.UsuarioMapper;
import com.example.apihermandad.domain.entity.Sesion;
import com.example.apihermandad.domain.entity.Usuario;
import com.example.apihermandad.domain.repository.SesionRepository;
import com.example.apihermandad.domain.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public LoginResponseDto login(LoginRequestDto request) {

        Usuario user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        JwtData jwtData = jwtService.generateToken(user);

        Sesion sesion = new Sesion();
        sesion.setIdUsuario(user);
        sesion.setToken(jwtData.getToken());
        sesion.setFechaEmision(jwtData.getIssuedAt());
        sesion.setFechaExpiracion(jwtData.getExpiresAt());

        sesionRepository.save(sesion);

        return new LoginResponseDto(
                jwtData.getToken(),
                userMapper.toDto(user)
        );
    }
}