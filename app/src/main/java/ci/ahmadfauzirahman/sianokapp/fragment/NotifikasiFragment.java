package ci.ahmadfauzirahman.sianokapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ci.ahmadfauzirahman.sianokapp.Model.NotifikasiModel;
import ci.ahmadfauzirahman.sianokapp.R;
import ci.ahmadfauzirahman.sianokapp.Response.NotifikasiResponse;
import ci.ahmadfauzirahman.sianokapp.Response.TokenResponse;
import ci.ahmadfauzirahman.sianokapp.adapter.AdapterNotifikasi;
import ci.ahmadfauzirahman.sianokapp.rest.ApiClient;
import ci.ahmadfauzirahman.sianokapp.rest.ApiInterface;
import ci.ahmadfauzirahman.sianokapp.utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifikasiFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    String kode_stakeholder;
    private String TAG = this.getClass().getName();
    SessionManager sessionManager;
    ApiInterface apiService =
            ApiClient.getClient().create(ApiInterface.class);
    Intent intent;

    public NotifikasiFragment() {
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
        view = inflater.inflate(R.layout.fragment_notifikasi, container, false);
        sessionManager = new SessionManager(getContext());
        kode_stakeholder= sessionManager.getUserDetail().get("username").toString();
        final String token = sessionManager.getToken();
        final String stakeholder = sessionManager.getUserDetail().get("username");

        insertToken(token,stakeholder);
        swipeRefreshLayout = view.findViewById(R.id.swpNotifikasi);
        recyclerView = (RecyclerView) view.findViewById(R.id.reyNotifikasi);
        getAllNotifikasi(kode_stakeholder);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // loading = ProgressDialog.show(context,null,"Sedang mendapatkan berita",true,false);
                swipeRefreshLayout.setRefreshing(false);
                getAllNotifikasi(kode_stakeholder);

            }
        });
        return view;
    }

    private void insertToken(String token, String kode_stakeholder) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<TokenResponse> call = apiService.insertToken(token,kode_stakeholder);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                System.out.println("onResponse: " + response.body().getResults());

            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e(TAG, "OnLog" + t.toString());
            }
        });
    }

    private void getAllNotifikasi(String kode_stakeholder) {
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.reyNotifikasi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        apiService.getAllNotifikasi(kode_stakeholder).enqueue(new Callback<NotifikasiResponse>() {
            @Override
            public void onResponse(Call<NotifikasiResponse> call, Response<NotifikasiResponse> response) {
                System.out.println("OnResponse Url" + response.toString());
                System.out.println("OnResponse Data" + response.body().getResults());

                if (response.isSuccessful()) {
                    List<NotifikasiModel> notifikasiModels = response.body().getResults();
                    recyclerView.setAdapter(new AdapterNotifikasi(notifikasiModels, R.layout.list_notifikasi, getContext()));
                } else {
                    System.out.println("OnResponse Data" + response.body().toString());

                    Toast.makeText(getContext(), "Tidak Terhubung KeJaringan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotifikasiResponse> call, Throwable t) {
                System.out.println("Error Aplikasi" +
                        "" + t.getLocalizedMessage());
                Log.e(TAG, t.getLocalizedMessage());

            }
        });

    }
}
