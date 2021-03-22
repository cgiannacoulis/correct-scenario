package org.example.database;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.h2.tools.Server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.exit;

public class DbDemo {
	private static final String DB_URL = "jdbc:h2:mem:sample";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";
	private final Properties sqlCommands = new Properties();
	private final Lorem generator = LoremIpsum.getInstance();

	private Server h2Server;

	public static void main(String[] args) throws InterruptedException {
		DbDemo demo = new DbDemo();
		// Load SQL commands
		demo.loadSqlCommands();

		// Start H2 database server
		demo.startH2Server();
		// Register JDBC driver and retrieve a connection
		Connection connection = demo.registerDatabaseDriver();
		// Create table
		demo.createTable(connection);
		// Insert data
		demo.insertData(connection);
		// Insert data in batch mode
		demo.batchInsertData(connection);
		// Read data
		demo.selectData(connection);

		// Update data
		demo.updateData(connection);
		// Read data
		demo.selectData(connection);

		// Delete data
		demo.deleteData(connection);
		// Read data
		demo.selectData(connection);

		// Stop H2 database server
		demo.stopH2Server();
	}

	private void loadSqlCommands() {
		try (InputStream inputStream = DbDemo.class.getClassLoader().getResourceAsStream("sql.properties")) {
			if (inputStream == null) {
				System.err.println("Unable to load SQL commands");
				exit(-1);
			}
			sqlCommands.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startH2Server() {
		try {
			h2Server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
			h2Server.start();
			System.out.println("H2 Database server is now accepting connections.");
		} catch (SQLException throwables) {
			System.err.println("Unable to start H2 database server.");
			throwables.printStackTrace();
			exit(-1);
		}
	}

	private Connection registerDatabaseDriver() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException throwables) {
			System.err.println("Unable to get a connection from H2 database server.");
			throwables.printStackTrace();
			exit(-1);
		}
		return connection;
	}

	private void createTable(Connection connection) {
		try (Statement statement = connection.createStatement()) {
			int resultRows = statement.executeUpdate(sqlCommands.getProperty("create.table.001"));

			System.out.println("Statement returned " + resultRows);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	private void insertData(Connection connection) {
		try (Statement statement = connection.createStatement()) {
			int resultRows = statement.executeUpdate(sqlCommands.getProperty("insert.table.001"));
			System.out.println("Statement returned " + resultRows);
			resultRows = statement.executeUpdate(sqlCommands.getProperty("insert.table.002"));
			System.out.println("Statement returned " + resultRows);
			resultRows = statement.executeUpdate(sqlCommands.getProperty("insert.table.003"));
			System.out.println("Statement returned " + resultRows);
			resultRows = statement.executeUpdate(sqlCommands.getProperty("insert.table.004"));
			System.out.println("Statement returned " + resultRows);
			resultRows = statement.executeUpdate(sqlCommands.getProperty("insert.table.005"));
			System.out.println("Statement returned " + resultRows);

		} catch (SQLException throwables) {
			System.err.println("Error occurred while inserting data");
			throwables.printStackTrace();
		}
	}

	private void batchInsertData(Connection connection) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sqlCommands.getProperty("insert.table.000"))) {
			generateData(preparedStatement, 1000);

			int[] affectedRows = preparedStatement.executeBatch();
			System.out.println("Rows inserted " + Arrays.stream(affectedRows).sum());

		} catch (SQLException throwables) {
			System.err.println("Error occurred while retrieving data");
			throwables.printStackTrace();
		}
	}

	private void selectData(Connection connection) {
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
				sqlCommands.getProperty("select.table.001"))) {

			while (resultSet.next()) {
				System.out.println("id:" + resultSet.getLong("ID") + ", firstName:" + resultSet.getString("FIRSTNAME") +
										   ", lastName:" + resultSet.getString("LASTNAME") + ", age:" +
										   resultSet.getInt("AGE"));
			}
		} catch (SQLException throwables) {
			System.err.println("Error occurred while retrieving data");
			throwables.printStackTrace();
		}
	}

	private void updateData(Connection connection) {
		try (Statement statement = connection.createStatement()) {
			int resultRows = statement.executeUpdate(sqlCommands.getProperty("update.table.001"));

			System.out.println("Rows updated " + resultRows);

		} catch (SQLException throwables) {
			System.err.println("Error occurred while updating data");
			throwables.printStackTrace();
		}
	}

	private void deleteData(Connection connection) {
		try (Statement statement = connection.createStatement()) {
			int resultRows = statement.executeUpdate(sqlCommands.getProperty("delete.table.001"));

			System.out.println("Rows updated " + resultRows);

		} catch (SQLException throwables) {
			System.err.println("Error occurred while updating data");
			throwables.printStackTrace();
		}
	}

	private void stopH2Server() {
		if (h2Server == null) {
			return;
		}

		if (h2Server.isRunning(true)) {
			h2Server.stop();
			h2Server.shutdown();
			System.out.println("H2 Database server has been shutdown.");
		}
	}

	private void generateData(PreparedStatement preparedStatement, int howMany) throws SQLException {
		for (int i = 1; i <= howMany; i++) {
			preparedStatement.clearParameters();

			preparedStatement.setLong(1, 1005 + i);
			preparedStatement.setString(2, generator.getFirstName());
			preparedStatement.setString(3, generator.getLastName());
			preparedStatement.setInt(4, ThreadLocalRandom.current().nextInt(18, 70));
			preparedStatement.addBatch();
		}
	}
}
