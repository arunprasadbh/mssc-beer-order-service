package guru.springframework.beer.order.service.domain;

/*
 * Created by arunabhamidipati on 03/01/2020
 */

public enum BeerOrderEventEnum {
    VALIDATE_ORDER, VALIDATION_PASSED, VALIDATION_FAILED,
    ALLOCATION_SUCCESS, ALLOCATION_NO_INVENTORY, ALLOCATION_FAILED,
    BEERORDER_PICKED_UP
}
