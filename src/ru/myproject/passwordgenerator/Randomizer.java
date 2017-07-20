package ru.myproject.passwordgenerator;

/**
 * Created by George on 27.03.2017.
 * Contains methods which returns arrays of randomized chars in ASCII from "!" up to "~"
 */
public class Randomizer {

    private static char[] charArray;

    /*Generates symbols of full range from "!" up to "~" in ASCII*/
    public static char[] generateFullRange(int length) {
        charArray = new char[length];
        for (int i = 0; i < length; i++) {
            charArray[i] = RandomChar.generateFullRangeChar();
        }
        return charArray;
    }

    /*Generates upper case symbols of range from "A" to "Z" in ASCII*/
    public static char[] generateAZUpperRange(int length) {
        charArray = new char[length];
        for (int i = 0; i < length; i++) {
            charArray[i] = RandomChar.generateAZUpperRangeChar();
        }
        return charArray;
    }

    /*Generates lower case symbols of range from "a" to "z" in ASCII*/
    public static char[] generateAZLowerRange(int length) {
        charArray = new char[length];
        for (int i = 0; i < length; i++) {
            charArray[i] = RandomChar.generateAZLowerRangeChar();
        }
        return charArray;
    }

    /*Generates both an upper and lower case symbols of range from "a" to "z" in ASCII*/
    public static char[] generateAZBothCasesRange(int length) {
        charArray = new char[length];
        int k;
        for (int i = 0; i < length; i++) {
            k = (int) (Math.random() * 2);
            if (k == 0) {
                charArray[i] = RandomChar.generateAZLowerRangeChar();
            } else if (k == 1) {
                charArray[i] = RandomChar.generateAZUpperRangeChar();
            }
        }
        return charArray;
    }

    /*Generates both an upper and lowercase symbols of range from "a" to "z" and numbers in ASCII*/
    public static char[] generateAZBothCasesAndNumbers(int length) {
        charArray = new char[length];
        int k;
        for (int i = 0; i < length; i++) {
            k = (int) (Math.random() * 3);
            if (k == 0) {
                charArray[i] = RandomChar.generateAZLowerRangeChar();
            } else if (k == 1) {
                charArray[i] = RandomChar.generateAZUpperRangeChar();
            } else if (k == 2) {
                charArray[i] = RandomChar.generateNumberChar();
            }
        }
        return charArray;
    }

    /*Generates both an upper and lowercase symbols of range from "a" to "z" and special symbols in ASCII*/
    public static char[] generateAZBothCasesAndSpecial(int length) {
        charArray = new char[length];
        int k;
        for (int i = 0; i < length; i++) {
            k = (int) (Math.random() * 3);
            if (k == 0) {
                charArray[i] = RandomChar.generateAZLowerRangeChar();
            } else if (k == 1) {
                charArray[i] = RandomChar.generateAZUpperRangeChar();
            } else if (k == 2) {
                charArray[i] = RandomChar.generateSpecChar();
            }
        }
        return charArray;
    }

    /*Generates lower case symbols of range from "a" to "z" and numbers in ASCII*/
    public static char[] generateAZLowercaseAndNumbers(int length) {
        charArray = new char[length];
        int k;
        for (int i = 0; i < length; i++) {
            k = (int) (Math.random() * 2);
            if (k == 0) {
                charArray[i] = RandomChar.generateAZLowerRangeChar();
            } else if (k == 1) {
                charArray[i] = RandomChar.generateNumberChar();
            }
        }
        return charArray;
    }

    /*Generates lower case symbols of range from "a" to "z" and specials in ASCII*/
    public static char[] generateAZLowercaseAndSpecial(int length) {
        charArray = new char[length];
        int k;
        for (int i = 0; i < length; i++) {
            k = (int) (Math.random() * 2);
            if (k == 0) {
                charArray[i] = RandomChar.generateAZLowerRangeChar();
            } else if (k == 1) {
                charArray[i] = RandomChar.generateSpecChar();
            }
        }
        return charArray;
    }

    /*Generates lower case symbols of range from "a" to "z", numbers and special symbols in ASCII*/
    public static char[] generateAZLowercaseAndNumbersAndSpecial(int length) {
        charArray = new char[length];
        int k;
        for (int i = 0; i < length; i++) {
            k = (int) (Math.random() * 3);
            if (k == 0) {
                charArray[i] = RandomChar.generateAZLowerRangeChar();
            } else if (k == 1) {
                charArray[i] = RandomChar.generateNumberChar();
            } else if (k == 2) {
                charArray[i] = RandomChar.generateSpecChar();
            }
        }
        return charArray;
    }

    /*Generates symbols numbers of range from "0" to "9" in ASCII*/
    public static char[] generateNumbersRange(int length) {
        charArray = new char[length];
        for (int i = 0; i < length; i++) {
            charArray[i] = RandomChar.generateNumberChar();
        }
        return charArray;
    }

    /*Generates special symbols in ASCII*/
    public static char[] generateSpecRange(int length) {
        charArray = new char[length];
        for (int i = 0; i < length; i++) {
            charArray[i] = RandomChar.generateSpecChar();
        }
        return charArray;
    }
}
