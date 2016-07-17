package tobi_wan.support;

import java.security.SecureRandom;
import java.util.Random;



public class StringOperations {
   public boolean stringIsNumber(String string) {
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

   public String makeRandomString(int stringLaennge) {
      char [] VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();
      SecureRandom randomSeed = new SecureRandom();
      Random random = new Random();
      char [] zeichen = new char [stringLaennge];

      // Array fuellen, mit den moeglichen Zeichen
      for (int i = 0; i < stringLaennge; i++) {
         if ((i % 10) == 0) {
            random.setSeed(randomSeed.nextLong());
         }
         zeichen[i] = VALID_CHARACTERS[random.nextInt(VALID_CHARACTERS.length)];
      }
      return new String(zeichen);
   } // generateRandomString
   
   public String cleanUpString(String input){
      String output ="";
      for (char character : input.toCharArray()) {
         output += changeUmlaut(character);
      }
      return output;
   }
   
   private String changeUmlaut(char input) {
      String output = "" + input;
      if(input == 'Ä') output = "Ae";
      else if(input == 'Ö') output = "Oe";
      else if(input == 'Ü') output = "Ue";
      else if(input == 'ä') output = "ae";
      else if(input == 'ö') output = "oe";
      else if(input == 'ü') output = "ue";
      else if(input == 'ß') output = "ss";
      return output;
   }
}
