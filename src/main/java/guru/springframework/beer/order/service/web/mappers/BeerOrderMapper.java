package guru.springframework.beer.order.service.web.mappers;

/*
 * Created by arunabhamidipati on 02/12/2019
 */

import guru.springframework.beer.order.service.domain.BeerOrder;
import guru.springframework.beer.order.service.web.model.BeerOrderDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);

    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);


}
