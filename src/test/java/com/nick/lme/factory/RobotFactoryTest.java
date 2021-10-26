package com.nick.lme.factory;

import com.nick.lme.Orientation;
import com.nick.lme.Robot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RobotFactoryTest {

    @Test
    public void testCreateRobotCorrectly(){
        RobotFactory factory = new RobotFactory();
        String specification = "1 5 N";
        Robot robot = factory.createRobot(specification);

        assertEquals(1,robot.getPosition().getX());
        assertEquals(5,robot.getPosition().getY());
        assertEquals(Orientation.N,robot.getOrientation());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSpecificationFormatWrong(){
        RobotFactory factory = new RobotFactory();
        String specification = "QWERTY";
        factory.createRobot(specification);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNonExistingOrientationSpecified(){
        RobotFactory factory = new RobotFactory();
        String specification = "1 5 Z";
        factory.createRobot(specification);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNonIntegerCoordinatesSpecified(){
        RobotFactory factory = new RobotFactory();
        String specification = "1 A N";
        factory.createRobot(specification);
    }


}
