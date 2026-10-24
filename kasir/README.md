# 🛒 Aplikasi Kasir POS (Point of Sales) - Java Desktop GUI (Swing/JFrame)

Aplikasi Kasir (Point of Sales) berbasis GUI Desktop interaktif menggunakan bahasa Java dan framework **Java Swing (`JFrame`)** untuk **Apache NetBeans**. Dilengkapi implementasi lengkap 5 konsep utama **Pemrograman Berorientasi Objek (PBO / OOP)**, fitur **CRUD**, **Point of Sales (Kasir)**, **fitur tema warna (*Live Theme Switcher*)**, **warna teks hitam pekat & tegas pada kotak header kolom & label form input**, serta **folder otomatis penyimpanan data struk belanja (`struk/`)**.

---

## 🖤 Pembaruan Warna Teks Kotak (Hitam Pekat & Tegas)

Sesuai permintaan, seluruh kotak yang berisi judul kolom dan form data produk:
- **Header Kolom Tabel** (`ID`, `Nama Produk`, `Kategori`, `Harga`, `Stok`, `Info Khusus`, `Qty`, `Subtotal`, `Total`):
  - Warna Teks: **Hitam Pekat** (`Color.BLACK`) tebal (*Bold*)
  - Background Kotak Header: Abu-abu Terang Bersih (`#E2E8F0`) dengan garis pembatas sel rapi (*Matte Border*).
- **Label Kotak Form Input Data Produk (CRUD)**:
  - `ID Produk:` (Teks Hitam Tebal)
  - `Nama Produk:` (Teks Hitam Tebal)
  - `Tipe Kategori:` (Teks Hitam Tebal)
  - `Harga Satuan (Rp):` (Teks Hitam Tebal)
  - `Jumlah Stok:` (Teks Hitam Tebal)
  - `Kadaluarsa / Garansi:` (Teks Hitam Tebal)
- **Kolom Isi Data Nama**: Tetap mempertahankan penandaan hijau mint lembut (`#ECFDF5`) dan teks hijau tua (`#047857`) agar nama produk/kasir mudah dibedakan.

---

## 📁 Folder Penyimpanan Struk (`struk/`)

Setiap kali transaksi kasir berhasil diproses (*Checkout*), sistem akan **secara otomatis membuat file nota struk teks (.txt)** dan menyimpannya ke dalam folder:

```text
kasir/
└── struk/
    ├── TRX-0001.txt
    ├── TRX-0002.txt
    └── ...
```

### Fitur Pengelolaan Struk:
1. **Otomatis Tersimpan**: Setiap pembayaran langsung tersimpan dalam format file `.txt` rapi dengan nomor transaksi unik.
2. **Tombol "📂 Buka Folder Struk"**: Tersedia pada jendela pop-up bukti pembayaran dan tab *Riwayat Transaksi*, yang akan langsung membuka Windows File Explorer pada folder `struk/`.
3. **Cetak Ulang**: Riwayat transaksi dapat dibuka kembali dan dicetak ulang kapan saja.

---

## 🎨 Tema Warna Baru (Live Theme Switcher)

Aplikasi dilengkapi dengan **5 pilihan palet warna modern** yang dapat diganti secara langsung melalui menu dropdown di pojok kanan atas jendela aplikasi:

1. **🌿 Emerald Green (Default - Kasir Minimarket Modern)**: Dominasi Dark Slate `#0F172A` dengan aksen Hijau Mint `#10B981` dan tombol bayar Emerald `#059669`.
2. **🌊 Ocean Blue (Biru Segar)**: Nuansa biru laut korporat dan finansial yang profesional.
3. **🍇 Royal Purple (Ungu Elegan)**: Nuansa ungu violet modern bergaya aplikasi *Fintech* kekinian.
4. **☕ Warm Amber (Cafe & Resto)**: Nuansa hangat stone dan amber keemasan ala kedai kopi & bakery.
5. **🖤 Midnight Charcoal (Teknologi / Cyber)**: Nuansa hitam obsidian minimalis dengan aksen *electric cyan*.

---

## 📦 Daftar Barang Baru (Katalog Produk Populer)

Data bawaan produk telah diperbarui dengan produk-produk populer yang sangat realistis:

### A. Kategori Makanan & Minuman (`ProdukMakanan`)
| ID | Nama Produk | Harga | Stok | Info Kadaluarsa |
|---|---|---|---|---|
| `M001` | Indomie Goreng Spesial | Rp 3.500 | 120 | Exp: 2027-06-20 |
| `M002` | Ultra Milk Cokelat 250ml | Rp 6.500 | 50 | Exp: 2027-03-10 |
| `M003` | Chitato Sapi Panggang 68g | Rp 11.000 | 40 | Exp: 2026-12-15 |
| `M004` | Kopi Kenangan Mantan 220ml | Rp 9.500 | 35 | Exp: 2027-01-25 |
| `M005` | SilverQueen Almond 58g | Rp 15.500 | 30 | Exp: 2027-08-14 |
| `M006` | Teh Botol Sosro 450ml | Rp 5.500 | 65 | Exp: 2027-04-30 |
| `M007` | Sari Roti Tawar Gandum | Rp 17.000 | 25 | Exp: 2026-11-20 |

