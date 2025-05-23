package org.br.callForPaper.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.br.callForPaper.dto.UserDetailsDTO;
import org.br.callForPaper.entity.User;
import org.br.callForPaper.repository.UserRepository;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResourceServiceImpl implements AuthResouceService{

    @Inject
    Mailer mailer;

    @Inject
    TokenServiceImpl tokenServiceImpl;

    @Inject
    UserRepository userRepository;

    @Override
    @Transactional
    public void requestLogin(UserDetailsDTO dto) {

        User verifyUser = userRepository.findByEmail(dto.getEmail());
        try {
            if(verifyUser == null) {
                User newUser = new User();
                newUser.setEmail(dto.getEmail());
                newUser.setNome(dto.getNome());
                userRepository.persist(newUser);
            }

            String token = tokenServiceImpl.generateToken(dto.getEmail());

            String link = "http://localhost:3000/verification/login?token=" + token;
             mailer.send(Mail.withText(dto.getEmail(), "Login na plataforma", "Clique no link de acesso para entrar: " + link));

        }catch (Exception e){
            throw new RuntimeException("Error ao enviar o link de acesso para o usuário: " + e.getMessage(), e);
        }


    }
}
