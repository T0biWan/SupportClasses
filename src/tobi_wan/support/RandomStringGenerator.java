// Datei: RandomStringGenerator.java
// Autor: Tobi Wan
// Datum: 14.06.2016
/*----------------------------------------------------------------------
   Die Klasse enthaelt:
   Konstruktoren:
      RandomStringGenerator()
      
   Methoden:
      String generateRandomString(int)
 ----------------------------------------------------------------------*/
package tobi_wan.support;

import java.security.SecureRandom;
import java.util.Random;



public class RandomStringGenerator {
   // Attribute
   private static char [] VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();

   // Konstruktor
   public RandomStringGenerator() {

   }

   // Methode
   public String generateRandomString(int stringLaennge) {
      // Attribute
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

} // class RandomStringGenerator
