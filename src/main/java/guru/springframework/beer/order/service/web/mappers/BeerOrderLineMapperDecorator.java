package guru.springframework.beer.order.service.web.mappers;

/*
 * Created by arunabhamidipati on 04/12/2019
 */

import guru.springframework.beer.order.service.domain.BeerOrderLine;
import guru.springframework.beer.order.service.services.beerservice.BeerService;
import guru.springframework.beer.order.service.services.beerservice.model.BeerDto;
import guru.springframework.beer.order.service.web.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {
    private BeerService beerService;
    private  BeerOrderLineMapper beerOrderLineMapper;

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Autowired
    @Qualifier("delegate")
    public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
        this.beerOrderLineMapper = beerOrderLineMapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine beerOrderLine) {
        BeerOrderLineDto orderLineDto = beerOrderLineMapper.beerOrderLineToDto(beerOrderLine);
        Optional<BeerDto> beerDtoOptional = beerService.getBeerByUpc(beerOrderLine.getUpc(), false);

        beerDtoOptional.ifPresent(beerDto -> {
            orderLineDto.setBeerName(beerDto.getBeerName());
            orderLineDto.setBeerStyle(beerDto.getBeerStyle().toString());
            orderLineDto.setPrice(beerDto.getPrice());
            orderLineDto.setBeerId(beerDto.getId());
        });
        return orderLineDto;
    }
}
