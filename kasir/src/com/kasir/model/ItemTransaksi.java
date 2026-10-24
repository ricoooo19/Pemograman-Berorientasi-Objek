package com.kasir.model;

import com.kasir.util.FormatUtil;

/**
 * Representasi 1 baris item di dalam keranjang belanja Point of Sales.
 * Mendemonstrasikan POLIMORFISME: objek 'produk' bertipe Produk (induk),
 * namun saat kalkulasi diskon dipanggil, method subclass yang sesuai
 * akan dieksekusi secara otomatis (Dynamic Polymorphism).
 */
public class ItemTransaksi {

    // =====================================================================
    // ATRIBUT (Enkapsulasi)
    // =====================================================================
    private Produk produk;      // Polimorfisme: referensi superclass Produk
    private int jumlah;
    private double subtotal;
    private double diskon;
    private double totalAkhir;

    public ItemTransaksi(Produk produk, int jumlah) {
        this.produk = produk;
        this.jumlah = jumlah;
        hitungUlang();
    }

    /**
     * Menghitung subtotal, diskon polimorfis, dan total bersih item
     */
    public void hitungUlang() {
        this.subtotal = produk.getHarga() * this.jumlah;
        // POLIMORFISME: method hitungDiskon milik ProdukMakanan atau ProdukElektronik
        // akan dijalankan secara dinamis sesuai tipe objek sebenarnya!
        this.diskon = produk.hitungDiskon(this.jumlah);
        this.totalAkhir = this.subtotal - this.diskon;
    }

    // =====================================================================
    // Getter & Setter (Enkapsulasi)
    // =====================================================================
    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
        hitungUlang();
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        if (jumlah > 0) {
            this.jumlah = jumlah;
            hitungUlang();
        } else {
            throw new IllegalArgumentException("Jumlah item harus lebih dari 0!");
        }
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDiskon() {
        return diskon;
    }

    public double getTotalAkhir() {
        return totalAkhir;
    }

    public void tambahJumlah(int tambahan) {
        setJumlah(this.jumlah + tambahan);
    }

    @Override
    public String toString() {
        return String.format("%-18s x%-3d @ %-12s (Disc: %-10s) = %s",
                produk.getNama(),
                jumlah,
                FormatUtil.toRupiah(produk.getHarga()),
                FormatUtil.toRupiah(diskon),
                FormatUtil.toRupiah(totalAkhir));
    }
}
