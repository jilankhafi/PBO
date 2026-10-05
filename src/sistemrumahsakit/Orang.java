package sistemrumahsakit;

public abstract class Orang {
    private String nama;
    private String nik;

    public Orang(String nama, String nik) {
        this.nama = nama;
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public String getNik() {
        return nik;
    }

    // Abstract method: WAJIB di-override oleh setiap subclass
    public abstract void tampilkanPeran();

    // Abstract method: perilaku khusus tiap peran (dipakai di simulasi)
    public abstract void lakukanAktivitas();

    // Method biasa yang bisa di-override (info dasar)
    public void tampilkanInfo() {
        System.out.printf("Nama: %-18s | NIK: %-16s", nama, nik);
    }
}