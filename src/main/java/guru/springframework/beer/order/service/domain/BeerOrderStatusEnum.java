package guru.springframework.beer.order.service.domain;

/*
 * Created by arunabhamidipati on 02/12/2019
 */

public enum BeerOrderStatusEnum {
    NEW, VALIDATED, VALIDATION_EXCEPTION, ALLOCATED, ALLOCATION_EXCEPTION,
    PENDING_INVENTORY, PICKED_UP, DELIVERED, DELIVERY_EXCEPTION
}
