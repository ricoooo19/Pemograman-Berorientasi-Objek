/**
 * SOAL PEMROGRAMAN NO. 24 - 50
 * Algoritma dan Pemrograman - 50 Soal
 * Dikerjakan dengan JavaScript (Node.js)
 *
 * Cara menjalankan:
 *   node soal-24-50.js
 */

// ============================================================
// Helper: cek tahun kabisat
// Kabisat jika habis dibagi 4 DAN (tidak habis dibagi 100 ATAU habis dibagi 400)
// ============================================================
function isKabisat(tahun) {
  return (tahun % 4 === 0 && tahun % 100 !== 0) || tahun % 400 === 0;
}

// ============================================================
// SOAL 24-28: Tahun kabisat dari n_awal s/d n_akhir yang angka
// TERAKHIRNYA tertentu (0, 2, 4, 6, 8)
// ============================================================
function tahunKabisatAngkaTerakhir(nAwal, nAkhir, angkaTerakhir) {
  const hasil = [];
  for (let tahun = nAwal; tahun <= nAkhir; tahun++) {
    if (tahun % 10 === angkaTerakhir && isKabisat(tahun)) {
      hasil.push(tahun);
    }
  }
  console.log(hasil.join(', '));
  return hasil;
}

function soal24(nAwal, nAkhir) { return tahunKabisatAngkaTerakhir(nAwal, nAkhir, 0); }
function soal25(nAwal, nAkhir) { return tahunKabisatAngkaTerakhir(nAwal, nAkhir, 2); }
function soal26(nAwal, nAkhir) { return tahunKabisatAngkaTerakhir(nAwal, nAkhir, 4); }
function soal27(nAwal, nAkhir) { return tahunKabisatAngkaTerakhir(nAwal, nAkhir, 6); }
function soal28(nAwal, nAkhir) { return tahunKabisatAngkaTerakhir(nAwal, nAkhir, 8); }

// ============================================================
// SOAL 29-33: Bilangan habis dibagi X (3,4,5,6,7) dari n_awal s/d n_akhir
// ============================================================
function bilanganHabisDibagi(nAwal, nAkhir, pembagi) {
  const hasil = [];
  for (let i = nAwal; i <= nAkhir; i++) {
    if (i % pembagi === 0) hasil.push(i);
  }
  console.log(hasil.join(', '));
  return hasil;
}

function soal29(nAwal, nAkhir) { return bilanganHabisDibagi(nAwal, nAkhir, 3); }
function soal30(nAwal, nAkhir) { return bilanganHabisDibagi(nAwal, nAkhir, 4); }
function soal31(nAwal, nAkhir) { return bilanganHabisDibagi(nAwal, nAkhir, 5); }
function soal32(nAwal, nAkhir) { return bilanganHabisDibagi(nAwal, nAkhir, 6); }
function soal33(nAwal, nAkhir) { return bilanganHabisDibagi(nAwal, nAkhir, 7); }

