package com.nick.lme;

public interface IPositionConstraintsProvider {

    public boolean isOutOfGrid(Position position);

    public boolean isScented(Position position);

    public void addScented(Position position);
}
