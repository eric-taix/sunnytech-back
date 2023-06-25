package io.jedar.sunnytech.repository;

import io.jedar.sunnytech.solver.ConferenceSolution;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class InMemoryConferenceRepository implements ConferenceRepository {

    private Map<Long, ConferenceSolution> conferenceSolutions = new HashMap<>();

    @Override
    public void save(ConferenceSolution conferenceSolution) {
        conferenceSolutions.put(conferenceSolution.getId(), conferenceSolution);
    }

    @Override
    public ConferenceSolution findById(Long id) {
        return conferenceSolutions.get(id);
    }
}
