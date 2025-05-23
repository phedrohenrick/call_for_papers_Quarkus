package org.br.callForPaper.controller;

import jakarta.annotation.security.RolesAllowed;
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
@Produces(MediaType.APPLICATION_JSON) //serializa
@Consumes(MediaType.APPLICATION_JSON) //desserializa

public class PaperController {

    private static final Logger LOG = Logger.getLogger(PaperController.class.getName());

    @Inject
    PaperService paperService;

    @GET
    @Path("/list")
    @RolesAllowed("user")
    public Response listPaper(){
        List<PaperDetailsDTO> papersList = paperService.listPapers();

        if(papersList.isEmpty()){
            return Response.status (Response.Status.NO_CONTENT).build();
        }
        return Response.ok(papersList).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("user")
    public Response getPaper(@PathParam("id") Long id){
        PaperDetailsDTO paper = paperService.getPaper(id);
        if(paper == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(paper).build();
    }

    @POST
    @RolesAllowed("user")
    public Response createPaper(@Valid PaperDetailsDTO paperDetailsDTO){

        LOG.info(" -- Recebendo Palestra -- ");
            try {
                PaperDetailsDTO paper = paperService.createPaper(paperDetailsDTO);
                return Response.status(Response.Status.CREATED).entity(paper).build();

            }catch (WebApplicationException e){
                return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
            }
    }

    @PUT
    @Path("update/{id}")
    @RolesAllowed("user")
    public Response updatePaper(@PathParam("id") Long id, @Valid PaperDetailsDTO paperDetailsDTO){

           try {
               paperService.updatePaper(id, paperDetailsDTO);
               return Response.ok(paperDetailsDTO).build();
           }catch (WebApplicationException e){
               return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
           }


    }
    @DELETE
    @Path("/{id}")
    @RolesAllowed("user")
    public Response deletePaper(@PathParam("id") Long id){

            paperService.deletePaper(id);
            return Response.status(Response.Status.NO_CONTENT).build();


    }

}
