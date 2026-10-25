package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static String[] kategoriBuku = {
        "Pemrograman", "Matematika",
        "Jaringan Komputer", "Basis Data"
    };

    static int[][] dataPeminjaman = {
        {5, 8, 3, 6, 9, 4},
        {2, 3, 4, 1, 5, 2},
        {4, 6, 5, 7, 8, 3},
        {7, 5, 6, 8, 9, 6}
    };

    static String[] hari = {
        "Senin", "Selasa", "Rabu",
        "Kamis", "Jumat", "Sabtu"
    };

    static final int JUMLAH_LANTAI = 2;
    static final int JUMLAH_RAK = 3;
    static final int JUMLAH_POSISI = 4;
    static final String KOSONG = "Kosong";

    static String[][][] lokasiBuku =
            new String[JUMLAH_LANTAI][JUMLAH_RAK][JUMLAH_POSISI];

    static ArrayList<Buku> daftarBuku = new ArrayList<>();

    // ANGGOTA 3: Riwayat Transaksi dan Antrean Mahasiswa
    static Stack<String> riwayatTransaksi = new Stack<>();
    static Queue<String> antreanMahasiswa = new LinkedList<>();

    static int nomorTransaksi = 0;
    
    public static void main(String[] args) {
        inisialisasiLokasi();
        
        boolean jalan = true;
        while (jalan) {
            tampilkanMenuUtama();
            int pilihan = bacaAngka("Pilih Menu 0-11: ");
            
            switch (pilihan) {
                case 1:  tampilkanKategori(); break;
                case 2:  tampilkanPeminjaman(); break;
                case 3:  tambahBuku(); break;
                case 4:  tampilkanDaftarBuku(); break;
                case 5:  hapusBuku(); break;
                case 6:  tampilkanLokasi(); break;
                case 7:  menuQueue(); break;
                case 8:  kembalikanBuku(); break;
                case 9:  menuStack(); break;
                case 10: tampilkanRingkasan(); break;
                case 11: contohData(); break;
                case 0:
                    jalan = false;
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }
    
    static void tampilkanMenuUtama() {
        System.out.println("\n=== SISTEM MANAJEMEN PERPUSTAKAAN KAMPUS ===");
        System.out.println("\n--- Data Kategori & Statistik ---");
        System.out.println("1.  Tampilkan Kategori Buku (Array 1D)");
        System.out.println("2.  Tampilkan Data Peminjaman (Array 2D)");
        System.out.println("\n--- Koleksi & Lokasi Buku ---");
        System.out.println("3.  Tambah Buku (ArrayList + Array 3D)");
        System.out.println("4.  Tampilkan Daftar Buku (ArrayList)");
        System.out.println("5.  Hapus Buku (ArrayList + Array 3D)");
        System.out.println("6.  Tampilkan Denah Lokasi (Array 3D)");
        System.out.println("\n--- Layanan Peminjaman ---");
        System.out.println("7.  Antrean Mahasiswa & Proses Peminjaman (Queue)");
        System.out.println("8.  Pengembalian Buku");
        System.out.println("9.  Riwayat Transaksi (Stack)");
        System.out.println("\n--- Lainnya ---");
        System.out.println("10. Ringkasan Seluruh Sistem");
        System.out.println("11. Isi Data Contoh (untuk pengujian)");
        System.out.println("0.  Keluar");
    }
    
    static void inisialisasiLokasi(){
        for (int l = 0; l < JUMLAH_LANTAI; l++) 
            for (int r = 0; r < JUMLAH_RAK; r++) 
                for (int p = 0; p < JUMLAH_POSISI; p++) 
                    lokasiBuku[l][r][p] = "Kosong";
    }

    static int bacaAngka(String pesan) {
        while (true) {
            System.out.println(pesan);
            if (!scanner.hasNextLine()) {
                System.out.println("\nInput berakhir. Program Selesai.");
                System.exit(0);
            }
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }
    
    static String bacaTeks(String pesan) {
        System.out.print(pesan);
        if (!scanner.hasNextLine()) {
            System.out.println("\nInput berakhir. Program selesai.");
            System.exit(0);
        }
        return scanner.nextLine().trim();
    }
    
    static void catat(String keterangan) {
        nomorTransaksi++;
        riwayatTransaksi.push("#" + nomorTransaksi + " " + keterangan);
    }
 
    static int indeksKategori(String nama) {
        for (int i = 0; i < kategoriBuku.length; i++)
            if (kategoriBuku[i].equals(nama)) return i;
        return -1;
    }

    static void tampilkanKategori() {
        System.out.println("\n[ Daftar Kategori Buku ]");

        for (int i = 0; i < kategoriBuku.length; i++) {
            System.out.println((i + 1) + ". " + kategoriBuku[i]);
        }
    }

    static void tampilkanPeminjaman() {
    System.out.println("\n[ Data Jumlah Peminjaman Buku per Kategori & Hari ]");

    final int LEBAR = 8; 

    System.out.printf("%-20s", "Kategori");
    for (String h : hari) {
        System.out.printf("%" + LEBAR + "s", h);
    }
    System.out.printf("%" + LEBAR + "s%n", "Total");

    System.out.println("-".repeat(20 + LEBAR * (hari.length + 1)));

    int[] totalHari = new int[hari.length];
    int totalSemua = 0;

    for (int i = 0; i < kategoriBuku.length; i++) {
        System.out.printf("%-20s", kategoriBuku[i]);
        int totalBaris = 0;

        for (int j = 0; j < hari.length; j++) {
            System.out.printf("%" + LEBAR + "d", dataPeminjaman[i][j]);
            totalBaris += dataPeminjaman[i][j];
            totalHari[j] += dataPeminjaman[i][j];
        }

        System.out.printf("%" + LEBAR + "d%n", totalBaris);
        totalSemua += totalBaris;
    }

    System.out.println("-".repeat(20 + LEBAR * (hari.length + 1)));
    System.out.printf("%-20s", "Total");
    for (int j = 0; j < hari.length; j++) {
        System.out.printf("%" + LEBAR + "d", totalHari[j]);
    }
    System.out.printf("%" + LEBAR + "d%n", totalSemua);
}

    static void tambahBuku() {
        System.out.println("\n[ Tambah Buku ]");
        String judul = bacaTeks("Judul buku: ");
        if (judul.isEmpty()) {
            System.out.println("Judul tidak boleh kosong!");
            return;
        }

        System.out.println("Pilih kategori:");

        for (int i = 0; i < kategoriBuku.length; i++) {
            System.out.println((i + 1) + ". " + kategoriBuku[i]);
        }

        int k = bacaAngka("Nomor kategori: ");

        if (k < 1 || k > kategoriBuku.length) {
            System.out.println("Kategori tidak valid!");
            return;
        }

        int lantai = bacaAngka("Lantai (1-" + JUMLAH_LANTAI + "): ");
        int rak = bacaAngka("Nomor rak (1-" + JUMLAH_RAK + "): ");
        int posisi = bacaAngka("Posisi (1-" + JUMLAH_POSISI + "): ");

        if (!tambahBukuLangsung(judul, k - 1, lantai, rak, posisi)) return;
        System.out.println("Buku \"" + judul + "\" berhasil ditambahkan.");
    }
        
    static boolean tambahBukuLangsung(String judul, int idxKategori, int lantai, int rak, int posisi) {
        if (lantai < 1 || lantai > JUMLAH_LANTAI
                || rak < 1 || rak > JUMLAH_RAK
                || posisi < 1 || posisi > JUMLAH_POSISI) {
            System.out.println("Lokasi di luar jangkauan rak!");
            return false;
        }

        if (!lokasiBuku[lantai - 1][rak - 1][posisi - 1].equals(KOSONG)) {
            System.out.println("Posisi Lt." + lantai + " Rak " + rak + " Pos "
                    + posisi + " sudah terisi: "
                    + lokasiBuku[lantai - 1][rak - 1][posisi - 1]);
            return false;
        }

        daftarBuku.add(new Buku(judul, kategoriBuku[idxKategori], lantai, rak, posisi));
        lokasiBuku[lantai - 1][rak - 1][posisi - 1] = judul;
        catat("TAMBAH BUKU: " + judul);
        return true;
    }

    static void tampilkanDaftarBuku() {
    System.out.println("\n[ Daftar Buku ]");
    if (daftarBuku.isEmpty()) {
        System.out.println("Belum ada buku.");
        return;
    }
    System.out.println(String.format("%-4s%-28s%-20s%-18s%s",
            "No", "Judul", "Kategori", "Lokasi", "Status"));
    for (int i = 0; i < daftarBuku.size(); i++) {
        Buku b = daftarBuku.get(i);
        String lokasi = "Lt." + b.getLantai() + " Rak " + b.getRak()
                + " Pos " + b.getPosisi();
        String status = b.isDipinjam() ? "Dipinjam" : "Tersedia";
        System.out.println(String.format("%-4d%-28s%-20s%-18s%s",
                i + 1, b.getJudul(), b.getKategori(), lokasi, status));
    }
    System.out.println("Total buku: " + daftarBuku.size());
    }

    static void hapusBuku() {
        System.out.println("\n[ Hapus Buku ]");

        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku yang bisa dihapus.");
            return;
        }

        tampilkanDaftarBuku();

        int nomor = bacaAngka("Nomor buku yang dihapus (0 = batal): ");
        if (nomor == 0) return;
        if (nomor < 1 || nomor > daftarBuku.size()) {
            System.out.println("Nomor buku tidak valid!");
            return;
        }
        
        if (daftarBuku.get(nomor - 1).isDipinjam()) {
            System.out.println("Buku sedang dipinjam, tidak bisa dihapus!");
            return;
        }

        Buku dihapus = daftarBuku.remove(nomor - 1);

        lokasiBuku[dihapus.getLantai() - 1]
                [dihapus.getRak() - 1]
                [dihapus.getPosisi() - 1] = "Kosong";

        catat("HAPUS BUKU: " + dihapus.getJudul());
        
        System.out.println("Buku \"" + dihapus.getJudul()
                + "\" berhasil dihapus dan posisinya dikosongkan.");
    }

    static void tampilkanLokasi() {
        System.out.println("\n[ Denah Lokasi Penyimpanan Buku ]");

        for (int l = 0; l < JUMLAH_LANTAI; l++) {
            System.out.println("== Lantai " + (l + 1) + " ==");

            for (int r = 0; r < JUMLAH_RAK; r++) {
                System.out.println("  Rak " + (r + 1) + ":");

                for (int p = 0; p < JUMLAH_POSISI; p++) {
                    System.out.println("    Posisi " + (p + 1)
                            + ": " + lokasiBuku[l][r][p]);
                }
            }
        }
    }

    static void menuQueue() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n=== ANTREAN MAHASISWA (QUEUE) ===");
            System.out.println("1. Tambah mahasiswa ke antrean");
            System.out.println("2. Tampilkan antrean");
            System.out.println("3. Layani mahasiswa berikutnya (pinjam buku)");
            System.out.println("4. Kembali ke menu utama");
 
            int pilihan = bacaAngka("Pilih menu (1-4): ");
            switch (pilihan) {
                case 1: {
                    String nama = bacaTeks("Nama mahasiswa: ");
                    if (nama.isEmpty()) {
                        System.out.println("Nama tidak boleh kosong!");
                    } else {
                        antreanMahasiswa.offer(nama);
                        catat("ANTRE: " + nama);
                        System.out.println(nama + " masuk antrean (posisi "
                                + antreanMahasiswa.size() + ").");
                    }
                    break;
                }
                case 2:
                    if (antreanMahasiswa.isEmpty()) {
                        System.out.println("Antrean masih kosong.");
                    } else {
                        System.out.println("Daftar antrean:");
                        int no = 1;
                        for (String m : antreanMahasiswa)
                            System.out.println(no++ + ". " + m);
                    }
                    break;
                case 3:
                    layaniBerikutnya();
                    break;
                case 4:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
 
    static void layaniBerikutnya() {
        if (antreanMahasiswa.isEmpty()) {
            System.out.println("Tidak ada mahasiswa dalam antrean.");
            return;
        }

        int tersedia = 0;
        for (Buku b : daftarBuku) if (!b.isDipinjam()) tersedia++;
        if (tersedia == 0) {
            System.out.println("Tidak ada buku tersedia. Antrean tidak diproses.");
            return;
        }
 
        String nama = antreanMahasiswa.peek();
        System.out.println("\nMelayani: " + nama);
        tampilkanDaftarBuku();
 
        int nomor = bacaAngka("Nomor buku yang dipinjam (0 = batal): ");
        if (nomor == 0) return;
        if (nomor < 1 || nomor > daftarBuku.size()) {
            System.out.println("Nomor buku tidak valid!");
            return;
        }
        Buku b = daftarBuku.get(nomor - 1);
        if (b.isDipinjam()) {
            System.out.println("Buku sedang dipinjam. Pilih buku lain.");
            return;
        }
 
        int h = bacaAngka("Hari peminjaman (1=Senin ... 6=Sabtu): ");
        if (h < 1 || h > hari.length) {
            System.out.println("Hari tidak valid!");
            return;
        }
 
        antreanMahasiswa.poll();
        b.setDipinjam(true);
        int idx = indeksKategori(b.getKategori());
        dataPeminjaman[idx][h - 1]++;
        catat("PINJAM: " + nama + " meminjam \"" + b.getJudul() + "\" ("
                + hari[h - 1] + ")");
        System.out.println(nama + " berhasil meminjam \"" + b.getJudul()
                + "\". Statistik " + b.getKategori() + " hari " + hari[h - 1]
                + " menjadi " + dataPeminjaman[idx][h - 1] + ".");
    }
 
    static void kembalikanBuku() {
        System.out.println("\n[ Pengembalian Buku ]");
        boolean ada = false;
        for (int i = 0; i < daftarBuku.size(); i++) {
            Buku b = daftarBuku.get(i);
            if (b.isDipinjam()) {
                System.out.println((i + 1) + ". " + b.getJudul());
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("Tidak ada buku yang sedang dipinjam.");
            return;
        }
        int nomor = bacaAngka("Nomor buku yang dikembalikan (0 = batal): ");
        if (nomor == 0) return;
        if (nomor < 1 || nomor > daftarBuku.size()
                || !daftarBuku.get(nomor - 1).isDipinjam()) {
            System.out.println("Nomor tidak valid / buku tidak sedang dipinjam!");
            return;
        }
        Buku b = daftarBuku.get(nomor - 1);
        b.setDipinjam(false);
        catat("KEMBALI: \"" + b.getJudul() + "\"");
        System.out.println("Buku \"" + b.getJudul() + "\" sudah dikembalikan.");
    }
 
    static void menuStack() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n=== RIWAYAT TRANSAKSI (STACK) ===");
            System.out.println("1. Catat transaksi manual");
            System.out.println("2. Lihat transaksi terakhir");
            System.out.println("3. Hapus catatan terakhir");
            System.out.println("4. Tampilkan semua riwayat");
            System.out.println("5. Kembali ke menu utama");
 
            int pilihan = bacaAngka("Pilih menu (1-5): ");
            switch (pilihan) {
                case 1: {
                    String t = bacaTeks("Masukkan transaksi: ");
                    if (t.isEmpty()) System.out.println("Transaksi tidak boleh kosong!");
                    else {
                        catat("MANUAL: " + t);
                        System.out.println("Transaksi tersimpan.");
                    }
                    break;
                }
                case 2:
                    if (riwayatTransaksi.isEmpty())
                        System.out.println("Belum ada transaksi.");
                    else
                        System.out.println("Transaksi terakhir: " + riwayatTransaksi.peek());
                    break;
                case 3:
                    if (riwayatTransaksi.isEmpty())
                        System.out.println("Tidak ada catatan untuk dihapus.");
                    else
                        System.out.println("Catatan dihapus: " + riwayatTransaksi.pop()
                                + "\n(Catatan: hanya menghapus log, data buku/antrean tidak berubah.)");
                    break;
                case 4:
                    if (riwayatTransaksi.isEmpty()) {
                        System.out.println("Belum ada riwayat transaksi.");
                    } else {
                        System.out.println("Riwayat (terbaru ke terlama):");
                        for (int i = riwayatTransaksi.size() - 1; i >= 0; i--)
                            System.out.println("  " + riwayatTransaksi.get(i));
                    }
                    break;
                case 5:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
 
    static void tampilkanRingkasan() {
        int dipinjam = 0, terisi = 0, totalPinjam = 0;
        for (Buku b : daftarBuku) if (b.isDipinjam()) dipinjam++;
        for (String[][] lt : lokasiBuku)
            for (String[] rk : lt)
                for (String s : rk) if (!s.equals(KOSONG)) terisi++;
        for (int[] baris : dataPeminjaman)
            for (int v : baris) totalPinjam += v;
 
        System.out.println("\n[ Ringkasan Sistem ]");
        System.out.println("Kategori (Array 1D)          : " + kategoriBuku.length);
        System.out.println("Total peminjaman (Array 2D)  : " + totalPinjam);
        System.out.println("Slot terisi (Array 3D)       : " + terisi + " / "
                + (JUMLAH_LANTAI * JUMLAH_RAK * JUMLAH_POSISI));
        System.out.println("Jumlah buku (ArrayList)      : " + daftarBuku.size()
                + " (dipinjam: " + dipinjam + ")");
        System.out.println("Riwayat transaksi (Stack)    : " + riwayatTransaksi.size());
        System.out.println("Antrean mahasiswa (Queue)    : " + antreanMahasiswa.size());
    }
 
    static void contohData() {
        System.out.println("\n[ Mengisi data contoh ]");

        String[] judul = {"Java Dasar", "Kalkulus 1", "Jaringan TCP/IP", "Basis Data SQL"};
        int[] kategori = {0, 1, 2, 3};
        int[][] lokasi = {{1, 1, 1}, {1, 2, 1}, {2, 1, 1}, {2, 2, 3}};

        int bukuBerhasil = 0;
        for (int i = 0; i < judul.length; i++) {
            if (tambahBukuLangsung(judul[i], kategori[i],
                    lokasi[i][0], lokasi[i][1], lokasi[i][2])) {
                bukuBerhasil++;
            }
        }

        String[] nama = {"Andi", "Budi", "Citra"};
        int antreBerhasil = 0;
        for (String n : nama) {
            if (!antreanMahasiswa.contains(n)) {
                antreanMahasiswa.offer(n);
                catat("ANTRE: " + n);
                antreBerhasil++;
            }
        }

        System.out.println(bukuBerhasil + " dari " + judul.length
                + " buku contoh ditambahkan, " + antreBerhasil
                + " mahasiswa masuk antrean.");
        System.out.println("Total sekarang: " + daftarBuku.size() + " buku, "
                + antreanMahasiswa.size() + " mahasiswa antre.");
    }
}