package fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {
    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraud(@PathVariable("customerId") Integer customerId) {
        boolean isFraud = fraudCheckService.isFraudulentCustomer(customerId);

        log.info("Checked fraud check service for customer {} : {}", customerId, isFraud);
        return new FraudCheckResponse(isFraud);
    }

}
