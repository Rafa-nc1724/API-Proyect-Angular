package com.example.apihermandad.infrastructure.seeder;

import com.example.apihermandad.domain.entity.*;
import com.example.apihermandad.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("docker")
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {

        if (usuarioRepository.count() > 0) {
            return;
        }

        Usuario admin = new Usuario();
        admin.setName("Admin");
        admin.setEmail("admin@email.com");
        admin.setDni("12345678A");
        admin.setAddress("Calle Principal");
        admin.setPassword("$2a$10$O2Q4Oeu8oV.wAwtxqlp9ieAOoc69Upry62gmWY6cIK5iycg8JalFq");
        admin.setRole("admin");
        admin.setActive(true);
        admin.setCreateDate(LocalDate.now());

        usuarioRepository.save(admin);

        System.out.println("✅ Base de datos inicializada");
    }
}