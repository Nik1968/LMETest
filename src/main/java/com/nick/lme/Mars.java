package com.nick.lme;

import com.nick.lme.factory.CommandFactory;
import com.nick.lme.factory.MarsFactory;
import com.nick.lme.factory.RobotFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mars implements IPositionConstraintsProvider {

    private int xSize;
    private int ySize;
    private Set<Position> scentedPositions = new HashSet<>();
    private CommandFactory commandFactory;
    private RobotFactory robotFactory;

    public Mars(int xMaxValue, int yMaxValue){
        if (xMaxValue<0 || xMaxValue>Constants.MAX_COORDINATE) {
            throw new IllegalArgumentException("x coordinate cannot be negative or more than 50, but was " + xSize);
        }
        if (yMaxValue<0 || yMaxValue>Constants.MAX_COORDINATE) {
            throw new IllegalArgumentException("y coordinate cannot be negative or more than 50, but was " + ySize);
        }
        //maxvalues are not a size. size is maxvalue+1 as it starts from 0. i would prefer spec using size not maxvalue
        this.xSize=xMaxValue+1;
        this.ySize=yMaxValue+1;
        this.commandFactory= new CommandFactory(this);
        this.robotFactory= new RobotFactory();
    }


    public void parseAndRunRobot(String robotString, String commandString, PrintStream io){
       if (commandString.length()> Constants.MAX_STRING_LENGTH){
           throw new IllegalArgumentException("The command string " + commandString + " is too long, can be no more thant " + Constants.MAX_STRING_LENGTH + " length");
       }
       Robot robot= robotFactory.createRobot(robotString);
       for (String commandChar : commandString.split("")){
           commandFactory.createCommand(commandChar).changeRobotState(robot);
        }
       io.println(robot);
    }

    public static void runMars(List<String> arguments, PrintStream io){
        MarsFactory marsFactory=new MarsFactory();
        Mars mars = marsFactory.createMars(arguments.get(0));
        for (int i= 1;i < arguments.size()-1; i=i+2){
            String robotSpec = arguments.get(i);
            String commandSpec = arguments.get(i+1);
            mars.parseAndRunRobot(robotSpec,commandSpec,io);
        }
    }

    public static void main(String [] args) throws Exception {
        String fileName;
        List<String>input;
        if (args.length==0) {
            InputStream stream = Mars.class.
                    getResourceAsStream("/sample-input.txt");
            try (Stream<String> lines = new BufferedReader(new InputStreamReader(stream)).lines()) {
                input = lines.collect(Collectors.toList());
            }
        }
        else {
            fileName=args[0];
            input = Files.readAllLines(Paths.get(fileName));
        }
        runMars(input,System.out);
    }


    @Override
    public boolean isOutOfGrid(Position position) {
        return position.getX()>=xSize || position.getY()>=ySize;
    }

    @Override
    public boolean isScented(Position position) {
       return scentedPositions.contains(position);
    }

    @Override
    public void addScented(Position position) {
        scentedPositions.add(position);
    }
}
