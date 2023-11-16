package edu.hw5.task8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task8 {
    private Task8() {
    }

    public static boolean oddLength(String input) {
        Pattern pattern = Pattern.compile("^[01]([01]{2})*$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean firstZeroAndOddLengthOrFirstOneAndEvenLength(String input) {
        Pattern pattern = Pattern.compile("^((0([01]{2})*)|(1([01]{2})*[01]))$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean zerosCountIsMultipleOfThree(String input) {
        Pattern pattern = Pattern.compile("^(1*01*01*01*)+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean anyStringOtherThanThreeOrTwoOnes(String input) {
        Pattern pattern = Pattern.compile("^(?!11$|111$)[01]*$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean everyOddSymbolIsOne(String input) {
        Pattern pattern = Pattern.compile("^1([01]1)*[01]?$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean atLeastTwoZerosAndNoMoreThanOneOne(String input) {
        Pattern pattern = Pattern.compile("^0+1?0+$|^0{2,}1?0*$|^0*1?0{2,}");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean noSequentialValueOfOne(String input) {
        Pattern pattern = Pattern.compile("^(1$|0+|10+)(10+)*1?$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
