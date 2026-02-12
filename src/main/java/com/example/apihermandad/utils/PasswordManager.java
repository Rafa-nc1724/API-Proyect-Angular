package com.example.apihermandad.utils;

import java.security.SecureRandom;

public final class PasswordManager {

  private static final String CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789";
  private static final SecureRandom RNG = new SecureRandom();

  private PasswordManager() {}

  public static String generatePassword() {
    return generateGroup(5) + "-" + generateGroup(5) + "-" + generateGroup(5);
  }

  private static String generateGroup(int len) {
    StringBuilder sb = new StringBuilder(len);

    for (int i = 0; i < len; i++) {
      sb.append(CHARS.charAt(RNG.nextInt(CHARS.length())));
    }

    return sb.toString();
  }
}
