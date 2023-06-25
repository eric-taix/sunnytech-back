package io.jedar.sunnytech.domain;

import java.time.LocalDate;
import java.util.List;

public class Track {

    private String room;
    private List<Slot> slots;
    private List<String> tags;
    private LocalDate date;

    public Track() {
        this.room = null;
        this.slots = null;
        this.tags = null;
    }

    public Track(String room, List<Slot> slots, List<String> tags, LocalDate date) {
        this.room = room;
        this.slots = slots;
        this.tags = tags;
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public List<String> getTags() {
        return tags;
    }

    public Track copyWith(LocalDate date) {
        return new Track(room, slots, tags, date);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Track{" +
                "room='" + room + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        return room.equals(track.room);
    }

    @Override
    public int hashCode() {
        return room.hashCode();
    }
}
