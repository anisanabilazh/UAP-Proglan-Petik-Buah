Fruit Picking Payment System

Fruit Picking Payment System adalah aplikasi berbasis Java Swing yang membantu pengguna menghitung
total pembayaran berdasarkan jenis buah dan berat buah yang dipilih.
Program ini menampilkan daftar harga buah, memungkinkan pengguna untuk menghitung total harga, dan mencatat transaksi pembayaran.
Program perhitugan pembelian buah ini jg mempermudah pembeli dengan menampilkan pricelist tiap buahnya

-Fitur Utama dalam program
Daftar Harga Buah:
- Anggur: IDR 50,000 / Kg
- Strawberry: IDR 25,000 / Kg
- Jeruk Bali: IDR 20,000 / Kg
- Apel Hijau: IDR 35,000 / Kg
- Mangga: IDR 30,000 / Kg

input Pengguna:
- Nama Pelanggan
- Nama Buah
- Berat (dalam kilogram)

Perhitungan Harga:
- Aplikasi secara otomatis menghitung total harga berdasarkan berat dan harga per kilogram.
- Hasil perhitungan ditampilkan dalam format IDR (Indonesia Rupiah).

pencatatan Transaksi:
- Menyimpan data setiap transaksi yang meliputi nama pelanggan, nama buah, berat, dan total harga.
- Menampilkan riwayat transaksi dalam area teks.

-java yang digunakan
java Swing: Untuk antarmuka pengguna.
java AWT: Untuk tata letak dan komponen GUI tambahan.
java Util: Untuk pengelolaan daftar (List).
java Text: Untuk format angka dan mata uang.

-Cara Menggunakan Program
1. Buka Aplikasi:
   Jalankan program menggunakan Java Virtual Machine (JVM).

2. Masukkan Data:
   Isi nama pelanggan di bidang "Customer Name".
   Isi nama buah di bidang "Fruit Name". Nama buah harus sesuai dengan daftar harga (contoh: "anggur", "strawberry").
   Masukkan berat buah dalam kilogram di bidang "Weight (Kg)".

3. Hitung Harga:
   Klik tombol Calculate untuk menghitung total harga.
   Hasil perhitungan akan muncul di bidang "Total Price (IDR)".

4. Simpan Transaksi:
   Klik tombol Submit untuk menyimpan transaksi.
   Data transaksi akan ditampilkan di area "Payment Records".
   Program akan membersihkan input untuk memulai transaksi baru.

-Exception Handling
-Nama Pelanggan atau Nama Buah Kosong:
Menampilkan pesan kesalahan jika nama pelanggan atau nama buah tidak diisi.

-Nama Buah Tidak Tersedia:
Menampilkan pesan kesalahan jika nama buah tidak sesuai dengan daftar harga.

-Berat Tidak Valid:
Menampilkan pesan kesalahan jika berat buah tidak diisi atau kurang dari atau sama dengan.