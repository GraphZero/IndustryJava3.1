import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Properties;

public class PropertiesReader {
    private static Logger logger = LoggerFactory.getLogger(PropertiesReader.class);
    private Properties appProps;
    private boolean isFileFound = false;
    private int clientId;
    private int ourId;
    private int clientAge;
    private BigDecimal ticketPrice;

    public PropertiesReader() {
        this.appProps = new Properties();
        isFileFound = loadPropertiesFile();
        if ( isFileFound ){
            getClientProperties();
        }
    }

    private boolean loadPropertiesFile(){
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().replace("%20", " ");
        String appConfigPath = rootPath + "client.properties";
        try {
            appProps.load(new FileInputStream(appConfigPath));
            logger.info("Client info is found.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Couldn't find client info.");
            return false;
        }
    }

    private void getClientProperties(){
        try{
            clientId =  Integer.parseInt(appProps.getProperty("ticketPrice"));
            ourId = Integer.parseInt(appProps.getProperty("clientAge"));
            clientAge = Integer.parseInt(appProps.getProperty("clientId"));
            ticketPrice = BigDecimal.valueOf(Double.parseDouble(appProps.getProperty("ourId")));
        } catch(NumberFormatException p){
            logger.error("Couldn't parse numbers...");
            isFileFound = false;
        }

    }

    public int getClientId() {
        if ( isFileFound ) return clientId;
        return -1;
    }

    public int getOurId() {
        if ( isFileFound ) return ourId;
        return -1;
    }

    public int getClientAge() {
        if ( isFileFound ) return clientAge;
        return -1;
    }

    public BigDecimal getTicketPrice() {
        if ( isFileFound ) return ticketPrice;
        return BigDecimal.valueOf(-1);
    }

    public boolean isFileFound() {
        return isFileFound;
    }
}
