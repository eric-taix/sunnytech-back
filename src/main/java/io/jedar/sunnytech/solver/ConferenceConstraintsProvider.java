package io.jedar.sunnytech.solver;

import io.jedar.sunnytech.domain.Talk;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConferenceConstraintsProvider implements ConstraintProvider {

    private Logger LOGGER = LoggerFactory.getLogger(ConferenceConstraintsProvider.class);

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                roomConflict(constraintFactory),
                speakerConflict(constraintFactory)
        };
    }

    private Constraint roomConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(Talk.class)
                .filter(Talk::hasSameRoom)
                .filter(Talk::withHoursOverlapped)
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Room Conflict");
    }

    private Constraint speakerConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(Talk.class)
                .filter(Talk::withAtLeastOneSameSpeaker)
                .filter(Talk::withHoursOverlapped)
                .filter(Talk::withSameDate)
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Speaker Conflict");
    }

}
