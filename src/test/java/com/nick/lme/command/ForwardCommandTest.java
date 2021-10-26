package com.nick.lme.command;

import com.nick.lme.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ForwardCommandTest {


    @Test
    public void testCommand(){
        Robot robot= new Robot(2,2, Orientation.E);
        ForwardCommand command = new ForwardCommand(noConstraints());
        command.changeRobotState(robot);
        assertEquals(3,robot.getPosition().getX());
        assertEquals(2,robot.getPosition().getY());
        assertEquals(Orientation.E,robot.getOrientation());
    }

    @Test
    public void testForwardCommandOrientation(){
        ForwardCommand command = new ForwardCommand(noConstraints());
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

    @Test
    public void testIgnoreCommandWhenOffTheGrid(){
        Robot robot= new Robot(2,2, Orientation.E);
        robot.setLost(true);
        ForwardCommand command = new ForwardCommand(noConstraints());
        command.changeRobotState(robot);
        assertEquals(2,robot.getPosition().getX());
        assertEquals(2,robot.getPosition().getY());
        assertEquals(Orientation.E,robot.getOrientation());
    }

    @Test
    public void testMoveRobotOffTheGrid(){
        Robot robot= new Robot(1,2,Orientation.N);
        Mars mars = new Mars(2,2);
        ForwardCommand command= new ForwardCommand(mars);
        command.changeRobotState(robot);
        assertTrue(robot.isLost());
    }



    @Test
    public void testMoveRobotOnTheGrid(){
        Robot robot= new Robot(1,1,Orientation.S);
        Mars mars = new Mars(2,2);
        ForwardCommand command= new ForwardCommand(mars);
        command.changeRobotState(robot);
        assertFalse(robot.isLost());
    }

    @Test
    public void testRobotDoNotMoveOnScented(){
        Robot robot1= new Robot(2,2,Orientation.N);
        Robot robot2= new Robot(2,1,Orientation.N);
        Mars mars = new Mars(2,2);
        ForwardCommand command= new ForwardCommand(mars);
        command.changeRobotState(robot1);
        // take robot 1 off grid and make position scented
        assertTrue(robot1.isLost());
        assertTrue(mars.isScented(new Position(2,2)));

        command.changeRobotState(robot2);
        // move robot 2 to scented position
        assertFalse(robot2.isLost());
        assertEquals(2,robot2.getPosition().getX());
        assertEquals(2,robot2.getPosition().getY());
        command.changeRobotState(robot2);
        // robot2 is not going off the grid and stays in the same location
        assertFalse(robot2.isLost());
        assertEquals(2,robot2.getPosition().getX());
        assertEquals(2,robot2.getPosition().getY());

        // now lets change orientation to the E, robot2 will not be able to move anyway
        new RightCommand().changeRobotState(robot2);
        command.changeRobotState(robot2);
        // robot2 is not going off the grid and stays in the same location
        assertFalse(robot2.isLost());
        assertEquals(2,robot2.getPosition().getX());
        assertEquals(2,robot2.getPosition().getY());

        // now change orientation to  S and robot2 will be able to move
        new RightCommand().changeRobotState(robot2);
        command.changeRobotState(robot2);

        assertFalse(robot2.isLost());
        assertEquals(2,robot2.getPosition().getX());
        assertEquals(1,robot2.getPosition().getY());

    }


    private IPositionConstraintsProvider noConstraints(){
        return new IPositionConstraintsProvider() {
            @Override
            public boolean isOutOfGrid(Position position) {
                return false;
            }

            @Override
            public boolean isScented(Position position) {
                return false;
            }

            @Override
            public void addScented(Position position) {

            }
        };
    }
}
