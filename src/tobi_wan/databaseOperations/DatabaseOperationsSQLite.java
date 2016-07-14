package tobi_wan.databaseOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tobi_wan.dataStructure.Table;
import tobi_wan.support.StringContainingIntegerValue;



public class DatabaseOperationsSQLite {
   private Connection                   connection;
   private Statement                    statement;
   private PreparedStatement            preparedStatement;
   private ResultSet                    resultSet;
   private StringContainingIntegerValue si = new StringContainingIntegerValue();

   public DatabaseOperationsSQLite() {
      connection = null;
      statement = null;
      preparedStatement = null;
      resultSet = null;
   }

   public void connect(String dbPath) throws SQLException {
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
      statement = connection.createStatement();
   }

   public void disconnect() throws SQLException {
      if (resultSet != null) resultSet.close();
      if (statement != null) statement.close();
      if (connection != null) connection.close();
   }

   public void sqlDataDefinition(String sqlDDLStatement) throws SQLException {
      statement.executeUpdate(sqlDDLStatement);
   }

   public ResultSet sqlDataManipulation(String sqlDMLStatement) throws SQLException {
      return statement.executeQuery(sqlDMLStatement);
   }

   private String makeDropTableString(String tableName) {
      return "DROP TABLE IF EXISTS " + tableName;
   }

   public void dropTable(String tableName) throws SQLException {
      sqlDataDefinition(makeDropTableString(tableName));
   }

   public void insertWithPreparedStatement(String insertStatement, Table table) throws SQLException {
      connection.setAutoCommit(false);
      preparedStatement = connection.prepareStatement(insertStatement);
      for (int row = 0; row < table.getNumberOfRows(); row++) {
         for (int column = 0; column < table.getNumberOfColumns(); column++) {
            int sqlColumn = column + 1;
            String input = table.getRow(row)[column];
            if (si.isNumber(input))
               preparedStatement.setInt(sqlColumn, si.parseStringToInt(input));
            else preparedStatement.setString(sqlColumn, input);
         }
         preparedStatement.executeUpdate();
      }
      connection.commit();
      connection.setAutoCommit(true);
   }

   public Table tableOutOfQuery(String sqlDMLStatement) throws SQLException {
      ResultSet resultSet = sqlDataManipulation(sqlDMLStatement);
      int numberOfColumns = resultSet.getMetaData().getColumnCount();
      ArrayList<String []> data = new ArrayList();
      while (resultSet.next()) {
         String [] row = new String [numberOfColumns];
         for (int i = 1; i <= numberOfColumns; i++) {
            row[i - 1] = resultSet.getString(i);
         }
         data.add(row);
      }
      return new Table(data);
   }

}
