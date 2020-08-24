package ci.ahmadfauzirahman.sianokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import ci.ahmadfauzirahman.sianokapp.rest.ApiClient;
import ci.ahmadfauzirahman.sianokapp.rest.ApiInterface;

public class Detail extends AppCompatActivity {
    ApiInterface apiService =
            ApiClient.getClient().create(ApiInterface.class);
    private String TAG = this.getClass().getName();
    String id,jenis_layanan,status,tgl,jam,kode_stakeholder;
    EditText kode_stakeholder_detail,nama_skrt_ed,st_sk_spm_ed,status_detail,jenis_layanan_e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        id = getIntent().getStringExtra("id");
        jenis_layanan = getIntent().getStringExtra("jenis_layanan");
        status = getIntent().getStringExtra("status");
        tgl = getIntent().getStringExtra("tgl");
        jam = getIntent().getStringExtra("jam");
        jam = getIntent().getStringExtra("jam");
        kode_stakeholder = getIntent().getStringExtra("kode_stakeholder");

        kode_stakeholder_detail = findViewById(R.id.kode_stakeholder_detail);
        jenis_layanan_e = findViewById(R.id.jenis_layanan);
        nama_skrt_ed = findViewById(R.id.nama_skrt_ed);
        st_sk_spm_ed = findViewById(R.id.st_sk_spm_ed);
        status_detail = findViewById(R.id.status_detail);

        nama_skrt_ed.setText(tgl.toString());
        st_sk_spm_ed.setText(jam.toString());
        kode_stakeholder_detail.setText(kode_stakeholder.toString());
        jenis_layanan_e.setText(jenis_layanan.toString());
        status_detail.setText(status.toString());
    }

    private void one(String id) {
    }
}
