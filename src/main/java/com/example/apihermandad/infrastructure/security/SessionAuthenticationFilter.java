package com.example.apihermandad.infrastructure.security;

import com.example.apihermandad.domain.entity.Sesion;
import com.example.apihermandad.domain.entity.Usuario;
import com.example.apihermandad.domain.repository.SesionRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class SessionAuthenticationFilter extends OncePerRequestFilter {

    private final SesionRepository sesionRepository;

    public SessionAuthenticationFilter(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader== null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);

        Optional<Sesion> sesionOpt = sesionRepository.findActiveSessionWithUser(token);

        if(sesionOpt.isEmpty()){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Sesion sesion = sesionOpt.get();

        long now = Instant.now().toEpochMilli();
        if (sesion.getFechaExpiracion() < now) {
            sesion.setActive(false);
            sesionRepository.save(sesion);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String currentFingerprint = buildFinguerprint(request);
        if(!currentFingerprint.equals(sesion.getFingerPrint())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Usuario usuario = sesion.getUsuario();
        var authentication = new UsernamePasswordAuthenticationToken(
                usuario,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().toUpperCase()))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private String buildFinguerprint(HttpServletRequest request) {
        String ip=request.getRemoteAddr();
        String userAgent=request.getHeader("User-Agent");
        String raw = ip + "|" + userAgent;
        return Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }
}
