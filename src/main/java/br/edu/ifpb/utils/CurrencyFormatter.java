package br.edu.ifpb.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {
    private static final NumberFormat currencyFormat;

    static {
        Locale brazilianLocale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
        currencyFormat = NumberFormat.getCurrencyInstance(brazilianLocale);
    }

    public static String format(double value) {
        return currencyFormat.format(value);
    }
}
