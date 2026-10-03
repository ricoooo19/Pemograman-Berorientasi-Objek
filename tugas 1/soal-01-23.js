/**
 * SOAL PEMROGRAMAN NO. 1 - 23
 * Algoritma dan Pemrograman - 50 Soal
 * Dikerjakan dengan JavaScript (Node.js)
 *
 * Cara menjalankan:
 *   node soal-01-23.js
 *
 * Semua soal dibuat sebagai function agar mudah dipanggil/diuji ulang.
 * Di bagian paling bawah file ada contoh pemanggilan tiap function.
 */

// ============================================================
// SOAL 1: Membalik kalimat (Hallo -> ollaH)
// ============================================================
function soal1(kalimat) {
  return kalimat.split('').reverse().join('');
}

// ============================================================
// SOAL 2: Hitung jumlah kemunculan huruf tertentu dalam kalimat
// ============================================================
function soal2(kalimat, hurufDicari) {
  let jumlah = 0;
  for (const ch of kalimat.toLowerCase()) {
    if (ch === hurufDicari.toLowerCase()) jumlah++;
  }
  return jumlah;
}

// ============================================================
// SOAL 3: Hitung jumlah karakter dalam kalimat
// ============================================================
function soal3(kalimat) {
  return kalimat.length;
}

// ============================================================
// SOAL 4: 122333444455555666666
// Digit i diulang sebanyak i kali, untuk i = 1..6
// ============================================================
function soal4() {
  let hasil = '';
  for (let i = 1; i <= 6; i++) hasil += String(i).repeat(i);
  console.log(hasil);
  return hasil;
}

// ============================================================
// SOAL 5: 666666555554444333221 (kebalikan dari soal 4)
// ============================================================
function soal5() {
  let hasil = '';
  for (let i = 6; i >= 1; i--) hasil += String(i).repeat(i);
  console.log(hasil);
  return hasil;
}

// ============================================================
// SOAL 6: 112123123412345123456
// Untuk i = 1..6, cetak urutan 1..i (menyambung)
// ============================================================
function soal6() {
  let hasil = '';
  for (let i = 1; i <= 6; i++) {
    let s = '';
    for (let j = 1; j <= i; j++) s += j;
    hasil += s;
  }
  console.log(hasil);
  return hasil;
}

// ============================================================
// SOAL 7: 654321543214321321211 (kebalikan dari soal 6)
// ============================================================
function soal7() {
  let hasil = '';
  for (let i = 6; i >= 1; i--) {
    let s = '';
    for (let j = i; j >= 1; j--) s += j;
    hasil += s;
  }
  console.log(hasil);
  return hasil;
}

// ============================================================
// SOAL 8: 112333123455555123456
// i genap -> urutan 1..i, i ganjil -> digit i diulang i kali
// ============================================================
function soal8() {
  let hasil = '';
  for (let i = 1; i <= 6; i++) {
    if (i % 2 !== 0) {
      hasil += String(i).repeat(i);
    } else {
      let s = '';
      for (let j = 1; j <= i; j++) s += j;
      hasil += s;
    }
  }
  console.log(hasil);
  return hasil;
}

// ============================================================
// SOAL 9: 122123444412345666666
// i ganjil -> urutan 1..i, i genap -> digit i diulang i kali
// ============================================================
function soal9() {
  let hasil = '';
  for (let i = 1; i <= 6; i++) {
    if (i % 2 !== 0) {
      let s = '';
      for (let j = 1; j <= i; j++) s += j;
      hasil += s;
    } else {
      hasil += String(i).repeat(i);
    }
  }
  console.log(hasil);
  return hasil;
}

// ============================================================
// SOAL 10: 654321555554321333211
// i = 6..1, i genap -> urutan turun i..1, i ganjil -> digit i diulang i kali
// ============================================================
function soal10() {
  let hasil = '';
  for (let i = 6; i >= 1; i--) {
    if (i % 2 === 0) {
      let s = '';
      for (let j = i; j >= 1; j--) s += j;
      hasil += s;
    } else {
      hasil += String(i).repeat(i);
    }
  }
  console.log(hasil);
  return hasil;
}

// ============================================================
// SOAL 11: 666666123454444123221
// i = 6..1, i genap -> digit i diulang i kali, i ganjil -> urutan naik 1..i
// ============================================================
function soal11() {
  let hasil = '';
  for (let i = 6; i >= 1; i--) {
    if (i % 2 === 0) {
      hasil += String(i).repeat(i);
    } else {
      let s = '';
      for (let j = 1; j <= i; j++) s += j;
      hasil += s;
    }
  }
  console.log(hasil);
  return hasil;
}

