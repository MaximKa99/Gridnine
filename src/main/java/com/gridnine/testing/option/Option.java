package com.gridnine.testing.option;

import com.gridnine.testing.model.Flight;

@FunctionalInterface
public interface Option {
    boolean check(Flight flight);
}
