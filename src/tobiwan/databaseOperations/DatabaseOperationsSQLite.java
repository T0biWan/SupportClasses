package tobiwan.databaseOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tobiwan.dataStructure.Table;
import tobiwan.support.StringOperations;



public class DatabaseOperationsSQLite {
   private Connection        connection;
   private Statement         statement;
   private PreparedStatement preparedStatement;
   private ResultSet         resultSet;
   private StringOperations  so = new StringOperations();

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
            if (so.stringIsNumber(input))
               preparedStatement.setInt(sqlColumn, so.parseStringToInt(input));
            else preparedStatement.setString(sqlColumn, input);
         }
         preparedStatement.executeUpdate();
      }
      connection.commit();
      connection.setAutoCommit(true);
   }

   private String [] getColumnNames(ResultSet resultSet) throws SQLException {
      ResultSetMetaData meta = resultSet.getMetaData();
      int numberOfColumns = meta.getColumnCount();
      String [] row = new String [numberOfColumns];
      for (int i = 0; i < numberOfColumns; i++) {
         row[i] = meta.getColumnName(i + 1);
      }
      return row;
   }

   private ArrayList<String []> getRows(ResultSet resultSet) throws SQLException {
      int numberOfColumns = resultSet.getMetaData().getColumnCount();
      ArrayList<String []> data = new ArrayList();
      while (resultSet.next()) {
         String [] row = new String [numberOfColumns];
         for (int i = 1; i <= numberOfColumns; i++) {
            row[i - 1] = resultSet.getString(i);
         }
         data.add(row);
      }
      return data;
   }

   public Table tableOutOfQuery(String sqlDMLStatement) throws SQLException {
      ArrayList<String []> data = new ArrayList();
      ResultSet resultSet = sqlDataManipulation(sqlDMLStatement);
      data.addAll(getRows(resultSet));
      return new Table(data);
   }

   public Table tableWithColumnNamesOutOfQuery(String sqlDMLStatement) throws SQLException {
      ArrayList<String []> data = new ArrayList();
      ResultSet resultSet = sqlDataManipulation(sqlDMLStatement);
      data.add(getColumnNames(resultSet));
      data.addAll(getRows(resultSet));
      return new Table(data);
   }

   private boolean valuesAreEqual(String value1, String value2) {
      return so.stringsAreEqual(value1, value2);
   }

   public void changeValueToForeignKey(Table tableGettingFK, int columnGettingFK, Table tableGivingFK, int columnGivingFK, int columnContainingFK) {
      // Die Tabelle "tableGettingFK" bekommt in Spalte "columnGettingFK" den FK
      // aus "columnOfFK".
      // Vorraussetzung ist das ein Wert von "columnGettingFK" uebereinstimmt
      // mit einem Wert aus "columnContainingFK"
      for (int i = 0; i < tableGettingFK.getNumberOfRows(); i++) {
         for (int j = 0; j < tableGivingFK.getNumberOfRows(); j++) {
            String valueGettingFK = tableGettingFK.getRow(i)[columnGettingFK];
            String valueGivingFK = tableGivingFK.getRow(j)[columnGivingFK];
            if (valuesAreEqual(valueGettingFK, valueGivingFK)) {
               tableGettingFK.getRow(i)[columnGettingFK] = tableGivingFK.getRow(j)[columnContainingFK];
            }
         }
      }
   }

}
