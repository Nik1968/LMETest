package com.nick.lme.command;

import com.nick.lme.Orientation;
import com.nick.lme.Position;
import com.nick.lme.Robot;

public class ForwardCommand implements ICommand{
    @Override
    public void changeRobotState(Robot robot) {
       robot.setPosition(nextPosition(robot.getPosition(), robot.getOrientation()));
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
