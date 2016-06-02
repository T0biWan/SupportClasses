// Datei: SupportMethods.java
// Autor: Tobi Wan
// Datum: 02.06.2016
/*----------------------------------------------------------------------
   Die Klasse enthaelt:
   Getter & Setter:
      void setSeparator(String)
      void setAmount(int)
      String getSeparator()
      int getAmount()
   
   Konstruktoren:
      SupportMethods(String, int)
      SupportMethods()
      
   Methoden:
      void p(String)
      void p(Object)
      void pln()
      void pln(String)
      void pln(Object)
      void pf(String, Object)
      void pTest(boolean, String)
      void plnTest(boolean, String)
      void pfTest(boolean, String, Object)
      String separator()
      void pSeparator()
      void plnSeparator()
 ----------------------------------------------------------------------*/
package Support;

public class SupportMethods {
   // Attribute
   String separator;
   int    amount;

   // Getter & Setter
   public void setSeparator(String separator) {
      this.separator = separator;
   } // setSeparator

   public String getSeparator() {
      return separator;
   } // getSeparator

   public void setAmount(int amount) {
      this.amount = amount;
   } // setAmount

   public int getAmount() {
      return amount;
   } // getAmount

   // Konstruktor
   public SupportMethods(String separator, int amount) {
      setSeparator(separator);
      setAmount(amount);
   } // SupportMethods

   public SupportMethods() {
      this("*", 10);
   } // SupportMethods

   // Methoden
   public void p(String string) {
      // Verkuerzte Schreibweise
      System.out.print(string);
   } // p

   public void p(Object ob) {
      // Verkuerzte Schreibweise
      System.out.print(ob);
   } // p

   public void pln() {
      // Verkuerzte Schreibweise
      System.out.println();
   } // pln

   public void pln(String string) {
      // Verkuerzte Schreibweise
      System.out.println(string);
   } // pln

   public void pln(Object ob) {
      // Verkuerzte Schreibweise
      System.out.println(ob);
   } // pln

   public void pf(String s, Object... ob) {
      // Verkuerzte Schreibweise
      System.out.printf(s, ob);
   } // pf

   public void pTest(boolean testen, String testString) {
      // Liefert eine, von einem boolean-Wert abhaengige, Testausgabe
      if (testen) p(testString);
   } // pTest

   public void plnTest(boolean testen, String testString) {
      // Liefert eine, von einem boolean-Wert abhaengige, Testausgabe
      if (testen) pln(testString);
   } // plnTest

   public void pfTest(boolean testen, String testString, Object ob) {
      // Liefert eine, von einem boolean-Wert abhaengige, Testausgabe
      if (testen) pf(testString, ob);
   } // pfTest

   public String separator() {
      // Liefert eine Trennzeile zur besseren Formatierung der Ausgabe
      // Dazu wird ein im Konstruktor festgelegtes Zeichen eine,
      // ebenfalls im Konstruktor festgelegte Anzahl oft wiederholt.
      // Beispiel:
      // ********************
      // Ausgabe
      // ********************
      String separatorString = "";
      if (amount > 0) {
         for (int i = 0; i < amount; i++)
            separatorString += separator;
      } else {
         separatorString += "Methode:\tseparator\nError:\t\tVariable amount ist zu klein";
      }
      return separatorString;
   } // separator

   public void pSeparator() {
      // Erzeugt die Ausgabe einer Trennzeile
      p(separator());
   } // pSeparator

   public void plnSeparator() {
      // Erzeugt die Ausgabe einer Trennzeile
      pln(separator());
   } // plnSeparator

} // class SupportMethods
