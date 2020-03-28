package pro.sisit.javacourse;

import pro.sisit.javacourse.optimal.DeliveryTask;
import pro.sisit.javacourse.optimal.Route;
import pro.sisit.javacourse.optimal.RouteType;
import pro.sisit.javacourse.optimal.Transport;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static pro.sisit.javacourse.optimal.RouteType.Road;
import static pro.sisit.javacourse.optimal.RouteType.Sea;

public class PathFinder {

    /**
     * Возвращает оптимальный транспорт для переданной задачи.
     * Если deliveryTask равна null, то оптимальеый транспорт тоже равен null.
     * Если список transports равен null, то оптимальеый транспорт тоже равен null.
     */
    public Transport getOptimalTransport(DeliveryTask deliveryTask, List<Transport> transports) {
        Optional<DeliveryTask> deliveryTaskOptional= Optional.ofNullable(deliveryTask);
        Optional<List> transportOptional= Optional.ofNullable(transports);
        if (deliveryTaskOptional.isPresent()&transportOptional.isPresent())
        {
            ArrayList<Transport>enableTransport=new ArrayList<>();
            for (Transport transport:transports) {
                for (Route route : deliveryTask.getRoutes()) {
                    if (transport.getType() == route.getType() &
                            transport.getVolume()
                                    .compareTo(deliveryTask.getVolume()) > -1) {
                        enableTransport.add(transport);
                    }
                }
            }
            enableTransport
                    .sort((o1,o2)->o1.getVolume()
                                    .multiply(getLenght(o1,deliveryTask))
                            .compareTo(o2.getVolume()
                                    .multiply(getLenght(o2,deliveryTask))));
            return enableTransport.get(0);
        } else {return null ;}
    }
    private BigDecimal getLenght(Transport transport,DeliveryTask deliveryTask) {
        for (Route route:deliveryTask.getRoutes()){
            if (transport.getType()==route.getType()){return route.getLength();}
        }
        return null;
    }
}