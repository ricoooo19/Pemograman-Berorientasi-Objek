package com.kasir.main;

import com.kasir.gui.KasirMainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * =========================================================================
 * ENTRY POINT APLIKASI KASIR POS
 * =========================================================================
 * Menjalankan antarmuka grafis desktop (GUI JFrame) secara otomatis
 * ketika proyek di-Run dari Apache NetBeans atau melalui file run.bat.
 */
public class MainKasir {

    public static void main(String[] args) {
        // Terapkan Look & Feel sistem operasi (Windows Look & Feel) agar UI modern & rapi
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Jika gagal, menggunakan Look & Feel standar Java
        }

        // Jalankan jendela GUI pada Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            KasirMainFrame frame = new KasirMainFrame();
            frame.setVisible(true);
        });
    }
}
