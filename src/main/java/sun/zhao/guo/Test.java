package sun.zhao.guo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {

    private final static Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        String test = "${java:hw}";
        logger.info("log4j =  {}", test);
    }

}
