package com.kasir.model;

import com.kasir.util.FormatUtil;

/**
 * =========================================================================
 * KONSEP OOP: ABSTRAKSI (Abstract Class) & ENKAPSULASI
 * =========================================================================
 * Produk adalah kelas abstrak yang mendefinisikan kerangka umum setiap produk.
 * Objek Produk tidak bisa di-instansiasi langsung (new Produk()), melainkan
 * harus melalui kelas turunannya (ProdukMakanan, ProdukElektronik).
 */
public abstract class Produk {

    // =====================================================================
    // 1. ATRIBUT (Instance Fields) - Penerapan ENKAPSULASI (private access)
    // =====================================================================
    private String idProduk;
    private String nama;
    private double harga;
    private int stok;

    // Konstruktor utama
    public Produk(String idProduk, String nama, double harga, int stok) {
        this.idProduk = idProduk;
        this.nama = nama;
        setHarga(harga); // menggunakan setter untuk validasi enkapsulasi
        setStok(stok);   // menggunakan setter untuk validasi enkapsulasi
    }

    // =====================================================================
    // 2. ENKAPSULASI (Getter dan Setter dengan validasi data)
    // =====================================================================
    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        if (idProduk != null && !idProduk.trim().isEmpty()) {
            this.idProduk = idProduk.trim();
        } else {
            throw new IllegalArgumentException("ID Produk tidak boleh kosong!");
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama.trim();
        } else {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong!");
        }
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        if (harga >= 0) {
            this.harga = harga;
        } else {
            throw new IllegalArgumentException("Harga tidak boleh bernilai negatif!");
        }
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        if (stok >= 0) {
            this.stok = stok;
        } else {
            throw new IllegalArgumentException("Stok tidak boleh bernilai negatif!");
        }
    }

    /**
     * Method enkapsulasi bisnis: mengurangi stok dengan proteksi validasi
     */
    public boolean kurangiStok(int jumlah) {
        if (jumlah <= 0) {
            System.out.println("[Error] Jumlah pengurangan stok harus lebih dari 0.");
            return false;
        }
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
            return true;
        } else {
            System.out.println("[Error] Stok tidak mencukupi! Sisa stok: " + this.stok);
            return false;
        }
    }

    /**
     * Method enkapsulasi bisnis: menambah stok barang
     */
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            this.stok += jumlah;
        } else {
            throw new IllegalArgumentException("Jumlah penambahan stok harus lebih dari 0!");
        }
    }

    // =====================================================================
    // 3. ABSTRAKSI (Abstract Methods)
    // Method-method ini WAJIB diimplementasikan (di-override) oleh subclass
    // sesuai karakteristik unik masing-masing produk (POLIMORFISME DINAMIS).
    // =====================================================================

    /**
     * Menghitung diskon berdasarkan tipe produk dan jumlah yang dibeli.
     * Masing-masing subclass memiliki logika diskon yang berbeda!
     */
    public abstract double hitungDiskon(int jumlah);

    /**
     * Mengembalikan nama kategori dari produk turunan
     */
    public abstract String getKategori();

    /**
     * Menampilkan detail spesifikasi khusus produk turunan
     */
    public abstract String getInfoKhusus();

    // =====================================================================
    // Method Konkret Tambahan
    // =====================================================================
    /**
     * Menghitung total harga kotor sebelum diskon
     */
    public double hitungSubtotal(int jumlah) {
        return this.harga * jumlah;
    }

    /**
     * Menghitung total harga bersih setelah dikurangi diskon polimorfis
     */
    public double hitungTotalBersih(int jumlah) {
        return hitungSubtotal(jumlah) - hitungDiskon(jumlah);
    }

    @Override
    public String toString() {
        return String.format("[%s] %-18s | Kategori: %-18s | Harga: %-14s | Stok: %3d | Info: %s",
                idProduk, nama, getKategori(), FormatUtil.toRupiah(harga), stok, getInfoKhusus());
    }
}
