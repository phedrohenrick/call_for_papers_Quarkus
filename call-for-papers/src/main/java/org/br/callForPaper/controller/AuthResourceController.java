package org.br.callForPaper.controller;

import io.smallrye.common.annotation.Blocking;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.br.callForPaper.dto.UserDetailsDTO;
import org.br.callForPaper.service.AuthResourceServiceImpl;
import org.br.callForPaper.service.TokenServiceImpl;

@Path("/api/auth")
public class AuthResourceController {

    @Inject
    AuthResourceServiceImpl authResourceService;

    @Inject
    TokenServiceImpl tokenService;

    @POST
    @Path("/request-login")
    @Blocking
    public Response requestLogin(UserDetailsDTO user){

        authResourceService.requestLogin(user);
        return Response.ok().build();
    }

    @GET
    @Path("/validate")
    @PermitAll
    public Response validateToken(@Context SecurityContext ctx) {
        String email = ctx.getUserPrincipal().getName();

        UserDetailsDTO user = tokenService.validateToken(email);

        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.ok(email).build();
    }
}
