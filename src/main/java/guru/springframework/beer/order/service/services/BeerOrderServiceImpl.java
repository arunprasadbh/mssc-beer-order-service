package guru.springframework.beer.order.service.services;

/*
 * Created by arunabhamidipati on 02/12/2019
 */

import guru.springframework.beer.order.service.domain.BeerOrder;
import guru.springframework.beer.order.service.domain.Customer;
import guru.springframework.beer.order.service.domain.OrderStatusEnum;
import guru.springframework.beer.order.service.repositories.BeerOrderRepository;
import guru.springframework.beer.order.service.repositories.CustomerRepository;
import guru.springframework.beer.order.service.web.mappers.BeerOrderMapper;
import guru.springframework.beer.order.service.web.model.BeerOrderDto;
import guru.springframework.beer.order.service.web.model.BeerOrderPagedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerOrderServiceImpl implements BeerOrderService {

    private final BeerOrderRepository beerOrderRepository;
    private final CustomerRepository customerRepository;
    private final BeerOrderMapper beerOrderMapper;
    private final ApplicationEventPublisher publisher;


    @Override
    public BeerOrderPagedList listOrders(UUID customerId, Pageable pageable) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Page<BeerOrder> beerOrderPage = beerOrderRepository.findAllByCustomer(optionalCustomer.get(), pageable);
            return new BeerOrderPagedList(beerOrderPage
                    .stream()
                    .map(beerOrderMapper::beerOrderToDto)
                    .collect(Collectors.toList()), PageRequest.of(
                            beerOrderPage.getPageable().getPageNumber(),
                    beerOrderPage.getPageable().getPageSize()),
                    beerOrderPage.getTotalElements());
        }else {
            return null;
        }
    }

    @Override
    public BeerOrderDto getOrderById(UUID customerId, UUID orderId) {
        return beerOrderMapper.beerOrderToDto(getOrder(customerId, orderId));
    }

    private BeerOrder getOrder(UUID customerId, UUID orderId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Optional<BeerOrder> beerOrderOptional = beerOrderRepository.findById(orderId);
            if (beerOrderOptional.isPresent()) {
                return (beerOrderOptional.get());
            }
            throw new RuntimeException("Beer Order is not found");
        }

        throw new RuntimeException("Customer is not found");
    }

    @Override
    public BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {

            BeerOrder beerOrder = beerOrderMapper.dtoToBeerOrder(beerOrderDto);
            beerOrder.setId(null);
            beerOrder.setCustomer(optionalCustomer.get());
            beerOrder.setOrderStatus(OrderStatusEnum.NEW);

            beerOrder.getBeerOrderLines().forEach(line -> line.setBeerOrder(beerOrder));

            BeerOrder savedOrder = beerOrderRepository.saveAndFlush(beerOrder);
            return beerOrderMapper.beerOrderToDto(savedOrder);
        }
        throw new RuntimeException("Customer not found");
    }

    @Override
    public void pickupOrder(UUID customerId, UUID orderId) {
        BeerOrder beerOrder = getOrder(customerId, orderId);
        beerOrder.setOrderStatus(OrderStatusEnum.PICKED_UP);

        beerOrderRepository.save(beerOrder);
    }
}
