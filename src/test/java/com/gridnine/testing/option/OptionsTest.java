package com.gridnine.testing.option;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class OptionsTest {

    @Test
    public void checkDepartureBeforeNow_fail() {
        List<Segment> segments = Arrays.asList(new Segment(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1)));
        Flight flight = new Flight(segments);

        Assertions.assertFalse(Options.departureBeforeNow(flight));
    }

    @Test
    public void checkDepartureBeforeNow_success() {
        List<Segment> segments = Arrays.asList(new Segment(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2)));
        Flight flight = new Flight(segments);

        Assertions.assertTrue(Options.departureBeforeNow(flight));
    }

    @Test
    public void checkArriveBeforeDeparture_fail() {
        List<Segment> segments = Arrays.asList(new Segment(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusHours(1)));
        Flight flight = new Flight(segments);

        Assertions.assertFalse(Options.arriveBeforeDeparture(flight));
    }

    @Test
    public void checkArriveBeforeDeparture_success() {
        List<Segment> segments = Arrays.asList(new Segment(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(3)));
        Flight flight = new Flight(segments);

        Assertions.assertTrue(Options.arriveBeforeDeparture(flight));
    }

    @Test
    public void checkTimeNotInSky_fail() {
        List<Segment> segments = Arrays.asList(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(6)));
        Flight flight = new Flight(segments);

        Assertions.assertFalse(Options.timeNotInSky(flight));
    }

    @Test
    public void checkTimeNotInSky_success() {
        List<Segment> segments = Arrays.asList(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(6)));
        Flight flight = new Flight(segments);

        Assertions.assertTrue(Options.timeNotInSky(flight));
    }

    @Test
    public void checkTimeNotInSkySingleSegment_success() {
        List<Segment> segments = Arrays.asList(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)));
        Flight flight = new Flight(segments);

        Assertions.assertTrue(Options.timeNotInSky(flight));
    }
}