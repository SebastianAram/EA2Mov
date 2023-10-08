package com.example.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity  implements View.OnClickListener {

    EditText ediUser, ediPass, ediNombre, ediApellido;
    Button btnActualizar, btnCancelar;
    int id = 0;
    Usuario u;
    AdminSQLite dao;
    Intent x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ediUser=(EditText)findViewById(R.id.EditUser);
        ediPass=(EditText)findViewById(R.id.EditPass);
        ediNombre=(EditText)findViewById(R.id.EditNombre);
        ediApellido=(EditText)findViewById(R.id.EditApellido);
        btnActualizar=(Button)findViewById(R.id.btnEditActualizar);
        btnCancelar=(Button)findViewById(R.id.btnEditCancelar);

        btnActualizar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);


        Bundle b=getIntent().getExtras();
        id=b.getInt("Id");
        dao=new AdminSQLite(this);
        u=dao.getUsuarioById(id);
        ediUser.setText(u.getUsuario());
        ediPass.setText(u.getPassword());
        ediNombre.setText(u.getNombre());
        ediApellido.setText(u.getApellidos());
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == R.id.btnEditActualizar) {
            u.setUsuario(ediUser.getText().toString());
            u.setPassword(ediPass.getText().toString());
            u.setNombre(ediNombre.getText().toString());
            u.setApellidos(ediApellido.getText().toString());
            if (!u.isNull()) {
                Toast.makeText(this, "Error campos vacios", Toast.LENGTH_SHORT).show();
            } else if (dao.updateUsuario(u)) {
                Toast.makeText(this, "Actualización Realizada", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(EditActivity.this, InicioActivity.class);
                i2.putExtra("Id", u.getId());
                startActivity(i2);
                finish();
            } else {
                Toast.makeText(this, "No se puede Actualizar", Toast.LENGTH_SHORT).show();
            }
        } else if (vId == R.id.btnEditCancelar) {
            Intent i2 = new Intent(EditActivity.this, InicioActivity.class);
            i2.putExtra("Id", u.getId());
            startActivity(i2);
            finish();
        }
    }
}