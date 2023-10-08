package com.example.userinterface;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText user, pass;
    Button btnIngresar, btnRegister;
    AdminSQLite dao;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.User);
        pass=(EditText)findViewById(R.id.Pass);
        btnIngresar=(Button)findViewById(R.id.btnIngresar);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        dao=new AdminSQLite(this);
        btnIngresar.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnIngresar) {
            String u = user.getText().toString();
            String p = pass.getText().toString();
            if (u.equals("") && p.equals("")) {
                Toast.makeText(this, "Error campos vacios", Toast.LENGTH_SHORT).show();
            } else if (dao.login(u, p) == 1) {
                Usuario ux = dao.getUsuario(u, p);
                Toast.makeText(this, "Datos Correctos", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(MainActivity.this, InicioActivity.class);
                i2.putExtra("Id", ux.getId());
                startActivity(i2);
                finish();
            } else {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.btnRegister) {
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity((i));
        }
    }
}
