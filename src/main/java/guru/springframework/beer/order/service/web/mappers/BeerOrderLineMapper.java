package guru.springframework.beer.order.service.web.mappers;

/*
 * Created by arunabhamidipati on 02/12/2019
 */

import guru.springframework.beer.order.service.domain.BeerOrderLine;
import guru.springframework.beer.order.service.web.model.BeerOrderLineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {

    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine beerOrderLine);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
