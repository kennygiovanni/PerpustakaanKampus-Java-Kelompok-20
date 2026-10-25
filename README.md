# Sistem Manajemen Perpustakaan Kampus

Program ini adalah sistem manajemen perpustakaan kampus berbasis Java (console) yang menggabungkan *enam struktur data* dalam satu alur kerja dengan menu utama interaktif berbasis Scanner.

| No | Struktur Data | Variabel | Fungsi dalam Sistem |
|:--:|---------------|----------|---------------------|
| 1 | Array 1D | kategoriBuku | Menyimpan daftar kategori buku |
| 2 | Array 2D | dataPeminjaman[kategori][hari] | Statistik jumlah peminjaman per kategori dan hari |
| 3 | Array 3D | lokasiBuku[lantai][rak][posisi] | Denah penyimpanan buku |
| 4 | ArrayList | daftarBuku (ArrayList<Buku>) | Menyimpan data buku beserta statusnya |
| 5 | Stack | riwayatTransaksi | Riwayat transaksi (LIFO) |
| 6 | Queue | antreanMahasiswa | Antrean mahasiswa (FIFO) |

## Tugas Pemrograman Java
- **Mata Kuliah:** Pemrograman Berorientasi Objek
- **Dosen Pengampu:** M. Ibadurrahman Arrasyid Supriyanto M.Kom.
- **Program Studi:** Sistem Informasi
- **Angkatan:** 2025
- **Kelompok:** 20
  
## Anggota Kelompok 20
1. *Adelia Githa Naveezha Hermawan* - 2509116110 - Kelas C
2. *Kenny Giovanni Gavra* - 2509116003 - Kelas A
3. *Mikhel Febian* - 2509116056 - Kelas B
4. *Muhammad Nadhir Sultan Azzaky* - 2509116080 - Kelas B

## Struktur File Proyek
 
```
SistemManajemenPerpustakaan
├── Source Packages
│   └── main
│       ├── Buku.java
│       └── Main.java
```
 
| File | Isi |
|------|-----|
| Buku.java | Class Buku: atribut judul, kategori, lantai, rak, posisi, dan status dipinjam |
| Main.java | Class Main: seluruh struktur data, menu utama, dan method pendukung |

### Alur

Keenam struktur data tidak berjalan sendiri-sendiri, tetapi saling terhubung:

```
Mahasiswa antre (Queue)
        │
        ▼
Dilayani, memilih buku (ArrayList<Buku>, lokasi di Array 3D)
        │
        ▼
Statistik naik: kategori buku dicari lewat Array 1D,
kemudian dicatat di Array 2D pada hari yang dipilih
        │
        ▼
Setiap langkah dicatat ke riwayat (Stack)
```

## Pembagian Bagian Program

| Bagian | Struktur Data / Fitur |
|--------|-----------------------|
| Kategori & Peminjaman | Array 1D, Array 2D |
| Lokasi & Daftar Buku | Array 3D, ArrayList |
| Riwayat Transaksi & Antrean | Stack, Queue |
| Integrasi & Pengujian | Menu utama, alur peminjaman terpadu, perbaikan error, pengujian |

## Menu Utama

| No | Menu | Struktur Data |
|:--:|------|---------------|
| 1 | Tampilkan Kategori Buku | Array 1D |
| 2 | Tampilkan Data Peminjaman | Array 2D |
| 3 | Tambah Buku | ArrayList + Array 3D |
| 4 | Tampilkan Daftar Buku | ArrayList |
| 5 | Hapus Buku | ArrayList + Array 3D |
| 6 | Tampilkan Denah Lokasi | Array 3D |
| 7 | Antrean Mahasiswa & Proses Peminjaman | Queue (terhubung ke ArrayList, Array 2D, Stack) |
| 8 | Pengembalian Buku | ArrayList + Stack |
| 9 | Riwayat Transaksi | Stack |
| 10 | Ringkasan Seluruh Sistem | Semua struktur data |
| 11 | Isi Data Contoh (untuk pengujian) | Semua struktur data |
| 0 | Keluar | |

Menu utama dijalankan dalam perulangan while (jalan) dan dipilih dengan switch. Input pilihan dibaca lewat bacaAngka() sehingga input non-angka ditolak dan diminta ulang.

## Penjelasan Source Code

### A. Class Buku

| Kode | Penjelasan |
|------|------------|
| judul, kategori, lantai, rak, posisi | Atribut identitas dan lokasi buku. |
| private boolean dipinjam; | *Baru.* Status buku, false berarti tersedia dan true berarti sedang dipinjam. Nilai awal false di constructor. |
| isDipinjam() / setDipinjam(boolean) | *Baru.* Membaca dan mengubah status peminjaman. |

### B. Deklarasi Struktur Data pada Main

