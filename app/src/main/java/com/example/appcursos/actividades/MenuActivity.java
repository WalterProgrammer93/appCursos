package com.example.appcursos.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.example.appcursos.adaptadores.MenuAdminAdaptador;
import com.example.appcursos.adaptadores.MenuAlumnoAdaptador;
import com.example.appcursos.adaptadores.MenuProfesorAdaptador;
import com.example.appcursos.adaptadores.MenuUsuarioAdaptador;
import com.example.appcursos.bd.PermisoBD;
import com.example.appcursos.bd.UsuarioBD;
import com.example.appcursos.modelos.Permiso;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    MenuAdminAdaptador maa;
    MenuAlumnoAdaptador mad;
    MenuProfesorAdaptador mpa;
    MenuUsuarioAdaptador mua;
    ArrayList<String> menuAdmin, menuAlumno, menuProfesor, menuUsuario;
    RecyclerView recyclerView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    UsuarioBD ubd;
    PermisoBD pbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ubd = new UsuarioBD(this);
        pbd = new PermisoBD(this);
        menuAdmin = new ArrayList<>();
        recyclerView = findViewById(R.id.my_recycler_view);
        Intent i = getIntent();
        String username = i.getStringExtra("Username");
        Permiso permisoAdmin = pbd.isPermiso(username, "Admin");
        if (permisoAdmin.getUsuario().equalsIgnoreCase(username) && permisoAdmin.getRol().equalsIgnoreCase("Admin")) {
            showDialog(0);
            menuAdmin.add("Curso");
            menuAdmin.add("Asignaturas");
            menuAdmin.add("Alumnos");
            menuAdmin.add("Profesores");
            menuAdmin.add("Usuarios");
            menuAdmin.add("Roles");
            menuAdmin.add("Permisos");
            menuAdmin.add("Multimedia");
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
            maa = new MenuAdminAdaptador(this, menuAdmin);
            recyclerView.setAdapter(maa);
            maa.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = recyclerView.getChildAdapterPosition(view);
                    switch (position) {
                        case 0:
                            Intent i0 = new Intent(MenuActivity.this, CursosActivity.class);
                            startActivity(i0);
                            Toast.makeText(getApplicationContext(), menuAdmin.get(0), Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Intent i1 = new Intent(MenuActivity.this, AsignaturasActivity.class);
                            startActivity(i1);
                            Toast.makeText(getApplicationContext(), menuAdmin.get(1), Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Intent i2 = new Intent(MenuActivity.this, AlumnosActivity.class);
                            startActivity(i2);
                            Toast.makeText(getApplicationContext(), menuAdmin.get(2), Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Intent i3 = new Intent(MenuActivity.this, ProfesoresActivity.class);
                            startActivity(i3);
                            Toast.makeText(getApplicationContext(), menuAdmin.get(3), Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Intent i4 = new Intent(MenuActivity.this, UsuariosActivity.class);
                            startActivity(i4);
                            Toast.makeText(getApplicationContext(), menuAdmin.get(4), Toast.LENGTH_SHORT).show();
                            break;
                        case 5:
                            Intent i5 = new Intent(MenuActivity.this, RolesActivity.class);
                            startActivity(i5);
                            Toast.makeText(getApplicationContext(), menuAdmin.get(5), Toast.LENGTH_SHORT).show();
                            break;
                        case 6:
                            Intent i6 = new Intent(MenuActivity.this, PermisosActivity.class);
                            startActivity(i6);
                            Toast.makeText(getApplicationContext(), menuAdmin.get(6), Toast.LENGTH_SHORT).show();
                            break;
                        case 7:
                            Intent i7 = new Intent(MenuActivity.this, MultimediaActivity.class);
                            startActivity(i7);
                            Toast.makeText(getApplicationContext(), menuAdmin.get(7), Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(MenuActivity.this, "You clicked " + maa.getItem(position) +
                                    " on row number " + position, Toast.LENGTH_SHORT).show();
                    }

                }
            });

        } else {
            Permiso permisoAlumno = pbd.isPermiso(username, "Alumno");
            if (permisoAlumno.getUsuario().equalsIgnoreCase(username) && permisoAlumno.getRol().equalsIgnoreCase("Alumno")) {
                showDialog(1);
                menuAlumno.add("Curso");
                menuAlumno.add("Asignaturas");
                menuAlumno.add("Multimedia");
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
                mad = new MenuAlumnoAdaptador(this, menuAlumno);
                mad.setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = recyclerView.getChildAdapterPosition(view);
                        switch (position) {
                            case 0:
                                Intent i0 = new Intent(MenuActivity.this, CursosActivity.class);
                                startActivity(i0);
                                Toast.makeText(getApplicationContext(), menuAlumno.get(0), Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Intent i1 = new Intent(MenuActivity.this, AsignaturasActivity.class);
                                startActivity(i1);
                                Toast.makeText(getApplicationContext(), menuAlumno.get(1), Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Intent i7 = new Intent(MenuActivity.this, MultimediaActivity.class);
                                startActivity(i7);
                                Toast.makeText(getApplicationContext(), menuAlumno.get(7), Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(MenuActivity.this, "You clicked " + mad.getItem(position) +
                                        " on row number " + position, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                recyclerView.setAdapter(mad);
            } else {
                Permiso permisoProfesor = pbd.isPermiso(username, "Profesor");
                if (permisoProfesor.getUsuario().equalsIgnoreCase(username) && permisoProfesor.getRol().equalsIgnoreCase("Profesor")) {
                    showDialog(2);
                    menuProfesor.add("Curso");
                    menuProfesor.add("Asignatura");
                    menuProfesor.add("Profesores");
                    menuProfesor.add("Multimedia");
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
                    mpa = new MenuProfesorAdaptador(this, menuProfesor);
                    mpa.setOnItemClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int position = recyclerView.getChildAdapterPosition(view);
                            switch (position) {
                                case 0:
                                    Intent i0 = new Intent(MenuActivity.this, CursosActivity.class);
                                    startActivity(i0);
                                    Toast.makeText(getApplicationContext(), menuProfesor.get(0), Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    Intent i1 = new Intent(MenuActivity.this, AsignaturasActivity.class);
                                    startActivity(i1);
                                    Toast.makeText(getApplicationContext(), menuProfesor.get(1), Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    Intent i3 = new Intent(MenuActivity.this, ProfesoresActivity.class);
                                    startActivity(i3);
                                    Toast.makeText(getApplicationContext(), menuProfesor.get(3), Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    Intent i7 = new Intent(MenuActivity.this, MultimediaActivity.class);
                                    startActivity(i7);
                                    Toast.makeText(getApplicationContext(), menuProfesor.get(7), Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(MenuActivity.this, "You clicked " + mpa.getItem(position) +
                                            " on row number " + position, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    recyclerView.setAdapter(mpa);
                } else {
                    Permiso permisoUsuario = pbd.isPermiso(username, "Usuario");
                    if (permisoUsuario.getUsuario().equalsIgnoreCase(username) && permisoUsuario.getRol().equalsIgnoreCase("Usuario")) {
                        showDialog(3);
                        menuUsuario.add("Multimedia");
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
                        mua = new MenuUsuarioAdaptador(this, menuUsuario);
                        mua.setOnItemClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int position = recyclerView.getChildAdapterPosition(view);
                                if (position == 0) {
                                    Intent i7 = new Intent(MenuActivity.this, MultimediaActivity.class);
                                    startActivity(i7);
                                    Toast.makeText(getApplicationContext(), menuUsuario.get(7), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MenuActivity.this, "You clicked " + mua.getItem(position) +
                                            " on row number " + position, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        recyclerView.setAdapter(mua);
                    }
                }
            }
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

    protected Dialog onCreateDialog(int id) {
        // Use the Builder class for convenient dialog construction
        Dialog dialogo = null;
        switch (id) {
            case 0:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.mensajeAdmin)
                        .setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.dismiss();
                            }
                        });
                // Create the AlertDialog object and return it
                dialogo = builder.create();
                break;
            case 1:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage(R.string.mensajeAlumno)
                        .setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.dismiss();
                            }
                        });
                // Create the AlertDialog object and return it
                dialogo = builder1.create();
                break;
            case 2:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setMessage(R.string.mensajeProfesor)
                        .setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.dismiss();
                            }
                        });
                // Create the AlertDialog object and return it
                dialogo = builder2.create();
                break;
            case 3:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                builder3.setMessage(R.string.mensajeUsuario)
                        .setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.dismiss();
                            }
                        });
                // Create the AlertDialog object and return it
                dialogo = builder3.create();
                break;
            default:
                return super.onCreateDialog(id);
        }
        return dialogo;
    }


}
