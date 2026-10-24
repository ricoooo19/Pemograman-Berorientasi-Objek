package com.kasir.service;

import com.kasir.model.Produk;
import java.util.List;

/**
 * =========================================================================
 * KONSEP OOP: ABSTRAKSI (Interface CRUD Produk)
 * =========================================================================
 * Interface IProdukService mendefinisikan operasi-operasi CRUD:
 * - Create : tambahProduk
 * - Read   : getSemuaProduk, cariProduk
 * - Update : updateProduk
 * - Delete : hapusProduk
 */
public interface IProdukService {

    /**
     * CREATE: Menambahkan produk baru ke dalam katalog
     */
    void tambahProduk(Produk produk);

    /**
     * READ: Mengambil seluruh daftar produk
     */
    List<Produk> getSemuaProduk();

    /**
     * READ (Polimorfisme Overloading 1): Mencari produk berdasarkan ID eksak
     */
    Produk cariProduk(String id);

    /**
     * READ (Polimorfisme Overloading 2): Mencari produk berdasarkan kata kunci nama
     */
    List<Produk> cariProduk(String keyword, boolean cariNama);

    /**
     * UPDATE: Memperbarui data produk
     */
    boolean updateProduk(Produk produkBaru);

    /**
     * DELETE: Menghapus produk dari katalog berdasarkan ID
     */
    boolean hapusProduk(String id);
}
