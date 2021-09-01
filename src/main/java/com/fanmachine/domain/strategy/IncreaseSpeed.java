package com.fanmachine.domain.strategy;

import com.fanmachine.domain.ModifiableSpeed;

public class IncreaseSpeed  implements Function {

    private final ModifiableSpeed fan;

    public IncreaseSpeed(ModifiableSpeed fan) { this.fan = fan; }

    public void execute() {
        fan.increaseSpeed();
    }
}
