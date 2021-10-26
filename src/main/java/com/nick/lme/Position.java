package com.nick.lme;

import java.util.Objects;

public final class Position {
    private final int x;
    private final int y;

    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Position))
            return false;
        Position other = (Position)o;
        return this.x==other.x && this.y==other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
  }
