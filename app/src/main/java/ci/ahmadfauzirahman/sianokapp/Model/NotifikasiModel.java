package ci.ahmadfauzirahman.sianokapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotifikasiModel {
    @SerializedName("id_notifikasi")
    @Expose
    private String idNotifikasi;
    @SerializedName("kd")
    @Expose
    private String kd;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("noted")
    @Expose
    private String noted;
    @SerializedName("operator")
    @Expose
    private Integer operator;
    @SerializedName("jns")
    @Expose
    private String jns;
    @SerializedName("tgl")
    @Expose
    private String tgl;
    @SerializedName("jam")
    @Expose
    private String jam;

    public String getIdNotifikasi() {
        return idNotifikasi;
    }

    public void setIdNotifikasi(String idNotifikasi) {
        this.idNotifikasi = idNotifikasi;
    }

    public String getKd() {
        return kd;
    }

    public void setKd(String kd) {
        this.kd = kd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoted() {
        return noted;
    }

    public void setNoted(String noted) {
        this.noted = noted;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public String getJns() {
        return jns;
    }

    public void setJns(String jns) {
        this.jns = jns;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }
}
