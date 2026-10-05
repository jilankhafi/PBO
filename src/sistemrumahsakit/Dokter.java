package sistemrumahsakit;

public class Dokter extends Orang {
    private String spesialisasi;

    public Dokter(String nama, String nik, String spesialisasi) {
        super(nama, nik);
        this.spesialisasi = spesialisasi;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    @Override
    public void tampilkanPeran() {
        System.out.println("[Dokter] Spesialis " + spesialisasi
                + " - bertugas mendiagnosis dan menangani pasien.");
    }

    @Override
    public void lakukanAktivitas() {
        System.out.println("-> dr. " + getNama() + " sedang memeriksa pasien dan menulis resep.");
    }

    @Override
    public void tampilkanInfo() {
        System.out.print("[Dokter]  ");
        super.tampilkanInfo();
        System.out.printf(" | Spesialisasi: %s%n", spesialisasi);
    }
}