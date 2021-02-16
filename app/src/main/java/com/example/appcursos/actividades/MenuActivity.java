package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
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
    private static final int REQUEST_IMAGE_CAPTURE = 1;
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
        menu.add("Roles");
        menu.add("Permisos");

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
                Intent i1 = new Intent(MenuActivity.this, CursosActivity.class);
                startActivity(i1);
                Toast.makeText(getApplicationContext(), menu.get(0), Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Intent i2 = new Intent(MenuActivity.this, AsignaturasActivity.class);
                startActivity(i2);
                Toast.makeText(getApplicationContext(), menu.get(1), Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Intent i3 = new Intent(MenuActivity.this, AlumnosActivity.class);
                startActivity(i3);
                Toast.makeText(getApplicationContext(), menu.get(2), Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Intent i4 = new Intent(MenuActivity.this, ProfesoresActivity.class);
                startActivity(i4);
                Toast.makeText(getApplicationContext(), menu.get(3), Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Intent i5 = new Intent(MenuActivity.this, UsuariosActivity.class);
                startActivity(i5);
                Toast.makeText(getApplicationContext(), menu.get(4), Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Intent i6 = new Intent(MenuActivity.this, RolesActivity.class);
                startActivity(i6);
                Toast.makeText(getApplicationContext(), menu.get(5), Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Intent i7 = new Intent(MenuActivity.this, PermisosActivity.class);
                startActivity(i7);
                Toast.makeText(getApplicationContext(), menu.get(6), Toast.LENGTH_SHORT).show();
                break;
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
                Toast.makeText(getApplicationContext(), "Ha salido correctamente", Toast.LENGTH_SHORT).show();
                Intent salir = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(salir);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void mapa() {
        Intent mapa = new Intent(MenuActivity.this, MapaActivity.class);
        startActivity(mapa);
    }

    public void llamar() {
        Intent call = new Intent(android.content.Intent.ACTION_CALL,
                Uri.parse("tel:640608295"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CALL_PHONE}, 1);
        }
        if (call.resolveActivity(getPackageManager()) != null) {
            startActivity(call);
        }
    }

    public void marcar() {
        Intent dial = new Intent(Intent.ACTION_DIAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CALL_PHONE}, 1);
        }
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.SEND_SMS}, 1);
        }
        if (sms.resolveActivity(getPackageManager()) != null) {
            startActivity(sms);
        }

    }

    public void contactos() {
        Intent contactos = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_CONTACTS}, 1);
        }
        if (contactos.resolveActivity(getPackageManager()) != null) {
            startActivity(contactos);
        }
    }

    public void camara() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA}, 1);
        }
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }
}
