package com.example.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener {
    EditText us, pas, nom, ap;
    Button reg, can;
    AdminSQLite dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        us=(EditText)findViewById(R.id.RegUser);
        pas=(EditText)findViewById(R.id.RegPass);
        nom=(EditText)findViewById(R.id.RegNombre);
        ap=(EditText)findViewById(R.id.RegApellido);
        reg=(Button)findViewById(R.id.btnRegRegistrar);
        can=(Button)findViewById(R.id.btnRegCancelar);
        dao=new AdminSQLite(this);
        reg.setOnClickListener(this);
        can.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnRegRegistrar) {
            Usuario u = new Usuario();
            u.setUsuario(us.getText().toString());
            u.setPassword(pas.getText().toString());
            u.setNombre(nom.getText().toString());
            u.setApellidos(ap.getText().toString());
            if (!u.isNull()) {
                Toast.makeText(this, "Error campos vacios", Toast.LENGTH_SHORT).show();
            } else if (dao.insertUsuario(u)) {
                Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i2);
                finish();
            } else {
                Toast.makeText(this, "Usuario Ya registrado", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.btnRegCancelar) {
            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}