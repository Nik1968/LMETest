package com.nick.lme.command;

import com.nick.lme.*;

public class ForwardCommand implements ICommand {

    private IPositionConstraintsProvider constraints;

    public ForwardCommand(IPositionConstraintsProvider constraints){
        this.constraints=constraints;
    }

    @Override
    public void changeRobotState(Robot robot) {
        if(robot.isLost()) {
          return;
        }
       Position currentPosition= robot.getPosition();
       Position newPosition=nextPosition(currentPosition,robot.getOrientation());
       if(constraints.isScented(currentPosition) && constraints.isOutOfGrid(newPosition)){
         // checking if next position is out of the grid
         return;
       }

       if(constraints.isOutOfGrid(newPosition)){
          robot.setLost(true);
          constraints.addScented(currentPosition);
       }else {
           robot.setPosition(newPosition);
       }
    }

    public Position nextPosition(Position position, Orientation orientation){
        switch(orientation){
            case N:
                return new Position(position.getX(), position.getY()+1);
            case E:
                return new Position(position.getX()+1,position.getY());
            case S:
                return new Position(position.getX(), position.getY()-1);
            case W:
                return new Position(position.getX()-1, position.getY());
            default:
                throw new IllegalArgumentException();
        }
    }
}
