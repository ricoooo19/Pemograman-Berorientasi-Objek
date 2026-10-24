package com.kasir.gui;

import com.kasir.model.*;
import com.kasir.service.*;
import com.kasir.util.FormatUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

/**
 * =========================================================================
 * GUI UTAMA APLIKASI KASIR POS (SWING / JFRAME)
 * =========================================================================
 * Tampilan antarmuka grafis desktop modern dengan palet warna terintegrasi
 * dan fitur pergantian tema warna secara interaktif.
 */
public class KasirMainFrame extends JFrame {

    private final IProdukService produkService;
    private final TransaksiService transaksiService;
    private TemaWarna temaAktif;

    // Komponen Header
    private JPanel pnlHeader;
    private JLabel lblStatusBadge;
    private JComboBox<TemaWarna> cmbTema;

    // --- Komponen Tab POS (Point of Sales) ---
    private DefaultTableModel tblModelKatalog;
    private JTable tblKatalog;
    private JTextField txtCariPOS;
    private JSpinner spnQtyPOS;
    private JButton btnTambahKeranjang;
    private DefaultTableModel tblModelKeranjang;
    private JTable tblKeranjang;

    private JPanel pnlKalkulasi;
    private JLabel lblSubtotalNilai;
    private JLabel lblDiskonNilai;
    private JLabel lblPajakNilai;
    private JLabel lblTotalAkhirNilai;
    private JTextField txtKasirPOS;
    private JTextField txtUangBayarPOS;
    private JLabel lblKembalianNilai;
    private JTextField txtCatatanPromoPOS;
    private JButton btnBayarStruk;

    // --- Komponen Tab CRUD Produk ---
    private DefaultTableModel tblModelCRUD;
    private JTable tblCRUD;
    private JTextField txtCariCRUD;
    private JTextField txtIdCRUD;
    private JTextField txtNamaCRUD;
    private JComboBox<String> cmbKategoriCRUD;
    private JTextField txtHargaCRUD;
    private JTextField txtStokCRUD;
    private JLabel lblAtributKhususCRUD;
    private JTextField txtAtributKhususCRUD;
    private JButton btnTambahCRUD;
    private JButton btnUbahCRUD;
    private JButton btnHapusCRUD;
    private JButton btnResetCRUD;

    // --- Komponen Tab Riwayat Transaksi ---
    private DefaultTableModel tblModelRiwayat;
    private JTable tblRiwayat;
    private JButton btnCetakUlang;
    private JButton btnBukaFolderRiwayat;

    public KasirMainFrame() {
        this.produkService = new ProdukService();
        this.transaksiService = new TransaksiService(this.produkService);
        this.temaAktif = TemaWarna.EMERALD; // Default tema Emerald Green modern

        inisialisasiUI();
        muatDataKatalogPOS(produkService.getSemuaProduk());
        muatDataTabelCRUD(produkService.getSemuaProduk());
        muatDataRiwayat();
        terapkanTemaWarna(temaAktif);
    }

