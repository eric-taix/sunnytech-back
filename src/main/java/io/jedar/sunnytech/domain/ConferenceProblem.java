package io.jedar.sunnytech.domain;

import java.util.Collections;
import java.util.List;

public class ConferenceProblem {

    private String name;
    private List<Day> days;
    private List<Talk> talks;


    public ConferenceProblem() {
        this.name = null;
        this.days = null;
    }
    public ConferenceProblem(String name, List<Day> days, List<Talk> talks) {
        this.name = name;
        this.days = days;
        this.talks = talks;
    }

    public String getName() {
        return name;
    }

    public List<Day> getDays() {
        return Collections.unmodifiableList(days);
    }

    public List<Talk> getTalks() {
        return talks;
    }
}