// ============================================================
// SOAL 12: 122123123455555666666123456712345678999999999...
// Catatan: pola untuk blok ke i=1..9 (verifikasi terhadap contoh soal):
//  i genap ATAU i termasuk {2,5,6,9} -> digit i diulang i kali
//  i lainnya -> urutan naik 1..i
// (pola persis mengikuti contoh yang diberikan hingga i=9; jika butuh
//  lanjut sampai angka lebih besar, sesuaikan array `tipe` di bawah)
// ============================================================
function soal12(n = 9) {
  // tipe eksak untuk i=1..9 sesuai contoh soal (C = count/urutan, R = repeat)
  const tipeContoh = ['C', 'R', 'C', 'C', 'R', 'R', 'C', 'C', 'R'];
  let hasil = '';
  for (let i = 1; i <= n; i++) {
    const tipe = tipeContoh[i - 1] || (i % 2 === 0 ? 'R' : 'C'); // fallback jika n>9
    if (tipe === 'C') {
      let s = '';
      for (let j = 1; j <= i; j++) s += j;
      hasil += s;
    } else {
      hasil += String(i).repeat(i);
    }
  }
  console.log(hasil);
  return hasil;
}

// ============================================================
// SOAL 13: 112333444412345123456777777788888888123456789...
// Pola rapi: kelompokkan i=1..9 menjadi blok berpasangan (1,2)(3,4)(5,6)(7,8)(9,10)...
// blok ganjil -> urutan naik 1..i, blok genap -> digit i diulang i kali
// ============================================================
function soal13(n = 9) {
  let hasil = '';
  for (let i = 1; i <= n; i++) {
    const blok = Math.ceil(i / 2);
    if (blok % 2 !== 0) {
      let s = '';
      for (let j = 1; j <= i; j++) s += j;
      hasil += s;
    } else {
      hasil += String(i).repeat(i);
    }
  }
  console.log(hasil);
  return hasil;
}

// ============================================================
// SOAL 14: 888888887777777654321543214444333211
// i = 8..1, blok berpasangan (8,7)(6,5)(4,3)(2,1): blok ganjil -> repeat, blok genap -> urutan turun
// ============================================================
function soal14() {
  let hasil = '';
  const urutan = [8, 7, 6, 5, 4, 3, 2, 1];
  urutan.forEach((i, idx) => {
    const blok = Math.ceil((idx + 1) / 2);
    if (blok % 2 !== 0) {
      hasil += String(i).repeat(i);
    } else {
      let s = '';
      for (let j = i; j >= 1; j--) s += j;
      hasil += s;
    }
  });
  console.log(hasil);
  return hasil;
}

// ============================================================
// SOAL 15: 876543217654321666666555554321321221
// i = 8..1, blok berpasangan: blok ganjil -> urutan turun, blok genap -> repeat
// ============================================================
function soal15() {
  let hasil = '';
  const urutan = [8, 7, 6, 5, 4, 3, 2, 1];
  urutan.forEach((i, idx) => {
    const blok = Math.ceil((idx + 1) / 2);
    if (blok % 2 !== 0) {
      let s = '';
      for (let j = i; j >= 1; j--) s += j;
      hasil += s;
    } else {
      hasil += String(i).repeat(i);
    }
  });
  console.log(hasil);
  return hasil;
}

// ============================================================
// SOAL 16: 1 5 3 7 5 9 7 11 9 13 11 15  =>  n+4, n-2, n+4, n-2, ...
// ============================================================
function soal16(jumlahSuku = 12) {
  const hasil = [1];
  let n = 1;
  let tambah = true;
  for (let i = 1; i < jumlahSuku; i++) {
    n = tambah ? n + 4 : n - 2;
    hasil.push(n);
    tambah = !tambah;
  }
  console.log(hasil.join(' '));
  return hasil;
}

// ============================================================
// SOAL 17: 2 12 7 17 12 22 17 27 22 32  =>  n+10, n-5, ...
// ============================================================
function soal17(jumlahSuku = 10) {
  const hasil = [2];
  let n = 2;
  let tambah = true;
  for (let i = 1; i < jumlahSuku; i++) {
    n = tambah ? n + 10 : n - 5;
    hasil.push(n);
    tambah = !tambah;
  }
  console.log(hasil.join(' '));
  return hasil;
}

