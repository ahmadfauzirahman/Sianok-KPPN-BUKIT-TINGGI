package ci.ahmadfauzirahman.sianokapp.rest;


import ci.ahmadfauzirahman.sianokapp.Response.AkunResponse;
import ci.ahmadfauzirahman.sianokapp.Response.AntrianResponse;
import ci.ahmadfauzirahman.sianokapp.Response.NotifikasiResponse;
import ci.ahmadfauzirahman.sianokapp.Response.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/v1/akun/login-api")
    Call<AkunResponse> login(
            @Field("KodeAkun") String KodeAkun,
            @Field("Password") String Password
    );

    @FormUrlEncoded
    @POST("api/v1/notif/index")
    Call<NotifikasiResponse> getAllNotifikasi(
            @Field("KodeAkun") String KodeAkun
    );

    @FormUrlEncoded
    @POST("api/v1/token/token")
    Call<TokenResponse> insertToken(
            @Field("token") String Token,
            @Field("kd") String kodeStakeholder
    );

    @FormUrlEncoded
    @POST("web/antrian-api/ambil-antrian")
    Call<AntrianResponse> sendAntrian(
            @Field("kode_stakeholder") String kode_stakeholder,
            @Field("jenis_layanan") String jenis_layanan,
            @Field("jumlah_spm") String jumlah_spm
    );

    @FormUrlEncoded
    @POST("api/v1/antrian/cek")
    Call<AntrianResponse> AntrianResponCek(
            @Field("kd") String kode_stakeholder
    );



//
//
//    @FormUrlEncoded
//    @POST("api-kontrak/getmobilekontrakapi")
//    Call<SpmResponse> getAllKontrak(
//            @Field("kode_stakeholder") String kode_stakeholder
//    );
//    @FormUrlEncoded
//    @POST("api-skpp/alldatabystakeholder")
//    Call<SkppResponse> getAllSkpp(
//            @Field("kode_stakeholder") String kode_stakeholder
//    );
//    @FormUrlEncoded
//    @POST("api-spm/addspm")
//    Call<SpmResponse> spmDaftar(
//            @Field("kode_stakeholder") String kode_stakeholder,
//            @Field("nomor_spm") String nomor_spm,
//            @Field("jenis_notif") String jenis_notif
//    );
//
//    @FormUrlEncoded
//    @POST("api-skpp/addskpp")
//    Call<SkppResponse> skppDaftar(
//            @Field("kode_stakeholder") String kode_stakeholder,
//            @Field("nomor_skpp") String nomor_skpp
//    );
//
//
//    @FormUrlEncoded
//    @POST("api-all-sikaping/getspm")
//    Call<SpmResponse> getOneSpm(
//            @Field("nomor_spm") String nomor_spm
//    );
//
//    @FormUrlEncoded
//    @POST("api-all-sikaping/getskpp")
//    Call<SkppResponse> getOneSkpp(
//            @Field("nomor_skpp") String nomor_skpp
//    );




}
