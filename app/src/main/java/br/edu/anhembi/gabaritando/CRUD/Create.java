package br.edu.anhembi.gabaritando.CRUD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Create extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB_GABARITANDO";
    private static final int DB_VERSION = 1;
    private static final String TB_DOCENTE = "TB_DOCENTE";

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

        String createTable = "CREATE TABLE IF NOT EXISTS " + TB_DOCENTE + " ("
                + "ID_USER INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "NOME TEXT, "
                + "EMAIL TEXT, "
                + "SENHA TEXT,"
                + "unique (EMAIL)) ";
        try {
            db.execSQL(createTable);
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