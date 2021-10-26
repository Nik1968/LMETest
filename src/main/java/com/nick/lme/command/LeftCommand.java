package com.nick.lme.command;

import com.nick.lme.Orientation;
import com.nick.lme.Robot;

public class LeftCommand implements ICommand{
    @Override
    public void changeRobotState(Robot robot) {
       robot.setOrientation(nextOrientation(robot.getOrientation()));
    }

    public Orientation nextOrientation(Orientation current) {
        Orientation[] values = Orientation.values();
        int index= current.ordinal();
        if(index>0) {
            return values[index-1];
        }else {
            return values[values.length-1];
        }
    }

}
