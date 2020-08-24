package ci.ahmadfauzirahman.sianokapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AntrianModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("counter")
    @Expose
    private Integer counter;
    @SerializedName("stakeKode")
    @Expose
    private String stakeKode;
    @SerializedName("waktu")
    @Expose
    private String waktu;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("jenis_layanan")
    @Expose
    private String jenisLayanan;
    @SerializedName("id_kppn")
    @Expose
    private String idKppn;
    @SerializedName("nomor_antrian")
    @Expose
    private String nomorAntrian;
    @SerializedName("nobc")
    @Expose
    private String nobc;
    @SerializedName("jml_spm")
    @Expose
    private Integer jmlSpm;
    @SerializedName("no_barcode")
    @Expose
    private String noBarcode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public String getStakeKode() {
        return stakeKode;
    }

    public void setStakeKode(String stakeKode) {
        this.stakeKode = stakeKode;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJenisLayanan() {
        return jenisLayanan;
    }

    public void setJenisLayanan(String jenisLayanan) {
        this.jenisLayanan = jenisLayanan;
    }

    public String getIdKppn() {
        return idKppn;
    }

    public void setIdKppn(String idKppn) {
        this.idKppn = idKppn;
    }

    public String getNomorAntrian() {
        return nomorAntrian;
    }

    public void setNomorAntrian(String nomorAntrian) {
        this.nomorAntrian = nomorAntrian;
    }

    public String getNobc() {
        return nobc;
    }

    public void setNobc(String nobc) {
        this.nobc = nobc;
    }

    public Integer getJmlSpm() {
        return jmlSpm;
    }

    public void setJmlSpm(Integer jmlSpm) {
        this.jmlSpm = jmlSpm;
    }

    public String getNoBarcode() {
        return noBarcode;
    }

    public void setNoBarcode(String noBarcode) {
        this.noBarcode = noBarcode;
    }

}
