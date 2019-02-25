package com.example.retrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TambahData extends AppCompatActivity {

    public static final String URL = "https://denandra.000webhostapp.com/";
    private RadioButton radioKelamin;
    private Spinner mSpinner;
//    Toolbar mToolbar;

    @BindView(R.id.nim)
    EditText nimText;
    @BindView(R.id.nama)
    EditText namaText;
    @BindView(R.id.radio_kelamin)
    RadioGroup radioGroup;
    @BindView(R.id.spinner_1)
    Spinner spinnerProdi;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_kembali);

        ambilSpinner();
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    public void insert(View view) {
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        //mengambil data dari edittext
        String nim = nimText.getText().toString();
        String nama = namaText.getText().toString();

        int SelectedId = radioGroup.getCheckedRadioButtonId();
        radioKelamin = (RadioButton) findViewById(SelectedId);
        String jenisKelamin = radioKelamin.getText().toString();

        String prodi = spinnerProdi.getSelectedItem().toString();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.mhs(nim, nama, jenisKelamin,prodi);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(TambahData.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TambahData.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(TambahData.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ambilSpinner(){
        Spinner mspinner = (Spinner)findViewById(R.id.spinner_1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.prodi, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinner.setAdapter(adapter);
    }
}
