package toby.thirdspring.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Clock;

//@Component
public class PaymentService {
    private final ExRateProvider exRateProvider;
    private final Clock clock;

    public PaymentService(ExRateProvider exRateProvider, Clock clock) {
        this.exRateProvider = exRateProvider;
        this.clock = clock;
    }
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exRate = exRateProvider.getExRate(currency);
        return Payment.createPrepared(orderId, currency, foreignCurrencyAmount, exRate, LocalDateTime.now(clock));
     }
}