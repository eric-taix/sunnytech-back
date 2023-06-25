package io.jedar.sunnytech.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jedar.sunnytech.domain.ConferenceProblem;
import io.jedar.sunnytech.repository.ConferenceRepository;
import io.jedar.sunnytech.solver.ConferenceSolution;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

@ApplicationScoped
public class ConferenceService {

    private static final Logger LOG = LoggerFactory.getLogger(ConferenceService.class);

    @Inject
    SolverManager<ConferenceSolution, Long> solverManager;
    @Inject
    ScoreManager<ConferenceSolution, HardSoftScore> scoreManager;
    @Inject
    ConferenceRepository conferenceRepository;

    public List<ConferenceProblem> getAllConferences() {
        List<String> conferenceResources = readResource("assets/conferences");
        List<ConferenceProblem> conferenceProblems = conferenceResources.stream().map(conferenceResource -> {
                    try {
                        return parseConference("assets/conferences/" + conferenceResource);
                    } catch (IOException e) {
                        LOG.error("Error parsing conference: {}", conferenceResource, e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return conferenceProblems;
    }

    public void solve(ConferenceSolution conferenceSolution) {
        LOG.info("Start to solve problem: " + conferenceSolution);
        conferenceRepository.save(conferenceSolution);
        solverManager.solveAndListen(conferenceSolution.getId(), (id) -> conferenceRepository.findById(conferenceSolution.getId()), this::save);
    }

    private void save(ConferenceSolution solution) {
        System.out.println("Found new Solution: " + solution);
        conferenceRepository.save(solution);
    }
    private List<String> readResource(String resourcePath) {
        List<String> result = new ArrayList<>();
        LOG.info("Reading resource: {}", resourcePath);
        try (final InputStream in = getContextClassLoader().getResourceAsStream(resourcePath)) {
            if (in != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.add(line);
                }
            } else {
                LOG.error("Could not find resource: {}", resourcePath);
            }
        } catch (Exception e) {
            LOG.error("Error reading resource: {}", resourcePath, e);
        }
        return result;
    }

    private ConferenceProblem parseConference(String conferenceResource) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> lines = readResource(conferenceResource);
        return mapper.readValue(String.join("", lines), ConferenceProblem.class);
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public ConferenceSolution getSolutions(long id) {
        return conferenceRepository.findById(id);
    }
}
