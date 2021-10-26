package com.nick.lme;

import org.junit.Test;

public class MarsTest {

    @Test(expected=IllegalArgumentException.class)
    public void testCannotUseXCoordinateMoreThanMax(){
        new Mars(1,Constants.MAX_COORDINATE+1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCannotUseYCoordinateMoreThanMax(){
        new Mars(Constants.MAX_COORDINATE+1,1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCannotHaveTooLongCommands(){
        Mars mars = new Mars(2,2);
        StringBuilder sb= new StringBuilder();
        for(int i= 0; i < 200; i++){
            sb.append("L");
        }
        mars.parseAndRunRobot("1 1 N", sb.toString(), System.out);
    }
}
