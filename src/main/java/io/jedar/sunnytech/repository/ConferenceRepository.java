package io.jedar.sunnytech.repository;

import io.jedar.sunnytech.solver.ConferenceSolution;

public interface ConferenceRepository {

    void save(ConferenceSolution conferenceSolution);

    ConferenceSolution findById(Long id);
}
