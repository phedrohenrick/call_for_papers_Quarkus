package org.br.callForPaper.service;

import org.br.callForPaper.dto.UserDetailsDTO;

public interface TokenService {
    String generateToken(String email);

    UserDetailsDTO validateToken(String email);
}
