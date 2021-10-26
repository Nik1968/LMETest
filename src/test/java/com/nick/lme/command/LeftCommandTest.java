package com.nick.lme.command;

import com.nick.lme.Orientation;
import com.nick.lme.Robot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeftCommandTest {

    @Test
    public void testCommand(){
        LeftCommand command= new LeftCommand();
        Robot robot= new Robot(2,2,Orientation.E);
        command.changeRobotState(robot);
        assertEquals(2,robot.getPosition().getX());
        assertEquals(2,robot.getPosition().getY());
        assertEquals(Orientation.N,robot.getOrientation());
    }

    @Test
    public void testLeftCommandOrientation(){
        LeftCommand command = new LeftCommand();
        assertEquals(Orientation.W,command.nextOrientation(Orientation.N));
        assertEquals(Orientation.S,command.nextOrientation(Orientation.W));
        assertEquals(Orientation.E,command.nextOrientation(Orientation.S));
        assertEquals(Orientation.N,command.nextOrientation(Orientation.E));
    }

    @Test
    public void testIgnoreCommandWhenOffTheGrid(){
        LeftCommand command= new LeftCommand();
        Robot robot= new Robot(2,2,Orientation.E);
        robot.setLost(true);
        command.changeRobotState(robot);
        assertEquals(2,robot.getPosition().getX());
        assertEquals(2,robot.getPosition().getY());
        assertEquals(Orientation.E,robot.getOrientation());
    }

}
