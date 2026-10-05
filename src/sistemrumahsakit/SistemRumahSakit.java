package sistemrumahsakit;

import java.util.Scanner;

public class SistemRumahSakit {

    public static void cariData(String nama, Orang[] daftar, int jumlah) {
        System.out.println("Mencari data dengan Nama (Teks): " + nama);
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getNama().toLowerCase().contains(nama.toLowerCase())) {
                System.out.print((i + 1) + ". ");
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Data tidak ditemukan.");
    }

    // Versi 2: cari berdasarkan INDEKS / nomor urut (int)
    public static void cariData(int indeks, Orang[] daftar, int jumlah) {
        System.out.println("Mencari data dengan Nomor Urut (Angka): " + indeks);
        if (indeks >= 1 && indeks <= jumlah) {
            System.out.print(indeks + ". ");
            daftar[indeks - 1].tampilkanInfo();
        } else {
            System.out.println("Data tidak ditemukan.");
        }
    }

    // ==========================================================
    // UPCASTING PADA PARAMETER + DYNAMIC BINDING
    // Parameter bertipe Orang bisa menerima Dokter/Pasien/Perawat.
    // Saat runtime, Java memanggil method milik objek aslinya.
    // ==========================================================
    public static void simulasiAktivitas(Orang orang) {
        orang.tampilkanPeran();     // dynamic binding
        orang.lakukanAktivitas();   // dynamic binding
    }

    // ----------------------------------------------------------
    // Helper input agar tidak kena masalah buffer nextInt()/nextLine()
    // ----------------------------------------------------------
    private static String bacaTeks(Scanner sc, String label) {
        System.out.print(label);
        return sc.nextLine().trim();
    }

    private static int bacaAngka(Scanner sc, String label) {
        while (true) {
            System.out.print(label);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka, coba lagi.");
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            // SUPERCLASS REFERENCE: array bertipe Orang menampung semua subclass
            Orang[] daftarOrang = new Orang[20];
            int jumlah = 0;

            // Data awal (5 objek, mencakup ketiga subclass) -> UPCASTING
            daftarOrang[jumlah++] = new Dokter("Dr. Andi Pratama", "1871010101800001", "Jantung");
            daftarOrang[jumlah++] = new Dokter("Dr. Sinta Dewi", "1871010202850002", "Anak");
            daftarOrang[jumlah++] = new Perawat("Rina Marlina", "1871010303900003", "ICU");
            daftarOrang[jumlah++] = new Pasien("Budi Santoso", "1871010404950004", "Demam tinggi");
            daftarOrang[jumlah++] = new Pasien("Siti Aminah", "1871010505000005", "Sakit kepala");

            System.out.println("========================================");
            System.out.println("   SISTEM MANAJEMEN RUMAH SAKIT");
            System.out.println("========================================");

            boolean isRunning = true;
            while (isRunning) {
                System.out.println("\nMenu Utama:");
                System.out.println("1. Tambah Data (Dokter/Pasien/Perawat)");
                System.out.println("2. Lihat Daftar Seluruh Data");
                System.out.println("3. Cari Data (Fitur Overloading)");
                System.out.println("4. Simulasi Aktivitas (Dynamic Binding)");
                System.out.println("5. Keluar");
                int pilihan = bacaAngka(sc, "Pilih Menu (1-5): ");

                switch (pilihan) {
                    case 1 -> {
                        if (jumlah >= daftarOrang.length) {
                            System.out.println("Maaf, kapasitas data sudah penuh!");
                            break;
                        }
                        System.out.println("\n-- Pilih Jenis Data --");
                        System.out.println("1. Dokter");
                        System.out.println("2. Pasien");
                        System.out.println("3. Perawat");
                        int jenis = bacaAngka(sc, "Pilihan (1/2/3): ");
                        if (jenis < 1 || jenis > 3) {
                            System.out.println("Pilihan tidak valid.");
                            break;
                        }

                        String nama = bacaTeks(sc, "Masukkan Nama: ");
                        String nik = bacaTeks(sc, "Masukkan NIK: ");

                        // Upcasting: objek subclass disimpan di referensi Orang
                        if (jenis == 1) {
                            String spesialisasi = bacaTeks(sc, "Masukkan Spesialisasi: ");
                            daftarOrang[jumlah] = new Dokter(nama, nik, spesialisasi);
                        } else if (jenis == 2) {
                            String keluhan = bacaTeks(sc, "Masukkan Keluhan: ");
                            daftarOrang[jumlah] = new Pasien(nama, nik, keluhan);
                        } else {
                            String ruang = bacaTeks(sc, "Masukkan Ruang Jaga: ");
                            daftarOrang[jumlah] = new Perawat(nama, nik, ruang);
                        }
                        jumlah++;
                        System.out.println("Sukses! Data berhasil ditambahkan.");
                    }
                    case 2 -> {
                        System.out.println("\n--- Daftar Seluruh Data Rumah Sakit ---");
                        if (jumlah == 0) {
                            System.out.println("Belum ada data.");
                        } else {
                            for (int i = 0; i < jumlah; i++) {
                                System.out.print((i + 1) + ". ");
                                daftarOrang[i].tampilkanInfo();     // dynamic binding
                                System.out.print("   Peran: ");
                                daftarOrang[i].tampilkanPeran();    // dynamic binding
                            }
                        }
                        System.out.println("\nTotal data: " + jumlah);
                    }
                    case 3 -> {
                        System.out.println("\n-- Fitur Cari Data --");
                        System.out.println("1. Cari berdasarkan Nama (String)");
                        System.out.println("2. Cari berdasarkan Nomor Urut (Integer)");
                        int mode = bacaAngka(sc, "Pilih (1/2): ");

                        if (mode == 1) {
                            String kataKunci = bacaTeks(sc, "Masukkan Nama: ");
                            cariData(kataKunci, daftarOrang, jumlah);      // -> versi String
                        } else if (mode == 2) {
                            int nomor = bacaAngka(sc, "Masukkan Nomor Urut: ");
                            cariData(nomor, daftarOrang, jumlah);          // -> versi int
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }
                    case 4 -> {
                        System.out.println("\n--- Simulasi Aktivitas Rumah Sakit ---");
                        if (jumlah == 0) {
                            System.out.println("Belum ada data.");
                        } else {
                            for (int i = 0; i < jumlah; i++) {
                                System.out.println((i + 1) + ". " + daftarOrang[i].getNama());
                                simulasiAktivitas(daftarOrang[i]);          // upcasting parameter
                                System.out.println();
                            }
                        }
                    }
                    case 5 -> {
                        System.out.println("Terima kasih telah menggunakan sistem ini. Sampai jumpa!");
                        isRunning = false;
                    }
                    default -> System.out.println("Pilihan tidak valid, silakan pilih 1-5.");
                }
            }
        }
    }
}