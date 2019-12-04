package guru.springframework.beer.order.service.services.beerservice;

/*
 * Created by arunabhamidipati on 01/11/2019
 */

import guru.springframework.beer.order.service.services.beerservice.model.BeerDto;
import guru.springframework.beer.order.service.services.beerservice.model.BeerPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    /*
    BeerDto getBeerById(UUID beerId, Boolean enhanceInventory);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, String beerStyle, PageRequest of, Boolean enhanceInventory);

    List<BeerDto> listAllBeers();
     */

    Optional<BeerDto> getBeerByUpc(String upc, Boolean enhanceInventory);
}