package guru.springframework.beer.order.service.repositories;

/*
 * Created by arunabhamidipati on 02/12/2019
 */

import guru.springframework.beer.order.service.domain.BeerOrderLine;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderLine, UUID> {
}
