package estm.dsic.jee.rest.controllers;

import java.util.List;

import estm.dsic.jee.rest.dal.NoteDao;
import estm.dsic.jee.rest.models.Note;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/notes")

public class NoteController {
    @Inject
    private NoteDao noteDao;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNote(Note note) {
        try {
            noteDao.create(note); 
            return Response.status(Response.Status.CREATED).entity(note).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error adding note: " + e.getMessage()).build();
        }
}

    @GET
    @Path("/{userId}/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNotes(@PathParam("userId") int userId) {
        try {
            List<Note> notes = noteDao.getAllNotesByUserId(userId);
            return Response.ok(notes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error retrieving notes: " + e.getMessage())
                        .build();
        }
    }

    
    @POST
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") String id, Note note) {
        try {
            noteDao.update(note, id);
            return Response.ok().entity("Note updated successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating note: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/delete/{id}")
    public Response deleteNote(@PathParam("id") String id,Note note) {
        try {
            noteDao.delete(note, id);
            return Response.ok().entity("Note deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting note: " + e.getMessage()).build();
        }
    }



    
}
