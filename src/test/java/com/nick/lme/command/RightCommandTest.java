package com.nick.lme.command;

import com.nick.lme.Orientation;
import com.nick.lme.Robot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RightCommandTest {
    @Test
    public void testCommand(){
        RightCommand command= new RightCommand();
        Robot robot= new Robot(2,2, Orientation.E);
        command.changeRobotState(robot);
        assertEquals(2,robot.getPosition().getX());
        assertEquals(2,robot.getPosition().getY());
        assertEquals(Orientation.S,robot.getOrientation());
    }

    @Test
    public void testLeftCommandOrientation(){
        RightCommand command = new RightCommand();
        assertEquals(Orientation.E,command.nextOrientation(Orientation.N));
        assertEquals(Orientation.S,command.nextOrientation(Orientation.E));
        assertEquals(Orientation.W,command.nextOrientation(Orientation.S));
        assertEquals(Orientation.N,command.nextOrientation(Orientation.W));
    }
}
