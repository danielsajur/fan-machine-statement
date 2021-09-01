package com.fanmachine.domain;

import com.fanmachine.domain.state.Clockwise;
import com.fanmachine.domain.state.CounterClockwise;
import com.fanmachine.domain.state.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitPlatform.class)
public class FanMachineTest {

    private static final Integer SPEED_ONE = 1;
    private static final Integer SPEED_TWO = 2;
    private static final Integer SPEED_THREE = 3;
    private static final String SPEED_OFF = "off";

    private FanMachine fanMachine;

    @BeforeEach
    public void init(){
        fanMachine = new FanMachine();
    }

    @Test
    public void shouldIncreaseSpeedFanFrom0To1(){
        var speed = fanMachine.increaseSpeed();
        assertThat(speed).isEqualTo(valueOf(SPEED_ONE));
    }

    @Test
    public void shouldIncreaseSpeedFanFrom1To2(){
        fanMachine.increaseSpeed();
        var speed = fanMachine.increaseSpeed();
        assertThat(speed).isEqualTo(valueOf(SPEED_TWO));
    }

    @Test
    public void shouldIncreaseSpeedFanFrom2To3(){
        fanMachine.increaseSpeed();
        fanMachine.increaseSpeed();
        var speed = fanMachine.increaseSpeed();
        assertThat(speed).isEqualTo(valueOf(SPEED_THREE));
    }

    @Test
    public void shouldIncreaseSpeedFanFrom0To1AndChangeDirection(){
        var speed = fanMachine.increaseSpeed();
        Direction direction = fanMachine.changeDirection();
        assertThat(speed).isEqualTo(valueOf(SPEED_ONE));
        assertThat(direction).isExactlyInstanceOf(CounterClockwise.class);
    }

    @Test
    public void shouldIncreaseSpeedFanFrom1To2AndChangeDirection(){
        fanMachine.increaseSpeed();
        fanMachine.increaseSpeed();
        var speed = fanMachine.increaseSpeed();
        assertThat(speed).isEqualTo(valueOf(SPEED_THREE));

        Direction direction = fanMachine.changeDirection();
        assertThat(direction).isExactlyInstanceOf(CounterClockwise.class);
    }

    @Test
    public void shouldIncreaseSpeedFanFrom2To3AndChangeDirection(){

        fanMachine.increaseSpeed();
        fanMachine.increaseSpeed();
        var speed = fanMachine.increaseSpeed();

        Direction direction = fanMachine.changeDirection();

        assertThat(speed).isEqualTo(valueOf(SPEED_THREE));
        assertThat(direction).isExactlyInstanceOf(CounterClockwise.class);
    }

    @Test
    public void shouldTurnOffTheFan(){
        fanMachine.increaseSpeed();
        fanMachine.increaseSpeed();
        fanMachine.increaseSpeed();

        var speed = fanMachine.increaseSpeed();
        assertThat(speed).isEqualTo(SPEED_OFF);
    }

    @Test
    public void shouldChangeDirectionFromAirDownToUp(){
        Direction direction = fanMachine.changeDirection();
        assertThat(direction).isExactlyInstanceOf(CounterClockwise.class);
    }

    @Test
    public void shouldChangeDirectionFromAirUpToDown(){
        Direction direction = fanMachine.changeDirection();
        assertThat(direction).isExactlyInstanceOf(CounterClockwise.class);

        direction = fanMachine.changeDirection();
        assertThat(direction).isExactlyInstanceOf(Clockwise.class);
    }

}
