package com.gridnine.testing.filter;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.option.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FilterImplTest {
    private Filter filter;

    @BeforeEach
    public void setup() {
        this.filter = new FilterImpl();
    }

    @Test
    public void checkAllFlightPassed() {
        Option option1 = Mockito.mock(Option.class);
        Option option2 = Mockito.mock(Option.class);
        Option option3 = Mockito.mock(Option.class);

        InOrder order = Mockito.inOrder(option1, option2, option3);

        Mockito.when(option1.check(Mockito.anyObject())).thenReturn(true);
        Mockito.when(option2.check(Mockito.anyObject())).thenReturn(true);
        Mockito.when(option3.check(Mockito.anyObject())).thenReturn(true);

        List<Option> options = Arrays.asList(option1, option2, option3);

        this.filter.setOptions(options);

        List<Flight> original = FlightBuilder.createFlights();

        Assertions.assertEquals(original, this.filter.filter(original));

        int i = 0;
        while (i++ < 6){
            order.verify(option1).check(Mockito.anyObject());
            order.verify(option2).check(Mockito.anyObject());
            order.verify(option3).check(Mockito.anyObject());
        }

    }

    @Test
    public void checkAllFlightNotPassed() {
        Option option1 = Mockito.mock(Option.class);
        Option option2 = Mockito.mock(Option.class);
        Option option3 = Mockito.mock(Option.class);

        InOrder order = Mockito.inOrder(option1, option2, option3);

        Mockito.when(option1.check(Mockito.anyObject())).thenReturn(false);
        Mockito.when(option2.check(Mockito.anyObject())).thenReturn(false);
        Mockito.when(option3.check(Mockito.anyObject())).thenReturn(false);

        List<Option> options = Arrays.asList(option1, option2, option3);

        this.filter.setOptions(options);

        List<Flight> original = FlightBuilder.createFlights();

        List<Flight> expected = new ArrayList<>();

        Assertions.assertEquals(expected, this.filter.filter(original));

        order.verify(option1, Mockito.times(6)).check(Mockito.anyObject());

    }

}