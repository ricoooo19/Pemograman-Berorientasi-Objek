package com.kasir.service;

import com.kasir.model.ItemTransaksi;
import com.kasir.model.Produk;
import com.kasir.model.Transaksi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service pengelola logika bisnis Point of Sales (POS):
 * - Keranjang belanja sementara (Cart)
 * - Validasi ketersediaan stok
 * - Proses checkout / pembayaran
 * - Pengurangan stok produk secara otomatis
 * - Riwayat transaksi
 */
public class TransaksiService {

    private final IProdukService produkService;
    private final List<ItemTransaksi> keranjang;
    private final List<Transaksi> riwayatTransaksi;
    private int counterTrx = 1;

    public TransaksiService(IProdukService produkService) {
        this.produkService = produkService;
        this.keranjang = new ArrayList<>();
        this.riwayatTransaksi = new ArrayList<>();
    }

    /**
     * Menambahkan produk ke dalam keranjang POS
     */
    public boolean tambahKeKeranjang(String idProduk, int jumlah) {
        if (jumlah <= 0) {
            System.out.println("[Error] Jumlah beli harus lebih dari 0!");
            return false;
        }

        Produk produk = produkService.cariProduk(idProduk);
        if (produk == null) {
            System.out.println("[Error] Produk dengan ID '" + idProduk + "' tidak ditemukan!");
            return false;
        }

        // Cek total qty yang sudah ada di keranjang untuk produk ini
        int qtySudahAda = 0;
        for (ItemTransaksi it : keranjang) {
            if (it.getProduk().getIdProduk().equalsIgnoreCase(idProduk)) {
                qtySudahAda += it.getJumlah();
                break;
            }
        }

        if (qtySudahAda + jumlah > produk.getStok()) {
            System.out.printf("[Error] Stok tidak mencukupi! Tersedia: %d, di keranjang: %d, permintaan: %d%n",
                    produk.getStok(), qtySudahAda, jumlah);
            return false;
        }

        // Jika sudah ada di keranjang, tambahkan jumlahnya saja
        for (ItemTransaksi it : keranjang) {
            if (it.getProduk().getIdProduk().equalsIgnoreCase(idProduk)) {
                it.tambahJumlah(jumlah);
                return true;
            }
        }

        // Jika belum ada, masukkan item baru
        keranjang.add(new ItemTransaksi(produk, jumlah));
        return true;
    }

    /**
     * Menghapus item dari keranjang
     */
    public boolean hapusDariKeranjang(String idProduk) {
        for (int i = 0; i < keranjang.size(); i++) {
            if (keranjang.get(i).getProduk().getIdProduk().equalsIgnoreCase(idProduk)) {
                keranjang.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Mengosongkan keranjang (batalkan transaksi)
     */
    public void kosongkanKeranjang() {
        keranjang.clear();
    }

    public List<ItemTransaksi> getKeranjang() {
        return Collections.unmodifiableList(keranjang);
    }

    public boolean isKeranjangKosong() {
        return keranjang.isEmpty();
    }

    public double hitungSubtotalKeranjang() {
        double subtotal = 0;
        for (ItemTransaksi it : keranjang) {
            subtotal += it.getSubtotal();
        }
        return subtotal;
    }

    public double hitungDiskonKeranjang() {
        double diskon = 0;
        for (ItemTransaksi it : keranjang) {
            diskon += it.getDiskon();
        }
        return diskon;
    }

    public double hitungTotalSebelumPajak() {
        return hitungSubtotalKeranjang() - hitungDiskonKeranjang();
    }

    public double hitungPajakKeranjang() {
        return hitungTotalSebelumPajak() * Transaksi.TARIF_PPN;
    }

    public double hitungTotalAkhirKeranjang() {
        return hitungTotalSebelumPajak() + hitungPajakKeranjang();
    }

    /**
     * Memproses pembayaran transaksi:
     * 1. Validasi uang pembayaran mencukupi total belanja.
     * 2. Mengurangi stok produk di inventaris/katalog secara permanen.
     * 3. Membuat objek Transaksi & menyimpan ke riwayat.
     * 4. Mengosongkan keranjang belanja.
     */
    public Transaksi prosesPembayaran(String namaKasir, double uangBayar, String pesanPromo) {
        if (keranjang.isEmpty()) {
            throw new IllegalStateException("Keranjang masih kosong, tidak dapat melakukan transaksi!");
        }

        double totalHarusBayar = hitungTotalAkhirKeranjang();
        if (uangBayar < totalHarusBayar) {
            throw new IllegalArgumentException(
                    String.format("Uang pembayaran kurang! Total: Rp %,.2f, Pembayaran: Rp %,.2f",
                            totalHarusBayar, uangBayar));
        }

        // Kurangi stok barang secara permanen
        for (ItemTransaksi item : keranjang) {
            item.getProduk().kurangiStok(item.getJumlah());
        }

        // Generate ID transaksi unik
        String kodeTrx = String.format("TRX-%04d", counterTrx++);

        // Buat objek transaksi
        Transaksi transaksiBaru = new Transaksi(kodeTrx, namaKasir, keranjang, uangBayar);

        // Otomatis simpan struk ke file di dalam folder 'struk/'
        try {
            com.kasir.util.FileStrukUtil.simpanStruk(transaksiBaru, pesanPromo);
        } catch (Exception e) {
            System.err.println("[Peringatan] Gagal menyimpan file struk: " + e.getMessage());
        }

        // Masukkan ke riwayat
        riwayatTransaksi.add(transaksiBaru);

        // Bersihkan keranjang belanja
        keranjang.clear();

        return transaksiBaru;
    }

    public List<Transaksi> getRiwayatTransaksi() {
        return Collections.unmodifiableList(riwayatTransaksi);
    }
}
