import java.time.LocalDate;
import java.time.Period;

public class Mahasiswa {
    private String namaMahasiswa;
    private LocalDate tanggalBulanTahunLahir;
    private long besaranUKT;

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public LocalDate getTanggalBulanTahunLahir() {
        return tanggalBulanTahunLahir;
    }

    public void setTanggalBulanTahunLahir(LocalDate tanggalBulanTahunLahir) {
        this.tanggalBulanTahunLahir = tanggalBulanTahunLahir;
    }

    public long getBesaranUKT() {
        return besaranUKT;
    }

    public void setBesaranUKT(long besaranUKT) {
        this.besaranUKT = besaranUKT;
    }

    public String getUsiaMahasiswa(){
        LocalDate past = getTanggalBulanTahunLahir();
        LocalDate current = LocalDate.now();

        Period period = Period.between(past, current);

        if (period.isNegative()) {
            return "Belum Lahir!";
        } else {
            return String.format("%d Tahun, %d Bulan, %d Hari",
                    period.getYears(),
                    period.getMonths(),
                    period.getDays());
        }
    }

    public String getKategoriUKT(){
        if (getBesaranUKT() <= 500000){
            return "UKT 1";
        } else if (getBesaranUKT() > 500000 && getBesaranUKT() <= 1500000) {
            return "UKT 2";
        } else if (getBesaranUKT() > 1500000 && getBesaranUKT() <= 3000000) {
            return "UKT 3";
        } else if (getBesaranUKT() > 3000000 && getBesaranUKT() <= 4500000) {
            return "UKT 4";
        } else if (getBesaranUKT() > 4500000 && getBesaranUKT() <= 5000000) {
            return "UKT 5";
        } else if (getBesaranUKT() > 5000000 && getBesaranUKT() <= 6000000) {
            return "UKT 6";
        } else if (getBesaranUKT() > 6000000 && getBesaranUKT() <= 7000000) {
            return "UKT 7";
        } else {
            return "UKT Undefined";
        }
    }

}
