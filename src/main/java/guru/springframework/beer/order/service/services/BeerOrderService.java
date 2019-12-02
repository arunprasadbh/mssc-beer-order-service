package guru.springframework.beer.order.service.services;

/*
 * Created by arunabhamidipati on 02/12/2019
 */

import guru.springframework.beer.order.service.web.model.BeerOrderDto;
import guru.springframework.beer.order.service.web.model.BeerOrderPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerOrderService {
    BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

    BeerOrderDto getOrderById(UUID customerId, UUID orderId);

    BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);

    void pickupOrder(UUID customerId, UUID orderId);
}
