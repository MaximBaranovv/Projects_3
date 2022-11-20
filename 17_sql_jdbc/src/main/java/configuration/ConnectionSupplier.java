package configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionSupplier {

    Connection getConnection() throws SQLException;

    DataSource getDataSource();
}
