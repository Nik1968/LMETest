package com.nick.lme.command;

import com.nick.lme.ICommand;
import com.nick.lme.Orientation;
import com.nick.lme.Robot;

public class RightCommand implements ICommand {
    @Override
    public void changeRobotState(Robot robot) {
        if (robot.isLost()) {
            return;
        }
        robot.setOrientation(nextOrientation(robot.getOrientation()));
    }

    public Orientation nextOrientation(Orientation current) {
        Orientation[] values = Orientation.values();
        int index= current.ordinal();
        if(index>=values.length-1) {
            return values[0];
        }else {
            return values[index+1];
        }
    }
}
