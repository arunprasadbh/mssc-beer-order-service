package guru.springframework.beer.order.service.web.mappers;

import guru.springframework.beer.order.service.domain.BeerOrder;
import guru.springframework.beer.order.service.domain.BeerOrder.BeerOrderBuilder;
import guru.springframework.beer.order.service.domain.BeerOrderLine;
import guru.springframework.beer.order.service.domain.BeerOrderStatusEnum;
import guru.springframework.beer.order.service.domain.Customer;
import guru.springframework.beer.order.service.web.model.BeerOrderDto;
import guru.springframework.beer.order.service.web.model.BeerOrderDto.BeerOrderDtoBuilder;
import guru.springframework.beer.order.service.web.model.BeerOrderLineDto;
import guru.springframework.beer.order.service.web.model.OrderStatusEnum;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-03T17:10:26+0000",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class BeerOrderMapperImpl implements BeerOrderMapper {

    @Autowired
    private DateMapper dateMapper;
    @Autowired
    private BeerOrderLineMapper beerOrderLineMapper;

    @Override
    public BeerOrder dtoToBeerOrder(BeerOrderDto dto) {
        if ( dto == null ) {
            return null;
        }

        BeerOrderBuilder beerOrder = BeerOrder.builder();

        beerOrder.id( dto.getId() );
        beerOrder.version( dto.getVersion() );
        beerOrder.createdDate( dateMapper.asTimestamp( dto.getCreatedDate() ) );
        beerOrder.lastModifiedDate( dateMapper.asTimestamp( dto.getLastModifiedDate() ) );
        beerOrder.customerRef( dto.getCustomerRef() );
        beerOrder.beerOrderLines( beerOrderLineDtoListToBeerOrderLineSet( dto.getBeerOrderLines() ) );
        beerOrder.orderStatus( orderStatusEnumToOrderStatusEnum( dto.getOrderStatus() ) );

        return beerOrder.build();
    }

    @Override
    public BeerOrderDto beerOrderToDto(BeerOrder beerOrder) {
        if ( beerOrder == null ) {
            return null;
        }

        BeerOrderDtoBuilder beerOrderDto = BeerOrderDto.builder();

        beerOrderDto.customerId( beerOrderCustomerId( beerOrder ) );
        beerOrderDto.id( beerOrder.getId() );
        beerOrderDto.version( beerOrder.getVersion() );
        beerOrderDto.createdDate( dateMapper.asOffsetDateTime( beerOrder.getCreatedDate() ) );
        beerOrderDto.lastModifiedDate( dateMapper.asOffsetDateTime( beerOrder.getLastModifiedDate() ) );
        beerOrderDto.customerRef( beerOrder.getCustomerRef() );
        beerOrderDto.beerOrderLines( beerOrderLineSetToBeerOrderLineDtoList( beerOrder.getBeerOrderLines() ) );
        beerOrderDto.orderStatus( orderStatusEnumToOrderStatusEnum1( beerOrder.getOrderStatus() ) );

        return beerOrderDto.build();
    }

    protected Set<BeerOrderLine> beerOrderLineDtoListToBeerOrderLineSet(List<BeerOrderLineDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<BeerOrderLine> set = new HashSet<BeerOrderLine>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( BeerOrderLineDto beerOrderLineDto : list ) {
            set.add( beerOrderLineMapper.dtoToBeerOrderLine( beerOrderLineDto ) );
        }

        return set;
    }

    protected BeerOrderStatusEnum orderStatusEnumToOrderStatusEnum(OrderStatusEnum orderStatusEnum) {
        if ( orderStatusEnum == null ) {
            return null;
        }

        BeerOrderStatusEnum beerOrderStatusEnum1;

        switch ( orderStatusEnum ) {
            case NEW: beerOrderStatusEnum1 = BeerOrderStatusEnum.NEW;
            break;
            case READY: beerOrderStatusEnum1 = BeerOrderStatusEnum.READY;
            break;
            case PICKED_UP: beerOrderStatusEnum1 = BeerOrderStatusEnum.PICKED_UP;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + orderStatusEnum );
        }

        return beerOrderStatusEnum1;
    }

    private UUID beerOrderCustomerId(BeerOrder beerOrder) {
        if ( beerOrder == null ) {
            return null;
        }
        Customer customer = beerOrder.getCustomer();
        if ( customer == null ) {
            return null;
        }
        UUID id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<BeerOrderLineDto> beerOrderLineSetToBeerOrderLineDtoList(Set<BeerOrderLine> set) {
        if ( set == null ) {
            return null;
        }

        List<BeerOrderLineDto> list = new ArrayList<BeerOrderLineDto>( set.size() );
        for ( BeerOrderLine beerOrderLine : set ) {
            list.add( beerOrderLineMapper.beerOrderLineToDto( beerOrderLine ) );
        }

        return list;
    }

    protected OrderStatusEnum orderStatusEnumToOrderStatusEnum1(BeerOrderStatusEnum beerOrderStatusEnum) {
        if ( beerOrderStatusEnum == null ) {
            return null;
        }

        OrderStatusEnum orderStatusEnum1;

        switch (beerOrderStatusEnum) {
            case NEW: orderStatusEnum1 = OrderStatusEnum.NEW;
            break;
            case READY: orderStatusEnum1 = OrderStatusEnum.READY;
            break;
            case PICKED_UP: orderStatusEnum1 = OrderStatusEnum.PICKED_UP;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + beerOrderStatusEnum);
        }

        return orderStatusEnum1;
    }
}
