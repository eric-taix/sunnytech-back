package io.jedar.sunnytech.solver;

import io.jedar.sunnytech.domain.Talk;
import io.jedar.sunnytech.domain.Slot;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
public class ConferenceSolution {

    private Long id;

    @ValueRangeProvider(id = "slotRange")
    @ProblemFactCollectionProperty
    private List<Slot> slots;
    private List<String> rooms;
    @PlanningEntityCollectionProperty
    private List<Talk> talks;

    @PlanningScore
    private HardSoftScore score;

    public ConferenceSolution() {
        String id = "Hello";
    }

    public ConferenceSolution(long id, List<Slot> slots, List<String> rooms, List<Talk> talks) {
        this.id = id;
        this.slots = slots;
        this.rooms = rooms;
        this.talks = talks;
    }

    public Long getId() {
        return id;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public HardSoftScore getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "ConferenceSolution{" +
                "score=" + score +
                ", talks=" + talks +
                '}';
    }
}
