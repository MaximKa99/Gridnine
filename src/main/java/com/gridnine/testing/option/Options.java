package com.gridnine.testing.option;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Options {

    public static void availableOptions() {
        int i = 1;

        Method[] methods = Options.class.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Optionable.class)) {
                String description = method.getAnnotation(Optionable.class).description();
                String option = String.format("[%d] %s", i, description);
                System.out.println(option);
                i++;
            }
        }
    }

    @Optionable(description = "Проверить, что вылет до текущего момента времени")
    public static boolean departureBeforeNow(Flight flight) {
        List<Segment> segments = flight.getSegments();
        for (Segment segment : segments) {
            if (segment.getDepartureDate().compareTo(LocalDateTime.now()) < 0) {
                return false;
            }
        }
        return true;
    }

    @Optionable(description = "Проверить, что имеются сегменты с датой прилёта раньше даты вылета")
    public static boolean ArriveBeforeDeparture(Flight flight) {
        List<Segment> segments = flight.getSegments();
        for (Segment segment : segments) {
            if (segment.getArrivalDate().compareTo(segment.getDepartureDate()) < 0) {
                return false;
            }
        }

        return true;
    }

    @Optionable(description = "Проверить, что общее время, проведённое на земле превышает два часа")
    public static boolean timeNotInSky(Flight flight) {
        List<Segment> segments = flight.getSegments();

        LocalDateTime firstArrival = segments.get(0).getArrivalDate();
        int i = 1;
        while (i < segments.size()) {
            Segment segment = segments.get(i);
            if (ChronoUnit.HOURS.between(firstArrival.toLocalTime(), segment.getDepartureDate().toLocalTime()) > 2) {
                return false;
            }
            i++;
        }

        return true;
    }
}
