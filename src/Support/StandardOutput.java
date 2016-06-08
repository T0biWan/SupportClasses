// Datei: SupportMethods.java
// Autor: Tobi Wan
// Datum: 02.06.2016
/*----------------------------------------------------------------------
   Die Klasse enthaelt:
   Getter & Setter:
      void setSeparator(String)
      void setSeparatorReptition(int)
      String getSeparator()
      int getSeparatorReptition()
   
   Konstruktoren:
      SupportMethods(String, int)
      defaultSupportMethods
      
   Methoden:
      void print(String)
      void print(Object)
      void println()
      void println(String)
      void println(Object)
      void printf(String, Object)
      void printTest(boolean, String)
      void printlnTest(boolean, String)
      void printfTest(boolean, String, Object)
      String generateSeparation()
      void printSeparation()
      void printlnSeparation()
 ----------------------------------------------------------------------*/
package Support;

public class StandardOutput {
   // Attribute
   String separator;
   int    separatorReptition;

   // Getter & Setter
   public void setSeparator(String separator) {
      this.separator = separator;
   } // setSeparator

   public String getSeparator() {
      return separator;
   } // getSeparator

   public void setSeparatorReptition(int amount) {
      this.separatorReptition = amount;
   } // setAmount

   public int getSeparatorReptition() {
      return separatorReptition;
   } // getAmount

   // Konstruktor
   public StandardOutput(String separator, int amount) {
      setSeparator(separator);
      setSeparatorReptition(amount);
   } // SupportMethods

   private StandardOutput() {
      this("*", 10);
   } // SupportMethods

   public static StandardOutput defaultSupportMethods() {
      return new StandardOutput();
   } // defaultSupportMethods

   // Methoden
   public void print(String string) {
      // Verkuerzte Schreibweise
      System.out.print(string);
   } // print

   public void print(Object ob) {
      // Verkuerzte Schreibweise
      System.out.print(ob);
   } // print

   public void println() {
      // Verkuerzte Schreibweise
      System.out.println();
   } // println

   public void println(String string) {
      // Verkuerzte Schreibweise
      System.out.println(string);
   } // println

   public void println(Object ob) {
      // Verkuerzte Schreibweise
      System.out.println(ob);
   } // println

   public void printf(String s, Object... ob) {
      // Verkuerzte Schreibweise
      System.out.printf(s, ob);
   } // printf

   public void printTest(boolean testen, String testString) {
      // Liefert eine, von einem boolean-Wert abhaengige, Testausgabe
      if (testen) print(testString);
   } // printTest

   public void printlnTest(boolean testen, String testString) {
      // Liefert eine, von einem boolean-Wert abhaengige, Testausgabe
      if (testen) println(testString);
   } // printlnTest

   public void printfTest(boolean testen, String testString, Object ob) {
      // Liefert eine, von einem boolean-Wert abhaengige, Testausgabe
      if (testen) printf(testString, ob);
   } // printfTest

   public String generateSeparation() {
      // Liefert eine Trennzeile zur besseren Formatierung der Ausgabe
      // Dazu wird ein im Konstruktor festgelegtes Zeichen eine,
      // ebenfalls im Konstruktor festgelegte Anzahl oft wiederholt.
      // Beispiel:
      // ********************
      // Ausgabe
      // ********************
      String separatorString = "";
      if (separatorReptition > 0) {
         for (int i = 0; i < separatorReptition; i++)
            separatorString += separator;
      } else {
         separatorString += "Methode:\tseparator\nError:\t\tVariable amount ist zu klein";
      }
      return separatorString;
   } // separator

   public void printSeparation() {
      // Erzeugt die Ausgabe einer Trennzeile
      print(generateSeparation());
   } // printSeparator

   public void printlnSeparation() {
      // Erzeugt die Ausgabe einer Trennzeile mit Zeilenumbruch
      println(generateSeparation());
   } // printlnSeparator

} // class SupportMethods
