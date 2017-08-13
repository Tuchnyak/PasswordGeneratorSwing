package main.java.ru.myproject.passwordgenerator;

/**
 * Created by George on 27.03.2017.
 * * Contains methods which returns symbols of randomized chars in ASCII from "!" up to "~"
 */
final class RandomChar {

    /*Generates symbol of full range from "!" up to "~" in ASCII*/
    static char generateFullRangeChar() {
        return (char) (33 + Math.random() * (127 - 33));
    }

    /*Generates upper case symbol of range from "A" to "Z" in ASCII*/
    static char generateAZUpperRangeChar() {
        return (char) (65 + Math.random() * (91 - 65));
    }

    /*Generates lower case symbol of range from "a" to "z" in ASCII*/
    static char generateAZLowerRangeChar() {
        return (char) (97 + Math.random() * (123 - 97));
    }

    /*Generates symbol number of range from "0" to "9" in ASCII*/
    static char generateNumberChar() {
        return (char) (48 + Math.random() * (58 - 48));
    }

    /*Generates special symbol in ASCII of 4 ranges, from "!" to "/", ":" to "@", "[" to "'" and "{" to "~" */
    static char generateSpecChar() {
        char sym;
        int k = (int) (Math.random() * 4);
        if (k == 0) {
            sym = (char) (33 + Math.random() * (48 - 33));
        } else if (k == 1) {
            sym = (char) (58 + Math.random() * (65 - 58));
        } else if (k == 2) {
            sym = (char) (91 + Math.random() * (97 - 91));
        } else {
            sym = (char) (123 + Math.random() * (127 - 123));
        }
        return sym;
    }

}
