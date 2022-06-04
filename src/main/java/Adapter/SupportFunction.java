/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import constant.GeneralStringConstant;
import constant.TitleStringConstant;
import DTO.BinTypeEnum;

/**
 *
 * @author Admin
 */
public class SupportFunction {

    public static boolean checkNumber(String s) {
        if (s == null) {
            // checks if the String is null 
            return false;
        }
        int len = s.length();
        for (int i = 0; i < len; i++) {
            // checks whether the character is not a digit
            // if it is not a digit then it will return false
            if ((Character.isDigit(s.charAt(i)) == false)) {
                return false;
            }
        }
        int num = Integer.valueOf(s);
        return num > 0;
    }

    public static boolean checkPriceNumber(String s) {
        if (s == null) {
            // checks if the String is null 
            return false;
        }
        int len = s.length();
        for (int i = 0; i < len; i++) {
            // checks whether the character is neither a '.' nor a digit
            // if it is neither a '.' nor a digit then it will return false
            if ((Character.isDigit(s.charAt(i)) == false) && s.charAt(i) != '.') {
                return false;
            }
        }
        Double numPrice = Double.valueOf(s);
        return numPrice > 0;
    }

    public static BinTypeEnum convertStringToBinType(String type) {
        switch (type) {
            case TitleStringConstant.BOOK -> {
                return BinTypeEnum.Book;
            }
            case TitleStringConstant.PUBLISHER -> {
                return BinTypeEnum.Publisher;
            }
            default -> {
                return null;
            }
        }
    }

    public static String convertBinTypeEnumToString(BinTypeEnum type) {
        if (type == BinTypeEnum.Book) {
            return TitleStringConstant.BOOK;
        } else if (type == BinTypeEnum.Publisher) {
            return TitleStringConstant.PUBLISHER;
        } else {
            return GeneralStringConstant.GENERAL_EMPTY;
        }
    }
}
