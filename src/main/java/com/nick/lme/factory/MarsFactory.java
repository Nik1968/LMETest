package com.nick.lme.factory;

import com.nick.lme.Mars;
import com.nick.lme.Orientation;
import com.nick.lme.Robot;

public class MarsFactory {
    public Mars createMars(String specification){
        if (specification.length()>100){
            throw new IllegalArgumentException("String " + specification + " is too long, must be less then 100 characters");
        }
        String [] arguments= specification.split(" ");
        if(arguments.length!=2) {
            throw new IllegalArgumentException("2 values : x, y must be specified in the string " + specification);
        }
        if (!arguments[0].matches("\\d+")){
            throw new IllegalArgumentException(" value "+ arguments[0]+ " should match integer");
        }
        if (!arguments[1].matches("\\d+")){
            throw new IllegalArgumentException(" value "+ arguments[1]+ " should match integer");
        }
        return new Mars(Integer.parseInt(arguments[0]),Integer.parseInt(arguments[1]));
    }
}
