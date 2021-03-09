package com.example.appcursos.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.appcursos.modelos.Alumno;
import com.example.appcursos.modelos.Asignatura;

import java.util.ArrayList;

public class AlumnoBD {

    private static final String DATABASE_NAME = "appcursos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLA_ALUMNOS = "alumnos";
    private static final String COL_ALUMNO_ID = "alumno_id";
    private static final int NUM_COL_ALUMNO_ID = 0;
    private static final String COL_NOMBRE_ALUMNO = "nombre_alumno";
    private static final int NUM_COL_NOMBRE_ALUMNO = 1;
    private static final String COL_APELLIDOS_ALUMNO = "apellidos_alumno";
    private static final int NUM_COL_APELLIDOS_ALUMNO = 2;
    private static final String COL_DNI = "dni";
    private static final int NUM_COL_DNI = 3;
    private static final String COL_TELEFONO_ALUMNO = "telefono_alumno";
    private static final int NUM_COL_TELEFONO_ALUMNO = 4;
    private static final String COL_ASIGNATURA_ID = "asignatura_id";
    private static final int NUM_COL_ASIGNATURA_ID = 5;

    private SQLiteDatabase bd;
    private AdminBD abd;

    public AlumnoBD(Context context) {
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

    public SQLiteDatabase getBD() {
        return bd;
    }

    public void insertarAlumno(Alumno alumno) {
        bd = abd.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(COL_NOMBRE_ALUMNO, alumno.getNombreAlumno());
        registro.put(COL_APELLIDOS_ALUMNO, alumno.getApellidosAlumno());
        registro.put(COL_DNI, alumno.getDni());
        registro.put(COL_TELEFONO_ALUMNO, alumno.getTelefonoAlumno());
        registro.put(COL_ASIGNATURA_ID, alumno.getAsignatura());
        bd.insert(TABLA_ALUMNOS, null, registro);
        bd.close();
    }

    public int editarAlumno(String nombreAlumno, Alumno alumno) {
        bd = abd.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(COL_NOMBRE_ALUMNO, alumno.getNombreAlumno());
        registro.put(COL_APELLIDOS_ALUMNO, alumno.getApellidosAlumno());
        registro.put(COL_DNI, alumno.getDni());
        registro.put(COL_TELEFONO_ALUMNO, alumno.getTelefonoAlumno());
        registro.put(COL_ASIGNATURA_ID, alumno.getAsignatura());
        int res = bd.update(TABLA_ALUMNOS, registro, COL_NOMBRE_ALUMNO + "=" + nombreAlumno,null);
        bd.close();
        return res;
    }

    public int eliminarAlumno(int id) {
        bd = abd.getReadableDatabase();
        int res = bd.delete(TABLA_ALUMNOS, COL_ALUMNO_ID + "=" + id, null);
        bd.close();
        return res;
    }

    public Alumno buscarAlumno(String nombre) {
        bd = abd.getReadableDatabase();
        Cursor cursor = bd.query(TABLA_ALUMNOS, new String[] {COL_ALUMNO_ID, COL_NOMBRE_ALUMNO, COL_APELLIDOS_ALUMNO, COL_DNI, COL_TELEFONO_ALUMNO, COL_ASIGNATURA_ID}, COL_NOMBRE_ALUMNO
                + " LIKE \"" + nombre + "\"", null, null, null, null, COL_NOMBRE_ALUMNO);
        bd.close();
        return seleccionarAlumno(cursor);
    }

    public Alumno seleccionarAlumno(Cursor cursor) {
        bd = abd.getReadableDatabase();
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        Alumno alumno = new Alumno();
        alumno.setAlumnoId(cursor.getInt(NUM_COL_ALUMNO_ID));
        alumno.setNombreAlumno(cursor.getString(NUM_COL_NOMBRE_ALUMNO));
        alumno.setApellidosAlumno(cursor.getString(NUM_COL_APELLIDOS_ALUMNO));
        alumno.setDni(cursor.getString(NUM_COL_DNI));
        alumno.setTelefonoAlumno(cursor.getString(NUM_COL_TELEFONO_ALUMNO));
        cursor.close();
        bd.close();
        return alumno;
    }
    public ArrayList<Alumno> listarAlumno() {
        bd = abd.getReadableDatabase();
        Cursor cursor = bd.query(TABLA_ALUMNOS, new String[] {
                COL_ALUMNO_ID, COL_NOMBRE_ALUMNO, COL_APELLIDOS_ALUMNO, COL_DNI, COL_TELEFONO_ALUMNO, COL_ASIGNATURA_ID
        }, null, null, null, null, COL_ALUMNO_ID);

        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        ArrayList<Alumno> listaAlumno = new ArrayList<>();
        while (cursor.moveToNext()) {
            Alumno alumno = new Alumno();
            alumno.setAlumnoId(cursor.getInt(NUM_COL_ALUMNO_ID));
            alumno.setNombreAlumno(cursor.getString(NUM_COL_NOMBRE_ALUMNO));
            alumno.setApellidosAlumno(cursor.getString(NUM_COL_APELLIDOS_ALUMNO));
            alumno.setDni(cursor.getString(NUM_COL_DNI));
            alumno.setTelefonoAlumno(cursor.getString(NUM_COL_TELEFONO_ALUMNO));
            listaAlumno.add(alumno);
        }
        cursor.close();
        bd.close();
        return listaAlumno;
    }

    public ArrayList<Asignatura> cargarAsignaturas() {
        bd = abd.getReadableDatabase();
        Cursor cursor = bd.rawQuery("select asignatura_id, nombre_asignatura from asignaturas", null);

        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        ArrayList<Asignatura> listaAsignaturas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Asignatura asignatura = new Asignatura();
            asignatura.setAsignaturaId(cursor.getInt(0));
            asignatura.setNombreAsignatura(cursor.getString(1));
            listaAsignaturas.add(asignatura);
        }
        cursor.close();
        bd.close();
        return listaAsignaturas;
    }

    public boolean isAlumnoExists(String nombreAlumno) {
        String[] columns = {COL_NOMBRE_ALUMNO};

        //Selection
        String selection = COL_NOMBRE_ALUMNO + " = ? ";

        //Selection Args
        String[] selection_Args = {nombreAlumno};

        bd = abd.getReadableDatabase();
        //Query
        Cursor cursor = bd.query(TABLA_ALUMNOS,
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
