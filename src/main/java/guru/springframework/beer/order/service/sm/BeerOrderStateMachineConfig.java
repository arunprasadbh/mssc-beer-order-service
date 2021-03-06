package guru.springframework.beer.order.service.sm;

/*
 * Created by arunabhamidipati on 03/01/2020
 */

import guru.springframework.beer.order.service.domain.BeerOrderEventEnum;
import guru.springframework.beer.order.service.domain.BeerOrderStatusEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class BeerOrderStateMachineConfig extends StateMachineConfigurerAdapter<BeerOrderStatusEnum, BeerOrderEventEnum> {
    @Override
    public void configure(StateMachineStateConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> states) throws Exception {
       states.withStates()
        .initial(BeerOrderStatusEnum.NEW)
        .states(EnumSet.allOf(BeerOrderStatusEnum.class))
               .end(BeerOrderStatusEnum.VALIDATION_EXCEPTION)
               .end(BeerOrderStatusEnum.ALLOCATION_EXCEPTION)
               .end(BeerOrderStatusEnum.DELIVERY_EXCEPTION)
               .end(BeerOrderStatusEnum.DELIVERED)
                .end(BeerOrderStatusEnum.PICKED_UP);

    }
}
