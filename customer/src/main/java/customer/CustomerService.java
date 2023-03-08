package customer;

import fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        if(!Objects.equals(customer.getEmail(), null)
                && !customer.getEmail().isBlank()) {
                Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());

                if(customerOptional.isPresent()) {
                    throw new IllegalStateException("Email is taken");
                }
            customerRepository.saveAndFlush(customer);

            //todo: check if fraudster
            FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                    "http://localhost:8081/api/v1/fraud-check/{customer-id}",
                    FraudCheckResponse.class,
                    customer.getId()

            );

            if(fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
                throw new IllegalStateException("frauster");
            }

            //todo: send notification

        }
        // todo: check if email valid
        // todo: check if email not taken
        // todo: store customer in db
    }
}
