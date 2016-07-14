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
package tobi_wan.dataStructure;

import java.util.ArrayList;
import tobi_wan.support.StandardOutput;
import tobi_wan.support.StringContainingIntegerValue;



public class Table {
   // Attribute
   private StandardOutput               s               = StandardOutput.defaultSupportMethods();
   private StringContainingIntegerValue si              = new StringContainingIntegerValue();
   private String                       tableName;
   private int                          numberOfColumns;
   private String []                    tableRows;
   private ArrayList<String []>         data            = new ArrayList<>();
   String                               border          = "|";
   String                               separator       = "-";
   String                               connector       = "+";
   String                               space           = " ";
   String                               leftBorder      = border + space;
   String                               middleBorder    = space + border + space;
   String                               rightBorder     = space + border;
   String                               leftConnecter   = connector + separator;
   String                               middleConnecter = separator + connector + separator;
   String                               rightConnecter  = separator + connector;

   // Konstruktor
   public Table(ArrayList<String []> data) {
      this.data = data;
      setNumberOfColumns();
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
      this.numberOfColumns = data.get(0).length;
   } // setNumberOfColumns

   public int getNumberOfRows() {
      return data.size();
   } // getNumberOfRows

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

   public void addAllRows(ArrayList<String []> tableData) {
      data = tableData;
      setNumberOfColumns();
   }

   public ArrayList<String []> getAllRows() {
      return data;
   } // getAllRows

   private void printTableSeparation() {
      int [] maxStringLengthOfColumn = getMaxStringLengthOfColumn();
      System.out.print(leftConnecter);
      for (int i = 0; i < numberOfColumns; i++) {
         for (int j = 0; j < maxStringLengthOfColumn[i]; j++) {
            System.out.print(separator);
         }
         if (i < numberOfColumns - 1) System.out.print(middleConnecter);
      }
      System.out.println(rightConnecter);
   }

   private int [] getMaxStringLengthOfColumn() {
      int [] maxStringLengthOfColumn = new int [numberOfColumns];
      for (int i = 0; i < data.size(); i++) {
         for (int column = 0; column < numberOfColumns; column++) {
            int elementLength = data.get(i)[column].length();
            if (maxStringLengthOfColumn[column] < elementLength) maxStringLengthOfColumn[column] = elementLength;
         }
      }
      return maxStringLengthOfColumn;
   }

   private void printTableRow(int row) {
      int [] maxStringLengthOfColumn = getMaxStringLengthOfColumn();
      System.out.print(leftBorder);
      for (int column = 0; column < numberOfColumns; column++) {
         String element = data.get(row)[column];
         if (si.isNumber(element)) {
            System.out.printf("%" + maxStringLengthOfColumn[column] + "s", element);
         } else {
            System.out.printf("%-" + maxStringLengthOfColumn[column] + "s", element);
         }
         if (column < numberOfColumns - 1) System.out.print(middleBorder);
      }
      System.out.println(rightBorder);
   }

   public void printTable() {
      int [] maxStringLengthOfColumn = getMaxStringLengthOfColumn();
      printTableSeparation();
      printTableRow(0);
      printTableSeparation();
      for (int row = 1; row < data.size(); row++) {
         printTableRow(row);
      }
      printTableSeparation();
   }

   public void printTableWithoutColumnTitle() {
      int [] maxStringLengthOfColumn = getMaxStringLengthOfColumn();
      printTableSeparation();
      for (int row = 0; row < data.size(); row++) {
         printTableRow(row);
      }
      printTableSeparation();
   }

} // class Table