package com.fanmachine.domain;

import com.fanmachine.domain.state.Clockwise;
import com.fanmachine.domain.state.Direction;
import com.fanmachine.domain.strategy.ChangeDirection;
import com.fanmachine.domain.strategy.Function;
import com.fanmachine.domain.strategy.IncreaseSpeed;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FanMachine implements Fan, ModifiableDirection, ModifiableSpeed {

    private static final String SPEED_OFF = "off";
    private static final String SPEED_DIRECTION_TEXT = "Speed: {0} | Direction: {1}";
    private static final String FUNCTION_EXCEPTION_MESSAGE = "Invalid function number!";

    private static final Integer THREAD_NUMBER = 1;

    private static final Integer SPEED_ZERO = 0;
    private static final Integer SPEED_THREE = 3;
    private static final Integer FUNCTION_ONE = 1;
    private static final Integer FUNCTION_TWO = 2;

    private final ExecutorService executor;
    private final Map<Integer, Function> functions = new HashMap<>();

    private Integer speed = 0;
    private Direction direction;

    public FanMachine(){
        functions.put(FUNCTION_ONE, new IncreaseSpeed(this));
        functions.put(FUNCTION_TWO, new ChangeDirection(this));

        direction = new Clockwise(this);

        executor = Executors.newFixedThreadPool(THREAD_NUMBER);
    }

    public int start() {
        executor.submit(getStatusPrinter());
        var input = new Scanner(System.in);
        int inputTyped = 1;
        while (inputTyped != 0){
            try {
                inputTyped = input.nextInt();
                if(inputTyped == 0) break;
                getFunction(inputTyped).execute();
            } catch(InputMismatchException e){
                System.out.println(FUNCTION_EXCEPTION_MESSAGE);
                return -1;
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Finishing the program...");

        return inputTyped;
    }

    private Runnable getStatusPrinter() {
        return () -> {
            boolean canRun = true;
            while(canRun) {
                try {
                    System.out.println(MessageFormat.format(SPEED_DIRECTION_TEXT, getSpeed(), direction));
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    canRun = false;
                }
            }
        };
    }

    public String increaseSpeed(){
        speed++;
        if(speed > SPEED_THREE){
            speed = SPEED_ZERO;
        }

        return getSpeed();
    }

    public Direction changeDirection(){

        return direction.changeDirection();
    }

    public void setDirection(Direction newDirection){
        direction = newDirection;
    }

    private String getSpeed() {
        if(speed.equals(SPEED_ZERO)){
            return SPEED_OFF;
        }

        return String.valueOf(speed);
    }

    private Function getFunction(Integer functionNumber){
		return Optional.ofNullable(functions.get(functionNumber)).orElseThrow((() -> new IllegalArgumentException(FUNCTION_EXCEPTION_MESSAGE)));
    }

}
