package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.option.Option;

import java.util.List;

public interface Filter {
    List<Flight> filter(List<Flight> original);

    void setOptions(List<Option> options);
}
