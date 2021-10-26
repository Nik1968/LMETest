package com.nick.lme.command;

import com.nick.lme.Orientation;
import com.nick.lme.Position;
import com.nick.lme.Robot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ForwardCommandTest {
    @Test
    public void testCommand(){
        ForwardCommand command= new ForwardCommand();
        Robot robot= new Robot(2,2, Orientation.E);
        command.changeRobotState(robot);
        assertEquals(3,robot.getPosition().getX());
        assertEquals(2,robot.getPosition().getY());
        assertEquals(Orientation.E,robot.getOrientation());
    }

    @Test
    public void testForwardCommandOrientation(){
        ForwardCommand command = new ForwardCommand();
        Position position = new Position(2,2);
        assertEquals(1,command.nextPosition(position,Orientation.W).getX());
        assertEquals(2,command.nextPosition(position,Orientation.W).getY());
        assertEquals(2,command.nextPosition(position,Orientation.N).getX());
        assertEquals(3,command.nextPosition(position,Orientation.N).getY());
        assertEquals(3,command.nextPosition(position,Orientation.E).getX());
        assertEquals(2,command.nextPosition(position,Orientation.E).getY());
        assertEquals(2,command.nextPosition(position,Orientation.S).getX());
        assertEquals(1,command.nextPosition(position,Orientation.S).getY());

    }
}
