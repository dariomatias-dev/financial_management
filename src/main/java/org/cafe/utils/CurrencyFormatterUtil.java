package org.cafe.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatterUtil {
    private static final NumberFormat currencyFormat;

    static {
        Locale brazilianLocale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
        currencyFormat = NumberFormat.getCurrencyInstance(brazilianLocale);
    }

    public static String format(double value) {
        return currencyFormat.format(value);
    }
}
