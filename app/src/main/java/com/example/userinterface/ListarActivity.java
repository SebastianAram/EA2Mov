package com.example.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListarActivity extends AppCompatActivity {
    ListView lista;
    AdminSQLite dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        lista=(ListView)findViewById(R.id.lista);
        dao = new AdminSQLite(this);
        ArrayList<Usuario> l=dao.selectUsuarios();
        ArrayList<String> list=new ArrayList<String>();

        for (Usuario u: l) {
            list.add(u.getNombre()+" "+u.getApellidos());
        }

        ArrayAdapter<String> a=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
        lista.setAdapter(a);

    }
}