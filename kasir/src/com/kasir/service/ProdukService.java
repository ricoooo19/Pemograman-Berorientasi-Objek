package com.kasir.service;

import com.kasir.model.Produk;
import com.kasir.model.ProdukElektronik;
import com.kasir.model.ProdukMakanan;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * =========================================================================
 * KONSEP OOP: IMPLEMENTASI INTERFACE (ABSTRAKSI) & POLIMORFISME OVERLOADING
 * =========================================================================
 * Kelas pengelola data produk (Katalog Produk & Operasi CRUD).
 */
public class ProdukService implements IProdukService {

    // Penyimpanan data produk menggunakan ArrayList
    private final List<Produk> daftarProduk;

    public ProdukService() {
        this.daftarProduk = new ArrayList<>();
        inisialisasiDataAwal(); // Menyiapkan sampel data produk baru yang variatif & populer
    }

    /**
     * Mengisi data produk baru (makanan/minuman minimarket & gadget/elektronik kekinian)
     */
    private void inisialisasiDataAwal() {
        // === DAFTAR PRODUK MAKANAN & MINUMAN (Subclass ProdukMakanan) ===
        tambahProduk(new ProdukMakanan("M001", "Indomie Goreng Spesial", 3_500, 120, "2027-06-20"));
        tambahProduk(new ProdukMakanan("M002", "Ultra Milk Cokelat 250ml", 6_500, 50, "2027-03-10"));
        tambahProduk(new ProdukMakanan("M003", "Chitato Sapi Panggang 68g", 11_000, 40, "2026-12-15"));
        tambahProduk(new ProdukMakanan("M004", "Kopi Kenangan Mantan 220ml", 9_500, 35, "2027-01-25"));
        tambahProduk(new ProdukMakanan("M005", "SilverQueen Almond 58g", 15_500, 30, "2027-08-14"));
        tambahProduk(new ProdukMakanan("M006", "Teh Botol Sosro 450ml", 5_500, 65, "2027-04-30"));
        tambahProduk(new ProdukMakanan("M007", "Sari Roti Tawar Gandum", 17_000, 25, "2026-11-20"));

        // === DAFTAR PRODUK ELEKTRONIK & GADGET (Subclass ProdukElektronik) ===
        tambahProduk(new ProdukElektronik("E001", "TWS Earphone Bluetooth 5.3", 175_000, 15, 12));
        tambahProduk(new ProdukElektronik("E002", "Kabel Data Type-C Fast 65W", 45_000, 30, 6));
        tambahProduk(new ProdukElektronik("E003", "Flashdisk SanDisk 64GB USB 3.0", 85_000, 25, 24));
        tambahProduk(new ProdukElektronik("E004", "Powerbank 10000mAh Slim PD", 145_000, 18, 12));
        tambahProduk(new ProdukElektronik("E005", "Smartwatch Fitness Tracker", 320_000, 10, 12));
        tambahProduk(new ProdukElektronik("E006", "Mouse Wireless Silent Click", 75_000, 22, 12));
        tambahProduk(new ProdukElektronik("E007", "Charger GaN 33W Dual Port", 110_000, 16, 12));
    }

    // =====================================================================
    // 1. CREATE
    // =====================================================================
    @Override
    public void tambahProduk(Produk produk) {
        if (produk == null) {
            throw new IllegalArgumentException("Data produk tidak boleh null!");
        }
        // Validasi duplikasi ID
        if (cariProduk(produk.getIdProduk()) != null) {
            throw new IllegalArgumentException("Produk dengan ID '" + produk.getIdProduk() + "' sudah ada!");
        }
        daftarProduk.add(produk);
    }

    // =====================================================================
    // 2. READ
    // =====================================================================
    @Override
    public List<Produk> getSemuaProduk() {
        // Mengembalikan unmodifiable list untuk menjaga enkapsulasi data
        return Collections.unmodifiableList(daftarProduk);
    }

    /**
     * POLIMORFISME OVERLOADING Versi 1:
     * Mencari satu produk berdasarkan ID eksak (Case-insensitive)
     */
    @Override
    public Produk cariProduk(String id) {
        if (id == null) return null;
        for (Produk p : daftarProduk) {
            if (p.getIdProduk().equalsIgnoreCase(id.trim())) {
                return p;
            }
        }
        return null;
    }

    /**
     * POLIMORFISME OVERLOADING Versi 2:
     * Mencari daftar produk berdasarkan pencarian kata kunci pada Nama produk
     */
    @Override
    public List<Produk> cariProduk(String keyword, boolean cariNama) {
        List<Produk> hasil = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return hasil;
        }

        String kwLower = keyword.trim().toLowerCase();
        for (Produk p : daftarProduk) {
            if (cariNama && p.getNama().toLowerCase().contains(kwLower)) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    // =====================================================================
    // 3. UPDATE
    // =====================================================================
    @Override
    public boolean updateProduk(Produk produkBaru) {
        if (produkBaru == null) return false;

        for (int i = 0; i < daftarProduk.size(); i++) {
            Produk lama = daftarProduk.get(i);
            if (lama.getIdProduk().equalsIgnoreCase(produkBaru.getIdProduk())) {
                daftarProduk.set(i, produkBaru);
                return true;
            }
        }
        return false;
    }

    // =====================================================================
    // 4. DELETE
    // =====================================================================
    @Override
    public boolean hapusProduk(String id) {
        Produk target = cariProduk(id);
        if (target != null) {
            return daftarProduk.remove(target);
        }
        return false;
    }
}
