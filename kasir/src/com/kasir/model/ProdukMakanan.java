package com.kasir.model;

/**
 * =========================================================================
 * KONSEP OOP: PEWARISAN (Inheritance) & POLIMORFISME (Dynamic / Overriding)
 * =========================================================================
 * ProdukMakanan adalah subclass yang mewarisi atribut dan perilaku dari
 * superclass Produk (menggunakan keyword 'extends').
 */
public class ProdukMakanan extends Produk {

    // =====================================================================
    // 1. ATRIBUT KHUSUS (Penerapan ENKAPSULASI)
    // =====================================================================
    private String tanggalKadaluarsa; // Format: YYYY-MM-DD

    // =====================================================================
    // Konstruktor dengan memanggil konstruktor superclass via 'super'
    // =====================================================================
    public ProdukMakanan(String idProduk, String nama, double harga, int stok, String tanggalKadaluarsa) {
        super(idProduk, nama, harga, stok); // Memanggil constructor Produk
        setTanggalKadaluarsa(tanggalKadaluarsa);
    }

    // =====================================================================
    // 2. ENKAPSULASI: Getter dan Setter
    // =====================================================================
    public String getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }

    public void setTanggalKadaluarsa(String tanggalKadaluarsa) {
        if (tanggalKadaluarsa != null && !tanggalKadaluarsa.trim().isEmpty()) {
            this.tanggalKadaluarsa = tanggalKadaluarsa.trim();
        } else {
            this.tanggalKadaluarsa = "Tidak Tercantum";
        }
    }

    // =====================================================================
    // 3. POLIMORFISME DINAMIS (Method Overriding)
    // Mengimplementasikan abstract method dari superclass Produk
    // =====================================================================

    /**
     * Logika Diskon Makanan:
     * - Jika beli >= 5 buah, pembeli mendapat diskon grosir 10% dari subtotal makanan.
     */
    @Override
    public double hitungDiskon(int jumlah) {
        if (jumlah >= 5) {
            double subtotal = getHarga() * jumlah;
            return subtotal * 0.10; // Diskon 10%
        }
        return 0.0;
    }

    @Override
    public String getKategori() {
        return "Makanan & Minuman";
    }

    @Override
    public String getInfoKhusus() {
        return "Kadaluarsa: " + tanggalKadaluarsa;
    }
}
