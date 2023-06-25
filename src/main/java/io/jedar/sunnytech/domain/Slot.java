package io.jedar.sunnytech.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class Slot {

    final String id;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    final LocalTime start;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    final LocalTime end;
    final List<String> tags;
    final SlotType type;
    final String room;
    final LocalDate date;
    final Integer size;

    public Slot() {
        this.id = null;
        this.start = null;
        this.end = null;
        this.tags = null;
        this.type = null;
        this.room = null;
        this.date = null;
        this.size = null;
    }

    public Slot(String id, LocalTime start, LocalTime end, List<String> tags, SlotType type, String room, LocalDate date, Integer size) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.tags = tags;
        this.type = type;
        this.room = room;
        this.date = date;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public Integer getSize() {
        return size;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public List<String> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public SlotType getType() {
        return type;
    }

    public String getRoom() {
        return room;
    }
    public LocalDate getDate() {
        return date;
    }

    public Slot copyWith(String room, LocalDate date) {
        return new Slot(id, start, end, tags, type, room, date, size);
    }

    @Override
    public String toString() {
        return "Slot{" +
                "start=" + start +
                ", end=" + end +
                ", room=" + room +
                ", tags=" + tags +
                ", type=" + type +
                '}';
    }
}
