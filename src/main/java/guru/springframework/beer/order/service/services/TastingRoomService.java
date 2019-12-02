package guru.springframework.beer.order.service.services;

/*
 * Created by arunabhamidipati on 02/12/2019
 */

import guru.springframework.beer.order.service.bootstrap.BeerOrderBootStrap;
import guru.springframework.beer.order.service.domain.Customer;
import guru.springframework.beer.order.service.repositories.BeerOrderRepository;
import guru.springframework.beer.order.service.repositories.CustomerRepository;
import guru.springframework.beer.order.service.web.controllers.BeerOrderController;
import guru.springframework.beer.order.service.web.model.BeerOrderDto;
import guru.springframework.beer.order.service.web.model.BeerOrderLineDto;
import guru.springframework.beer.order.service.web.model.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class TastingRoomService {

    private final CustomerRepository customerRepository;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderService beerOrderService;
    private final BeerOrderController beerOrderController;



    private final List<String> beerUpcs = new ArrayList<>(3);

    public TastingRoomService(CustomerRepository customerRepository, BeerOrderRepository beerOrderRepository, BeerOrderService beerOrderService, BeerOrderController beerOrderController) {
        this.customerRepository = customerRepository;
        this.beerOrderRepository = beerOrderRepository;
        this.beerOrderService = beerOrderService;
        this.beerOrderController = beerOrderController;

        beerUpcs.add(BeerOrderBootStrap.BEER_1_UPC);
        beerUpcs.add(BeerOrderBootStrap.BEER_2_UPC);
        beerUpcs.add(BeerOrderBootStrap.BEER_3_UPC);
    }

    @Transactional
    @Scheduled(fixedRate = 2000)
    public void placeTastingRoomOrder(){
        List<Customer> customerList = customerRepository.findAllByCustomerNameLike(BeerOrderBootStrap.TASTING_ROOM);

        if (customerList.size() == 1) {
            doPlaceOrder2(customerList.get(0));
        }else{
            log.error("Too many or too few customers found");
        }

    }


    private void doPlaceOrder(Customer customer) {
        String beerToOrder = getRandomBeerUpc();

        log.info("Placing a new Order");
        BeerOrderLineDto beerOrderLineDto = BeerOrderLineDto.builder()
                .upc(beerToOrder)
                .orderQuantity(new Random().nextInt(6))
                .build();
        List<BeerOrderLineDto> beerOrderLineSet = new ArrayList<>();
        beerOrderLineSet.add(beerOrderLineDto);

        BeerOrderDto beerOrderDto = BeerOrderDto.builder()
                .beerOrderLines(beerOrderLineSet)
                .orderStatus(OrderStatusEnum.NEW)
                .customerId(customer.getId())
                .customerRef(customer.getCustomerName())
                .build();
        BeerOrderDto savedBeerOrderDto = beerOrderService.placeOrder(customer.getId(), beerOrderDto);
    }

    private void doPlaceOrder2(Customer customer) {
        String beerToOrder = getRandomBeerUpc();

        log.info("Placing a new Order");
        BeerOrderLineDto beerOrderLineDto = BeerOrderLineDto.builder()
                .upc(beerToOrder)
                .orderQuantity(new Random().nextInt(6))
                .build();
        List<BeerOrderLineDto> beerOrderLineSet = new ArrayList<>();
        beerOrderLineSet.add(beerOrderLineDto);

        BeerOrderDto beerOrderDto = BeerOrderDto.builder()
                .beerOrderLines(beerOrderLineSet)
                .orderStatus(OrderStatusEnum.NEW)
                .customerId(customer.getId())
                .customerRef(customer.getCustomerName())
                .build();
        BeerOrderDto savedBeerOrderDto = beerOrderController.placeOrder(customer.getId(), beerOrderDto);
    }

    private String getRandomBeerUpc() {
        return beerUpcs.get(new Random().nextInt(beerUpcs.size()-1));
    }
}
