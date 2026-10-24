package com.kasir.gui;

import java.awt.Color;

/**
 * Pengelola Palet Warna (Theme Manager) untuk Aplikasi Kasir.
 * Memungkinkan pergantian tema warna secara instan dan mempercantik tampilan UI.
 */
public class TemaWarna {

    public final String nama;
    public final Color headerBg;
    public final Color headerText;
    public final Color accentColor;
    public final Color btnBayarBg;
    public final Color btnTambahBg;
    public final Color btnUbahBg;
    public final Color btnHapusBg;
    public final Color tableHeaderBg;
    public final Color cardSummaryBg;

    public TemaWarna(String nama, Color headerBg, Color headerText, Color accentColor,
                     Color btnBayarBg, Color btnTambahBg, Color btnUbahBg, Color btnHapusBg,
                     Color tableHeaderBg, Color cardSummaryBg) {
        this.nama = nama;
        this.headerBg = headerBg;
        this.headerText = headerText;
        this.accentColor = accentColor;
        this.btnBayarBg = btnBayarBg;
        this.btnTambahBg = btnTambahBg;
        this.btnUbahBg = btnUbahBg;
        this.btnHapusBg = btnHapusBg;
        this.tableHeaderBg = tableHeaderBg;
        this.cardSummaryBg = cardSummaryBg;
    }

    // 1. TEMA EMERALD MINT (Default - Kasir Modern / Minimarket Kekinian)
    public static final TemaWarna EMERALD = new TemaWarna(
            "🌿 Emerald Green (Kasir Modern)",
            new Color(15, 23, 42),          // Header Dark Slate #0F172A
            Color.WHITE,
            new Color(16, 185, 129),        // Accent Mint Emerald #10B981
            new Color(5, 150, 105),         // Tombol Bayar Emerald #059669
            new Color(14, 165, 233),        // Tombol Tambah Sky Blue #0EA5E9
            new Color(79, 70, 229),         // Tombol Ubah Indigo #4F46E5
            new Color(225, 29, 72),         // Tombol Hapus Rose #E11D48
            new Color(30, 41, 59),          // Table Header #1E293B
            new Color(240, 253, 244)        // Card Bg Soft Mint
    );

    // 2. TEMA OCEAN BLUE (Biru Segar / Finansial)
    public static final TemaWarna OCEAN_BLUE = new TemaWarna(
            "🌊 Ocean Blue (Biru Segar)",
            new Color(12, 74, 110),         // Header Deep Ocean
            Color.WHITE,
            new Color(56, 189, 248),        // Accent Cyan
            new Color(2, 132, 199),         // Tombol Bayar Blue
            new Color(16, 185, 129),        // Tombol Tambah Green
            new Color(37, 99, 235),         // Tombol Ubah Royal Blue
            new Color(239, 68, 68),         // Tombol Hapus Red
            new Color(15, 23, 42),          // Table Header
            new Color(240, 249, 255)        // Card Bg Soft Blue
    );

    // 3. TEMA ROYAL PURPLE (Ungu Elegan / Gaya Fintech Modern)
    public static final TemaWarna ROYAL_PURPLE = new TemaWarna(
            "🍇 Royal Purple (Ungu Elegan)",
            new Color(46, 16, 101),         // Header Deep Purple
            Color.WHITE,
            new Color(192, 132, 252),       // Accent Light Purple
            new Color(124, 58, 237),        // Tombol Bayar Violet
            new Color(16, 185, 129),        // Tombol Tambah Green
            new Color(99, 102, 241),        // Tombol Ubah Indigo
            new Color(225, 29, 72),         // Tombol Hapus Rose
            new Color(30, 27, 75),          // Table Header
            new Color(250, 245, 255)        // Card Bg Soft Lavender
    );

    // 4. TEMA WARM AMBER (Nuansa Cafe & Resto)
    public static final TemaWarna WARM_AMBER = new TemaWarna(
            "☕ Warm Amber (Cafe & Resto)",
            new Color(41, 37, 36),          // Header Dark Stone
            Color.WHITE,
            new Color(245, 158, 11),        // Accent Golden Amber
            new Color(217, 119, 6),         // Tombol Bayar Amber
            new Color(13, 148, 136),        // Tombol Tambah Teal
            new Color(79, 70, 229),         // Tombol Ubah Indigo
            new Color(220, 38, 38),         // Tombol Hapus Red
            new Color(68, 64, 60),          // Table Header
            new Color(255, 251, 235)        // Card Bg Warm Cream
    );

    // 5. TEMA MIDNIGHT CHARCOAL (Minimalist Tech)
    public static final TemaWarna MIDNIGHT = new TemaWarna(
            "🖤 Midnight Charcoal (Teknologi)",
            new Color(9, 9, 11),            // Header Obsidian Black
            Color.WHITE,
            new Color(34, 211, 238),        // Accent Electric Cyan
            new Color(13, 148, 136),        // Tombol Bayar Teal
            new Color(59, 130, 246),        // Tombol Tambah Blue
            new Color(139, 92, 246),        // Tombol Ubah Purple
            new Color(244, 63, 94),         // Tombol Hapus Rose
            new Color(24, 24, 27),          // Table Header Zinc
            new Color(244, 244, 245)        // Card Bg Zinc Light
    );

    public static final TemaWarna[] SEMUA_TEMA = {
            EMERALD, OCEAN_BLUE, ROYAL_PURPLE, WARM_AMBER, MIDNIGHT
    };

    @Override
    public String toString() {
        return this.nama;
    }
}
