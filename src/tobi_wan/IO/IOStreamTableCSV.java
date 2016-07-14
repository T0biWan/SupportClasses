package tobi_wan.IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
      ArrayList<String []> data = new ArrayList();
      for (String line : lines)
         data.add(line.split(getSeparator()));
      return new Table(data);
   }
}