| Kode | Penjelasan |
|------|------------|
| static String[] kategoriBuku | Array 1D berisi 4 kategori: Pemrograman, Matematika, Jaringan Komputer, Basis Data. |
| static int[][] dataPeminjaman | Array 2D (4 kategori x 6 hari). Baris mengikuti urutan kategoriBuku, kolom mengikuti hari. |
| static String[][][] lokasiBuku | Array 3D berukuran 2 lantai x 3 rak x 4 posisi. Slot kosong berisi konstanta KOSONG. |
| static ArrayList<Buku> daftarBuku | Menyimpan objek Buku. |
| static Stack<String> riwayatTransaksi | Stack riwayat transaksi. |
| static Queue<String> antreanMahasiswa = new LinkedList<>() | Queue antrean dengan implementasi LinkedList. |
| static final String KOSONG | *Baru.* Konstanta penanda slot kosong, menggantikan string "Kosong" yang tadinya ditulis berulang. |
| static int nomorTransaksi | *Baru.* Penghitung nomor urut log transaksi. |

### C. Method Pendukung

| Method | Penjelasan |
|--------|------------|
| bacaAngka(String) | Membaca bilangan bulat dengan try-catch. Jika input habis (misalnya Ctrl+D), program berhenti dengan rapi, tidak crash. |
| bacaTeks(String) | Membaca teks satu baris dengan pengecekan hasNextLine(). |
| catat(String) | *Baru.* Menambahkan log bernomor ke Stack, misalnya #3 PINJAM: Andi meminjam "Java Dasar" (Rabu). Dipakai oleh semua fitur yang perlu tercatat. |
| indeksKategori(String) | *Baru.* Mencari indeks kategori di Array 1D. Hasilnya dipakai sebagai indeks baris Array 2D. |
| inisialisasiLokasi() | Mengisi seluruh Array 3D dengan KOSONG menggunakan tiga perulangan bersarang. |

### D. Array 1D dan Array 2D

| Method | Penjelasan |
|--------|------------|
| tampilkanKategori() | Perulangan for untuk menampilkan isi kategoriBuku bernomor. |
| tampilkanPeminjaman() | Perulangan bersarang untuk menampilkan tabel Array 2D, dilengkapi *kolom Total* per kategori dan *baris Total* per hari serta total keseluruhan. |

### E. ArrayList dan Array 3D

| Method | Penjelasan |
|--------|------------|
| tambahBuku() | Meminta judul, kategori, dan lokasi. Judul kosong atau kategori di luar rentang ditolak. Penyimpanan diserahkan ke tambahBukuLangsung(). |
| tambahBukuLangsung(...) | *Baru.* Memvalidasi lokasi (rentang lantai/rak/posisi dan slot belum terisi), lalu daftarBuku.add(...), mengisi lokasiBuku[l-1][r-1][p-1] dengan judul, dan catat("TAMBAH BUKU: ..."). Juga dipakai oleh contohData(). |
| tampilkanDaftarBuku() | Menampilkan No, Judul, Kategori, Lokasi, dan *Status* (Tersedia/Dipinjam). |
| hapusBuku() | Menghapus buku dari ArrayList dan mengosongkan slot di Array 3D. Dapat dibatalkan dengan input 0. *Buku berstatus Dipinjam ditolak.* Penghapusan dicatat ke Stack. |
| tampilkanLokasi() | Perulangan tiga tingkat untuk menampilkan denah per lantai, rak, dan posisi. |

### F. Riwayat Transaksi (Stack)

Menu Stack dipanggil dari menu utama nomor *9* melalui menuStack().

| Operasi | Penjelasan |
|---------|------------|
| push(...) | Dilakukan lewat catat(). Entri baru berada di puncak Stack. |
| peek() | Menampilkan transaksi terakhir tanpa menghapusnya. |
| pop() | Menghapus dan mengembalikan transaksi di puncak Stack. |
| isEmpty() | Memeriksa Stack kosong sebelum peek() atau pop(). |
| get(i) | Dipakai dalam perulangan dari indeks terakhir ke 0 untuk menampilkan riwayat dari terbaru ke terlama. |

Submenu Stack:

| Pilihan | Fitur | Penjelasan |
|:-------:|-------|------------|
| 1 | Catat transaksi manual | Teks kosong ditolak. Disimpan dengan awalan MANUAL:. |
| 2 | Lihat transaksi terakhir | Memakai peek(). |
| 3 | Hapus catatan terakhir | Memakai pop(). |
| 4 | Tampilkan semua riwayat | Dari terbaru ke terlama. |
| 5 | Kembali | Kembali ke menu utama. |

