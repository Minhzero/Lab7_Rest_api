package minhnqph38692.fpoly.kiemtra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import minhnqph38692.fpoly.kiemtra.modal.ResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;

import minhnqph38692.fpoly.kiemtra.adapter.KiemTraAdapter;
import minhnqph38692.fpoly.kiemtra.handle.Item_KT_Handle;
import minhnqph38692.fpoly.kiemtra.modal.KiemtraDTO;
import minhnqph38692.fpoly.kiemtra.server.HttpRequest;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    HttpRequest httpRequest;
    RecyclerView recyclerView;
    KiemTraAdapter adapter;
    FloatingActionButton add;
    Item_KT_Handle handle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycview);
        add = findViewById(R.id.add);
        httpRequest = new HttpRequest();

        handle = new Item_KT_Handle() {
            @Override
            public void Delete(String id) {
                DeleteD(id);
            }

            @Override
            public void Upadte(String id, KiemtraDTO kiemtraDTO) {
                UpdateS(id, new KiemtraDTO());
            }
        };
        httpRequest.callAPI()
                .getListKT()
                .enqueue(getKTAPI);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });
    }

    private void getData(List<KiemtraDTO> list) {
        adapter = new KiemTraAdapter(list, this, handle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    Callback<ResponseDTO<ArrayList<KiemtraDTO>>> getKTAPI = new Callback<ResponseDTO<ArrayList<KiemtraDTO>>>() {
        @Override
        public void onResponse(Call<ResponseDTO<ArrayList<KiemtraDTO>>> call, Response<ResponseDTO<ArrayList<KiemtraDTO>>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    List<KiemtraDTO> list = response.body().getData();
                    getData(list);
                    Toast.makeText(MainActivity.this, response.body().getMessager(), Toast.LENGTH_SHORT).show();

                }
            }
        }

        @Override
        public void onFailure(Call<ResponseDTO<ArrayList<KiemtraDTO>>> call, Throwable t) {
            Log.d(">>> GetListDitributor", " onFailure" + t.getMessage());

        }
    };

    public void DeleteD(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Thông báo!");
        builder.setMessage("Bạn có chắc xóa?");
        AlertDialog dialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                httpRequest.callAPI()
                        .deleteKTById(id)
                        .enqueue(responKTAPI);
                Toast.makeText(MainActivity.this, "Xoas thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }



    public void Add() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_themcongty, null);
        builder.setView(v);
//        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        TextInputEditText ed_themten = v.findViewById(R.id.ten);
        TextInputEditText ma = v.findViewById(R.id.ma);
        TextInputEditText die = v.findViewById(R.id.die);

        Button btn_themten = v.findViewById(R.id.btn_them);

        btn_themten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = ed_themten.getText().toString();
                String ma1 = ma.getText().toString();
                String die1 = die.getText().toString();

                KiemtraDTO distributor = new KiemtraDTO();
                distributor.setName(ten);
                distributor.setMaSV(ma1);
                distributor.setDiem(Integer.parseInt(die1));


                httpRequest.callAPI()
                        .addKT(distributor)
                        .enqueue(responKTAPI);
                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();

    }
    public  void UpdateS(String id,  KiemtraDTO kiemtraDTO  ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_themcongty,null);
        builder.setView(v);
//        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        TextInputEditText ed_themten = v.findViewById(R.id.ten);
        TextInputEditText ma = v.findViewById(R.id.ma);
        TextInputEditText die = v.findViewById(R.id.die);

        Button btn_updateten = v.findViewById(R.id.btn_them);


        btn_updateten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = ed_themten.getText().toString();
                String ma2 = ma.getText().toString();
                String die2 = die.getText().toString();

//                 distributor = new Distributor();
                kiemtraDTO.setName(ten);
                kiemtraDTO.setMaSV(ma2);
                kiemtraDTO.setName(die2);

                httpRequest.callAPI()
                        .updateKtById(id,kiemtraDTO)
                        .enqueue(responKTAPI);
                Toast.makeText(MainActivity.this, "upadte thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    Callback<ResponseDTO<KiemtraDTO>> responKTAPI = new Callback<ResponseDTO<KiemtraDTO>>() {
        @Override
        public void onResponse(Call<ResponseDTO<KiemtraDTO>> call, Response<ResponseDTO<KiemtraDTO>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    httpRequest.callAPI()
                            .getListKT()
                            .enqueue(getKTAPI);
//                    Toast.makeText(MainActivity.this, response.body().getMessenger(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseDTO<KiemtraDTO>> call, Throwable t) {
            Log.d(">>> GetListDitributor", " onFailure" + t.getMessage());

        }
    };
}