// ============================================================
// SOAL 34-41: Animasi angka 0 bergerak dalam satu baris
// Karena dijalankan di terminal (Node.js), animasi disimulasikan dengan
// mencetak ulang baris (console.clear + delay) sehingga terlihat seperti
// '0' berjalan dari satu sisi ke sisi lain lalu kembali.
//
// Parameter `lebar` = panjang baris, `jeda` = delay antar frame (ms)
// ============================================================
function delay(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

async function animasiBaris(urutanPosisi, lebar, jeda = 150) {
  for (const posisi of urutanPosisi) {
    let baris = ' '.repeat(posisi) + '0' + ' '.repeat(lebar - posisi - 1);
    console.clear();
    console.log(`[${baris}]`);
    await delay(jeda);
  }
}

// 34: kiri atas -> kanan atas -> kiri atas lagi -> kanan atas (pergi-pergi)
async function soal34(lebar = 20, jeda = 100) {
  const pergi = [];
  for (let i = 0; i < lebar; i++) pergi.push(i);
  // "pergi lagi dari kiri ke kanan" -> ulangi pergi ke kanan sekali lagi
  await animasiBaris([...pergi, ...pergi], lebar, jeda);
}

// 35: kiri atas -> kanan atas -> balik ke kiri atas (pergi lalu pulang)
async function soal35(lebar = 20, jeda = 100) {
  const pergi = [];
  for (let i = 0; i < lebar; i++) pergi.push(i);
  const pulang = [...pergi].reverse();
  await animasiBaris([...pergi, ...pulang], lebar, jeda);
}

// 36: sama seperti 34 tapi baris bawah (secara visual sama, beda posisi cetak)
async function soal36(lebar = 20, jeda = 100) {
  const pergi = [];
  for (let i = 0; i < lebar; i++) pergi.push(i);
  await animasiBaris([...pergi, ...pergi], lebar, jeda);
}

// 37: baris bawah, pergi lalu pulang
async function soal37(lebar = 20, jeda = 100) {
  const pergi = [];
  for (let i = 0; i < lebar; i++) pergi.push(i);
  const pulang = [...pergi].reverse();
  await animasiBaris([...pergi, ...pulang], lebar, jeda);
}

// 38-41: animasi vertikal (atas-bawah) -> dicetak sebagai banyak baris berurutan
async function animasiKolom(urutanPosisi, tinggi, jeda = 150) {
  for (const posisi of urutanPosisi) {
    console.clear();
    for (let baris = 0; baris < tinggi; baris++) {
      console.log(baris === posisi ? '0' : ' ');
    }
    await delay(jeda);
  }
}

// 38: kiri atas -> kiri bawah -> ulang lagi dari kiri atas ke kiri bawah
async function soal38(tinggi = 10, jeda = 100) {
  const turun = [];
  for (let i = 0; i < tinggi; i++) turun.push(i);
  await animasiKolom([...turun, ...turun], tinggi, jeda);
}

// 39: kiri atas -> kiri bawah -> balik ke kiri atas (turun lalu naik)
async function soal39(tinggi = 10, jeda = 100) {
  const turun = [];
  for (let i = 0; i < tinggi; i++) turun.push(i);
  const naik = [...turun].reverse();
  await animasiKolom([...turun, ...naik], tinggi, jeda);
}

// 40: kanan atas -> kanan bawah -> ulang turun lagi
async function soal40(tinggi = 10, jeda = 100) {
  const turun = [];
  for (let i = 0; i < tinggi; i++) turun.push(i);
  await animasiKolom([...turun, ...turun], tinggi, jeda);
}

// 41: kanan atas -> kanan bawah -> balik naik ke kanan atas
async function soal41(tinggi = 10, jeda = 100) {
  const turun = [];
  for (let i = 0; i < tinggi; i++) turun.push(i);
  const naik = [...turun].reverse();
  await animasiKolom([...turun, ...naik], tinggi, jeda);
}

// ============================================================
// SOAL 42: Input min 10 angka (looping), cari bilangan TERBESAR
// ============================================================
function soal42(daftarAngka) {
  let terbesar = daftarAngka[0];
  for (let i = 1; i < daftarAngka.length; i++) {
    if (daftarAngka[i] > terbesar) terbesar = daftarAngka[i];
  }
  console.log('Bilangan terbesar:', terbesar);
  return terbesar;
}

// ============================================================
// SOAL 43: Input min 10 angka (looping), cari bilangan TERKECIL
// ============================================================
function soal43(daftarAngka) {
  let terkecil = daftarAngka[0];
  for (let i = 1; i < daftarAngka.length; i++) {
    if (daftarAngka[i] < terkecil) terkecil = daftarAngka[i];
  }
  console.log('Bilangan terkecil:', terkecil);
  return terkecil;
}

// ============================================================
// SOAL 44: Input min 10 angka (looping), hitung JUMLAH bilangan GENAP
// ============================================================
function soal44(daftarAngka) {
  let jumlah = 0;
  for (const angka of daftarAngka) {
    if (angka % 2 === 0) jumlah++;
  }
  console.log('Jumlah bilangan genap:', jumlah);
  return jumlah;
}

// ============================================================
// SOAL 45: Input min 10 angka (looping), hitung JUMLAH bilangan GANJIL
// ============================================================
function soal45(daftarAngka) {
  let jumlah = 0;
  for (const angka of daftarAngka) {
    if (angka % 2 !== 0) jumlah++;
  }
  console.log('Jumlah bilangan ganjil:', jumlah);
  return jumlah;
}

// ============================================================
// SOAL 46: Total bilangan bulat positif dari n_awal s/d n_akhir
// ============================================================
function soal46(nAwal, nAkhir) {
  let total = 0;
  for (let i = nAwal; i <= nAkhir; i++) {
    if (i > 0) total += i;
  }
  console.log('Total bilangan positif:', total);
  return total;
}

// ============================================================
// SOAL 47: Total bilangan GENAP dari n_awal s/d n_akhir
// ============================================================
function soal47(nAwal, nAkhir) {
  let total = 0;
  for (let i = nAwal; i <= nAkhir; i++) {
    if (i % 2 === 0) total += i;
  }
  console.log('Total bilangan genap:', total);
  return total;
}

// ============================================================
// SOAL 48: Total bilangan GANJIL dari n_awal s/d n_akhir
// ============================================================
function soal48(nAwal, nAkhir) {
  let total = 0;
  for (let i = nAwal; i <= nAkhir; i++) {
    if (i % 2 !== 0) total += i;
  }
  console.log('Total bilangan ganjil:', total);
  return total;
}

// ============================================================
// Helper: cek bilangan prima
// ============================================================
function isPrima(n) {
  if (n < 2) return false;
  for (let i = 2; i * i <= n; i++) {
    if (n % i === 0) return false;
  }
  return true;
}

// ============================================================
// SOAL 49: Tampilkan bilangan PRIMA dari n_awal s/d n_akhir
// ============================================================
function soal49(nAwal, nAkhir) {
  const hasil = [];
  for (let i = nAwal; i <= nAkhir; i++) {
    if (isPrima(i)) hasil.push(i);
  }
  console.log(hasil.join(', '));
  return hasil;
}

// ============================================================
// SOAL 50: Jumlah TOTAL bilangan PRIMA dari n_awal s/d n_akhir
// ============================================================
function soal50(nAwal, nAkhir) {
  let jumlah = 0;
  for (let i = nAwal; i <= nAkhir; i++) {
    if (isPrima(i)) jumlah++;
  }
  console.log('Jumlah bilangan prima:', jumlah);
  return jumlah;
}

// ============================================================
// CONTOH PEMANGGILAN / TESTING
// ============================================================
async function main() {
  console.log('=== SOAL 24 (kabisat, akhiran 0) ==='); soal24(1900, 2050);
  console.log('=== SOAL 25 (kabisat, akhiran 2) ==='); soal25(1900, 2050);
  console.log('=== SOAL 26 (kabisat, akhiran 4) ==='); soal26(1900, 2050);
  console.log('=== SOAL 27 (kabisat, akhiran 6) ==='); soal27(1900, 2050);
  console.log('=== SOAL 28 (kabisat, akhiran 8) ==='); soal28(1900, 2050);

  console.log('\n=== SOAL 29 (habis dibagi 3) ==='); soal29(1, 30);
  console.log('=== SOAL 30 (habis dibagi 4) ==='); soal30(1, 30);
  console.log('=== SOAL 31 (habis dibagi 5) ==='); soal31(1, 30);
  console.log('=== SOAL 32 (habis dibagi 6) ==='); soal32(1, 30);
  console.log('=== SOAL 33 (habis dibagi 7) ==='); soal33(1, 30);

  console.log('\n=== SOAL 42-45 (contoh 10 angka) ===');
  const contohAngka = [12, 45, 3, 78, 22, 5, 91, 60, 17, 8];
  soal42(contohAngka);
  soal43(contohAngka);
  soal44(contohAngka);
  soal45(contohAngka);

  console.log('\n=== SOAL 46-48 ===');
  soal46(-5, 10);
  soal47(1, 20);
  soal48(1, 20);

  console.log('\n=== SOAL 49-50 ===');
  soal49(1, 50);
  soal50(1, 50);

  // Animasi (34-41) sengaja tidak dijalankan otomatis di sini karena
  // memakai console.clear() + delay, cocoknya dijalankan manual, contoh:
  // soal34(); atau await soal39(15, 80);
  console.log('\n(Soal 34-41 adalah animasi. Jalankan manual, misal: node -e "require(\'./soal-24-50\').soal35()")');
}

if (require.main === module) {
  main();
}

module.exports = {
  isKabisat, soal24, soal25, soal26, soal27, soal28,
  soal29, soal30, soal31, soal32, soal33,
  soal34, soal35, soal36, soal37, soal38, soal39, soal40, soal41,
  soal42, soal43, soal44, soal45, soal46, soal47, soal48,
  isPrima, soal49, soal50,
};
