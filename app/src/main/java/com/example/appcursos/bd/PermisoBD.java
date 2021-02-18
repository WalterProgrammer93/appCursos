package com.example.appcursos.bd;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.appcursos.modelos.Permiso;
import com.example.appcursos.modelos.Rol;
import com.example.appcursos.modelos.Usuario;
import java.util.ArrayList;

public class PermisoBD {

    private static final String DATABASE_NAME = "appcursos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLA_PERMISOS = "permisos";
    private static final String COL_PERMISO_ID = "permiso_id";
    private static final int NUM_COL_PERMISO_ID = 0;
    private static final String COL_USUARIO_ID = "usuario_id";
    private static final int NUM_COL_USUARIO_ID = 1;
    private static final String COL_ROL_ID = "rol_id";
    private static final int NUM_COL_ROL_ID = 2;

    private SQLiteDatabase bd;
    private AdminBD abd;

    public PermisoBD(Context context) {
        abd = new AdminBD(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void leerBD() {
        bd = abd.getReadableDatabase();
    }
    public void escribirBD() {
        bd = abd.getWritableDatabase();
    }
    public void cerrarBD() {
        bd.close();
    }

    public void insertarPermiso(Permiso permiso) {
        bd = abd.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(COL_USUARIO_ID, permiso.getUsuario());
        registro.put(COL_ROL_ID, permiso.getRol());
        bd.insert(TABLA_PERMISOS, null, registro);
        bd.close();
    }

    public void editarPermiso(int id, Permiso permiso) {
        bd = abd.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(COL_USUARIO_ID, permiso.getUsuario());
        registro.put(COL_ROL_ID, permiso.getRol());
        bd.update(TABLA_PERMISOS, registro, COL_PERMISO_ID + "=" + id,null);
        bd.close();
    }

    public void eliminarPermiso(int id) {
        bd = abd.getReadableDatabase();
        bd.delete(TABLA_PERMISOS, COL_PERMISO_ID + "=" + id, null);
        bd.close();
    }

    public Permiso buscarPermiso(int id) {
        bd = abd.getReadableDatabase();
        Cursor cursor = bd.query(TABLA_PERMISOS, new String[] {COL_PERMISO_ID, COL_USUARIO_ID, COL_ROL_ID}, COL_PERMISO_ID
                + " LIKE \"" + id + "\"", null, null, null, null, COL_PERMISO_ID);
        bd.close();
        return seleccionarPermiso(cursor);
    }

    public Permiso seleccionarPermiso(Cursor cursor) {
        bd = abd.getReadableDatabase();
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        Permiso permiso = new Permiso();
        permiso.setPermisoId(cursor.getInt(NUM_COL_PERMISO_ID));
        permiso.setUsuario(cursor.getString(NUM_COL_USUARIO_ID));
        permiso.setRol(cursor.getString(NUM_COL_ROL_ID));
        cursor.close();
        bd.close();
        return permiso;
    }
    public ArrayList<Permiso> listarPermisos() {
        bd = abd.getReadableDatabase();
        Cursor cursor = bd.query(TABLA_PERMISOS, new String[] {
                COL_PERMISO_ID, COL_USUARIO_ID, COL_ROL_ID
        }, null, null, null, null, COL_PERMISO_ID);

        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        ArrayList<Permiso> listaPermisos = new ArrayList<>();
        while (cursor.moveToNext()) {
            Permiso permiso = new Permiso();
            permiso.setPermisoId(cursor.getInt(NUM_COL_PERMISO_ID));
            permiso.setUsuario(cursor.getString(NUM_COL_USUARIO_ID));
            permiso.setRol(cursor.getString(NUM_COL_ROL_ID));
            listaPermisos.add(permiso);
        }
        cursor.close();
        bd.close();
        return listaPermisos;
    }

    public ArrayList<Usuario> cargarUsuarios() {
        bd = abd.getReadableDatabase();
        Cursor cursor = bd.rawQuery("select curso_id, nombre_curso from cursos", null);

        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        while (cursor.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setUsername(cursor.getString(1));
            listaUsuarios.add(usuario);
        }
        cursor.close();
        bd.close();
        return listaUsuarios;
    }

    public ArrayList<Rol> cargarRoles() {
        bd = abd.getReadableDatabase();
        Cursor cursor = bd.rawQuery("select rol_id, nombre_rol from roles", null);

        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        ArrayList<Rol> listaRoles = new ArrayList<>();
        while (cursor.moveToNext()) {
            Rol rol = new Rol();
            rol.setNombreRol(cursor.getString(1));
            listaRoles.add(rol);
        }
        cursor.close();
        bd.close();
        return listaRoles;
    }

    public boolean isPermisoExists(String usuario, String rol) {
        String[] columns = {COL_USUARIO_ID, COL_ROL_ID};

        //Selection
        String selection = COL_USUARIO_ID + " = ? and " + COL_ROL_ID + " = ? ";

        //Selection Args
        String[] selection_Args = {usuario, rol};

        bd = abd.getReadableDatabase();
        //Query
        Cursor cursor = bd.query(TABLA_PERMISOS,
                columns,
                selection,
                selection_Args,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            return true;

        }
        cursor.close();
        bd.close();
        return false;
    }
}
