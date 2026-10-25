package main;

public class Buku {

    private String judul;
    private String kategori;
    private int lantai;
    private int rak;
    private int posisi;
    private boolean dipinjam;

    public Buku(String judul, String kategori, int lantai, int rak, int posisi) {
        this.judul = judul;
        this.kategori = kategori;
        this.lantai = lantai;
        this.rak = rak;
        this.posisi = posisi;
        this.dipinjam = false;
    }

    public String getJudul() { return judul; }
    public String getKategori() { return kategori; }
    public int getLantai() { return lantai; }
    public int getRak() { return rak; }
    public int getPosisi() { return posisi; }
    public boolean isDipinjam() { return dipinjam; }
    public void setDipinjam(boolean dipinjam) { this.dipinjam = dipinjam; }
}