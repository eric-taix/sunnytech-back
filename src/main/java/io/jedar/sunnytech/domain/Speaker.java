package io.jedar.sunnytech.domain;

import java.util.Optional;

public class Speaker {

    private final Optional<String> imageUrl;
    private final String name;

    public Speaker() {
        this.imageUrl = null;
        this.name = null;
    }

    public Speaker(Optional<String> imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public Optional<String> getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Speaker{" +
                " name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speaker speaker = (Speaker) o;

        return name != null && name.equals(speaker.name);
    }

    @Override
    public int hashCode() {
        return (name != null) ? name.hashCode() : 0;
    }
}
