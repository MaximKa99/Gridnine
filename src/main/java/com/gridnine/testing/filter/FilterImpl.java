package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.option.Option;

import java.util.ArrayList;
import java.util.List;

public class FilterImpl implements Filter{
    private List<Option> options;

    public List<Flight> filter(List<Flight> original) {
        List<Flight> result = new ArrayList<>();

        for (Flight flight : original) {
            if (checkFlight(flight)) {
                result.add(flight);
            }
        }

        return result;
    }

    @Override
    public void setOptions(List<Option> options) {
        this.options = options;
    }

    private boolean checkFlight(Flight flight) {

        for (Option option : options) {
            if (!option.check(flight)) {
                return false;
            }
        }

        return true;
    }
}