| Awalan Log | Dibuat saat |
|------------|-------------|
| TAMBAH BUKU: | Buku berhasil ditambahkan |
| HAPUS BUKU: | Buku berhasil dihapus |
| ANTRE: | Mahasiswa masuk antrean |
| PINJAM: | Mahasiswa berhasil meminjam buku |
| KEMBALI: | Buku dikembalikan |
| MANUAL: | Pengguna mencatat sendiri lewat menu Stack |

### G. Antrean Mahasiswa (Queue) dan Proses Peminjaman

Menu Queue dipanggil dari menu utama nomor *7* melalui menuQueue().

| Operasi | Penjelasan |
|---------|------------|
| offer(nama) | Menambahkan mahasiswa ke belakang antrean. |
| peek() | Melihat mahasiswa terdepan tanpa menghapus. Dipakai saat melayani, sebelum validasi selesai. |
| poll() | Mengambil dan menghapus mahasiswa terdepan, *hanya setelah seluruh validasi peminjaman lolos*. |
| isEmpty() | Memeriksa antrean kosong. |
| for-each | Menampilkan antrean sesuai urutan tanpa menghapus. |

Submenu Queue:

| Pilihan | Fitur |
|:-------:|-------|
| 1 | Tambah mahasiswa ke antrean (nama kosong ditolak, dicatat ke Stack) |
| 2 | Tampilkan antrean |
| 3 | Layani mahasiswa berikutnya (pinjam buku) |
| 4 | Kembali |

*Alur layaniBerikutnya()* (menghubungkan Queue, ArrayList, Array 2D, dan Stack):

| Langkah | Penjelasan |
|:-------:|------------|
| 1 | Jika antrean kosong, atau tidak ada buku berstatus Tersedia, proses dihentikan dengan pesan. Antrean tidak berubah. |
| 2 | Menampilkan nama mahasiswa terdepan (peek()) dan daftar buku. |
| 3 | Pengguna memilih nomor buku (0 untuk batal). Buku yang sedang dipinjam ditolak. |
| 4 | Pengguna memilih hari (1 = Senin sampai 6 = Sabtu). |
| 5 | Setelah semua validasi lolos: poll() mengeluarkan mahasiswa dari antrean, setDipinjam(true), dataPeminjaman[indeksKategori][hari - 1]++, lalu catat("PINJAM: ..."). |

Dengan urutan ini, jika pengguna salah input atau membatalkan, mahasiswa *tidak hilang dari antrean*.

### H. Pengembalian Buku

kembalikanBuku() menampilkan buku yang berstatus Dipinjam, lalu pengguna memilih nomor buku. Status diubah menjadi Tersedia (setDipinjam(false)) dan dicatat sebagai KEMBALI: di Stack. Nomor yang tidak valid atau buku yang tidak sedang dipinjam ditolak.

### I. Ringkasan dan Data Contoh

| Method | Penjelasan |
|--------|------------|
| tampilkanRingkasan() | Menampilkan jumlah kategori, total peminjaman (Array 2D), slot terisi dari 24 (Array 3D), jumlah buku dan yang dipinjam (ArrayList), jumlah riwayat (Stack), dan jumlah antrean (Queue). |
| contohData() | Mengisi 4 buku (Java Dasar, Kalkulus 1, Jaringan TCP/IP, Basis Data SQL) dan 3 mahasiswa (Andi, Budi, Citra) untuk mempercepat pengujian. Posisi yang sudah terisi dilewati. |

## Skenario Pengujian

| No | Skenario | Langkah | Hasil yang Diharapkan |
|:--:|----------|---------|-----------------------|
| 1 | Stack kosong | Di awal program (sebelum ada transaksi), pilih menu **9**, lalu pilih **2** | Muncul pesan "Belum ada transaksi." |
| 2 | Antrean kosong | Di awal program, pilih menu **7**, lalu pilih **2**, kemudian pilih **3** | Muncul pesan bahwa antrean kosong, program tidak error |
| 3 | Slot sudah terisi | Jalankan menu **11** (data contoh), lalu pilih menu **3** dan tambah buku di lantai 1, rak 1, posisi 1 | Ditolak dengan pesan, buku tidak masuk ke ArrayList |
| 4 | Lokasi di luar rentang | Pilih menu **3** dan tambah buku dengan lantai 3 | Ditolak dengan pesan, karena lantai yang tersedia hanya 1 dan 2 |
| 5 | Judul kosong | Pilih menu **3** dan tambah buku dengan judul dikosongkan | Ditolak dengan pesan |
| 6 | Input bukan angka | Ketik `abc` di menu utama | Muncul pesan "Input harus berupa angka!" dan program meminta input ulang |
| 7 | Hapus buku yang sedang dipinjam | Setelah buku berstatus Dipinjam (menu **7**, layani mahasiswa dan pilih buku), pilih menu **5** dan hapus buku tersebut, sebelum melakukan pengembalian lewat menu **8** | Ditolak karena buku berstatus Dipinjam |
