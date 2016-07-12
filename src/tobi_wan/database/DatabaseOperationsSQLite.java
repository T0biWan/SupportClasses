package tobi_wan.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import tobi_wan.dataStructure.Table;
import tobi_wan.support.StringContainingIntegerValue;



public class DatabaseOperationsSQLite {
   private Connection              connection;
   private Statement               statement;
   private PreparedStatement       preparedStatement;
   private ResultSet               resultSet;
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

   public void sqlDataDefinition(String sqlStatement) throws SQLException {
      statement.executeUpdate(sqlStatement);
   }

   public ResultSet sqlDataManipulation(String sqlStatement) throws SQLException {
      return statement.executeQuery(sqlStatement);
   }

   private String makeDropTableString(String tableName) {
      return "DROP TABLE " + tableName;
   }

   public void dropTable(String tableName) throws SQLException {
      sqlDataDefinition(makeDropTableString(tableName));
   }

   public void insertWithPreparedStatement(String insertStatement, Table table) throws SQLException {
      connection.setAutoCommit(false);
      preparedStatement = connection.prepareStatement(insertStatement);
      for (int row = 0; row < table.getNumberOfRows(); row++) {
         for (int column = 0; column < table.getNumberOfColumns(); column++) {
            // preparedStatement.setInt(1,
            // Integer.parseInt(input.getRow(i)[0]));
            // preparedStatement.setString(2, input.getRow(i)[1]);
            // preparedStatement.executeUpdate();

            // if (table.getDatatypesOfColumns()[column] == "int") {
            // getPreparedStatement().setInt(sqlColumn,
            // Integer.parseInt(table.getRow(row)[column]));
            // } else if (table.getDatatypesOfColumns()[column] == "String") {
            // getPreparedStatement().setString(sqlColumn,
            // table.getRow(row)[column]);
            // }

            int sqlColumn = column + 1;
            String input = table.getRow(row)[column];
            // System.out.println("Column: " + sqlColumn);
            // System.out.println(input);
            System.out.println(sqlColumn);
            // if (si.isNumber(input)) preparedStatement.setInt(sqlColumn,
            // si.parseStringToInt(input));
            // else preparedStatement.setString(sqlColumn, input);
            // preparedStatement.executeUpdate();
         }
         preparedStatement.setInt(1, si.parseStringToInt(table.getRow(row)[0]));
         preparedStatement.setString(2, table.getRow(row)[1]);
         preparedStatement.executeUpdate();
      }
      connection.commit();
      connection.setAutoCommit(true);
   }

   private String generateTableHeadFromResultSet(ResultSet resultSet) throws SQLException {
      String string = "";
      ResultSetMetaData meta = resultSet.getMetaData();
      int columnNumber = meta.getColumnCount();
      for (int i = 1; i <= columnNumber; i++) {
         string += meta.getColumnName(i) + "\t";
      }
      string += "\n";
      return string;
   }

   private String generateTableBodyFromResultSet(ResultSet resultSet) throws SQLException {
      ResultSetMetaData meta = resultSet.getMetaData();
      int columnNumber = meta.getColumnCount();
      String string = "";
      while (resultSet.next()) {
         for (int i = 1; i <= columnNumber; i++) {
            string += resultSet.getString(i) + "\t";
         }
         string += "\n";
      }
      return string;
   }

   private String generateTableFromResultSet(ResultSet resultSet) throws SQLException {
      return generateTableHeadFromResultSet(resultSet) + generateTableBodyFromResultSet(resultSet);
   }

   public String generateTableFromQuery(String queryStatement) throws SQLException {
      return generateTableFromResultSet(sqlDataManipulation(queryStatement));
   }
}
