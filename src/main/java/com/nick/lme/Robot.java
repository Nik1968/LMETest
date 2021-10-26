package com.nick.lme;

public class Robot {
    private Position position;
    private Orientation orientation;
    private boolean lost;

    public Robot(int x, int y, Orientation orientation){
        if (x<0 || x>Constants.MAX_COORDINATE) {
            throw new IllegalArgumentException("x coordinate cannot be negative or more than 50, but was " + x);
        }
        if (y<0 || y>Constants.MAX_COORDINATE) {
            throw new IllegalArgumentException("y coordinate cannot be negative or more than 50, but was " + y);
        }

        this.position=new Position(x,y);
        this.orientation=orientation;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation(){
        return orientation;
    }

    public void setPosition(Position position){
        this.position=position;
    }

    public void setOrientation(Orientation orientation){
        this.orientation=orientation;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost){
        this.lost = lost;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getPosition().getX());
        sb.append(" ");
        sb.append(getPosition().getY());
        sb.append(" ");
        sb.append(getOrientation());
        if (isLost()) {
            sb.append(" ");
            sb.append("LOST");
        }
        return sb.toString();
    }

}
