package io.jedar.sunnytech.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Day {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    final LocalDate date;
    final List<Track> tracks;

    public Day() {
        this.date = null;
        this.tracks = null;
    }

    public Day(LocalDate date, List<Track> tracks) {
        this.date = date;
        this.tracks = tracks;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Track> getTracks() {
        return Collections.unmodifiableList(tracks);
    }
}
