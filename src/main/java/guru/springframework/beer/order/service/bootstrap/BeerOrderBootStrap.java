package guru.springframework.beer.order.service.bootstrap;

/*
 * Created by arunabhamidipati on 02/12/2019
 */

import guru.springframework.beer.order.service.domain.Customer;
import guru.springframework.beer.order.service.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BeerOrderBootStrap implements CommandLineRunner {
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final String TASTING_ROOM = "Tasting Room";

    private final CustomerRepository customerRepository;


    @Override
    public void run(String... args) throws Exception {
        loadCustomerData();
    }

    private void loadCustomerData() {
        if (customerRepository.count() == 0) {
            customerRepository.save(Customer.builder()
                    .apiKey(UUID.randomUUID())
                    .customerName(TASTING_ROOM)
            .build());
        }
    }
}
