package org.br.callForPaper.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.br.callForPaper.dto.PaperDetailsDTO;
import org.br.callForPaper.service.PaperService;

import java.util.List;
import java.util.logging.Logger;

@Path("/api/paper")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaperController {

    private static final Logger LOG = Logger.getLogger(PaperController.class.getName());

    @Inject
    PaperService paperService;

    @GET
    @Path("/list")
    public Response listPaper(){
        List<PaperDetailsDTO> papersList = paperService.listPapers();

        if(papersList.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(papersList).build();
    }

}
