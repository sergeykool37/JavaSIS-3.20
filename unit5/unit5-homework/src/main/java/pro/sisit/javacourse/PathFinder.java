package pro.sisit.javacourse;

import pro.sisit.javacourse.optimal.DeliveryTask;
import pro.sisit.javacourse.optimal.RouteType;
import pro.sisit.javacourse.optimal.Transport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;
import static java.util.Comparator.comparing;

public class PathFinder {

    /**
     * Возвращает оптимальный транспорт для переданной задачи.
     * Если deliveryTask равна null, то оптимальеый транспорт тоже равен null.
     * Если список transports равен null, то оптимальеый транспорт тоже равен null.
     */

    public Transport getOptimalTransport( DeliveryTask deliveryTask, List<Transport> transports) {
       Optional<DeliveryTask> deliveryTaskOptional= Optional.ofNullable(deliveryTask);
       Optional<List> transportOptional = Optional.ofNullable(transports);
       if (deliveryTaskOptional.isPresent()&transportOptional.isPresent())
        {
            ArrayList<Transport> enableTransport=(transports
                    .stream()
                    .filter((transport)-> transport==filterTransport(transport,deliveryTask))
                    .collect(Collectors.toCollection(()->new ArrayList<>())));
            enableTransport
                    .sort(comparing((Transport o) -> o.getVolume()
                            .multiply(getLenght(o, deliveryTask))));
            return enableTransport.get(0);
        } else {return null ;}
    }

        private BigDecimal getLenght(Transport transport,DeliveryTask deliveryTask) {
            return deliveryTask.getRoutes()
                    .stream()
                    .filter(route -> route.getType()==transport.getType())
                    .findAny().orElse(null).getLength();
        }
        private Transport filterTransport(Transport transport,DeliveryTask deliveryTask) {
            boolean result=deliveryTask.getRoutes()
                    .stream()
                    .anyMatch(route -> transport.getType() == route.getType() &
                            transport.getVolume().compareTo(deliveryTask.getVolume()) > -1);
            if (result){return transport;}
            else {return null;}
        }
}