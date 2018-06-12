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
    private static final String TB_TURMAS = "TB_TURMAS";
    private static final String TB_ALUNOS = "TB_ALUNOS";

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

    public boolean validaSenha(String email, String senha) {
        openDB();
        String validaSenha = "SELECT * FROM " + TB_DOCENTE + " WHERE email = '" + email + "' AND senha = '" + senha + "'";

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
    public boolean checaEmail(String email) {
        openDB();
        String checaEmail = "SELECT EMAIL FROM " + TB_DOCENTE + " WHERE EMAIL = '" + email + "'";

          try{
            Cursor c = db.rawQuery(checaEmail, null);
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


    public String selectNome (String email) {
        openDB();
        String selectNome = "SELECT NOME FROM " + TB_DOCENTE + " WHERE EMAIL = '" + email + "'";
        String nome;

        try{
            Cursor c = db.rawQuery(selectNome, null);

            System.out.println("Query de validação executada");
            if (c.moveToFirst()) {
                nome = c.getString(0);
                return nome;
            } else {
                return "Erro ao carregar Nome";
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro ao executar query de validação");
            return "0";
        } finally {
            db.close();
        }
    }

    //funcao para ler turmas
    public void selectNomeTurmas(ArrayList turma, ArrayList universidade, ArrayList campus) {
        openDB();
        String selectNomeTurmas =  "SELECT NOME_TURMA, UNIVERSIDADE_TURMA, CAMPUS_TURMA FROM " + TB_TURMAS;
        try {

            Cursor c = db.rawQuery(selectNomeTurmas, null);

            if (c != null ) {
                if  (c.moveToFirst()) {
                    do {
                        String nome = c.getString(c.getColumnIndex("NOME_TURMA"));
                        String uni  = c.getString(c.getColumnIndex("UNIVERSIDADE_TURMA"));
                        String camp = c.getString(c.getColumnIndex("CAMPUS_TURMA"));
                        turma.add(nome);
                        universidade.add(uni);
                        campus.add(camp);
                    }while (c.moveToNext());
                }
            }
        } catch (Exception e ) {
            e.printStackTrace();
            System.out.println("Erro ao executar a query");
        } finally {
            db.close();
        }

    }

    public void selectNomeAlunos(ArrayList aluno, ArrayList raAluno, ArrayList turmaAluno) {
        openDB();
        String selectNomeAlunos =  "SELECT RA_ALUNO, NOME_ALUNO, TURMA_ALUNO FROM " + TB_ALUNOS;
        try {

            Cursor c = db.rawQuery(selectNomeAlunos, null);

            if (c != null ) {
                if  (c.moveToFirst()) {
                    do {
                        String ra  = c.getString(c.getColumnIndex("RA_ALUNO"));
                        String nome = c.getString(c.getColumnIndex("NOME_ALUNO"));
                        String turma = c.getString(c.getColumnIndex("TURMA_ALUNO"));
                        raAluno.add(ra);
                        aluno.add(nome);
                        turmaAluno.add(turma);
                    }while (c.moveToNext());
                }
            }
        } catch (Exception e ) {
            e.printStackTrace();
            System.out.println("Erro ao executar a query");
        } finally {
            db.close();
        }

    }





//  Método para retornar um arraylist dos usuários cadastrados no banco

//    public ArrayList<Docente> getDocentes(){
//        openDB();
//        ArrayList<Docente> dArray = new ArrayList<>();
//        String getDocentes = "SELECT * FROM " + TB_DOCENTE;
//
//        try{
//            Cursor c = db.rawQuery(getDocentes, null);
//
//            if (c.moveToFirst()){
//                do {
//                    Docente d = new Docente();
//                    d.setId(c.getInt(0));
//                    d.setNome(c.getString(1));
//                    d.setEmail(c.getString(2));
//                    d.setSenha(c.getString(3));
//                    dArray.add(d);
//                } while(c.moveToNext());
//            }
//
//        } catch (Exception e){
//            e.printStackTrace();
//            return null;
//        } finally {
//            db.close();
//        }
//
//        return dArray;
//    }

    private void openDB() {
     if(!db.isOpen()){
         db = mContext.openOrCreateDatabase(DB_PATH, SQLiteDatabase.OPEN_READWRITE, null);
     }

    }
}