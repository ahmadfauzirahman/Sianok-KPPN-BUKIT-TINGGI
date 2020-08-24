package ci.ahmadfauzirahman.sianokapp.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ci.ahmadfauzirahman.sianokapp.Model.NotifikasiModel;

public class NotifikasiResponse {
    @SerializedName("con")
    @Expose
    private Boolean con;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("results")
    @Expose
    private List<NotifikasiModel> results = null;

    public Boolean getCon() {
        return con;
    }

    public void setCon(Boolean con) {
        this.con = con;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NotifikasiModel> getResults() {
        return results;
    }

    public void setResults(List<NotifikasiModel> results) {
        this.results = results;
    }
}
