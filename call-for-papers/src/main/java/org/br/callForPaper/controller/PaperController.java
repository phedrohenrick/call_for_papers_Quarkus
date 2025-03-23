package org.br.callForPaper.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
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
    @POST
    public Response createPaper(@Valid PaperDetailsDTO paperDetailsDTO){

        LOG.info(" -- Recebendo Palestra -- ");

        try{
            paperService.createPaper(paperDetailsDTO);
            return Response.ok().build();
        }catch (Exception e){
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("update/{id}")
    public Response updatePaper(@PathParam("id") Long id, @Valid PaperDetailsDTO paperDetailsDTO){

        try{
            paperService.updatePaper(id, paperDetailsDTO);
            return Response.ok(paperDetailsDTO).build();

        }catch (Exception e){
            LOG.info(" -- error -- ");
            return Response.serverError().build();
        }
    }

}
