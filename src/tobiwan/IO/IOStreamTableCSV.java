package tobiwan.IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import tobiwan.dataStructure.Table;



public class IOStreamTableCSV extends IOStreamCSV {
   // Attribute

   // Konstruktor
   public IOStreamTableCSV(String separator) {
      super(separator);
   }

   // Getter & Setter

   // Methoden
   public Table readCSVIntoTable(String path) throws IOException {
      List<String> lines = Files.readAllLines(Paths.get(path));
      ArrayList<String []> data = new ArrayList();
      for (String line : lines)
         data.add(line.split(getSeparator()));
      return new Table(data);
   }

   public void writeCSVFromTable(String path, Table table) throws IOException {
      ArrayList<String> output = new ArrayList();
      for (String [] row : table.getAllRows()) {
         output.add(makeCSVRow(row));
      }
      Files.write(Paths.get(path), output);
   }

   private String makeCSVRow(String [] row) {
      String outputRow = "";
      for (int i = 0; i < row.length; i++) {
         outputRow += row[i];
         if (i < row.length - 1) outputRow += ";";
      }
      return outputRow;
   }

}