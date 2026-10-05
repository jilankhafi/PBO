package sistemrumahsakit;

public class Pasien extends Orang {
    private String keluhan;

    public Pasien(String nama, String nik, String keluhan) {
        super(nama, nik);
        this.keluhan = keluhan;
    }

    public String getKeluhan() {
        return keluhan;
    }

    @Override
    public void tampilkanPeran() {
        System.out.println("[Pasien] Datang dengan keluhan: " + keluhan + ".");
    }

    @Override
    public void lakukanAktivitas() {
        System.out.println("-> " + getNama() + " sedang menunggu antrean dan menjalani pemeriksaan.");
    }

    @Override
    public void tampilkanInfo() {
        System.out.print("[Pasien]  ");
        super.tampilkanInfo();
        System.out.printf(" | Keluhan: %s%n", keluhan);
    }
}