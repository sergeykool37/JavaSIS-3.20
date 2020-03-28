package pro.sisit.javacourse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pro.sisit.javacourse.PathFinder;
import pro.sisit.javacourse.optimal.DeliveryTask;
import pro.sisit.javacourse.optimal.Route;
import pro.sisit.javacourse.optimal.RouteType;
import pro.sisit.javacourse.optimal.Transport;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pro.sisit.javacourse.TestData.*;
import static pro.sisit.javacourse.optimal.RouteType.Road;
import static pro.sisit.javacourse.optimal.RouteType.Sea;


public class TestPathFinder {

    private List<Transport> getAvailableTransport() {
        return Arrays.asList(GAZelle, Plane, Tanker, Train, SemiTrailerTruck, Submarine, BelAZ);
    }

    public PathFinder getPathFinder() {
        return new PathFinder();
    }

    @Test
    public void testNullDeliveryTask() {
        PathFinder pathFinder = getPathFinder();
        Transport optimalTransport = pathFinder.getOptimalTransport(null, getAvailableTransport());
        Assertions.assertNull(optimalTransport);
    }

    @Test
    public void testNullTransports() {
        PathFinder pathFinder = getPathFinder();
        Transport optimalTransport = pathFinder.getOptimalTransport(KingKongDelivery, null);
        Assertions.assertNull(optimalTransport);
    }


    private BigDecimal getLenght(Transport transport,DeliveryTask deliveryTask) {
        for (Route route:deliveryTask.getRoutes()){
            if (transport.getType()==route.getType()){return route.getLength();}
        }
        return null;
    }


    @ParameterizedTest
    @ArgumentsSource(TestGetOptimalTransportArguments.class)
    void testGetOptimalTransport(DeliveryTask deliveryTask, Transport expectedTransport) {
        PathFinder pathFinder = getPathFinder();
        Transport optimalTransport = pathFinder.getOptimalTransport(deliveryTask, getAvailableTransport());
        System.out.println("actual: " + optimalTransport);
        System.out.println("expected: " + expectedTransport);
        Assertions.assertEquals(expectedTransport, optimalTransport);
    }

    static class TestGetOptimalTransportArguments implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    new Object[]{ApplesDelivery, SemiTrailerTruck},
                    new Object[]{SmartphoneDelivery, Plane},
                    new Object[]{CarsDelivery, Train},
                    new Object[]{BreadDelivery, GAZelle},
                    new Object[]{KingKongDelivery, Tanker},
                    new Object[]{NuclearWeaponDelivery, Submarine}
            ).map(Arguments::of);
        }
    }
}
