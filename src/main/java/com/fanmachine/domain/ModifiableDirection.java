package com.fanmachine.domain;

import com.fanmachine.domain.state.Direction;

public interface ModifiableDirection {

    Direction changeDirection();

    void setDirection(Direction newDirection);
}
