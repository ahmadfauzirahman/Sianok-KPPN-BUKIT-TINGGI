package ci.ahmadfauzirahman.sianokapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

import ci.ahmadfauzirahman.sianokapp.Response.TokenResponse;
import ci.ahmadfauzirahman.sianokapp.rest.ApiClient;
import ci.ahmadfauzirahman.sianokapp.rest.ApiInterface;
import ci.ahmadfauzirahman.sianokapp.ui.login.LoginActivity;
import ci.ahmadfauzirahman.sianokapp.utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    SessionManager sessionManager;
    String kode_stakeholder;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(this);

        checkSessionLogin();

        tabLayout = findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_text_1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_text_2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_text_3));

        final ViewPager viewPager = findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(MainActivity.this, TentangAplikasiActivity.class));
        }

        if (item.getItemId() == R.id.vLogout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Konfirmasi logout")
                    .setMessage("Klik ya jika ingin logout")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sessionManager.logoutUser();
                            finish();
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                        }
                    })
                    .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }

        if (item.getItemId() == R.id.privacy) {
            startActivity(new Intent(MainActivity.this, Privacy.class));
        }

        return true;
    }

    private void checkSessionLogin() {
        if (!sessionManager.isLoggedIn()) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            MainActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Tutup Aplikasi?")
                .setMessage("Ya, untuk tutup aplikasi dan aplikasi akan melakukan log out pada akun anda secara otomatis")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessionManager.logoutUser();
                        finish();
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
