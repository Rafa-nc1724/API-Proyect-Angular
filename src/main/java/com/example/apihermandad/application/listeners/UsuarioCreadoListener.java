package com.example.apihermandad.application.listeners;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.apihermandad.application.events.UsuarioCreadoEvent;
import com.example.apihermandad.application.services.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class UsuarioCreadoListener {

  private final EmailService emailService;

  @TransactionalEventListener
  public void onUsuarioCreado(UsuarioCreadoEvent event) throws MessagingException {
    emailService.sendEmailOnUserCreation(event.user());
  }
}
