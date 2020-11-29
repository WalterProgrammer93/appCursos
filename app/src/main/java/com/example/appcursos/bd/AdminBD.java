package com.example.appcursos.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminBD  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "appcursos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLA_USUARIOS = "usuarios";
    private static final String COL_USUARIO_ID = "usuario_id";
    private static final String COL_USUARIO_NAME = "username";
    private static final String COL_USUARIO_EMAIL = "email";
    private static final String COL_USUARIO_ROL = "rol";
    private static final String COL_USUARIO_PASSWORD = "password";
    private static final String CREATE_TABLE_USUARIOS = "CREATE TABLE " + TABLA_USUARIOS + " (" +
            COL_USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_USUARIO_NAME + " TEXT NOT NULL, " +
            COL_USUARIO_EMAIL + " TEXT NOT NULL, "+
            COL_USUARIO_ROL +" TEXT NOT NULL, "+
            COL_USUARIO_PASSWORD + " TEXT NOT NULL);";

    private static final String TABLA_CURSOS = "cursos";
    private static final String COL_CURSO_ID = "curso_id";
    private static final String COL_NOMBRE_CURSO = "nombre";
    private static final String COL_CENTRO = "centro";
    private static final String COL_NUM_ALUMNOS = "numero_alumnos";
    private static final String COL_DISPONIBILIDAD = "disponibilidad";
    private static final String COL_TEMAS = "temas";
    private static final String CREATE_TABLE_CURSOS = "CREATE TABLE " + TABLA_CURSOS + " (" +
            COL_CURSO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOMBRE_CURSO + " TEXT NOT NULL, " +
            COL_CENTRO + " TEXT NOT NULL, " +
            COL_NUM_ALUMNOS + " TEXT NOT NULL, " +
            COL_DISPONIBILIDAD + " TEXT NOT NULL, " +
            COL_TEMAS + " TEXT NOT NULL);";

    private static final String TABLA_ASIGNATURAS = "asignaturas";
    private static final String COL_ASIGNATURA_ID = "asignatura_id";
    private static final String COL_NOMBRE_ASIGNATURA = "nombre_asignatura";
    private static final String COL_DESCRIPCION_ASIGNATURA = "descripcion_asignatura";
    private static final String CREATE_TABLE_ASIGNATURAS = "CREATE TABLE " + TABLA_ASIGNATURAS + " (" +
            COL_ASIGNATURA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOMBRE_ASIGNATURA + " TEXT NOT NULL, " +
            COL_DESCRIPCION_ASIGNATURA + " TEXT NOT NULL, " +
            COL_CURSO_ID + " INTEGER NOT NULL, FOREIGN KEY (" + COL_CURSO_ID + ") REFERENCES " + TABLA_CURSOS + " (" + COL_CURSO_ID + "));";

    private static final String TABLA_ALUMNOS = "alumnos";
    private static final String COL_ALUMNO_ID = "alumno_id";
    private static final String COL_NOMBRE_ALUMNO = "nombre_alumno";
    private static final String COL_APELLIDOS_ALUMNO = "apellidos_alumno";
    private static final String COL_DNI = "dni";
    private static final String COL_TELEFONO_ALUMNO = "telefono_alumno";
    private static final String CREATE_TABLE_ALUMNOS = "CREATE TABLE " + TABLA_ALUMNOS + " (" +
            COL_ALUMNO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOMBRE_ALUMNO + " TEXT NOT NULL, " +
            COL_APELLIDOS_ALUMNO + " TEXT NOT NULL, " +
            COL_DNI + " TEXT NOT NULL, " +
            COL_TELEFONO_ALUMNO + " TEXT NOT NULL, " +
            COL_ASIGNATURA_ID + " INTEGER NOT NULL, FOREIGN KEY (" +
            COL_ASIGNATURA_ID + ") REFERENCES " + TABLA_ASIGNATURAS + " (" + COL_ASIGNATURA_ID + "));";

    private static final String TABLA_PROFESORES = "profesores";
    private static final String COL_PROFESOR_ID = "profesor_id";
    private static final String COL_NOMBRE_PROFESOR = "nombre_profesor";
    private static final String COL_APELLIDOS_PROFESOR = "apellidos_profesor";
    private static final String COL_DEPARTAMENTO = "departamento";
    private static final String COL_TELEFONO_PROFESOR = "telefono_profesor";
    private static final String CREATE_TABLE_PROFESORES = "CREATE TABLE " + TABLA_PROFESORES + " (" +
            COL_PROFESOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOMBRE_PROFESOR + " TEXT NOT NULL, " +
            COL_APELLIDOS_PROFESOR + " TEXT NOT NULL, " +
            COL_DEPARTAMENTO + " TEXT NOT NULL, " +
            COL_TELEFONO_PROFESOR + " TEXT NOT NULL, " +
            COL_ASIGNATURA_ID + " INTEGER NOT NULL, FOREIGN KEY (" + COL_ASIGNATURA_ID + ") REFERENCES " + TABLA_ASIGNATURAS + " (" + COL_ASIGNATURA_ID + "));";

    private static final String DROP_TABLE_USUARIOS = "DROP TABLE IF EXISTS " + TABLA_USUARIOS;
    private static final String DROP_TABLE_CURSOS = "DROP TABLE IF EXISTS " + TABLA_CURSOS;
    private static final String DROP_TABLE_ASIGNATURAS = "DROP TABLE IF EXISTS " + TABLA_ASIGNATURAS;
    private static final String DROP_TABLE_ALUMNOS = "DROP TABLE IF EXISTS " + TABLA_ALUMNOS;
    private static final String DROP_TABLE_PROFESORES = "DROP TABLE IF EXISTS " + TABLA_PROFESORES;

    private SQLiteDatabase bd;

    public AdminBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(CREATE_TABLE_USUARIOS);
        bd.execSQL(CREATE_TABLE_CURSOS);
        bd.execSQL(CREATE_TABLE_ASIGNATURAS);
        bd.execSQL(CREATE_TABLE_ALUMNOS);
        bd.execSQL(CREATE_TABLE_PROFESORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        bd.execSQL(DROP_TABLE_USUARIOS);
        bd.execSQL(DROP_TABLE_CURSOS);
        bd.execSQL(DROP_TABLE_ASIGNATURAS);
        bd.execSQL(DROP_TABLE_ALUMNOS);
        bd.execSQL(DROP_TABLE_PROFESORES);
        onCreate(bd);
    }

    public void escribirBD() {
        bd  = this.getWritableDatabase();
    }

    public void leerBD() {
        bd = this.getReadableDatabase();
    }

    public void cerrarBD() {
        bd.close();
    }
}
