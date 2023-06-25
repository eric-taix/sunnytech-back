package io.jedar.sunnytech.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.Collections;
import java.util.List;

@PlanningEntity
public class Talk {

    private final String title;
    private final List<String> tags;
    private final List<Speaker> speakers;
    private final String description;
    @PlanningId
    private long id;
    @PlanningVariable(valueRangeProviderRefs = "slotRange")
    private Slot slot;

    public Talk() {
        this.title = null;
        this.tags = null;
        this.speakers = null;
        this.description = null;
        this.slot = null;
    }

    public Talk(long id, String title, List<String> tags, List<Speaker> speakers, String description, Slot slot) {
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.speakers = speakers;
        this.description = description;
        this.slot = slot;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public String getDescription() {
        return description;
    }

    public Slot getSlot() {
        return slot;
    }

    public Talk setSlot(Slot slot) {
        this.slot = slot;
        return this;
    }

    public boolean withHoursOverlapped(Talk talk) {
        if (this.slot == null) {
            return false;
        }
        Slot otherSlot = talk.getSlot();

        assert otherSlot.end != null;
        assert slot.start != null;
        assert slot.end != null;
        assert otherSlot.start != null;
        return (!slot.start.isAfter(otherSlot.end) || (!slot.end.isAfter(otherSlot.end))) && (!slot.start.isBefore(otherSlot.start) || (!slot.end.isBefore(otherSlot.start)));
    }

    public boolean withAtLeastOneSameSpeaker(Talk talk) {
        return !Collections.disjoint(this.speakers, talk.getSpeakers());
    }

    public boolean hasSameRoom(Talk talk) {
        if (this.slot == null) {
            return false;
        }
        Slot otherSlot = talk.getSlot();
        return slot.getRoom().equals(otherSlot.getRoom());
    }

    public boolean withSameDate(Talk talk) {
        if (this.slot == null) {
            return false;
        }
        Slot otherSlot = talk.getSlot();
        return slot.getDate().equals(otherSlot.getDate());
    }

    @Override
    public String toString() {
        return "Talk{" +
                "title='" + title + '\'' +
                ", slot=" + slot +
                ", room=" + (slot != null ? slot.getRoom() : null) +
                ", speakers=" + speakers +
                '}';
    }
}