    private void inisialisasiUI() {
        setTitle("Aplikasi Kasir POS - " + Transaksi.NAMA_TOKO);
        setSize(1120, 740);
        setMinimumSize(new Dimension(980, 640));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Atas
        pnlHeader = buatPanelHeader();
        add(pnlHeader, BorderLayout.NORTH);

        // Tabbed Pane Utama
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 13));

        tabbedPane.addTab("🛒 Transaksi Kasir ", buatTabPOS());
        tabbedPane.addTab("📦 Manajemen Produk ", buatTabCRUD());
        tabbedPane.addTab("📜 Riwayat Transaksi", buatTabRiwayat());
        

        add(tabbedPane, BorderLayout.CENTER);
    }

    // =========================================================================
    // HEADER ATAS DENGAN PEMILIH TEMA WARNA
    // =========================================================================
    private JPanel buatPanelHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(temaAktif.headerBg);
        header.setBorder(new EmptyBorder(12, 20, 12, 20));

        JPanel pnlKiri = new JPanel(new GridLayout(2, 1));
        pnlKiri.setOpaque(false);
        JLabel lblJudul = new JLabel("🛒 " + Transaksi.NAMA_TOKO);
        lblJudul.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblJudul.setForeground(Color.WHITE);

        JLabel lblSubJudul = new JLabel(Transaksi.ALAMAT_TOKO + " | Kasir POS Modern Berbasis OOP Java");
        lblSubJudul.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSubJudul.setForeground(new Color(210, 225, 245));

        pnlKiri.add(lblJudul);
        pnlKiri.add(lblSubJudul);

        // Panel Kanan: Pemilih Tema & Status
        JPanel pnlKanan = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 5));
        pnlKanan.setOpaque(false);

        JLabel lblPilihTema = new JLabel("🎨 Tema Warna:");
        lblPilihTema.setForeground(Color.WHITE);
        lblPilihTema.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlKanan.add(lblPilihTema);

        cmbTema = new JComboBox<>(TemaWarna.SEMUA_TEMA);
        cmbTema.setSelectedItem(temaAktif);
        cmbTema.setPreferredSize(new Dimension(215, 28));
        cmbTema.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cmbTema.addActionListener(e -> {
            TemaWarna terpilih = (TemaWarna) cmbTema.getSelectedItem();
            if (terpilih != null) {
                terapkanTemaWarna(terpilih);
            }
        });
        pnlKanan.add(cmbTema);

        lblStatusBadge = new JLabel(" 🟢 Online ");
        lblStatusBadge.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblStatusBadge.setForeground(temaAktif.accentColor);
        pnlKanan.add(lblStatusBadge);

        header.add(pnlKiri, BorderLayout.WEST);
        header.add(pnlKanan, BorderLayout.EAST);
        return header;
    }

    /**
     * Mengubah seluruh warna antarmuka aplikasi secara instan
     */
    public void terapkanTemaWarna(TemaWarna tema) {
        this.temaAktif = tema;

        // 1. Header
        pnlHeader.setBackground(tema.headerBg);
        lblStatusBadge.setForeground(tema.accentColor);

        // 2. Tombol Aksi POS (Selalu Hitam Elegan)
        if (btnBayarStruk != null) {
            styleTombolHitamBesar(btnBayarStruk);
        }
        if (btnTambahKeranjang != null) {
            styleTombolHitam(btnTambahKeranjang);
        }
        if (pnlKalkulasi != null) {
            pnlKalkulasi.setBackground(tema.cardSummaryBg);
        }
        if (lblDiskonNilai != null) {
            lblDiskonNilai.setForeground(new Color(4, 120, 87));
        }

        // 3. Tombol Aksi CRUD (Selalu Hitam Elegan)
        if (btnTambahCRUD != null) {
            styleTombolHitam(btnTambahCRUD);
        }
        if (btnUbahCRUD != null) {
            styleTombolHitam(btnUbahCRUD);
        }
        if (btnHapusCRUD != null) {
            styleTombolHitam(btnHapusCRUD);
        }

        // 4. Riwayat Transaksi (Selalu Hitam Elegan)
        if (btnCetakUlang != null) {
            styleTombolHitam(btnCetakUlang);
        }
        if (btnBukaFolderRiwayat != null) {
            styleTombolHitam(btnBukaFolderRiwayat);
        }

        // 5. Header Tabel-Tabel
        styleTableHeader(tblKatalog, tema);
        styleTableHeader(tblKeranjang, tema);
        styleTableHeader(tblCRUD, tema);
        styleTableHeader(tblRiwayat, tema);

        repaint();
    }

    private void styleTableHeader(JTable table, TemaWarna tema) {
        if (table == null) return;
        JTableHeader th = table.getTableHeader();
        th.setBackground(new Color(226, 232, 240)); // Background kotak header terang
        th.setForeground(Color.BLACK);              // Teks HITAM tebal
        th.setFont(new Font("SansSerif", Font.BOLD, 12));
        th.setPreferredSize(new Dimension(0, 30));

        // Header renderer kustom agar warna teks HITAM pekat konsisten di semua platform/NetBeans
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, col);
                lbl.setBackground(new Color(226, 232, 240)); // Background kotak header
                lbl.setForeground(Color.BLACK);              // WARNA TEKS HITAM
                lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
                lbl.setHorizontalAlignment(col == 0 || col == 4 ? JLabel.CENTER : JLabel.LEFT);
                lbl.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 2, 1, new Color(180, 195, 215)),
                        BorderFactory.createEmptyBorder(5, 8, 5, 8)
                ));
                return lbl;
            }
        };

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        th.repaint();
    }

    // =========================================================================
    // TAB 1: POINT OF SALES (KASIR)
    // =========================================================================
    private JPanel buatTabPOS() {
        JPanel pnlPOS = new JPanel(new GridLayout(1, 2, 12, 10));
        pnlPOS.setBorder(new EmptyBorder(10, 10, 10, 10));

        // --- SISI KIRI: KATALOG PRODUK ---
        JPanel pnlKiri = new JPanel(new BorderLayout(5, 5));
        pnlKiri.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225)), "Katalog Produk Tersedia",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), Color.BLACK));

        // Filter / Search Bar
        JPanel pnlSearch = new JPanel(new BorderLayout(6, 6));
        pnlSearch.setBorder(new EmptyBorder(5, 6, 6, 6));
        JLabel lblCariPOS = new JLabel("🔍 Cari Produk: ");
        lblCariPOS.setForeground(Color.BLACK);
        lblCariPOS.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlSearch.add(lblCariPOS, BorderLayout.WEST);
        txtCariPOS = new JTextField();
        txtCariPOS.setFont(new Font("SansSerif", Font.PLAIN, 13));
        pnlSearch.add(txtCariPOS, BorderLayout.CENTER);
        JButton btnResetCariPOS = new JButton("Reset");
        styleTombolHitam(btnResetCariPOS);
        pnlSearch.add(btnResetCariPOS, BorderLayout.EAST);
        pnlKiri.add(pnlSearch, BorderLayout.NORTH);

        txtCariPOS.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { cariProdukPOS(); }
            public void removeUpdate(DocumentEvent e) { cariProdukPOS(); }
            public void changedUpdate(DocumentEvent e) { cariProdukPOS(); }
        });
        btnResetCariPOS.addActionListener(e -> {
            txtCariPOS.setText("");
            muatDataKatalogPOS(produkService.getSemuaProduk());
        });

        // Tabel Katalog Produk
        String[] colKatalog = {"ID", "Nama Produk", "Kategori", "Harga", "Stok", "Info Khusus"};
        tblModelKatalog = new DefaultTableModel(colKatalog, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tblKatalog = buatTabelStriped(tblModelKatalog);
        tblKatalog.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblKatalog.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblKatalog.getColumnModel().getColumn(1).setPreferredWidth(155);
        tblKatalog.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblKatalog.getColumnModel().getColumn(3).setPreferredWidth(85);
        tblKatalog.getColumnModel().getColumn(4).setPreferredWidth(45);
        tblKatalog.getColumnModel().getColumn(5).setPreferredWidth(110);
        formatRataTengahTabel(tblKatalog, 0, 4);
        formatKolomNamaHijau(tblKatalog, 1); // Beri warna hijau pada kolom Nama Produk

        pnlKiri.add(new JScrollPane(tblKatalog), BorderLayout.CENTER);

        // Aksi Bawah Katalog: Qty & Tombol Tambah Keranjang
        JPanel pnlAksiKatalog = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 6));
        pnlAksiKatalog.add(new JLabel("Kuantitas (Qty):"));
        spnQtyPOS = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        spnQtyPOS.setPreferredSize(new Dimension(70, 30));
        pnlAksiKatalog.add(spnQtyPOS);

        btnTambahKeranjang = new JButton("➕ Masukkan ke Keranjang");
        styleTombolHitam(btnTambahKeranjang);
        btnTambahKeranjang.setPreferredSize(new Dimension(240, 34));
        btnTambahKeranjang.addActionListener(e -> tambahItemKeKeranjang());
        pnlAksiKatalog.add(btnTambahKeranjang);

        pnlKiri.add(pnlAksiKatalog, BorderLayout.SOUTH);

        // --- SISI KANAN: KERANJANG BELANJA & PEMBAYARAN ---
        JPanel pnlKanan = new JPanel(new BorderLayout(5, 5));
        pnlKanan.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225)), "Keranjang Belanja & Pembayaran",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), Color.BLACK));

        // Tabel Keranjang
        String[] colKeranjang = {"No", "ID", "Nama Produk", "Harga", "Qty", "Diskon", "Subtotal"};
        tblModelKeranjang = new DefaultTableModel(colKeranjang, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tblKeranjang = buatTabelStriped(tblModelKeranjang);
        tblKeranjang.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblKeranjang.getColumnModel().getColumn(1).setPreferredWidth(45);
        tblKeranjang.getColumnModel().getColumn(2).setPreferredWidth(125);
        tblKeranjang.getColumnModel().getColumn(3).setPreferredWidth(75);
        tblKeranjang.getColumnModel().getColumn(4).setPreferredWidth(40);
        tblKeranjang.getColumnModel().getColumn(5).setPreferredWidth(70);
        tblKeranjang.getColumnModel().getColumn(6).setPreferredWidth(85);
        formatRataTengahTabel(tblKeranjang, 0, 1, 4);
        formatKolomNamaHijau(tblKeranjang, 2); // Beri warna hijau pada kolom Nama Produk

        JPanel pnlTabelKeranjang = new JPanel(new BorderLayout(3, 3));
        pnlTabelKeranjang.add(new JScrollPane(tblKeranjang), BorderLayout.CENTER);

        // Tombol hapus item keranjang
        JPanel pnlOpsiKeranjang = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 2));
        JButton btnHapusItemKeranjang = new JButton("🗑️ Hapus Baris");
        btnHapusItemKeranjang.addActionListener(e -> hapusItemDariKeranjang());
        JButton btnKosongkanKeranjang = new JButton("❌ Kosongkan Keranjang");
        btnKosongkanKeranjang.addActionListener(e -> {
            transaksiService.kosongkanKeranjang();
            refreshTampilanKeranjang();
        });
        pnlOpsiKeranjang.add(btnHapusItemKeranjang);
        pnlOpsiKeranjang.add(btnKosongkanKeranjang);
        pnlTabelKeranjang.add(pnlOpsiKeranjang, BorderLayout.SOUTH);

        pnlKanan.add(pnlTabelKeranjang, BorderLayout.CENTER);

        // Panel Ringkasan & Form Bayar
        JPanel pnlBayar = buatPanelRingkasanDanBayar();
        pnlKanan.add(pnlBayar, BorderLayout.SOUTH);

        pnlPOS.add(pnlKiri);
        pnlPOS.add(pnlKanan);
        return pnlPOS;
    }

    private JPanel buatPanelRingkasanDanBayar() {
        JPanel pnlContainer = new JPanel(new BorderLayout(5, 5));
        pnlContainer.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Rincian Kalkulasi Tagihan
        pnlKalkulasi = new JPanel(new GridLayout(4, 2, 4, 3));
        pnlKalkulasi.setBackground(temaAktif.cardSummaryBg);
        pnlKalkulasi.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 220, 230)),
                new EmptyBorder(8, 12, 8, 12)));

        pnlKalkulasi.add(new JLabel("Subtotal Belanja:"));
        lblSubtotalNilai = new JLabel("Rp 0", SwingConstants.RIGHT);
        lblSubtotalNilai.setFont(new Font("SansSerif", Font.PLAIN, 13));
        pnlKalkulasi.add(lblSubtotalNilai);

        pnlKalkulasi.add(new JLabel("Hemat Diskon (Polimorfis):"));
        lblDiskonNilai = new JLabel("- Rp 0", SwingConstants.RIGHT);
        lblDiskonNilai.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblDiskonNilai.setForeground(temaAktif.btnBayarBg);
        pnlKalkulasi.add(lblDiskonNilai);

        pnlKalkulasi.add(new JLabel("PPN 11%:"));
        lblPajakNilai = new JLabel("Rp 0", SwingConstants.RIGHT);
        lblPajakNilai.setFont(new Font("SansSerif", Font.PLAIN, 13));
        pnlKalkulasi.add(lblPajakNilai);

        JLabel lblTagihan = new JLabel("TOTAL HARUS BAYAR:");
        lblTagihan.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTagihan.setForeground(new Color(185, 28, 28));
        pnlKalkulasi.add(lblTagihan);

        lblTotalAkhirNilai = new JLabel("Rp 0", SwingConstants.RIGHT);
        lblTotalAkhirNilai.setFont(new Font("SansSerif", Font.BOLD, 17));
        lblTotalAkhirNilai.setForeground(new Color(185, 28, 28));
        pnlKalkulasi.add(lblTotalAkhirNilai);

        // Input Uang Tunai & Kembalian
        JPanel pnlInputBayar = new JPanel(new GridLayout(4, 2, 6, 6));
        pnlInputBayar.setBorder(new EmptyBorder(6, 0, 6, 0));

        JLabel lblKasir = new JLabel("Nama Kasir:");
        lblKasir.setForeground(new Color(4, 120, 87));
        lblKasir.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlInputBayar.add(lblKasir);
        txtKasirPOS = new JTextField("Kasir-01");
        pnlInputBayar.add(txtKasirPOS);

        pnlInputBayar.add(new JLabel("Uang Pembeli (Rp):"));
        txtUangBayarPOS = new JTextField();
        txtUangBayarPOS.setFont(new Font("SansSerif", Font.BOLD, 14));
        pnlInputBayar.add(txtUangBayarPOS);

        pnlInputBayar.add(new JLabel("Uang Kembalian:"));
        lblKembalianNilai = new JLabel("Rp 0", SwingConstants.LEFT);
        lblKembalianNilai.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblKembalianNilai.setForeground(new Color(30, 64, 175));
        pnlInputBayar.add(lblKembalianNilai);

        pnlInputBayar.add(new JLabel("Pesan Struk Promo:"));
        txtCatatanPromoPOS = new JTextField("Terima Kasih! Selamat Berbelanja Kembali.");
        pnlInputBayar.add(txtCatatanPromoPOS);

        // Live calculation kembalian
        txtUangBayarPOS.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { hitungLiveKembalian(); }
            public void removeUpdate(DocumentEvent e) { hitungLiveKembalian(); }
            public void changedUpdate(DocumentEvent e) { hitungLiveKembalian(); }
        });

        // Tombol Bayar
        btnBayarStruk = new JButton("💳 PROSES BAYAR & CETAK STRUK");
        btnBayarStruk.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnBayarStruk.setBackground(temaAktif.btnBayarBg);
        btnBayarStruk.setForeground(Color.BLACK);
        btnBayarStruk.setPreferredSize(new Dimension(100, 44));
        btnBayarStruk.addActionListener(e -> prosesBayarDanCetakStruk());

        pnlContainer.add(pnlKalkulasi, BorderLayout.NORTH);
        pnlContainer.add(pnlInputBayar, BorderLayout.CENTER);
        pnlContainer.add(btnBayarStruk, BorderLayout.SOUTH);
        return pnlContainer;
    }

    private void hitungLiveKembalian() {
        double total = transaksiService.hitungTotalAkhirKeranjang();
        String strUang = txtUangBayarPOS.getText().trim();
        if (strUang.isEmpty()) {
            lblKembalianNilai.setText("Rp 0");
            lblKembalianNilai.setForeground(Color.GRAY);
            return;
        }
        try {
            double bayar = Double.parseDouble(strUang);
            double kembali = bayar - total;
            if (kembali >= 0) {
                lblKembalianNilai.setText(FormatUtil.toRupiah(kembali));
                lblKembalianNilai.setForeground(new Color(16, 120, 60));
            } else {
                lblKembalianNilai.setText("Kurang " + FormatUtil.toRupiah(Math.abs(kembali)));
                lblKembalianNilai.setForeground(new Color(220, 38, 38));
            }
        } catch (NumberFormatException e) {
            lblKembalianNilai.setText("Format angka salah");
            lblKembalianNilai.setForeground(Color.RED);
        }
    }

    private void tambahItemKeKeranjang() {
        int barisTerpilih = tblKatalog.getSelectedRow();
        if (barisTerpilih < 0) {
            JOptionPane.showMessageDialog(this, "Pilih salah satu produk pada tabel katalog terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String id = (String) tblModelKatalog.getValueAt(barisTerpilih, 0);
        int qty = (int) spnQtyPOS.getValue();

        boolean sukses = transaksiService.tambahKeKeranjang(id, qty);
        if (sukses) {
            refreshTampilanKeranjang();
            spnQtyPOS.setValue(1);
        } else {
            Produk p = produkService.cariProduk(id);
            JOptionPane.showMessageDialog(this,
                    "Stok produk '" + p.getNama() + "' tidak mencukupi!\nTersisa: " + p.getStok(),
                    "Stok Kurang", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapusItemDariKeranjang() {
        int baris = tblKeranjang.getSelectedRow();
        if (baris < 0) {
            JOptionPane.showMessageDialog(this, "Pilih item di keranjang yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String id = (String) tblModelKeranjang.getValueAt(baris, 1);
        transaksiService.hapusDariKeranjang(id);
        refreshTampilanKeranjang();
    }

    private void refreshTampilanKeranjang() {
        tblModelKeranjang.setRowCount(0);
        List<ItemTransaksi> items = transaksiService.getKeranjang();
        int no = 1;
        for (ItemTransaksi it : items) {
            tblModelKeranjang.addRow(new Object[]{
                    no++,
                    it.getProduk().getIdProduk(),
                    it.getProduk().getNama(),
                    FormatUtil.toRupiah(it.getProduk().getHarga()),
                    it.getJumlah(),
                    FormatUtil.toRupiah(it.getDiskon()),
                    FormatUtil.toRupiah(it.getTotalAkhir())
            });
        }

        double subtotal = transaksiService.hitungSubtotalKeranjang();
        double diskon = transaksiService.hitungDiskonKeranjang();
        double pajak = transaksiService.hitungPajakKeranjang();
        double total = transaksiService.hitungTotalAkhirKeranjang();

        lblSubtotalNilai.setText(FormatUtil.toRupiah(subtotal));
        lblDiskonNilai.setText("- " + FormatUtil.toRupiah(diskon));
        lblPajakNilai.setText(FormatUtil.toRupiah(pajak));
        lblTotalAkhirNilai.setText(FormatUtil.toRupiah(total));

        hitungLiveKembalian();
    }

    private void prosesBayarDanCetakStruk() {
        if (transaksiService.isKeranjangKosong()) {
            JOptionPane.showMessageDialog(this, "Keranjang belanja masih kosong! Silakan pilih produk terlebih dahulu.", "Keranjang Kosong", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double totalHarusBayar = transaksiService.hitungTotalAkhirKeranjang();
        String strUang = txtUangBayarPOS.getText().trim();

        if (strUang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan jumlah uang pembayaran dari pembeli!", "Validasi", JOptionPane.WARNING_MESSAGE);
            txtUangBayarPOS.requestFocus();
            return;
        }

        double uangBayar;
        try {
            uangBayar = Double.parseDouble(strUang);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nominal uang bayar harus berupa angka valid!", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (uangBayar < totalHarusBayar) {
            JOptionPane.showMessageDialog(this,
                    "Nominal uang pembayaran kurang!\nTotal Tagihan : " + FormatUtil.toRupiah(totalHarusBayar) +
                            "\nUang Diterima : " + FormatUtil.toRupiah(uangBayar) +
                            "\nKekurangan    : " + FormatUtil.toRupiah(totalHarusBayar - uangBayar),
                    "Uang Kurang", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String kasir = txtKasirPOS.getText().trim();
        String catatanPromo = txtCatatanPromoPOS.getText().trim();

        try {
            // Memproses transaksi POS (mengurangi stok inventaris secara permanen)
            Transaksi trx = transaksiService.prosesPembayaran(kasir, uangBayar, catatanPromo);

            // Perbarui data tabel
            muatDataKatalogPOS(produkService.getSemuaProduk());
            muatDataTabelCRUD(produkService.getSemuaProduk());
            muatDataRiwayat();
            refreshTampilanKeranjang();
            txtUangBayarPOS.setText("");

            // Buka Pop-up Struk Belanja
            DialogStruk dialog = new DialogStruk(this, trx, catatanPromo);
            dialog.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal memproses transaksi: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cariProdukPOS() {
        String kw = txtCariPOS.getText().trim();
        if (kw.isEmpty()) {
            muatDataKatalogPOS(produkService.getSemuaProduk());
            return;
        }
        Produk byId = produkService.cariProduk(kw);
        if (byId != null) {
            muatDataKatalogPOS(List.of(byId));
        } else {
            List<Produk> hasil = produkService.cariProduk(kw, true);
            muatDataKatalogPOS(hasil);
        }
    }

    private void muatDataKatalogPOS(List<Produk> list) {
        tblModelKatalog.setRowCount(0);
        for (Produk p : list) {
            tblModelKatalog.addRow(new Object[]{
                    p.getIdProduk(),
                    p.getNama(),
                    p.getKategori(),
                    FormatUtil.toRupiah(p.getHarga()),
                    p.getStok(),
                    p.getInfoKhusus()
            });
        }
    }

    // =========================================================================
    // TAB 2: MANAJEMEN PRODUK (CRUD)
    // =========================================================================
    private JPanel buatTabCRUD() {
        JPanel pnlCRUD = new JPanel(new BorderLayout(10, 10));
        pnlCRUD.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Form Input (Sisi Kiri)
        JPanel pnlForm = new JPanel(new BorderLayout(5, 5));
        pnlForm.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225)), "Form Data Produk (Create / Update)",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), Color.BLACK));
        pnlForm.setPreferredSize(new Dimension(390, 0));

        JPanel pnlFields = new JPanel(new GridLayout(7, 2, 6, 8));
        pnlFields.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblKategori = new JLabel("Tipe Kategori:");
        lblKategori.setForeground(Color.BLACK);
        lblKategori.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlFields.add(lblKategori);
        cmbKategoriCRUD = new JComboBox<>(new String[]{"Makanan & Minuman", "Elektronik & Gadget"});
        cmbKategoriCRUD.setForeground(Color.BLACK);
        pnlFields.add(cmbKategoriCRUD);

        JLabel lblId = new JLabel("ID Produk:");
        lblId.setForeground(Color.BLACK);
        lblId.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlFields.add(lblId);
        txtIdCRUD = new JTextField();
        txtIdCRUD.setForeground(Color.BLACK);
        pnlFields.add(txtIdCRUD);

        JLabel lblNamaPrd = new JLabel("Nama Produk:");
        lblNamaPrd.setForeground(Color.BLACK);
        lblNamaPrd.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlFields.add(lblNamaPrd);
        txtNamaCRUD = new JTextField();
        txtNamaCRUD.setForeground(Color.BLACK);
        pnlFields.add(txtNamaCRUD);

        JLabel lblHarga = new JLabel("Harga Satuan (Rp):");
        lblHarga.setForeground(Color.BLACK);
        lblHarga.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlFields.add(lblHarga);
        txtHargaCRUD = new JTextField();
        txtHargaCRUD.setForeground(Color.BLACK);
        pnlFields.add(txtHargaCRUD);

        JLabel lblStok = new JLabel("Jumlah Stok:");
        lblStok.setForeground(Color.BLACK);
        lblStok.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlFields.add(lblStok);
        txtStokCRUD = new JTextField();
        txtStokCRUD.setForeground(Color.BLACK);
        pnlFields.add(txtStokCRUD);

        lblAtributKhususCRUD = new JLabel("Kadaluarsa (YYYY-MM-DD):");
        lblAtributKhususCRUD.setForeground(Color.BLACK);
        lblAtributKhususCRUD.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlFields.add(lblAtributKhususCRUD);
        txtAtributKhususCRUD = new JTextField("2027-12-31");
        txtAtributKhususCRUD.setForeground(Color.BLACK);
        pnlFields.add(txtAtributKhususCRUD);

        cmbKategoriCRUD.addActionListener(e -> {
            int idx = cmbKategoriCRUD.getSelectedIndex();
            if (idx == 0) {
                lblAtributKhususCRUD.setText("Kadaluarsa (YYYY-MM-DD):");
                txtAtributKhususCRUD.setText("2027-12-31");
            } else {
                lblAtributKhususCRUD.setText("Garansi Resmi (Bulan):");
                txtAtributKhususCRUD.setText("12");
            }
        });

        pnlForm.add(pnlFields, BorderLayout.NORTH);

        // Tombol-tombol CRUD
        JPanel pnlTombolCRUD = new JPanel(new GridLayout(2, 2, 8, 8));
        pnlTombolCRUD.setBorder(new EmptyBorder(0, 10, 15, 10));

        btnTambahCRUD = new JButton("➕ Tambah (Create)");
        btnTambahCRUD.setBackground(temaAktif.btnBayarBg);
        btnTambahCRUD.setForeground(Color.WHITE);
        btnTambahCRUD.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnTambahCRUD.addActionListener(e -> aksiTambahProdukCRUD());

        btnUbahCRUD = new JButton("✏️ Simpan Ubah (Update)");
        btnUbahCRUD.setBackground(temaAktif.btnUbahBg);
        btnUbahCRUD.setForeground(Color.WHITE);
        btnUbahCRUD.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnUbahCRUD.addActionListener(e -> aksiUbahProdukCRUD());

        btnHapusCRUD = new JButton("🗑️ Hapus (Delete)");
        btnHapusCRUD.setBackground(temaAktif.btnHapusBg);
        btnHapusCRUD.setForeground(Color.WHITE);
        btnHapusCRUD.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnHapusCRUD.addActionListener(e -> aksiHapusProdukCRUD());

        btnResetCRUD = new JButton("🔄 Bersihkan Form");
        btnResetCRUD.addActionListener(e -> resetFormCRUD());

        pnlTombolCRUD.add(btnTambahCRUD);
        pnlTombolCRUD.add(btnUbahCRUD);
        pnlTombolCRUD.add(btnHapusCRUD);
        pnlTombolCRUD.add(btnResetCRUD);
        pnlForm.add(pnlTombolCRUD, BorderLayout.CENTER);

        // Panel Tabel Master Produk (Sisi Kanan)
        JPanel pnlTabelMaster = new JPanel(new BorderLayout(5, 5));
        pnlTabelMaster.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225)), "Katalog Master Produk (Read)",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), Color.BLACK));

        // Search Bar CRUD
        JPanel pnlSearchCRUD = new JPanel(new BorderLayout(6, 6));
        pnlSearchCRUD.setBorder(new EmptyBorder(5, 6, 6, 6));
        JLabel lblSearchCRUD = new JLabel("🔍 Cari (ID / Nama): ");
        lblSearchCRUD.setForeground(Color.BLACK);
        lblSearchCRUD.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlSearchCRUD.add(lblSearchCRUD, BorderLayout.WEST);
        txtCariCRUD = new JTextField();
        pnlSearchCRUD.add(txtCariCRUD, BorderLayout.CENTER);
        JButton btnResetCariCRUD = new JButton("Reset");
        pnlSearchCRUD.add(btnResetCariCRUD, BorderLayout.EAST);
        pnlTabelMaster.add(pnlSearchCRUD, BorderLayout.NORTH);

        txtCariCRUD.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { cariProdukCRUD(); }
            public void removeUpdate(DocumentEvent e) { cariProdukCRUD(); }
            public void changedUpdate(DocumentEvent e) { cariProdukCRUD(); }
        });
        btnResetCariCRUD.addActionListener(e -> {
            txtCariCRUD.setText("");
            muatDataTabelCRUD(produkService.getSemuaProduk());
        });

        // Tabel Master
        String[] colMaster = {"ID", "Nama Produk", "Kategori", "Harga Satuan", "Stok", "Info Spesifik"};
        tblModelCRUD = new DefaultTableModel(colMaster, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tblCRUD = buatTabelStriped(tblModelCRUD);
        tblCRUD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        formatRataTengahTabel(tblCRUD, 0, 4);
        formatKolomNamaHijau(tblCRUD, 1); // Beri warna hijau pada kolom Nama Produk

        // Klik baris tabel otomatis mengisi form
        tblCRUD.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblCRUD.getSelectedRow() >= 0) {
                int row = tblCRUD.getSelectedRow();
                String id = (String) tblModelCRUD.getValueAt(row, 0);
                Produk p = produkService.cariProduk(id);
                if (p != null) {
                    txtIdCRUD.setText(p.getIdProduk());
                    txtIdCRUD.setEditable(false);
                    txtNamaCRUD.setText(p.getNama());
                    txtHargaCRUD.setText(String.valueOf((long) p.getHarga()));
                    txtStokCRUD.setText(String.valueOf(p.getStok()));

                    if (p instanceof ProdukMakanan) {
                        cmbKategoriCRUD.setSelectedIndex(0);
                        txtAtributKhususCRUD.setText(((ProdukMakanan) p).getTanggalKadaluarsa());
                    } else if (p instanceof ProdukElektronik) {
                        cmbKategoriCRUD.setSelectedIndex(1);
                        txtAtributKhususCRUD.setText(String.valueOf(((ProdukElektronik) p).getGaransiBulan()));
                    }
                }
            }
        });

        pnlTabelMaster.add(new JScrollPane(tblCRUD), BorderLayout.CENTER);

        pnlCRUD.add(pnlForm, BorderLayout.WEST);
        pnlCRUD.add(pnlTabelMaster, BorderLayout.CENTER);
        return pnlCRUD;
    }

    private void cariProdukCRUD() {
        String kw = txtCariCRUD.getText().trim();
        if (kw.isEmpty()) {
            muatDataTabelCRUD(produkService.getSemuaProduk());
            return;
        }
        Produk byId = produkService.cariProduk(kw);
        if (byId != null) {
            muatDataTabelCRUD(List.of(byId));
        } else {
            List<Produk> hasil = produkService.cariProduk(kw, true);
            muatDataTabelCRUD(hasil);
        }
    }

    private void muatDataTabelCRUD(List<Produk> list) {
        tblModelCRUD.setRowCount(0);
        for (Produk p : list) {
            tblModelCRUD.addRow(new Object[]{
                    p.getIdProduk(),
                    p.getNama(),
                    p.getKategori(),
                    FormatUtil.toRupiah(p.getHarga()),
                    p.getStok(),
                    p.getInfoKhusus()
            });
        }
    }

    private void resetFormCRUD() {
        txtIdCRUD.setText("");
        txtIdCRUD.setEditable(true);
        txtNamaCRUD.setText("");
        txtHargaCRUD.setText("");
        txtStokCRUD.setText("");
        cmbKategoriCRUD.setSelectedIndex(0);
        txtAtributKhususCRUD.setText("2027-12-31");
        tblCRUD.clearSelection();
    }

    private void aksiTambahProdukCRUD() {
        String id = txtIdCRUD.getText().trim();
        String nama = txtNamaCRUD.getText().trim();
        String strHarga = txtHargaCRUD.getText().trim();
        String strStok = txtStokCRUD.getText().trim();
        String atributKhusus = txtAtributKhususCRUD.getText().trim();

        if (id.isEmpty() || nama.isEmpty() || strHarga.isEmpty() || strStok.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom input wajib diisi!", "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (produkService.cariProduk(id) != null) {
            JOptionPane.showMessageDialog(this, "Produk dengan ID '" + id + "' sudah terdaftar!", "Duplikasi ID", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double harga = Double.parseDouble(strHarga);
            int stok = Integer.parseInt(strStok);

            Produk produkBaru;
            if (cmbKategoriCRUD.getSelectedIndex() == 0) {
                produkBaru = new ProdukMakanan(id, nama, harga, stok, atributKhusus);
            } else {
                int garansi = Integer.parseInt(atributKhusus);
                produkBaru = new ProdukElektronik(id, nama, harga, stok, garansi);
            }

            produkService.tambahProduk(produkBaru);
            JOptionPane.showMessageDialog(this, "Produk '" + nama + "' berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            resetFormCRUD();
            muatDataTabelCRUD(produkService.getSemuaProduk());
            muatDataKatalogPOS(produkService.getSemuaProduk());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga, stok, atau garansi harus berupa angka!", "Format Salah", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal menambah produk: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aksiUbahProdukCRUD() {
        String id = txtIdCRUD.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih produk dari tabel yang ingin diubah!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Produk produkAda = produkService.cariProduk(id);
        if (produkAda == null) {
            JOptionPane.showMessageDialog(this, "Produk tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nama = txtNamaCRUD.getText().trim();
        String strHarga = txtHargaCRUD.getText().trim();
        String strStok = txtStokCRUD.getText().trim();
        String atributKhusus = txtAtributKhususCRUD.getText().trim();

        try {
            double harga = Double.parseDouble(strHarga);
            int stok = Integer.parseInt(strStok);

            Produk produkUpdate;
            if (cmbKategoriCRUD.getSelectedIndex() == 0) {
                produkUpdate = new ProdukMakanan(id, nama, harga, stok, atributKhusus);
            } else {
                int garansi = Integer.parseInt(atributKhusus);
                produkUpdate = new ProdukElektronik(id, nama, harga, stok, garansi);
            }

            boolean berhasil = produkService.updateProduk(produkUpdate);
            if (berhasil) {
                JOptionPane.showMessageDialog(this, "Data produk '" + nama + "' berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                resetFormCRUD();
                muatDataTabelCRUD(produkService.getSemuaProduk());
                muatDataKatalogPOS(produkService.getSemuaProduk());
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga, stok, atau garansi harus berupa angka!", "Format Salah", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aksiHapusProdukCRUD() {
        String id = txtIdCRUD.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih produk yang ingin dihapus dari tabel!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Produk p = produkService.cariProduk(id);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Produk tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Anda yakin ingin menghapus produk:\n[" + p.getIdProduk() + "] " + p.getNama() + " ?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            boolean terhapus = produkService.hapusProduk(id);
            if (terhapus) {
                JOptionPane.showMessageDialog(this, "Produk berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                resetFormCRUD();
                muatDataTabelCRUD(produkService.getSemuaProduk());
                muatDataKatalogPOS(produkService.getSemuaProduk());
            }
        }
    }

    // =========================================================================
    // TAB 3: RIWAYAT TRANSAKSI
    // =========================================================================
    private JPanel buatTabRiwayat() {
        JPanel pnlRiwayat = new JPanel(new BorderLayout(8, 8));
        pnlRiwayat.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] colRiwayat = {"Kode Trx", "Waktu Transaksi", "Kasir", "Jumlah Item", "Total Akhir", "Uang Bayar", "Kembalian"};
        tblModelRiwayat = new DefaultTableModel(colRiwayat, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tblRiwayat = buatTabelStriped(tblModelRiwayat);
        tblRiwayat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        formatRataTengahTabel(tblRiwayat, 0, 1, 3);
        formatKolomNamaHijau(tblRiwayat, 2); // Beri warna hijau pada kolom Kasir (Nama Kasir)

        pnlRiwayat.add(new JScrollPane(tblRiwayat), BorderLayout.CENTER);

        JPanel pnlBawah = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        btnBukaFolderRiwayat = new JButton("📂 Buka Folder Penyimpanan Struk");
        btnBukaFolderRiwayat.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnBukaFolderRiwayat.setBackground(temaAktif.btnBayarBg);
        btnBukaFolderRiwayat.setForeground(Color.WHITE);
        btnBukaFolderRiwayat.setPreferredSize(new Dimension(260, 34));
        btnBukaFolderRiwayat.addActionListener(e -> {
            boolean dibuka = com.kasir.util.FileStrukUtil.bukaFolderStruk();
            if (!dibuka) {
                JOptionPane.showMessageDialog(this,
                        "Folder struk berada di: " + com.kasir.util.FileStrukUtil.getFolderStruk().getAbsolutePath(),
                        "Folder Struk", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnCetakUlang = new JButton("🖨️ Lihat / Cetak Ulang Struk Terpilih");
        btnCetakUlang.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnCetakUlang.setBackground(temaAktif.btnUbahBg);
        btnCetakUlang.setForeground(Color.WHITE);
        btnCetakUlang.setPreferredSize(new Dimension(250, 34));
        btnCetakUlang.addActionListener(e -> {
            int row = tblRiwayat.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Pilih salah satu baris riwayat transaksi terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String kode = (String) tblModelRiwayat.getValueAt(row, 0);
            for (Transaksi t : transaksiService.getRiwayatTransaksi()) {
                if (t.getKodeTransaksi().equalsIgnoreCase(kode)) {
                    DialogStruk dialog = new DialogStruk(this, t, "Salinan Struk Resmi");
                    dialog.setVisible(true);
                    return;
                }
            }
        });
        pnlBawah.add(btnBukaFolderRiwayat);
        pnlBawah.add(btnCetakUlang);
        pnlRiwayat.add(pnlBawah, BorderLayout.SOUTH);

        return pnlRiwayat;
    }

    private void muatDataRiwayat() {
        tblModelRiwayat.setRowCount(0);
        for (Transaksi t : transaksiService.getRiwayatTransaksi()) {
            tblModelRiwayat.addRow(new Object[]{
                    t.getKodeTransaksi(),
                    FormatUtil.formatTanggal(t.getWaktuTransaksi()),
                    t.getNamaKasir(),
                    t.getDaftarItem().size(),
                    FormatUtil.toRupiah(t.getTotalAkhir()),
                    FormatUtil.toRupiah(t.getUangDiterima()),
                    FormatUtil.toRupiah(t.getUangKembalian())
            });
        }
    }

    // =========================================================================
    // TAB 4: RINGKASAN KONSEP OOP
    // =========================================================================
    private JPanel buatTabKonsepOOP() {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBorder(new EmptyBorder(15, 15, 15, 15));

        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditable(false);
        editorPane.setBackground(Color.WHITE);

        String html = "<html><body style='font-family:SansSerif; padding:12px; color:#1E293B;'>"
                + "<h2 style='color:#0F172A; border-bottom:2px solid #0F172A; padding-bottom:5px;'>"
                + "Implementasi 5 Konsep Pemrograman Berorientasi Objek (OOP) pada Aplikasi Kasir</h2>"

                + "<div style='margin-bottom:14px;'>"
                + "<h3 style='color:#059669; margin-bottom:4px;'>1. ATRIBUT (Attributes / Fields)</h3>"
                + "<p>Menyimpan data dan keadaan (state) objek:</p>"
                + "<ul>"
                + "<li><code>idProduk</code>, <code>nama</code>, <code>harga</code>, <code>stok</code> di <b>Produk.java</b></li>"
                + "<li><code>tanggalKadaluarsa</code> di <b>ProdukMakanan.java</b></li>"
                + "<li><code>garansiBulan</code> di <b>ProdukElektronik.java</b></li>"
                + "<li>Konstanta statis: <code>NAMA_TOKO</code>, <code>TARIF_PPN</code> di <b>Transaksi.java</b></li>"
                + "</ul>"
                + "</div>"

                + "<div style='margin-bottom:14px;'>"
                + "<h3 style='color:#059669; margin-bottom:4px;'>2. ENKAPSULASI (Encapsulation)</h3>"
                + "<p>Menyembunyikan atribut dengan access modifier <code>private</code> dan hanya bisa diakses via getter/setter tervalidasi:</p>"
                + "<ul>"
                + "<li>Proteksi harga dan stok agar tidak pernah negatif (<code>harga >= 0</code>, <code>stok >= 0</code>).</li>"
                + "<li>Method <code>kurangiStok(int qty)</code> melindungi inventaris agar stok tidak minus saat transaksi kasir POS.</li>"
                + "</ul>"
                + "</div>"

                + "<div style='margin-bottom:14px;'>"
                + "<h3 style='color:#059669; margin-bottom:4px;'>3. PEWARISAN (Inheritance)</h3>"
                + "<p>Reusability kode dengan hierarki kelas superclass dan subclass menggunakan <code>extends</code> & <code>super(...)</code>:</p>"
                + "<ul>"
                + "<li><b>ProdukMakanan extends Produk</b> (mewarisi atribut Produk & menambah tanggal kadaluarsa).</li>"
                + "<li><b>ProdukElektronik extends Produk</b> (mewarisi atribut Produk & menambah garansi resmi).</li>"
                + "</ul>"
                + "</div>"

                + "<div style='margin-bottom:14px;'>"
                + "<h3 style='color:#059669; margin-bottom:4px;'>4. ABSTRAKSI (Abstraction)</h3>"
                + "<p>Pemisahan antara konsep umum dengan implementasi detail:</p>"
                + "<ul>"
                + "<li><b>Abstract Class Produk</b>: Mengharuskan subclass merealisasikan <code>hitungDiskon()</code>, <code>getKategori()</code>, dan <code>getInfoKhusus()</code>.</li>"
                + "<li><b>Interface ICetakStruk</b>: Kontrak resmi untuk pencetakan nota pembayaran.</li>"
                + "<li><b>Interface IProdukService</b>: Kontrak resmi untuk operasi CRUD katalog produk.</li>"
                + "</ul>"
                + "</div>"

                + "<div style='margin-bottom:14px;'>"
                + "<h3 style='color:#059669; margin-bottom:4px;'>5. POLIMORFISME (Polymorphism)</h3>"
                + "<ul>"
                + "<li><b>Polimorfisme Dinamis (Method Overriding)</b>: Subclass meng-override <code>hitungDiskon(int jumlah)</code>. Produk Makanan diskon 10% jika beli >= 5; Produk Elektronik diskon 5% + Rp 25.000 jika beli >= 2. Dijalankan dinamis saat checkout.</li>"
                + "<li><b>Polimorfisme Statis (Method Overloading)</b>: Di <b>ProdukService</b> (<code>cariProduk(String id)</code> vs <code>cariProduk(String keyword, boolean cariNama)</code>) dan di <b>Transaksi</b> (<code>cetakStruk()</code> vs <code>cetakStruk(String pesanPromo)</code>).</li>"
                + "</ul>"
                + "</div>"

                + "</body></html>";

        editorPane.setText(html);
        editorPane.setCaretPosition(0);

        pnl.add(new JScrollPane(editorPane), BorderLayout.CENTER);
        return pnl;
    }

    // =========================================================================
    // HELPER TAMPILAN TABEL STRIPED (Zebra Modern)
    // =========================================================================
    private JTable buatTabelStriped(DefaultTableModel model) {
        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    // Baris ganjil & genap berselang-seling (Zebra striping)
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 250, 252));
                }
                return c;
            }
        };
        table.setRowHeight(25);
        table.setGridColor(new Color(226, 232, 240));
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);
        return table;
    }

    private void formatRataTengahTabel(JTable table, int... kolomIndex) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int idx : kolomIndex) {
            table.getColumnModel().getColumn(idx).setCellRenderer(centerRenderer);
        }
    }

    /**
     * Memberikan warna hijau khas pada seluruh kolom yang berisi Nama (Nama Produk / Kasir)
     * Background hijau mint lembut (#ECFDF5) dengan teks hijau tua tebal (#047857)
     */
    private void formatKolomNamaHijau(JTable table, int kolomIndex) {
        DefaultTableCellRenderer namaRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(new Color(236, 253, 245)); // Background Hijau Mint (#ECFDF5)
                    c.setForeground(new Color(4, 120, 87));     // Teks Hijau Tua Tegas (#047857)
                    c.setFont(new Font("SansSerif", Font.BOLD, 12));
                } else {
                    c.setBackground(tbl.getSelectionBackground());
                    c.setForeground(Color.WHITE);
                }
                setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8)); // Padding teks agar rapi
                return c;
            }
        };
        table.getColumnModel().getColumn(kolomIndex).setCellRenderer(namaRenderer);
    }

    /**
     * Memformat tombol dengan warna HITAM elegan, teks putih tebal,
     * border halus, dan cursor pointer.
     */
        /**
     * Memformat tombol dengan warna HITAM elegan, teks putih tebal.
     */
    public static void styleTombolHitam(JButton button) {
        if (button == null) return;
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBackground(new Color(15, 23, 42));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(51, 65, 85), 1),
                BorderFactory.createEmptyBorder(6, 14, 6, 14)
        ));
    }

    /**
     * Memformat tombol aksi utama (Bayar) dengan warna HITAM pekat lebih besar.
     */
    public static void styleTombolHitamBesar(JButton button) {
        if (button == null) return;
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBackground(new Color(10, 15, 30));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(71, 85, 105), 1),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));
    }
}