package com.nick.lme;

import org.junit.Test;

public class RobotTest {

    @Test(expected=IllegalArgumentException.class)
    public void testCannotUseXCoordinateMoreThanMax(){
        new Robot(1,Constants.MAX_COORDINATE+1,Orientation.N);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCannotUseYCoordinateMoreThanMax(){
        new Robot(Constants.MAX_COORDINATE+1,1,Orientation.N);
    }
}
