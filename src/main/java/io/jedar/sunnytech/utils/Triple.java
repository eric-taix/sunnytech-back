package io.jedar.sunnytech.utils;

public class Triple <L, M, R> extends Pair<L, R> {

    final M middle;

    public Triple(L left, M middle, R right) {
        super(left, right);
        this.middle = middle;
    }

    public M getMiddle() {
        return middle;
    }
}