### B. Kategori Elektronik & Gadget (`ProdukElektronik`)
| ID | Nama Produk | Harga | Stok | Info Garansi |
|---|---|---|---|---|
| `E001` | TWS Earphone Bluetooth 5.3 | Rp 175.000 | 15 | Garansi: 12 Bulan |
| `E002` | Kabel Data Type-C Fast 65W | Rp 45.000 | 30 | Garansi: 6 Bulan |
| `E003` | Flashdisk SanDisk 64GB USB 3.0 | Rp 85.000 | 25 | Garansi: 24 Bulan |
| `E004` | Powerbank 10000mAh Slim PD | Rp 145.000 | 18 | Garansi: 12 Bulan |
| `E005` | Smartwatch Fitness Tracker | Rp 320.000 | 10 | Garansi: 12 Bulan |
| `E006` | Mouse Wireless Silent Click | Rp 75.000 | 22 | Garansi: 12 Bulan |
| `E007` | Charger GaN 33W Dual Port | Rp 110.000 | 16 | Garansi: 12 Bulan |

---

## 📁 Struktur Folder Proyek

```text
kasir/
├── struk/                              # 📁 FOLDER PENYIMPANAN DATA STRUK (.txt)
├── nbproject/                          # Konfigurasi Apache NetBeans
│   ├── project.xml
│   └── project.properties
├── build.xml                           # Script Ant untuk build NetBeans
├── run.bat                             # Script sekali klik di Windows
├── README.md                           # Panduan lengkap
└── src/
    └── com/
        └── kasir/
            ├── model/                  # DATA ENTITY & HIRARKI OOP
            │   ├── Produk.java            # Abstract Class (Atribut, Enkapsulasi, Abstraksi)
            │   ├── ProdukMakanan.java     # Subclass 1 (Pewarisan & Polimorfisme Overriding)
            │   ├── ProdukElektronik.java  # Subclass 2 (Pewarisan & Polimorfisme Overriding)
            │   ├── ItemTransaksi.java     # Model item keranjang belanja
            │   └── Transaksi.java         # Model transaksi & struk (Overloading)
            │
            ├── service/                # LOGIKA BISNIS & KONTRAK ABSTRAKSI
            │   ├── ICetakStruk.java       # Interface Abstraksi Cetak Struk
            │   ├── IProdukService.java    # Interface Abstraksi Operasi CRUD
            │   ├── ProdukService.java     # Implementasi CRUD & Overloading Cari
            │   └── TransaksiService.java  # Logika Bisnis Point of Sales & Stok
            │
            ├── gui/                    # TAMPILAN GRAFIS DESKTOP (JFRAME)
            │   ├── TemaWarna.java         # Pengelola 5 Tema Warna (Theme Manager)
            │   ├── KasirMainFrame.java    # Jendela Utama (POS, CRUD, Riwayat, OOP)
            │   └── DialogStruk.java       # Pop-up Struk Thermal & Cetak Printer
            │
            ├── util/                   # FORMATTER & FILE I/O
            │   ├── FileStrukUtil.java     # Otomasi simpan & buka folder struk/
            │   └── FormatUtil.java        # Formatter Rupiah & Tanggal
            │
            └── main/                   # ENTRY POINT
                └── MainKasir.java         # Peluncur Jendela Desktop (EDT)
```

---

## 🎯 Pemetaan 5 Konsep Utama OOP

1. **Atribut (Attributes)**: Field instance (`idProduk`, `nama`, `harga`, `stok`, `tanggalKadaluarsa`, `garansiBulan`) dan konstanta statis (`NAMA_TOKO`, `TARIF_PPN`).
2. **Enkapsulasi (Encapsulation)**: Atribut `private`, diakses via getter/setter tervalidasi serta method proteksi bisnis `kurangiStok(qty)`.
3. **Pewarisan (Inheritance)**: `ProdukMakanan` dan `ProdukElektronik` mewarisi superclass `Produk` dengan kata kunci `extends` dan `super(...)`.
4. **Abstraksi (Abstraction)**: `abstract class Produk` mendefinisikan method abstrak `hitungDiskon()`, `getKategori()`, dan `getInfoKhusus()`. Didukung interface `IProdukService` dan `ICetakStruk`.
5. **Polimorfisme (Polymorphism)**:
   - **Dinamis (Overriding)**: Diskon makanan (10% jika beli $\ge 5$) vs Diskon elektronik (5% + Rp 25.000 jika beli $\ge 2$).
   - **Statis (Overloading)**: `cariProduk(id)` vs `cariProduk(keyword, cariNama)` dan `cetakStruk()` vs `cetakStruk(pesanPromo)`.

---

## 🚀 Cara Menjalankan di NetBeans

1. Buka **Apache NetBeans**.
2. Klik **File** $\rightarrow$ **Open Project...** (`Ctrl + Shift + O`).
3. Pilih folder: `C:\Users\user\OneDrive\Dokumen\kasir`.
4. Klik **Open Project**, lalu tekan **F6** (atau tombol hijau **Run**).
5. Jendela aplikasi kasir visual akan langsung terbuka!
