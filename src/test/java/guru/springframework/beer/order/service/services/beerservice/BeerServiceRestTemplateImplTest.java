package guru.springframework.beer.order.service.services.beerservice;

import guru.springframework.beer.order.service.bootstrap.BeerOrderBootStrap;
import guru.springframework.beer.order.service.services.beerservice.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


/*
 * Created by arunabhamidipati on 04/12/2019
 */

@SpringBootTest
class BeerServiceRestTemplateImplTest {

    @Autowired
    BeerServiceRestTemplateImpl beerServiceRestTemplate;

//    @Test
//    void getBeerByUpc() {
//        Optional<BeerDto> beerDto = beerServiceRestTemplate.getBeerByUpc(BeerOrderBootStrap.BEER_1_UPC, false);
//        System.out.println("BeerDto:" + beerDto.toString());
//    }
}