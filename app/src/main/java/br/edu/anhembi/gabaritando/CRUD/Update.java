package br.edu.anhembi.gabaritando.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.edu.anhembi.gabaritando.Docente;

public class Update extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB_GABARITANDO";
    private static final int DB_VERSION = 1;
    private static final String TB_DOCENTE = "TB_DOCENTE";

    private static final String DB_PATH = "/data/user/0/br.edu.anhembi.gabaritando/database/DB_GABARITANDO";
    private Context mContext;
    private SQLiteDatabase db;


    public Update(Context context) {
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

    public boolean insertDocente (Docente d){
        openDB();
        try {
            ContentValues cv = new ContentValues();
            cv.put("NOME", d.getNome());
            cv.put("EMAIL", d.getEmail());
            cv.put("SENHA", d.getSenha());
            db.insert(TB_DOCENTE, null, cv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    public boolean updateDocente (Docente d){
        openDB();
        try {
            String where = "EMAIL = '" + d.getEmail() + "'";
            ContentValues cv = new ContentValues();
            cv.put("NOME", d.getNome());
            cv.put("EMAIL", d.getEmail());
            cv.put("SENHA", d.getSenha());
            db.update(TB_DOCENTE, cv, where, null);
            return true;
        } catch (Exception e) {
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
