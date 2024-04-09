package minhnqph38692.fpoly.kiemtra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import minhnqph38692.fpoly.kiemtra.R;
import minhnqph38692.fpoly.kiemtra.handle.Item_KT_Handle;
import minhnqph38692.fpoly.kiemtra.modal.KiemtraDTO;

public class KiemTraAdapter extends  RecyclerView.Adapter<KiemTraAdapter.ViewHolder> {


    List<KiemtraDTO> list;
    Context  context;
    Item_KT_Handle handle;

    public KiemTraAdapter(List<KiemtraDTO> list, Context context, Item_KT_Handle handle) {
        this.list = list;
        this.context = context;
        this.handle = handle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.viewholder_kt),parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        KiemtraDTO kiemtraDTO = list.get(position);
        holder.maSV.setText(kiemtraDTO.getMaSV());
        holder.diem.setText(""+kiemtraDTO.getDiem());
        holder.name.setText(kiemtraDTO.getName());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handle.Delete(list.get(position).getId());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handle.Upadte(list.get(position).getId(),kiemtraDTO);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView maSV,name,diem;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maSV = itemView.findViewById(R.id.maSV);
            name=itemView.findViewById(R.id.name);
            diem = itemView.findViewById(R.id.diem);
            img = itemView.findViewById(R.id.delete);
        }
    }
}
