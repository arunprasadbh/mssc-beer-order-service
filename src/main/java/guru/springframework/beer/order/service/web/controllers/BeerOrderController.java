package guru.springframework.beer.order.service.web.controllers;

/*
 * Created by arunabhamidipati on 02/12/2019
 */

import guru.springframework.beer.order.service.services.BeerOrderService;
import guru.springframework.beer.order.service.web.model.BeerOrderDto;
import guru.springframework.beer.order.service.web.model.BeerOrderPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/customers/{customerId}/")
@RestController
public class BeerOrderController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerOrderService service;

    public BeerOrderController(BeerOrderService service) {
        this.service = service;
    }

    @GetMapping("orders")
    public BeerOrderPagedList listOrders(@PathVariable("customerId") UUID customerId,
                                         @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;

        }

        return service.listOrders(customerId, PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("orders/orderId")
    public BeerOrderDto getOrder(@PathVariable("customerId") UUID customerId,
                                 @PathVariable("orderId") UUID orderId) {
        return service.getOrderById(customerId, orderId);
    }

    @PostMapping("orders")
    @ResponseStatus(HttpStatus.CREATED)
    public BeerOrderDto placeOrder(@PathVariable("customerId") UUID customerId,
                                   @RequestBody BeerOrderDto beerOrderDto) {
        return service.placeOrder(customerId, beerOrderDto);
    }

    @PutMapping("orders/{orderId}/pickup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pickupOrder(@PathVariable("customerId") UUID customerId,
                            @PathVariable("orderId") UUID orderId) {
        service.pickupOrder(customerId, orderId);
    }
}
