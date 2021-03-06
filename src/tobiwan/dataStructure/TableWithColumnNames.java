// Datei: Table.java
// Autor: Tobi Wan
// Datum: 10.06.2016
/*----------------------------------------------------------------------
   Die Klasse enthaelt:
   Konstruktor:
      Table(String, String...)
        
   Getter & Setter:
      String getTableName()
      void setTableName(String)
      int getNumberOfColumns()
      setNumberOfColumns(int)
      int getNumberOfRows()
      String[] getColumnNames()
      void setColumnNames(String...)
      
   Methoden:
      void addRow(String[])
      String[] getRow(int)
      void deleteRow(int)
      ArrayList<String []> getAllRows()
      String toString()
      void printTable()
 ----------------------------------------------------------------------*/
package tobiwan.dataStructure;

import java.util.ArrayList;
import tobiwan.support.StandardOutput;



public class TableWithColumnNames {
   // Attribute
   StandardOutput               s    = StandardOutput.defaultSupportMethods();
   private String               tableName;
   private int                  numberOfColumns;
   private String []            columnNames;
   private String []            tableRows;
   private ArrayList<String []> data = new ArrayList<>();

   // Konstruktor
   public TableWithColumnNames(String tableName, String... columnNames) {
      setTableName(tableName);
      setColumnNames(columnNames);
      setNumberOfColumns();
      tableRows = new String [getNumberOfColumns()];
   } // Table

   // Getter & Setter
   public String getTableName() {
      return tableName;
   } // getTableName

   public void setTableName(String tableName) {
      this.tableName = tableName;
   } // setTableName

   public int getNumberOfColumns() {
      return numberOfColumns;
   } // getNumberOfColumns

   private void setNumberOfColumns() {
      this.numberOfColumns = columnNames.length;
   } // setNumberOfColumns

   public int getNumberOfRows() {
      return data.size();
   } // getNumberOfRows

   public String [] getColumnNames() {
      return columnNames;
   } // getColumnNames

   private void setColumnNames(String... columnNames) {
      this.columnNames = columnNames;
      setNumberOfColumns();
   } // setColumnNames

   // Methoden
   public void addRow(String [] tableRow) {
      data.add(tableRow);
   } // addRow

   public String [] getRow(int row) {
      return data.get(row);
   } // getRow

   public void deleteRow(int row) {
      data.remove(getRow(row));
   } // deleteRow

   public ArrayList<String []> getAllRows() {
      return data;
   } // getAllRows

   public String toString() {
      String string = "Table:\nName:\t\t" + getTableName() + "\nColumns:\t" + getNumberOfColumns() + "\nRows:\t\t" + getNumberOfRows() + "\n";
      string += s.arrayToString(columnNames) + "\n";
      for (int i = 0; i < getNumberOfRows(); i++)
         string += s.arrayToString(getRow(i)) + "\n";
      return string;
   } // toString

   public void printTable() {
      s.println(toString());
   } // printTable

} // class Table