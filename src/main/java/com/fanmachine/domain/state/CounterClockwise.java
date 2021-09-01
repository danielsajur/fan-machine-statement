package com.fanmachine.domain.state;

import com.fanmachine.domain.ModifiableDirection;

public class CounterClockwise implements Direction {

    private final ModifiableDirection fan;

    public CounterClockwise(ModifiableDirection fan){
        this.fan = fan;
    }

    public Direction changeDirection() {
        var direction = new Clockwise(fan);
        fan.setDirection(direction);
        return direction;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