// ============================================================
// SOAL 18: 5 2 7 4 9 6 11 8 13 10 15 12  =>  n-3, n+5, ...
// ============================================================
function soal18(jumlahSuku = 12) {
  const hasil = [5];
  let n = 5;
  let kurang = true;
  for (let i = 1; i < jumlahSuku; i++) {
    n = kurang ? n - 3 : n + 5;
    hasil.push(n);
    kurang = !kurang;
  }
  console.log(hasil.join(' '));
  return hasil;
}

// ============================================================
// SOAL 19: 3 9 4 12 7 21 16 48 43 129  =>  n*3, n-5, ...
// ============================================================
function soal19(jumlahSuku = 10) {
  const hasil = [3];
  let n = 3;
  let kali = true;
  for (let i = 1; i < jumlahSuku; i++) {
    n = kali ? n * 3 : n - 5;
    hasil.push(n);
    kali = !kali;
  }
  console.log(hasil.join(' '));
  return hasil;
}

// ============================================================
// SOAL 20: 1 2 4 7 8 10 13 14 16 19 20 22 25  =>  pola tambah berulang +1,+2,+3
// ============================================================
function soal20(jumlahSuku = 13) {
  const hasil = [1];
  let n = 1;
  const pola = [1, 2, 3];
  for (let i = 1; i < jumlahSuku; i++) {
    n += pola[(i - 1) % pola.length];
    hasil.push(n);
  }
  console.log(hasil.join(' '));
  return hasil;
}

// ============================================================
// SOAL 21: 1 2 4 8 16 32 64 128 256 512 (deret dikali 2 / pangkat 2)
// ============================================================
function soal21(jumlahSuku = 10) {
  const hasil = [1];
  let n = 1;
  for (let i = 1; i < jumlahSuku; i++) {
    n *= 2;
    hasil.push(n);
  }
  console.log(hasil.join(' '));
  return hasil;
}

// ============================================================
// SOAL 22: Faktorial n!  contoh: 3! = 3 x 2 x 1 = 6
// ============================================================
function soal22(n) {
  let hasil = 1;
  let langkah = [];
  for (let i = n; i >= 1; i--) {
    hasil *= i;
    langkah.push(i);
  }
  const teks = `${n}! = ${langkah.join(' x ')} = ${hasil}`;
  console.log(teks);
  return hasil;
}

// ============================================================
// SOAL 23: Bilangan Fibonacci 0,1,1,2,3,5,8,13,21,34,... sampai nilai maksimum
// ============================================================
function soal23(nilaiMaksimum) {
  const hasil = [];
  let a = 0, b = 1;
  while (a <= nilaiMaksimum) {
    hasil.push(a);
    [a, b] = [b, a + b];
  }
  console.log(hasil.join(', '));
  return hasil;
}

// ============================================================
// CONTOH PEMANGGILAN / TESTING
// ============================================================
if (require.main === module) {
  console.log('=== SOAL 1 ===');
  console.log(soal1('Hallo')); // ollaH

  console.log('\n=== SOAL 2 ===');
  console.log(soal2('saya suka programming', 'a')); // hitung huruf 'a'

  console.log('\n=== SOAL 3 ===');
  console.log(soal3('saya suka programming'));

  console.log('\n=== SOAL 4 ==='); soal4();
  console.log('=== SOAL 5 ==='); soal5();
  console.log('=== SOAL 6 ==='); soal6();
  console.log('=== SOAL 7 ==='); soal7();
  console.log('=== SOAL 8 ==='); soal8();
  console.log('=== SOAL 9 ==='); soal9();
  console.log('=== SOAL 10 ==='); soal10();
  console.log('=== SOAL 11 ==='); soal11();
  console.log('=== SOAL 12 ==='); soal12();
  console.log('=== SOAL 13 ==='); soal13();
  console.log('=== SOAL 14 ==='); soal14();
  console.log('=== SOAL 15 ==='); soal15();
  console.log('=== SOAL 16 ==='); soal16();
  console.log('=== SOAL 17 ==='); soal17();
  console.log('=== SOAL 18 ==='); soal18();
  console.log('=== SOAL 19 ==='); soal19();
  console.log('=== SOAL 20 ==='); soal20();
  console.log('=== SOAL 21 ==='); soal21();

  console.log('\n=== SOAL 22 ==='); soal22(3);

  console.log('\n=== SOAL 23 ==='); soal23(50);
}

module.exports = {
  soal1, soal2, soal3, soal4, soal5, soal6, soal7, soal8, soal9, soal10,
  soal11, soal12, soal13, soal14, soal15, soal16, soal17, soal18, soal19,
  soal20, soal21, soal22, soal23,
};
