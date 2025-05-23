package org.br.callForPaper.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.br.callForPaper.dto.UserDetailsDTO;
import org.br.callForPaper.entity.User;
import org.br.callForPaper.repository.UserRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class TokenServiceImpl implements TokenService{

    @Inject
    UserRepository UserRepository;

    @Override
    public String generateToken(String email) {
        long exp = Instant.now().plus(10, ChronoUnit.MINUTES).getEpochSecond();

        return Jwt.issuer("call-for-papers")
                .upn(email)
                .expiresAt(exp)
                .groups("user")
                .sign();
    }

    @Override
    public UserDetailsDTO validateToken(String email) {

        User user = UserRepository.find("email", email).firstResult();
        if (user == null) {
            throw new WebApplicationException(401);
        }
        UserDetailsDTO result = new UserDetailsDTO();
        result.setEmail(user.getEmail());
        result.setNome(user.getNome());
        result.setCpf(user.getCpf());

        return result;

    }
}
