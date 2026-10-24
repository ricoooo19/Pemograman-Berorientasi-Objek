package com.kasir.model;

/**
 * =========================================================================
 * KONSEP OOP: PEWARISAN (Inheritance) & POLIMORFISME (Dynamic / Overriding)
 * =========================================================================
 * ProdukElektronik adalah subclass yang mewarisi dari Produk.
 * Memiliki atribut spesifik garansi serta aturan diskon yang berbeda.
 */
public class ProdukElektronik extends Produk {

    // =====================================================================
    // 1. ATRIBUT KHUSUS (Penerapan ENKAPSULASI)
    // =====================================================================
    private int garansiBulan; // Masa garansi dalam satuan bulan

    // =====================================================================
    // Konstruktor
    // =====================================================================
    public ProdukElektronik(String idProduk, String nama, double harga, int stok, int garansiBulan) {
        super(idProduk, nama, harga, stok);
        setGaransiBulan(garansiBulan);
    }

    // =====================================================================
    // 2. ENKAPSULASI: Getter dan Setter
    // =====================================================================
    public int getGaransiBulan() {
        return garansiBulan;
    }

    public void setGaransiBulan(int garansiBulan) {
        if (garansiBulan >= 0) {
            this.garansiBulan = garansiBulan;
        } else {
            throw new IllegalArgumentException("Masa garansi tidak boleh negatif!");
        }
    }

    // =====================================================================
    // 3. POLIMORFISME DINAMIS (Method Overriding)
    // =====================================================================

    /**
     * Logika Diskon Elektronik:
     * - Jika beli >= 2 unit dan subtotal > Rp 500.000, diskon 5%
     * - Plus potongan promo Rp 25.000
     */
    @Override
    public double hitungDiskon(int jumlah) {
        double subtotal = getHarga() * jumlah;
        if (jumlah >= 2 && subtotal >= 500_000) {
            return (subtotal * 0.05) + 25_000;
        } else if (jumlah >= 2) {
            return 25_000;
        }
        return 0.0;
    }

    @Override
    public String getKategori() {
        return "Elektronik & Gadget";
    }

    @Override
    public String getInfoKhusus() {
        return "Garansi Resmi: " + garansiBulan + " Bulan";
    }
}
