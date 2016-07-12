package tobi_wan.IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import tobi_wan.dataStructure.Table;



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
      int numberOfColumns = lines.get(0).split(getSeparator()).length;
      Table table = new Table("", new String [numberOfColumns]);
      for (String line : lines)
         table.addRow(line.split(getSeparator()));
      return table;
   }
}