package configuration.jdbc;

import com.project.ppp.servlets.AddUserServlet;
import configuration.ConnectionSupplier;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionSupplierJdbc implements ConnectionSupplier {
    private static final Logger LOGGER = Logger.getLogger(ConnectionSupplierJdbc.class.getName());

    private final BasicDataSource dataSource;

    public ConnectionSupplierJdbc() throws IOException {
        Properties config = new Properties();
        config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties"));
        dataSource = new BasicDataSource();
        try {
            Class.forName(config.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        dataSource.setUrl(config.getProperty("url"));
        dataSource.setUsername(config.getProperty("username"));
        dataSource.setPassword(config.getProperty("password"));

        // the settings below are optional -- dbcp can work with defaults
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(20);
        dataSource.setMaxOpenPreparedStatements(180);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
    public DataSource getDataSource() {
        return this.dataSource;
    }
}
