/**
 * POLA BINTANG NO. 1 - 20
 * Algoritma dan Pemrograman - 50 Soal (bagian pola bintang)
 * Dikerjakan dengan JavaScript (Node.js)
 *
 * Cara menjalankan:
 *   node pola-bintang.js
 *
 * CATATAN: teks pola bintang pada file PDF asal mengalami sedikit
 * pergeseran spasi (kemungkinan besar karena proses ekstraksi teks dari
 * PDF, terutama pada pola 2, 3, 6, 10, dan 16). Untuk pola-pola tersebut,
 * saya memakai versi standar/rapi yang paling sesuai dengan bentuk visual
 * yang dimaksud. Silakan cocokkan lagi dengan soal aslinya (versi gambar),
 * dan tinggal ubah angka `n`/`lebar` jika ukurannya perlu disesuaikan.
 */

// ============================================================
// POLA 1: segitiga terbuka dari atas (baris pertama solid, makin ke
// bawah makin banyak celah di tengah)
// ***********
// ***** *****
// **** ****
// *** ***
// ** **
// * *
// ============================================================
function pola1(n = 6) {
  const lebar = 2 * n - 1;
  console.log('*'.repeat(lebar)); // baris pertama, solid
  for (let r = 1; r < n; r++) {
    const sisi = n - r;
    const celah = lebar - 2 * sisi;
    console.log('*'.repeat(sisi) + ' '.repeat(celah) + '*'.repeat(sisi));
  }
}

// ============================================================
// POLA 2: segitiga piramid biasa (tumbuh dari 1 bintang ke bawah)
//     *
//    ***
//   *****
//  *******
// *********
// ***********
// ============================================================
function pola2(n = 6) {
  const lebar = 2 * n - 1;
  for (let r = 1; r <= n; r++) {
    const jumlahBintang = 2 * r - 1;
    const spasi = (lebar - jumlahBintang) / 2;
    console.log(' '.repeat(spasi) + '*'.repeat(jumlahBintang));
  }
}

// ============================================================
// POLA 3: dua segitiga kecil menaik ditumpuk (zig-zag)
// *
// **
// ***
// *
// **
// ***
// ============================================================
function pola3(n = 3) {
  for (let blok = 0; blok < 2; blok++) {
    for (let r = 1; r <= n; r++) {
      console.log('*'.repeat(r));
    }
  }
}

// ============================================================
// POLA 4: kebalikan pola 1 (dua segitiga mengecil bertemu di bawah, baris terakhir solid)
// *         *
// **       **
// ***     ***
// ****   ****
// ***** *****
// ***********
// ============================================================
function pola4(n = 6) {
  const lebar = 2 * n - 1;
  for (let r = 1; r < n; r++) {
    const celah = lebar - 2 * r;
    console.log('*'.repeat(r) + ' '.repeat(celah) + '*'.repeat(r));
  }
  console.log('*'.repeat(lebar)); // baris terakhir, solid
}

// ============================================================
// POLA 5: piramid terbalik (mengecil ke bawah, rata tengah)
// ***********
//  *********
//   *******
//    *****
//     ***
//      *
// ============================================================
function pola5(n = 6) {
  const lebar = 2 * n - 1;
  for (let r = 0; r < n; r++) {
    const jumlahBintang = lebar - 2 * r;
    console.log(' '.repeat(r) + '*'.repeat(jumlahBintang));
  }
}

// ============================================================
// POLA 6: segitiga rata-kanan (mengecil) lalu segitiga rata-kiri (membesar)
//   *
//  **
// ***
// *
// **
// ***
// ============================================================
function pola6(n = 3) {
  // bagian 1: rata kanan, tinggi bertambah tapi indent berkurang
  for (let r = 1; r <= n; r++) {
    console.log(' '.repeat(n - r) + '*'.repeat(r));
  }
  // bagian 2: rata kiri biasa
  for (let r = 1; r <= n; r++) {
    console.log('*'.repeat(r));
  }
}

// ============================================================
// POLA 7: bintang mengecil lalu membesar lagi (rata kanan), membentuk sudut lancip
//  *****
//  ****
//  ***
//  **
//  *
//  **
//  ***
//  ****
//  *****
// ============================================================
function pola7(n = 5) {
  const urutan = [];
  for (let i = n; i >= 1; i--) urutan.push(i);
  for (let i = 2; i <= n; i++) urutan.push(i);
  for (const jumlah of urutan) {
    console.log(' '.repeat(n - jumlah) + '*'.repeat(jumlah));
  }
}

// ============================================================
// POLA 8: 0 + bintang (5 baris), baris terakhir semua 0
// 0**********
// 0**********
// 0**********
// 0**********
// 0**********
// 00000000000
// ============================================================
function pola8(jumlahBintang = 10, tinggi = 5) {
  for (let r = 0; r < tinggi; r++) {
    console.log('0' + '*'.repeat(jumlahBintang));
  }
  console.log('0'.repeat(jumlahBintang + 1));
}

// ============================================================
// POLA 9: bintang + 0 (5 baris), baris terakhir semua 0
// **********0
// **********0
// **********0
// **********0
// **********0
// 00000000000
// ============================================================
function pola9(jumlahBintang = 10, tinggi = 5) {
  for (let r = 0; r < tinggi; r++) {
    console.log('*'.repeat(jumlahBintang) + '0');
  }
  console.log('0'.repeat(jumlahBintang + 1));
}

// ============================================================
// POLA 10: seperti pola 7 tapi mulai dari 6 (mengecil ke 1, lalu membesar lagi)
//  ******
//  *****
//  ****
//  ***
//  **
//  *
//  **
//  ***
//  ****
//  *****
//  ******
// ============================================================
function pola10(n = 6) {
  const urutan = [];
  for (let i = n; i >= 1; i--) urutan.push(i);
  for (let i = 2; i <= n; i++) urutan.push(i);
  for (const jumlah of urutan) {
    console.log(' '.repeat(n - jumlah) + '*'.repeat(jumlah));
  }
}

