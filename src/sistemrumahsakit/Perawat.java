package sistemrumahsakit;

public class Perawat extends Orang {
    private String ruangJaga;

    public Perawat(String nama, String nik, String ruangJaga) {
        super(nama, nik);
        this.ruangJaga = ruangJaga;
    }

    public String getRuangJaga() {
        return ruangJaga;
    }

    @Override
    public void tampilkanPeran() {
        System.out.println("[Perawat] Bertugas jaga di ruang " + ruangJaga
                + " - membantu dokter dan merawat pasien.");
    }

    @Override
    public void lakukanAktivitas() {
        System.out.println("-> Ns. " + getNama() + " sedang mengecek tensi dan membagikan obat di " + ruangJaga + ".");
    }

    @Override
    public void tampilkanInfo() {
        System.out.print("[Perawat] ");
        super.tampilkanInfo();
        System.out.printf(" | Ruang Jaga: %s%n", ruangJaga);
    }
}