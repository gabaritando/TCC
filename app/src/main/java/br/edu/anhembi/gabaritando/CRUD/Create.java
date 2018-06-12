package br.edu.anhembi.gabaritando.CRUD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Create extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB_GABARITANDO";
    private static final int DB_VERSION = 1;
    private static final String TB_DOCENTE = "TB_DOCENTE";
    private static final String TB_TURMAS = "TB_TURMAS";
    private static final String TB_ALUNOS = "TB_ALUNOS";

    private static final String DB_PATH = "/data/user/0/br.edu.anhembi.gabaritando/database/DB_GABARITANDO";
    private Context mContext;
    private SQLiteDatabase db;


    public Create(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
        db = getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean createTable () {

        openDB();

        String createTableDocente = "CREATE TABLE IF NOT EXISTS " + TB_DOCENTE + " ("
                + "ID_USER INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "NOME TEXT, "
                + "EMAIL TEXT, "
                + "SENHA TEXT,"
                + "unique (EMAIL)) ";
        String createTableTurmas = "CREATE TABLE IF NOT EXISTS " + TB_TURMAS + " ("
                + "ID_TURMA INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "NOME_TURMA TEXT, "
                + "UNIVERSIDADE_TURMA TEXT, "
                + "CAMPUS_TURMA TEXT)";
        String createTableAlunos = "CREATE TABLE IF NOT EXISTS " + TB_ALUNOS + " ("
                + "ID_ALUNO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "NOME_ALUNO TEXT, "
                + "TURMA_ALUNO TEXT)";
        try {
            db.execSQL(createTableDocente);
            db.execSQL(createTableTurmas);
            db.execSQL(createTableAlunos);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao criar a database");
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    private void openDB() {
     if(!db.isOpen()){
         db = mContext.openOrCreateDatabase(DB_PATH, SQLiteDatabase.OPEN_READWRITE, null);
     }

    }
}