package ci.ahmadfauzirahman.sianokapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ci.ahmadfauzirahman.sianokapp.Detail;
import ci.ahmadfauzirahman.sianokapp.Model.NotifikasiModel;
import ci.ahmadfauzirahman.sianokapp.R;

public class AdapterNotifikasi extends RecyclerView.Adapter<AdapterNotifikasi.AdapterViewHolder> {

    private List<NotifikasiModel> notifikasiModels;
    private int rowLayout;
    private Context context;
    private String TAG = this.getClass().getName();

    public AdapterNotifikasi(List<NotifikasiModel> notifikasiModels, int rowLayout, Context context){
        this.notifikasiModels = notifikasiModels;
        this.rowLayout = rowLayout;
        this.context = context;
    }
    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.kd.setText(notifikasiModels.get(position).getJns());
        holder.id_notifikasi.setText(notifikasiModels.get(position).getIdNotifikasi());
        holder.status.setText(notifikasiModels.get(position).getStatus());
        holder.tgl.setText(notifikasiModels.get(position).getTgl());
        holder.jam.setText(notifikasiModels.get(position).getJam());
        holder.kode_stakeholder.setText(notifikasiModels.get(position).getKd());

    }

    @Override
    public int getItemCount() {
        return notifikasiModels.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lnNotifikasi;
        TextView id_notifikasi,kd,status,tgl,jam,kode_stakeholder;
        public AdapterViewHolder(@NonNull final View itemView) {
            super(itemView);
            lnNotifikasi =  itemView.findViewById(R.id.lnNotifikasi);
            id_notifikasi = itemView.findViewById(R.id.id_notifikasi);
            kd = itemView.findViewById(R.id.kd);
            status = itemView.findViewById(R.id.status);
            tgl = itemView.findViewById(R.id.tgl);
            jam = itemView.findViewById(R.id.jam);
            kode_stakeholder = itemView.findViewById(R.id.kode_stakeholder);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(itemView.getContext(), Detail.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", id_notifikasi.getText());
                    intent.putExtra("jenis_layanan", kd.getText());
                    intent.putExtra("status", status.getText());
                    intent.putExtra("tgl", tgl.getText());
                    intent.putExtra("jam", jam.getText());
                    intent.putExtra("kode_stakeholder", kode_stakeholder.getText());

                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
