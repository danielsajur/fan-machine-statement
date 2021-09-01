package com.fanmachine.domain.state;

import com.fanmachine.domain.ModifiableDirection;

public class Clockwise implements Direction {

    private final ModifiableDirection fan;

    public Clockwise(ModifiableDirection fan){
        this.fan = fan;
    }

    public Direction changeDirection() {
        var direction = new CounterClockwise(fan);
        fan.setDirection(direction);

        return direction;
    }

    public String toString() { return this.getClass().getSimpleName(); }
}
