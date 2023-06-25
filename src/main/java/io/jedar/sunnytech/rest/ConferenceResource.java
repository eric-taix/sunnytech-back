package io.jedar.sunnytech.rest;

import io.jedar.sunnytech.domain.*;
import io.jedar.sunnytech.services.ConferenceService;
import io.jedar.sunnytech.solver.ConferenceSolution;
import io.jedar.sunnytech.utils.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Produces({"application/json"})
@Consumes({"application/json"})
@Path("/api")
public class ConferenceResource {

  private static final Logger LOG = LoggerFactory.getLogger(ConferenceResource.class);
  Random random = new Random();

  @Inject
  ConferenceService conferenceService;

  @GET
  @Path("/problems")
  @Produces(MediaType.APPLICATION_JSON)
  public List<ConferenceProblem> conferences() {
    return conferenceService.getAllConferences();
  }

  @POST
  @Path("/solve")
  public long solve(ConferenceProblem problem) {
    List<Track> tracks = problem.getDays().stream()
      .flatMap(day -> day.getTracks().stream().map(track -> track.copyWith(day.getDate())))
      .toList();
    List<Slot> slots = tracks.stream().map(track -> new Pair<Track, Stream<Slot>>(track, track.getSlots().stream()))
      .flatMap(roomAndTrack -> roomAndTrack.getRight()
        .map(trackSlot -> trackSlot.copyWith(roomAndTrack.getLeft().getRoom(), roomAndTrack.getLeft().getDate())))
      .toList();
    List<String> rooms = tracks.stream().map(Track::getRoom).distinct().toList();
    List<Talk> talks = problem.getTalks();
    long id = random.nextLong(10000000);
    ConferenceSolution conferenceSolution = new ConferenceSolution(id, slots, rooms, talks);
    conferenceService.solve(conferenceSolution);
    return conferenceSolution.getId();
  }

  @GET
  @Path("/solutions/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public ConferenceSolution solutions(@PathParam("id") long id) {
    return conferenceService.getSolutions(id);
  }
}
