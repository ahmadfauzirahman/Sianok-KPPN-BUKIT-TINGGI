package ci.ahmadfauzirahman.sianokapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ci.ahmadfauzirahman.sianokapp.R;
import ci.ahmadfauzirahman.sianokapp.utils.SessionManager;


public class AkunFragment extends Fragment {

    View view;
    SessionManager sessionManager;
    String kode_stakeholder,nama,email,telp;
EditText namaE,kdE,emailE,teleponE;
    private String TAG = this.getClass().getName();

    public AkunFragment() {
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
        view = inflater.inflate(R.layout.fragment_akun, container, false);
        sessionManager = new SessionManager(getContext());

        kode_stakeholder= sessionManager.getUserDetail().get("username").toString();
        nama= sessionManager.getUserDetail().get("nama").toString();
        email= sessionManager.getUserDetail().get("EMAIL").toString();
        telp= "07777";
        final String token = sessionManager.getToken();

        namaE = view.findViewById(R.id.nama);
        kdE = view.findViewById(R.id.kd);
        emailE = view.findViewById(R.id.email);
        teleponE = view.findViewById(R.id.telepon);

        namaE.setText(nama.toString());
        kdE.setText(kode_stakeholder.toString());
        emailE.setText(kode_stakeholder.toString());
        teleponE.setText(token);
        return view;
    }
}
