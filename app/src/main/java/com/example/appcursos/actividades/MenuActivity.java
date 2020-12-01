package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.appcursos.R;
import com.example.appcursos.adaptadores.MenuAdaptador;
import com.example.appcursos.bd.UsuarioBD;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuAdaptador.ItemClickListener {

    MenuAdaptador adapter;
    ArrayList<String> menu;
    RecyclerView recyclerView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    UsuarioBD ubd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ubd = new UsuarioBD(this);
        menu = new ArrayList<>();
        menu.add("Curso");
        menu.add("Asignaturas");
        menu.add("Alumnos");
        menu.add("Profesores");
        menu.add("Usuarios");

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        adapter = new MenuAdaptador(this, menu);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                Intent i1 = new Intent(getApplicationContext(), CursosActivity.class);
                startActivity(i1);
                break;
            case 1:
                Intent i2 = new Intent(getApplicationContext(), AsignaturasActivity.class);
                startActivity(i2);
                break;
            case 2:
                Intent i3 = new Intent(getApplicationContext(), AlumnosActivity.class);
                startActivity(i3);
                break;
            case 3:
                Intent i4 = new Intent(getApplicationContext(), ProfesoresActivity.class);
                startActivity(i4);
                break;
            case 4:
                Intent i5 = new Intent(getApplicationContext(), UsuariosActivity.class);
                startActivity(i5);
            default:
                Toast.makeText(this, "You clicked " + adapter.getItem(position) +
                        " on row number " + position, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_llamar:
                llamar();
                return true;
            case R.id.action_marcar:
                marcar();
                return true;
            case R.id.action_sms:
                enviar();
                return true;
            case R.id.action_contactos:
                contactos();
                return true;
            case R.id.action_camara:
                camara();
                return true;
            case R.id.action_mapa:
                mapa();
            case R.id.action_salir:
                finish();
                Toast.makeText(getApplicationContext(), "LogOut Successful", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void mapa() {
        Intent mapa = new Intent(MenuActivity.this, MapaActivity.class);
        startActivity(mapa);
    }

    public void llamar() {
        String numero = "674926136";
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse("tel:" + numero));
        if (call.resolveActivity(getPackageManager()) != null) {
            startActivity(call);
        }
    }

    public void marcar() {
        Intent dial = new Intent(Intent.ACTION_DIAL);
        if (dial.resolveActivity(getPackageManager()) != null) {
            startActivity(dial);
        }
    }

    public void enviar() {
        String strPhone = "640608295";
        String strMessage = "LoremnIpsum";
        Uri sms_uri = Uri.parse("smsto:+" + strPhone);
        Intent sms = new Intent(Intent.ACTION_SENDTO, sms_uri);
        sms.putExtra("sms_body", strMessage);
        startActivity(sms);
    }

    public void contactos() {
        Intent contactos = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        if (contactos.resolveActivity(getPackageManager()) != null) {
            startActivity(contactos);
        }
    }

    public void camara() {
        Intent camara = new Intent("android.media.action.IMAGE_CAPTURE");
        if (camara.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(camara, REQUEST_IMAGE_CAPTURE);
        }

    }
}
