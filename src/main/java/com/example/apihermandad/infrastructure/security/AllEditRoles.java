package com.example.apihermandad.infrastructure.security;


import org.springframework.security.access.prepost.PreAuthorize;
import java.lang.annotation.*;



@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@PreAuthorize("hasAnyRole('ADMIN', 'JUNTA', 'CAPATAZ')")
public @interface AllEditRoles { }
