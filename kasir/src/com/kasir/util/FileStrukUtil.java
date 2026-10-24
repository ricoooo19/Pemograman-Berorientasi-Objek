package com.kasir.util;

import com.kasir.model.Transaksi;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility untuk menyimpan, mengelola, dan membuka file struk transaksi
 * pada folder penyimpanan 'struk/'.
 */
public class FileStrukUtil {

    public static final String DIRECTORY_NAME = "struk";

    /**
     * Memastikan folder penyimpanan 'struk/' sudah ada
     */
    public static File getFolderStruk() {
        File folder = new File(DIRECTORY_NAME);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    /**
     * Menyimpan data struk transaksi ke dalam file teks (.txt) di dalam folder 'struk/'
     * Contoh nama file: TRX-0001.txt
     */
    public static File simpanStruk(Transaksi transaksi, String pesanPromo) throws IOException {
        File folder = getFolderStruk();
        String namaFile = transaksi.getKodeTransaksi() + ".txt";
        File fileStruk = new File(folder, namaFile);

        String isiStruk;
        if (pesanPromo == null || pesanPromo.trim().isEmpty()) {
            isiStruk = transaksi.getFormatStruk();
        } else {
            isiStruk = transaksi.getFormatStruk(pesanPromo);
        }

        try (FileWriter writer = new FileWriter(fileStruk, StandardCharsets.UTF_8)) {
            writer.write(isiStruk);
        }

        return fileStruk;
    }

    /**
     * Membuka folder 'struk/' langsung di Windows File Explorer
     */
    public static boolean bukaFolderStruk() {
        try {
            File folder = getFolderStruk();
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(folder);
                return true;
            }
        } catch (Exception e) {
            System.err.println("[Error] Gagal membuka folder: " + e.getMessage());
        }
        return false;
    }

    /**
     * Mengambil daftar seluruh file struk yang telah tersimpan di folder 'struk/'
     */
    public static List<File> getDaftarFileStruk() {
        File folder = getFolderStruk();
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        if (files == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(files);
    }
}
