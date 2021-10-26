package com.nick.lme;

public class Robot {
  private Position position;
  private Orientation orientation;

  public Robot(int x, int y, Orientation orientation){
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


}
