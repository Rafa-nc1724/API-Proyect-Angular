package com.example.apihermandad.infrastructure.security;

import com.example.apihermandad.domain.entity.Sesion;
import com.example.apihermandad.domain.entity.Usuario;
import com.example.apihermandad.domain.repository.SesionRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
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
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        String method = request.getMethod();

        // Swagger
        if (path.startsWith("/swagger-ui/") || path.startsWith("/v3/api-docs") || path.equals("/swagger-ui.html")) {
            return true;
        }

        // Login
        if (path.equals("/api/auth/login")) {
            return true;
        }

        // GET públicos
        if (HttpMethod.GET.matches(method)) {
            if (path.startsWith("/api/events/") || path.startsWith("/api/news/") || path.startsWith("/api/image/")) {
                return true;
            }
        }

        // (opcional) /error
        if (path.equals("/error")) {
            return true;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7).trim();
        if (token.isEmpty()) {
            throw new BadCredentialsException("Bearer token vacío");
        }

        Optional<Sesion> sesionOpt = sesionRepository.findActiveSessionWithUser(token);
        if (sesionOpt.isEmpty()) {
            throw new BadCredentialsException("Token inválido");
        }

        Sesion sesion = sesionOpt.get();

        long now = Instant.now().toEpochMilli();
        if (sesion.getFechaExpiracion() < now) {
            sesion.setActive(false);
            sesionRepository.save(sesion);
            throw new CredentialsExpiredException("Sesión expirada");
        }

        String currentFingerprint = buildFingerprint(request);
        if (!currentFingerprint.equals(sesion.getFingerPrint())) {
            SecurityContextHolder.clearContext();
            throw new BadCredentialsException("Fingerprint inválido");
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

    private String buildFingerprint(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String raw = ip + "|" + userAgent;
        return Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }
}
