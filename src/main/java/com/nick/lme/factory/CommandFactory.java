package com.nick.lme.factory;

import com.nick.lme.ICommand;
import com.nick.lme.IPositionConstraintsProvider;
import com.nick.lme.command.ForwardCommand;
import com.nick.lme.command.LeftCommand;
import com.nick.lme.command.RightCommand;

public class CommandFactory {

    private IPositionConstraintsProvider constraints;

    //prepared commands- as we can reuse them. factory can be re-implemented so commands are created each time

    private LeftCommand leftCommand;
    private RightCommand rightCommand;
    private ForwardCommand forwardCommand;

    public CommandFactory(IPositionConstraintsProvider constraints){
        this.constraints=constraints;
        this.leftCommand=new LeftCommand();
        this.rightCommand=new RightCommand();
        this.forwardCommand = new ForwardCommand(constraints);
    }

    public ICommand createCommand(String command){
        switch(command){
            case "L":
                return leftCommand;
            case "R":
                return rightCommand;
            case "F":
                return forwardCommand;
            default:
                throw new IllegalArgumentException();
        }
    }


}
