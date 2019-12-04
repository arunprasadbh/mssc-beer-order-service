package guru.springframework.beer.order.service.services.beerservice;

/*
 * Created by arunabhamidipati on 01/11/2019
 */


import guru.springframework.beer.order.service.services.beerservice.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.Optional;


@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BeerServiceRestTemplateImpl implements BeerService {

    private final String BEER_UPC_SERVICE_PATH="/api/v1/beerUpc/";

    private final RestTemplate restTemplate;

    private  String beerServiceHost;

    public BeerServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setBeerServiceHost(String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }

    public Optional<BeerDto> getBeerByUpc(String upc, Boolean enhanceInventory) {

        //log.info("Attempting url: {}{} ", beerServiceHost + BEER_UPC_SERVICE_PATH, upc);

        return Optional.of(restTemplate.getForObject(beerServiceHost + BEER_UPC_SERVICE_PATH + upc ,BeerDto.class ));
//
//        ResponseEntity<BeerDto> response =
//                restTemplate.getForEntity(beerServiceHost + BEER_UPC_SERVICE_PATH + upc ,BeerDto.class );
//       return response.getBody() ;

    }
}
