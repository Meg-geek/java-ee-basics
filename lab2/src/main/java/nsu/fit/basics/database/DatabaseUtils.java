package nsu.fit.basics.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseUtils {
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseUtils.class);
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static final String userName = "postgres";
    private static final String password = "root";
    private static final String URL_CONNECTION = "jdbc:postgresql://localhost:5432/test_db";
    private static final String INIT_SCRIPT_PATH = "src/main/resources/init.sql";
    private static Connection connection;
    private static boolean isDatabaseCreated = false;

    public static void initDatabase() throws ClassNotFoundException, SQLException, IOException {
        if (!isDatabaseCreated) {
            Class.forName(DRIVER_NAME);
            connection = initConnection();
            Statement initDdlStatement = connection.createStatement();
            String sqlInitStatement = Files.readString(Path.of(INIT_SCRIPT_PATH));

            initDdlStatement.execute(sqlInitStatement);
            isDatabaseCreated = true;
            LOG.info("Database created");
        }
    }

    private static Connection initConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        Connection connection = DriverManager.getConnection(URL_CONNECTION, connectionProps);
        connection.setAutoCommit(false);
        LOG.info("Connected to database");
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            LOG.info("Close database connection");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
