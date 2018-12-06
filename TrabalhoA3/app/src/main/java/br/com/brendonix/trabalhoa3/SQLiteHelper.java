package br.com.brendonix.trabalhoa3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    // Versão do DataBase.
    private static final int DATABASE_VERSION = 1;
    // Nome do DataBase
    private static final String DATABASE_NAME = "unipac.db";
    // Tabela de albums.
    public static final String TABLE_ALBUM = "album";

    public SQLiteHelper(Context context) {
        // Setando o contexto.
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase openDatabase() {
        // Abrindo o DataBase.
        return this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criando tabela de albums.
        db.execSQL(String.format("CREATE TABLE %s (id INTEGER PRIMARY KEY AUTOINCREMENT, userId INTEGER, title TEXT)", TABLE_ALBUM));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Deleta a tabela de albums.
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_ALBUM));
        // Executa oncreate
        this.onCreate(db);
    }
}
