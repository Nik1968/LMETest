package com.nick.lme.factory;

import com.nick.lme.IPositionConstraintsProvider;
import com.nick.lme.Position;
import com.nick.lme.command.ForwardCommand;
import com.nick.lme.command.LeftCommand;
import com.nick.lme.command.RightCommand;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommandFactoryTest {

    @Test
    public void testCorrectCommand(){
        CommandFactory factory = new CommandFactory(noConstraints());
        assertTrue(factory.createCommand("L") instanceof LeftCommand);
        assertTrue(factory.createCommand("R") instanceof RightCommand);
        assertTrue(factory.createCommand("F") instanceof ForwardCommand);
    }

    @Test (expected= IllegalArgumentException.class)
    public void testIncorrectCommand(){
        CommandFactory factory = new CommandFactory(noConstraints());
        factory.createCommand("Z");
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
