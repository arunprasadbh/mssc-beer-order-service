package guru.springframework.beer.order.service.web.model;

/*
 * Created by arunabhamidipati on 02/12/2019
 */

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerOrderPagedList extends PageImpl<BeerOrderDto> {
    public BeerOrderPagedList(List<BeerOrderDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerOrderPagedList(List<BeerOrderDto> content) {
        super(content);
    }
}
