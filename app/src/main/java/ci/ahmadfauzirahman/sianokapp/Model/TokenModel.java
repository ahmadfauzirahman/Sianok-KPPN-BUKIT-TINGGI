package ci.ahmadfauzirahman.sianokapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenModel {
    @SerializedName("id_token")
    @Expose
    private Integer idToken;
    @SerializedName("kd")
    @Expose
    private String kd;
    @SerializedName("stakeKode")
    @Expose
    private String stakeKode;

    public Integer getIdToken() {
        return idToken;
    }

    public void setIdToken(Integer idToken) {
        this.idToken = idToken;
    }

    public String getKd() {
        return kd;
    }

    public void setKd(String kd) {
        this.kd = kd;
    }

    public String getStakeKode() {
        return stakeKode;
    }

    public void setStakeKode(String stakeKode) {
        this.stakeKode = stakeKode;
    }
}
