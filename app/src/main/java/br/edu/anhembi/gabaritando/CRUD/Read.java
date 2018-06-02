package br.edu.anhembi.gabaritando.CRUD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import br.edu.anhembi.gabaritando.Docente;

public class Read extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB_GABARITANDO";
    private static final int DB_VERSION = 1;
    private static final String TB_DOCENTE = "TB_DOCENTE";

    private static final String DB_PATH = "/data/user/0/br.edu.anhembi.gabaritando/database/DB_GABARITANDO";
    private Context mContext;
    private SQLiteDatabase db;


    public Read(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
        db = getReadableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<Docente> getDocentes(){
        openDB();
        ArrayList<Docente> dArray = new ArrayList<>();
        String getDocentes = "SELECT * FROM " + TB_DOCENTE;

        try{
            Cursor c = db.rawQuery(getDocentes, null);

            if (c.moveToFirst()){
                do {
                    Docente d = new Docente();
                    d.setNome(c.getString(0));
                    d.setEmail(c.getString(1));
                    d.setSenha(c.getString(2));
                    dArray.add(d);
                } while(c.moveToNext());
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }

        return dArray;
    }

    public boolean validaSenha(String email, String senha) {
        openDB();
        String validaSenha = "SELECT * FROM " + TB_DOCENTE + " WHERE email = '" + email + "' AND senha = '" + senha + "'";
        System.out.println(validaSenha);

        try{
            Cursor c = db.rawQuery(validaSenha, null);
            System.out.println("Query de validação executada");
            if (c.getCount() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro ao executar query de validação");
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