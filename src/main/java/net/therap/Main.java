package net.therap;

import net.therap.config.PersistenceManager;
import net.therap.processor.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashrafhasan
 * @author shakhawat.hossain
 * @since 10/10/16
 */
public class Main {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.debug("\n\n\n\n");
        logger.debug("==============================" + "Start of Execution" + "=================================");

        PersistenceManager manager = new PersistenceManager();

        manager.execute(new JpqlDemoProcessor());

        manager.close();

        logger.debug("==============================" + "End of Execution" + "=================================");
    }
}