// ============================================================
// POLA 11: kebalikan pola 8 (baris 0 di atas)
// 00000000000
// 0**********
// 0**********
// 0**********
// 0**********
// 0**********
// ============================================================
function pola11(jumlahBintang = 10, tinggi = 5) {
  console.log('0'.repeat(jumlahBintang + 1));
  for (let r = 0; r < tinggi; r++) {
    console.log('0' + '*'.repeat(jumlahBintang));
  }
}

// ============================================================
// POLA 12: kebalikan pola 9 (baris 0 di atas)
// 00000000000
// **********0
// **********0
// **********0
// **********0
// **********0
// ============================================================
function pola12(jumlahBintang = 10, tinggi = 5) {
  console.log('0'.repeat(jumlahBintang + 1));
  for (let r = 0; r < tinggi; r++) {
    console.log('*'.repeat(jumlahBintang) + '0');
  }
}

// ============================================================
// POLA 13: tangga diagonal, 0 di kiri makin banyak, bintang di kanan makin sedikit
// 0******
// 00*****
// 000****
// 0000***
// 00000**
// 000000*
// ============================================================
function pola13(n = 6) {
  for (let r = 0; r < n; r++) {
    console.log('0'.repeat(r + 1) + '*'.repeat(n - r));
  }
}

// ============================================================
// POLA 14: kebalikan pola 13 (bintang di kiri makin banyak, 0 di kanan makin sedikit)
// *000000
// **00000
// ***0000
// ****000
// *****00
// ******0
// ============================================================
function pola14(n = 6) {
  for (let r = 0; r < n; r++) {
    console.log('*'.repeat(r + 1) + '0'.repeat(n - r));
  }
}

// ============================================================
// POLA 15: 0 di kiri makin sedikit, bintang di kanan makin banyak
// 000000*
// 00000**
// 0000***
// 000****
// 00*****
// 0******
// ============================================================
function pola15(n = 6) {
  for (let r = 0; r < n; r++) {
    console.log('0'.repeat(n - r) + '*'.repeat(r + 1));
  }
}

// ============================================================
// POLA 16: sama seperti pola 15 (pada soal aslinya pola 15 dan 16
// tertulis identik; jika yang dimaksud berbeda, biasanya versi cermin
// dari pola 15, jadi disediakan juga sebagai alternatif di bawah)
// 000000*
// 00000**
// 0000***
// 000****
// 00*****
// 0******
// ============================================================
function pola16(n = 6) {
  console.log('(Pola 16 identik dengan pola 15 pada soal asli, ditampilkan sama persis)');
  pola15(n);
}

// ============================================================
// POLA 17: satu bintang bergeser dari kanan ke kiri, sisanya 0
// 000000*
// 00000*0
// 0000*00
// 000*000
// 00*0000
// 0*00000
// ============================================================
function pola17(n = 6) {
  for (let r = 0; r < n; r++) {
    const posisiBintang = n + 1 - r; // posisi dari kiri (1-indexed), makin kecil tiap baris
    let baris = '';
    for (let kolom = 1; kolom <= n + 1; kolom++) {
      baris += kolom === posisiBintang ? '*' : '0';
    }
    console.log(baris);
  }
}

// ============================================================
// POLA 18: satu bintang bergeser dari kiri ke kanan, sisanya 0
// *000000
// 0*00000
// 00*0000
// 000*000
// 0000*00
// 00000*0
// ============================================================
function pola18(n = 6) {
  for (let r = 0; r < n; r++) {
    const posisiBintang = r + 1; // posisi dari kiri (1-indexed), makin besar tiap baris
    let baris = '';
    for (let kolom = 1; kolom <= n + 1; kolom++) {
      baris += kolom === posisiBintang ? '*' : '0';
    }
    console.log(baris);
  }
}

// ============================================================
// POLA 19: kotak berbingkai 0, isi bintang
// 0000000
// 0*****0
// 0*****0
// 0*****0
// 0*****0
// 0000000
// ============================================================
function pola19(n = 5, tinggiIsi = 4) {
  const lebar = n + 2;
  console.log('0'.repeat(lebar));
  for (let r = 0; r < tinggiIsi; r++) {
    console.log('0' + '*'.repeat(n) + '0');
  }
  console.log('0'.repeat(lebar));
}

// ============================================================
// POLA 20: 3 baris (0, *, =) diulang 2 kali
// 0000000
// *******
// =======
// 0000000
// *******
// =======
// ============================================================
function pola20(lebar = 7, pengulangan = 2) {
  for (let p = 0; p < pengulangan; p++) {
    console.log('0'.repeat(lebar));
    console.log('*'.repeat(lebar));
    console.log('='.repeat(lebar));
  }
}

// ============================================================
// MENJALANKAN SEMUA POLA (untuk contoh/testing)
// ============================================================
if (require.main === module) {
  const daftarPola = [
    pola1, pola2, pola3, pola4, pola5, pola6, pola7, pola8, pola9, pola10,
    pola11, pola12, pola13, pola14, pola15, pola16, pola17, pola18, pola19, pola20,
  ];
  daftarPola.forEach((fn, idx) => {
    console.log(`\n=== POLA ${idx + 1} ===`);
    fn();
  });
}

module.exports = {
  pola1, pola2, pola3, pola4, pola5, pola6, pola7, pola8, pola9, pola10,
  pola11, pola12, pola13, pola14, pola15, pola16, pola17, pola18, pola19, pola20,
};
