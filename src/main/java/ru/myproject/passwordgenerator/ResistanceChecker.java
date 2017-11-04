package ru.myproject.passwordgenerator;

import java.awt.*;

/**
 * Contains static method which returns colors that describes a password resistance
 * - RED if a password is weak
 * - YELLOW if a password has medium resistance
 * - GREEN if a password is strong
 *
 * Taken from:
 * https://habrahabr.ru/post/116331/
 */
final class ResistanceChecker {

    static Color passwordColor(char[] arr) {

        int intBits = (int) Math.floor(bitsCalc(arr));

        if (intBits < 56) {
            return Color.pink;
        } else if (intBits < 128) {
            return Color.yellow;
        } else return Color.green;

    }

    static int passwordRating(char[] arr) {

        int intBits;

        intBits = (int) Math.floor(bitsCalc(arr));

        if (arr.length == 0) {
            return 0;
        } else {
            if (intBits < 56) {
                return 1;
            } else if (intBits < 64) {
                return 2;
            } else if (intBits < 128) {
                return 3;
            } else return 4;
        }

    }

    private static double bitsCalc(char[] arr) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String upper_punct = "~`!@#$%^&*()-_+=";
        String digits = "1234567890";
        int totalChars = 0x7f - 0x20;
        int alphaChars = alpha.length();
        int upperChars = upper.length();
        int upper_punctChars = upper_punct.length();
        int digitChars = digits.length();
        int otherChars = totalChars - (alphaChars + upperChars + upper_punctChars + digitChars);

        boolean fAlpha = false;
        boolean fUpper = false;
        boolean fUpperPunct = false;
        boolean fDigit = false;
        boolean fOther = false;
        int charset = 0;

        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (alpha.indexOf(ch) != -1) {
                fAlpha = true;
            } else if (upper.indexOf(ch) != -1) {
                fUpper = true;
            } else if (upper_punct.indexOf(ch) != -1) {
                fUpperPunct = true;
            } else if (digits.indexOf(ch) != -1) {
                fDigit = true;
            } else fOther = true;
        }

        if (fAlpha) charset += alphaChars;
        if (fUpper) charset += upperChars;
        if (fUpperPunct) charset += upper_punctChars;
        if (fDigit) charset += digitChars;
        if (fOther) charset += otherChars;

        return Math.log(charset) * (arr.length / Math.log(2));
    }

}
