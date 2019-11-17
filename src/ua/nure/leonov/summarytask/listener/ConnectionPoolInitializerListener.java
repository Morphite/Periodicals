package ua.nure.leonov.summarytask.listener;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.pool.ConnectionPool;
import ua.nure.leonov.summarytask.pool.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * The Class Listener which listen and init Connection Pool
 */
@WebListener
public class ConnectionPoolInitializerListener implements ServletContextListener {

    /**
     * The constant logger.
     */
    private static final Logger LOG = Logger.getLogger(ConnectionPoolInitializerListener.class);

    /**
     * The static logger.
     */
    public static ConnectionPool connectionPool;

    private static final int POOL_SIZE = 50;

    /**
     * Method initialize the Connection Pool
     * @param sce the ServletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("Connection pool - listener init");
        try {
            ConnectionPool.init(POOL_SIZE);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method destroy the Connection Pool
     * @param sce the ServletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("Connection pool - listener destroy");
        try {
            connectionPool.destroy();
        } catch (ConnectionPoolException e) {
                e.printStackTrace();
        }
    }
}
