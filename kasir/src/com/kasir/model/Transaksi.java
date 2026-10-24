package com.kasir.model;

import com.kasir.service.ICetakStruk;
import com.kasir.util.FormatUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * =========================================================================
 * KONSEP OOP: ABSTRAKSI (implements ICetakStruk) & POLIMORFISME STATIS (Overloading)
 * =========================================================================
 * Kelas Transaksi merepresentasikan nota transaksi penjualan kasir (Point of Sales).
 */
public class Transaksi implements ICetakStruk {

    // =====================================================================
    // ATRIBUT STATIS (Konstanta Toko) & INSTANCE
    // =====================================================================
    public static final String NAMA_TOKO = "MINIMARKET RIKO";
       public static final String ALAMAT_TOKO = "Jl.SENDANG REJO KEC.PARENGAN";
    public static final double TARIF_PPN = 0.11; // PPN 11%

    private String kodeTransaksi;
    private LocalDateTime waktuTransaksi;
    private String namaKasir;
    private List<ItemTransaksi> daftarItem;
    private double totalKotor;
    private double totalDiskon;
    private double pajakPPN;
    private double totalAkhir;
    private double uangDiterima;
    private double uangKembalian;

    public Transaksi(String kodeTransaksi, String namaKasir, List<ItemTransaksi> itemKeranjang, double uangDiterima) {
        this.kodeTransaksi = kodeTransaksi;
        this.waktuTransaksi = LocalDateTime.now();
        this.namaKasir = (namaKasir == null || namaKasir.trim().isEmpty()) ? "Kasir-01" : namaKasir.trim();
        this.daftarItem = new ArrayList<>(itemKeranjang);
        this.uangDiterima = uangDiterima;

        kalkulasiTransaksi();
    }

    /**
     * Menghitung total kotor, diskon, PPN, total akhir dan kembalian
     */
    private void kalkulasiTransaksi() {
        this.totalKotor = 0;
        this.totalDiskon = 0;

        for (ItemTransaksi item : daftarItem) {
            this.totalKotor += item.getSubtotal();
            this.totalDiskon += item.getDiskon();
        }

        double subtotalSetelahDiskon = this.totalKotor - this.totalDiskon;
        this.pajakPPN = subtotalSetelahDiskon * TARIF_PPN;
        this.totalAkhir = subtotalSetelahDiskon + this.pajakPPN;
        this.uangKembalian = this.uangDiterima - this.totalAkhir;
    }

    // =====================================================================
    // ENKAPSULASI (Getters)
    // =====================================================================
    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public LocalDateTime getWaktuTransaksi() {
        return waktuTransaksi;
    }

    public String getNamaKasir() {
        return namaKasir;
    }

    public List<ItemTransaksi> getDaftarItem() {
        return daftarItem;
    }

    public double getTotalKotor() {
        return totalKotor;
    }

    public double getTotalDiskon() {
        return totalDiskon;
    }

    public double getPajakPPN() {
        return pajakPPN;
    }

    public double getTotalAkhir() {
        return totalAkhir;
    }

    public double getUangDiterima() {
        return uangDiterima;
    }

    public double getUangKembalian() {
        return uangKembalian;
    }

    // =====================================================================
    // POLIMORFISME: OVERLOADING (Statis) & IMPLEMENTASI ABSTRAKSI
    // =====================================================================

    /**
     * Menghasilkan teks struk belanja berformat string rapi
     */
    public String getFormatStruk() {
        return getFormatStruk("Terima kasih atas kunjungan Anda! Barang yang sudah dibeli tidak dapat ditukar.");
    }

    /**
     * Menghasilkan teks struk belanja dengan pesan kustom
     */
    public String getFormatStruk(String pesanPromo) {
        StringBuilder sb = new StringBuilder();
        String line50 = "==================================================\n";
        String dash50 = "--------------------------------------------------\n";

        sb.append(line50);
        sb.append(String.format("%32s%n", NAMA_TOKO));
        sb.append(String.format("%42s%n", ALAMAT_TOKO));
        sb.append(line50);
        sb.append(String.format("No. Transaksi : %-20s%n", kodeTransaksi));
        sb.append(String.format("Waktu         : %-20s%n", FormatUtil.formatTanggal(waktuTransaksi)));
        sb.append(String.format("Kasir         : %-20s%n", namaKasir));
        sb.append(dash50);

        sb.append(String.format("%-20s %4s %11s %11s%n", "Produk", "Qty", "Harga", "Subtotal"));
        sb.append(dash50);

        for (ItemTransaksi it : daftarItem) {
            sb.append(String.format("%-20s %4d %11s %11s%n",
                    it.getProduk().getNama(),
                    it.getJumlah(),
                    FormatUtil.toRupiah(it.getProduk().getHarga()),
                    FormatUtil.toRupiah(it.getSubtotal())));

            if (it.getDiskon() > 0) {
                sb.append(String.format("  * Diskon (%s): -%s%n",
                        it.getProduk().getKategori(),
                        FormatUtil.toRupiah(it.getDiskon())));
            }
        }

        sb.append(dash50);
        sb.append(String.format("%-28s : %19s%n", "Total Kotor", FormatUtil.toRupiah(totalKotor)));
        sb.append(String.format("%-28s : %19s%n", "Total Hemat (Diskon)", "-" + FormatUtil.toRupiah(totalDiskon)));
        sb.append(String.format("%-28s : %19s%n", "PPN (11%)", FormatUtil.toRupiah(pajakPPN)));
        sb.append(line50);
        sb.append(String.format("%-28s : %19s%n", "TOTAL BAYAR", FormatUtil.toRupiah(totalAkhir)));
        sb.append(String.format("%-28s : %19s%n", "TUNAI / BAYAR", FormatUtil.toRupiah(uangDiterima)));
        sb.append(String.format("%-28s : %19s%n", "KEMBALIAN", FormatUtil.toRupiah(uangKembalian)));
        sb.append(line50);

        if (pesanPromo != null && !pesanPromo.trim().isEmpty()) {
            sb.append("Catatan:\n");
            sb.append(pesanPromo).append("\n");
            sb.append(line50);
        }
        return sb.toString();
    }

    /**
     * Implementasi interface ICetakStruk: cetakStruk() versi 1 (standar)
     */
    @Override
    public void cetakStruk() {
        System.out.print(getFormatStruk());
    }

    /**
     * POLIMORFISME STATIS (Method Overloading): cetakStruk() versi 2 (dengan pesan custom)
     */
    @Override
    public void cetakStruk(String pesanPromo) {
        System.out.print(getFormatStruk(pesanPromo));
    }
}
