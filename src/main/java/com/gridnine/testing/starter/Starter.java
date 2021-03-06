package com.gridnine.testing.starter;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.filter.FilterImpl;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.option.Option;
import com.gridnine.testing.option.Options;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Starter {
    private static final String invite = "Введите через пробел номера желаемых опций фильтрации или stop, чтобы закончить:";

    public void start() {
        String input;

        while (!(input = waitInput()).equals("stop")) {
            List<Flight> flights = FlightBuilder.createFlights();

            Filter filter = new FilterImpl();

            filter.setOptions(createListOption(input));

            List<Flight> filteredFlights = filter.filter(flights);

            for (Flight flight : filteredFlights) {
                System.out.println(flight);
            }
        }
        System.out.println("Bye!");
    }

    private String waitInput() {
        System.out.println(invite);
        Options.availableOptions();
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        return input;
    }

    private List<Option> createListOption(String input) {
        List<Option> options = new ArrayList<>();

        String[] args = input.split(" ");

        for (String arg : args) {
            if (arg.equals("1")) {
                options.add(Options::departureBeforeNow);
            }
            if (arg.equals("2")) {
                options.add(Options::arriveBeforeDeparture);
            }
            if (arg.equals("3")) {
                options.add(Options::timeNotInSky);
            }
        }

        return options;
    }
}
