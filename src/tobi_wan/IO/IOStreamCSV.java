package tobi_wan.IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;



public class IOStreamCSV extends IOStream {
   // Attribute
   String separator;

   // Getter & Setter
   public String getSeparator() {
      return separator;
   }

   public void setSeparator(String separator) {
      this.separator = separator;
   }

   // Konstruktor
   public IOStreamCSV(String separator) {
      super();
      setSeparator(separator);
   }

   // Methoden
   public void writeCSV(String path, List<String> data) throws IOException {

   }

   public List readCSV(String path) throws IOException {
      return Files.readAllLines(Paths.get(path));
   }
}