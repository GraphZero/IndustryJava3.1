import com.external.PaymentsService;
import com.internal.DiscountCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(PropertiesReader.class);

    private static BigDecimal calculateDiscount(BigDecimal ticketPrice, int customerAge ){
        DiscountCalculator discountCalculator = new DiscountCalculator();
        return discountCalculator.calculateDiscount(ticketPrice, customerAge);
    }

    private static void makeTransaction(PropertiesReader propertiesReader){
        PaymentsService paymentsService = new PaymentsService();
        BigDecimal discount = calculateDiscount(propertiesReader.getTicketPrice(), propertiesReader.getClientAge());
        paymentsService.makePayment(propertiesReader.getClientId(),
                propertiesReader.getOurId(),
                propertiesReader.getTicketPrice().subtract(discount) );
    }

    public static void main(String[] args) {
        PropertiesReader propertiesReader = new PropertiesReader();
        if ( propertiesReader.isFileFound() ){
            makeTransaction(propertiesReader);
            logger.info("Transaction was made!");
        } else{
            logger.info("Client info couldn't be found, sorry. ");
        }
    }

}
