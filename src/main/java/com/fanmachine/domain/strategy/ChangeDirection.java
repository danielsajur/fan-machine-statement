package com.fanmachine.domain.strategy;

import com.fanmachine.domain.ModifiableDirection;

public class ChangeDirection implements Function {

    private final ModifiableDirection fan;

    public ChangeDirection(ModifiableDirection fan) {
        this.fan = fan;
    }

    public void execute() { fan.changeDirection(); }

}
