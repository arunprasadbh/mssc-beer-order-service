package guru.springframework.beer.order.service.web.mappers;

import guru.springframework.beer.order.service.domain.BeerOrderLine;
import guru.springframework.beer.order.service.domain.BeerOrderLine.BeerOrderLineBuilder;
import guru.springframework.beer.order.service.web.model.BeerOrderLineDto;
import guru.springframework.beer.order.service.web.model.BeerOrderLineDto.BeerOrderLineDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-04T10:17:00+0000",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class BeerOrderLineMapperImpl implements BeerOrderLineMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine beerOrderLine) {
        if ( beerOrderLine == null ) {
            return null;
        }

        BeerOrderLineDtoBuilder beerOrderLineDto = BeerOrderLineDto.builder();

        beerOrderLineDto.id( beerOrderLine.getId() );
        beerOrderLineDto.version( beerOrderLine.getVersion() );
        beerOrderLineDto.createdDate( dateMapper.asOffsetDateTime( beerOrderLine.getCreatedDate() ) );
        beerOrderLineDto.lastModifiedDate( dateMapper.asOffsetDateTime( beerOrderLine.getLastModifiedDate() ) );
        beerOrderLineDto.upc( beerOrderLine.getUpc() );
        beerOrderLineDto.beerId( beerOrderLine.getBeerId() );
        beerOrderLineDto.orderQuantity( beerOrderLine.getOrderQuantity() );

        return beerOrderLineDto.build();
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        if ( dto == null ) {
            return null;
        }

        BeerOrderLineBuilder beerOrderLine = BeerOrderLine.builder();

        beerOrderLine.version( dto.getVersion() );
        beerOrderLine.createdDate( dateMapper.asTimestamp( dto.getCreatedDate() ) );
        beerOrderLine.lastModifiedDate( dateMapper.asTimestamp( dto.getLastModifiedDate() ) );
        beerOrderLine.beerId( dto.getBeerId() );
        beerOrderLine.upc( dto.getUpc() );
        beerOrderLine.orderQuantity( dto.getOrderQuantity() );

        return beerOrderLine.build();
    }
}
