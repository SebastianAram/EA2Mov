package com.example.userinterface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userinterface.AdminSQLite;
import com.example.userinterface.EditActivity;
import com.example.userinterface.MainActivity;
import com.example.userinterface.Usuario;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnEditar, btnEliminar, btnMostrar, btnSalir;
    TextView nombre;
    int id =0;
    Usuario u;
    AdminSQLite dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        nombre=(TextView)findViewById(R.id.nombreUsuario);
        btnEditar=(Button)findViewById(R.id.btnEditar);
        btnEliminar=(Button)findViewById(R.id.btnEliminar);
        btnMostrar=(Button)findViewById(R.id.btnMostrar);
        btnSalir=(Button)findViewById(R.id.btnSalir);

        btnEditar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        id=b.getInt("Id");
        dao=new AdminSQLite(this);
        u=dao.getUsuarioById(id);
        nombre.setText(u.getNombre()+" "+u.getApellidos());
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == R.id.btnEditar) {
            Intent a = new Intent(InicioActivity.this, EditActivity.class);
            a.putExtra("Id", id);
            startActivity(a);
        } else if (vId == R.id.btnEliminar) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setMessage("Quieres eliminar la cuenta?");
            b.setCancelable(false);
            b.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (dao.deleteUsuario(id)) {
                        Toast.makeText(InicioActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();
                        Intent a = new Intent(InicioActivity.this, MainActivity.class);
                        a.putExtra("Id", id);
                        startActivity(a);
                        finish();
                    } else {
                        Toast.makeText(InicioActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            b.show();
        } else if (vId == R.id.btnMostrar) {
            Intent c = new Intent(InicioActivity.this, ListarActivity.class);
            startActivity(c);
        } else if (vId == R.id.btnSalir) {
            Intent i2 = new Intent(InicioActivity.this, MainActivity.class);
            startActivity(i2);
            finish();
        }
    }
}