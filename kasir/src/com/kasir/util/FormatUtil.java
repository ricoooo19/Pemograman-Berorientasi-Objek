package com.kasir.util;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Utility class untuk membantu formatting mata uang, tanggal, dan tampilan teks.
 */
public class FormatUtil {

    private static final Locale LOCALE_ID = Locale.forLanguageTag("id-ID");
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(LOCALE_ID);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    /**
     * Memformat angka double menjadi format Rupiah, contoh: Rp 25.000,00
     */
    public static String toRupiah(double nominal) {
        return CURRENCY_FORMAT.format(nominal);
    }

    /**
     * Memformat LocalDateTime menjadi string tanggal dan waktu Indonesia
     */
    public static String formatTanggal(LocalDateTime waktu) {
        if (waktu == null) return "-";
        return waktu.format(DATE_FORMAT);
    }

    /**
     * Membuat garis pembatas untuk tampilan console
     */
    public static void printLine(int length, char ch) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(ch);
        }
        System.out.println(sb.toString());
    }
}
