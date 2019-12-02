package guru.springframework.beer.order.service.web.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/*
 * Created by arunabhamidipati on 02/12/2019
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class BeerOrderDto extends BaseItem{


    @Builder
    public BeerOrderDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                        UUID customerId, String customerRef, List<BeerOrderLineDto> beerOrderLines,
                        OrderStatusEnum orderStatus, String orderStatusCallBackUrl) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerId = customerId;
        this.customerRef = customerRef;
        this.beerOrderLines = beerOrderLines;
        this.orderStatus = orderStatus;
        this.orderStatusCallBackUrl = orderStatusCallBackUrl;
    }

    private UUID customerId;

    private String customerRef;

    private List<BeerOrderLineDto> beerOrderLines;

    private OrderStatusEnum orderStatus;

    private String orderStatusCallBackUrl;

}
