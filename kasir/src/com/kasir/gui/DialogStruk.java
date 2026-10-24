package com.kasir.gui;

import com.kasir.model.Transaksi;
import com.kasir.util.FileStrukUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;

/**
 * Dialog Pop-up untuk menampilkan cetakan Struk Belanja Kasir secara visual,
 * sekaligus mengonfirmasi penyimpanan file struk di folder 'struk/'.
 */
public class DialogStruk extends JDialog {

    private final JTextArea txtStruk;

    public DialogStruk(Window parent, Transaksi transaksi, String pesanPromo) {
        super(parent, "Struk Transaksi - " + transaksi.getKodeTransaksi(), ModalityType.APPLICATION_MODAL);
        setSize(520, 670);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Header dialog dengan gaya Dark Slate
        JPanel pnlHeader = new JPanel(new GridLayout(2, 1, 2, 2));
        pnlHeader.setBackground(new Color(15, 23, 42));
        pnlHeader.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblHeader = new JLabel("🧾 BUKTI PEMBAYARAN RESMI KASIR", SwingConstants.CENTER);
        lblHeader.setForeground(new Color(16, 185, 129));
        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 15));

        JLabel lblFileSaved = new JLabel("📁 Tersimpan di: " + FileStrukUtil.DIRECTORY_NAME + "/" + transaksi.getKodeTransaksi() + ".txt", SwingConstants.CENTER);
        lblFileSaved.setForeground(new Color(203, 213, 225));
        lblFileSaved.setFont(new Font("SansSerif", Font.PLAIN, 11));

        pnlHeader.add(lblHeader);
        pnlHeader.add(lblFileSaved);
        add(pnlHeader, BorderLayout.NORTH);

        // Receipt Text Area
        txtStruk = new JTextArea();
        txtStruk.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtStruk.setEditable(false);
        txtStruk.setBackground(new Color(254, 254, 250));
        txtStruk.setForeground(new Color(15, 23, 42));
        txtStruk.setMargin(new Insets(15, 15, 15, 15));

        // Format teks struk menggunakan metode overloading di Transaksi
        String strukContent;
        if (pesanPromo == null || pesanPromo.trim().isEmpty()) {
            strukContent = transaksi.getFormatStruk();
        } else {
            strukContent = transaksi.getFormatStruk(pesanPromo);
        }
        txtStruk.setText(strukContent);
        txtStruk.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(txtStruk);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // Footer tombol aksi
        JPanel pnlFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        pnlFooter.setBackground(new Color(241, 245, 249));

        JButton btnBukaFolder = new JButton("📂 Buka Folder Struk");
        btnBukaFolder.setFont(new Font("SansSerif", Font.BOLD, 11));
        btnBukaFolder.setBackground(new Color(16, 185, 129));
        btnBukaFolder.setForeground(Color.BLACK);
        btnBukaFolder.addActionListener(e -> {
            boolean dibuka = FileStrukUtil.bukaFolderStruk();
            if (!dibuka) {
                JOptionPane.showMessageDialog(this,
                        "Folder struk berada di: " + FileStrukUtil.getFolderStruk().getAbsolutePath(),
                        "Lokasi Folder", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton btnSalin = new JButton("📋 Salin");
        btnSalin.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnSalin.addActionListener(e -> {
            StringSelection sel = new StringSelection(txtStruk.getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
            JOptionPane.showMessageDialog(this, "Teks struk berhasil disalin ke Clipboard!", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton btnPrint = new JButton("🖨️ Cetak Printer");
        btnPrint.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnPrint.setBackground(new Color(14, 165, 233));
        btnPrint.setForeground(Color.BLACK);
        btnPrint.addActionListener(e -> {
            try {
                boolean selesai = txtStruk.print();
                if (selesai) {
                    JOptionPane.showMessageDialog(this, "Pencetakan struk selesai.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Gagal mencetak: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnTutup = new JButton("Tutup");
        btnTutup.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnTutup.addActionListener(e -> dispose());

        pnlFooter.add(btnBukaFolder);
        pnlFooter.add(btnSalin);
        pnlFooter.add(btnPrint);
        pnlFooter.add(btnTutup);
        add(pnlFooter, BorderLayout.SOUTH);
    }
}
