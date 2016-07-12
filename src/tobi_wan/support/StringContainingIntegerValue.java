package tobi_wan.support;

public class StringContainingIntegerValue {
   public boolean isNumber(String string) {
      try {
         Integer.parseInt(string);
      } catch (NumberFormatException e) {
         return false;
      }
      return true;
   }
   
   public int parseStringToInt(String string) {
      return Integer.parseInt(string);
   }
}
