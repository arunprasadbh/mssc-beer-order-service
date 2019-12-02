package guru.springframework.beer.order.service.web.model;

/*
 * Created by arunabhamidipati on 02/12/2019
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends BaseItem{

    public CustomerDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                       String name) {
        super(id, version, createdDate, lastModifiedDate);
        this.name = name;
    }

    private String name;
}
