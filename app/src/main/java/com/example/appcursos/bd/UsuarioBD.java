package com.example.appcursos.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.appcursos.modelos.Usuario;
import java.util.ArrayList;

public class UsuarioBD {

    private static final String DATABASE_NAME = "appcursos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLA_USUARIOS = "usuarios";
    private static final String COL_USUARIO_ID = "usuario_id";
    private static final int NUM_COL_USUARIO_ID = 0;
    private static final String COL_USUARIO_NAME = "username";
    private static final int NUM_COL_USUARIO_NAME = 1;
    private static final String COL_USUARIO_EMAIL = "email";
    private static final int NUM_COL_USUARIO_EMAIL = 2;
    private static final String COL_USUARIO_ROL = "rol";
    private static final int NUM_COL_USUARIO_ROL = 3;
    private static final String COL_USUARIO_PASSWORD = "password";
    private static final int NUM_COL_USUARIO_PASSWORD = 4;

    private SQLiteDatabase db;
    private AdminBD abd;

    public UsuarioBD(Context context) {
        abd = new AdminBD(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertarUsuario(Usuario usuario) {
        abd.escribirBD();
        ContentValues registro = new ContentValues();
        registro.put(COL_USUARIO_NAME, usuario.getUsername());
        registro.put(COL_USUARIO_EMAIL, usuario.getEmail());
        registro.put(COL_USUARIO_ROL, usuario.getRol());
        registro.put(COL_USUARIO_PASSWORD, usuario.getPassword());
        db.insert(TABLA_USUARIOS, null, registro);
        abd.cerrarBD();
    }
    public void editarUsuario(int id, Usuario usuario) {
        abd.escribirBD();
        ContentValues registro = new ContentValues();
        registro.put(COL_USUARIO_NAME, usuario.getUsername());
        registro.put(COL_USUARIO_EMAIL, usuario.getEmail());
        registro.put(COL_USUARIO_ROL, usuario.getRol());
        registro.put(COL_USUARIO_PASSWORD, usuario.getPassword());
        db.update(TABLA_USUARIOS, registro, COL_USUARIO_ID + "=" + id,null);
        abd.cerrarBD();
    }

    public void eliminarUsuario(String nombre) {
        abd.leerBD();
        db.delete(TABLA_USUARIOS, COL_USUARIO_NAME + "=" + nombre, null);
        abd.cerrarBD();
    }

    public Usuario buscarUsuario(String nombre) {
        Cursor cursor = db.query(TABLA_USUARIOS, new String[] {COL_USUARIO_ID, COL_USUARIO_NAME, COL_USUARIO_EMAIL, COL_USUARIO_ROL, COL_USUARIO_PASSWORD}, COL_USUARIO_NAME
                + " LIKE \"" + nombre + "\"", null, null, null, null, COL_USUARIO_NAME);
        return seleccionarUsuario(cursor);
    }

    private Usuario seleccionarUsuario(Cursor cursor) {
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(cursor.getInt(NUM_COL_USUARIO_ID));
        usuario.setUsername(cursor.getString(NUM_COL_USUARIO_NAME));
        usuario.setEmail(cursor.getString(NUM_COL_USUARIO_EMAIL));
        usuario.setRol(cursor.getString(NUM_COL_USUARIO_ROL));
        usuario.setPassword(cursor.getString(NUM_COL_USUARIO_PASSWORD));
        cursor.close();
        return usuario;
    }
    ArrayList<Usuario> listarUsuarios() {
        Cursor cursor = db.query(TABLA_USUARIOS, new String[] {
                COL_USUARIO_ID, COL_USUARIO_NAME, COL_USUARIO_EMAIL, COL_USUARIO_ROL, COL_USUARIO_PASSWORD
        }, null, null, null, null, COL_USUARIO_NAME);

        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        while (cursor.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(cursor.getInt(NUM_COL_USUARIO_ID));
            usuario.setUsername(cursor.getString(NUM_COL_USUARIO_NAME));
            usuario.setEmail(cursor.getString(NUM_COL_USUARIO_EMAIL));
            usuario.setRol(cursor.getString(NUM_COL_USUARIO_ROL));
            usuario.setPassword(cursor.getString(NUM_COL_USUARIO_PASSWORD));
            listaUsuario.add(usuario);
        }
        cursor.close();
        return listaUsuario;
    }

    public Usuario Authenticate(Usuario usuario) {

        // array of columns to fetch
        String[] columns = {
                COL_USUARIO_ID,
                COL_USUARIO_NAME,
                COL_USUARIO_EMAIL,
                COL_USUARIO_ROL,
                COL_USUARIO_PASSWORD
        };
        abd.leerBD();

        Cursor cursor = db.query(TABLA_USUARIOS, //Table to query
                columns,                    //columns to return
                null,                  //columns for the WHERE clause
                null,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            do {

                Usuario user = new Usuario(cursor.getString(cursor.getColumnIndex(COL_USUARIO_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_USUARIO_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_USUARIO_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(COL_USUARIO_ROL)),
                        cursor.getString(cursor.getColumnIndex(COL_USUARIO_PASSWORD)));

                if (user.getPassword().equalsIgnoreCase(usuario.getPassword()) && user.getEmail().equals(usuario.getEmail())) {
                    return user;

                }
            } while (cursor.moveToNext());

        }
        cursor.close();
        return null;
    }

    public boolean isEmailExists(String email) {
        // array of columns to fetch
        String[] columns = {COL_USUARIO_EMAIL};

        //Selection
        String selection = COL_USUARIO_EMAIL + " = ? ";

        //Selection Args
        String[] selection_Args = {email};

        //Query
        Cursor cursor = db.query(TABLA_USUARIOS,
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
        return false;
    }

}
