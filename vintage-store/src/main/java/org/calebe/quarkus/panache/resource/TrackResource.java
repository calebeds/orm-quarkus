package org.calebe.quarkus.panache.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.calebe.quarkus.panache.model.Track;

@Path("api/tracks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(TxType.SUPPORTS)
public class TrackResource {

    @GET
    @Path("{id}")
    public Track findTrackById(@PathParam("id") long id) {
        return (Track) Track.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }
    
    @GET
    public List<Track> listAllTracks() {
        return Track.listAll();
    }

    @Transactional(TxType.REQUIRED)
    @POST
    public Response persistTrack(Track track, @Context UriInfo uriInfo) {
        Track.persist(track);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(track.id));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deleteTrack(@PathParam("id") long id) {
        Track.deleteById(id);
    }
}
