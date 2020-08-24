package ci.ahmadfauzirahman.sianokapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.URI;
import java.util.List;

import ci.ahmadfauzirahman.sianokapp.MainActivity;
import ci.ahmadfauzirahman.sianokapp.Model.AntrianModel;
import ci.ahmadfauzirahman.sianokapp.R;
import ci.ahmadfauzirahman.sianokapp.Response.AntrianResponse;
import ci.ahmadfauzirahman.sianokapp.config.ServerConfig;
import ci.ahmadfauzirahman.sianokapp.rest.ApiClient;
import ci.ahmadfauzirahman.sianokapp.rest.ApiInterface;
import ci.ahmadfauzirahman.sianokapp.ui.login.LoginActivity;
import ci.ahmadfauzirahman.sianokapp.utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AntrianFragment extends Fragment {

    View view;
    EditText jumlah_spm,tanggalDatang,nomorAntrian;
    Integer nilaiJumlahSpm;
    String Layanan = "SPM";
    SessionManager sessionManager;
    Button daftarAntrian, downloadPdf;
    String kode_stakeholder;
    LinearLayout antrianSudahTerdaftar;
    private String TAG = this.getClass().getName();
    EditText nilai;
    TextView textJumlahSPM;
    public AntrianFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_antrian, container, false);
        sessionManager = new SessionManager(getContext());
        kode_stakeholder = sessionManager.getUserDetail().get("username").toString();
        daftarAntrian = view.findViewById(R.id.daftarAntrian);
        antrianSudahTerdaftar = view.findViewById(R.id.antrianSudahTerdaftar);
        downloadPdf = view.findViewById(R.id.downloadPdf);
        nomorAntrian = view.findViewById(R.id.nomorAntrian);
        tanggalDatang = view.findViewById(R.id.tanggalDatang);
        textJumlahSPM = view.findViewById(R.id.textJumlahSPM);
        nilai = view.findViewById(R.id.jumlah_spm_s);
        cekPendaftaran(kode_stakeholder);
        daftarAntrian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("OnInput" + nilai.getText().toString());

                insertAntrian(kode_stakeholder, Layanan, nilai.getText().toString());

            }
        });
        return view;
    }

    private void insertAntrian(String kode_stakeholder, String layanan, String nilaiJumlahSpm) {
        if (TextUtils.isEmpty(nilaiJumlahSpm)) {
            AlertDialog alertDialog = new AlertDialog.Builder(
                    view.getContext()).create();
            alertDialog.setMessage("Jumlah Nilai SPM Yang Diajukan Tidak Boleh Kosong");
            alertDialog.show();
        }


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<AntrianResponse> call = apiService.sendAntrian(kode_stakeholder, Layanan.toString(), nilaiJumlahSpm);
        call.enqueue(new Callback<AntrianResponse>() {
            @Override
            public void onResponse(Call<AntrianResponse> call, Response<AntrianResponse> response) {
                System.out.println("OnResponse Url" + response.toString());
                if (response.isSuccessful()) {
                    if (response.body().getCon()) {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(
                                view.getContext()).create();
                        alertDialog.setMessage(response.body().getMsg());
                        alertDialog.show();
                    }
                }
//                System.out.println("onResponse: " + response.body().getResults());

            }

            @Override
            public void onFailure(Call<AntrianResponse> call, Throwable t) {
                Log.e(TAG, "OnLog" + t.toString());

            }
        });
    }

    public void cekPendaftaran(String kode_stakeholder) {
        System.out.println("Kode Stakeholder" + kode_stakeholder);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AntrianResponse> call = apiService.AntrianResponCek(kode_stakeholder);
        call.enqueue(new Callback<AntrianResponse>() {
            @Override
            public void onResponse(Call<AntrianResponse> call, Response<AntrianResponse> response) {
                System.out.println("OnResponse Url" + response.toString());
                System.out.println("OnResponse Hasil" + response.body().getCon());

                if (response.isSuccessful()) {
                    if (response.body().getCon()) {
                        daftarAntrian.setVisibility(view.GONE);
                        nilai.setVisibility(view.GONE);
                        textJumlahSPM.setVisibility(view.GONE);
                        List<AntrianModel> antrianModelList = response.body().getResults();
                        final AntrianModel antrianModel = antrianModelList.get(0);
                        tanggalDatang.setText(antrianModel.getWaktu());
                        nomorAntrian.setText(antrianModel.getNomorAntrian());
                        downloadPdf.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ServerConfig.SERVER_CETAK + antrianModel.getId()));
                                startActivity(browserIntent);
                            }
                        });
                    } else {
                        antrianSudahTerdaftar.setVisibility(view.GONE);
                        downloadPdf.setVisibility(view.GONE);
                        daftarAntrian.setVisibility(view.VISIBLE);
                        nilai.setVisibility(view.VISIBLE);
                        textJumlahSPM.setVisibility(view.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<AntrianResponse> call, Throwable t) {

            }
        });
    }
}