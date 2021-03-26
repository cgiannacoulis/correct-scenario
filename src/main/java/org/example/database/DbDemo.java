package org.example.database;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.exit;

public class DbDemo {
	private static final Logger logger = LoggerFactory.getLogger(DbDemo.class);
	private final Properties sqlCommands = new Properties();
	private final Lorem generator = LoremIpsum.getInstance();

	public static void main(String[] args) throws InterruptedException {
		DbDemo demo = new DbDemo();
		// Load SQL commands
		demo.loadSqlCommands();

		DbInstance dbInstance = new DbInstance();
		// Start H2 database server
		dbInstance.startH2Server();

		// Create table
		if (demo.createTable()) {
			// Insert data
			demo.insertData();
			// Insert data in batch mode
			demo.batchInsertData();
		}

		demo.selectData();

		// Update data
		demo.updateData();
		demo.selectData();

		// Delete data
		demo.deleteData();
		demo.selectData();

		demo.selectData();

		// Stop H2 database server via shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread(() -> dbInstance.stopH2Server()));

		while (true) {
		}
	}

	private void loadSqlCommands() {
		try (InputStream inputStream = DbDemo.class.getClassLoader().getResourceAsStream("sql.properties")) {
			if (inputStream == null) {
				logger.error("Unable to load SQL commands.");
				exit(-1);
			}
			sqlCommands.load(inputStream);
		} catch (IOException e) {
			logger.error("Error while loading SQL commands.", e);
		}
	}

	private boolean createTable() {
		try (Connection connection = ConnectionPoolProvider.getConnection();
			 Statement statement = connection.createStatement()) {
			int resultRows = statement.executeUpdate(sqlCommands.getProperty("create.table.001"));

			logger.debug("Statement returned {}.", resultRows);
			return true;
		} catch (SQLException throwables) {
			logger.warn("Unable to create target database table. It already exists.");
			return false;
		}
	}

	private void insertData() {
		try (Connection connection = ConnectionPoolProvider.getConnection();
			 Statement statement = connection.createStatement()) {
			int resultRows = statement.executeUpdate(sqlCommands.getProperty("insert.table.001"));
			logger.debug("Statement returned {}.", resultRows);
			resultRows = statement.executeUpdate(sqlCommands.getProperty("insert.table.002"));
			logger.debug("Statement returned {}.", resultRows);
			resultRows = statement.executeUpdate(sqlCommands.getProperty("insert.table.003"));
			logger.debug("Statement returned {}.", resultRows);
			resultRows = statement.executeUpdate(sqlCommands.getProperty("insert.table.004"));
			logger.debug("Statement returned {}.", resultRows);
			resultRows = statement.executeUpdate(sqlCommands.getProperty("insert.table.005"));
			logger.debug("Statement returned {}.", resultRows);

		} catch (SQLException throwables) {
			logger.error("Error occurred while inserting data.", throwables);
		}
	}

	private void batchInsertData() {
		try (Connection connection = ConnectionPoolProvider.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(
					 sqlCommands.getProperty("insert.table.000"))) {
			generateData(preparedStatement, 10);

			int[] affectedRows = preparedStatement.executeBatch();
			logger.debug("Rows inserted {}.", Arrays.stream(affectedRows).sum());

		} catch (SQLException throwables) {
			logger.error("Error occurred while batch inserting data.", throwables);
		}
	}

	private void selectData() {
		try (Connection connection = ConnectionPoolProvider.getConnection();
			 Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
				sqlCommands.getProperty("select.table.001"))) {

			while (resultSet.next()) {
				//@formatter:off
				logger.info("id:{}, firstName:{}, lastName:{}, age:{}.",
							resultSet.getLong("ID"),
							resultSet.getString("FIRSTNAME"),
							resultSet.getString("LASTNAME"),
							resultSet.getInt("AGE"));
				//@formatter:on
			}
		} catch (SQLException throwables) {
			logger.error("Error occurred while retrieving data", throwables);
		}
	}

	private void updateData() {
		try (Connection connection = ConnectionPoolProvider.getConnection();
			 Statement statement = connection.createStatement()) {
			int resultRows = statement.executeUpdate(sqlCommands.getProperty("update.table.001"));

			logger.debug("Rows updated {}.", resultRows);

		} catch (SQLException throwables) {
			logger.error("Error occurred while updating data.", throwables);
		}
	}

	private void deleteData() {
		try (Connection connection = ConnectionPoolProvider.getConnection();
			 Statement statement = connection.createStatement()) {
			int resultRows = statement.executeUpdate(sqlCommands.getProperty("delete.table.001"));

			logger.debug("Rows updated {}.", resultRows);

		} catch (SQLException throwables) {
			logger.error("Error occurred while updating data.", throwables);
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
