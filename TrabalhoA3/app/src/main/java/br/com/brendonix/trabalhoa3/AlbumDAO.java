package br.com.brendonix.trabalhoa3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.brendonix.model.Album;


public class AlbumDAO {
    // DataBase.
    private SQLiteDatabase db;
    // DataBaseHelper.
    private SQLiteHelper helper;

    // Tabela de albums.
    private static final String TABLE_ALBUM = SQLiteHelper.TABLE_ALBUM;

    // Colunas.
    private static final String KEY_ID = "id";
    private static final String KEY_USERID = "userId";
    private static final String KEY_TITLE = "title";
    private static final String[] COLUMNS = {KEY_ID, KEY_USERID, KEY_TITLE};

    // Instancia.
    public AlbumDAO(Context context) {
        this.helper = new SQLiteHelper(context);
        this.db = helper.openDatabase();
    }

    public long addAlbum(Album album) {

        ContentValues values = new ContentValues();

        values.put(KEY_USERID, album.getUserId());
        values.put(KEY_TITLE, album.getTitle());

        return this.db.insert(TABLE_ALBUM, null, values);
    }


    public Album getAlbum(Integer id) {

        Cursor cursor = this.db.query(TABLE_ALBUM, COLUMNS, KEY_ID + " = ? ", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Album album = new Album();
        album.setId(cursor.getInt(0));
        album.setUserId(cursor.getInt(1));
        album.setTitle(cursor.getString(2));

        return album;
    }

    public ArrayList<Album> getAllAlbums() {

        ArrayList<Album> albums = new ArrayList();

        Cursor cursor = this.db.rawQuery(String.format("SELECT * FROM %s", TABLE_ALBUM), null);

        if (cursor != null) {
            Album album = null;
            while (cursor.moveToNext()) {
                album = new Album();
                album.setId(cursor.getInt(0));
                album.setUserId(cursor.getInt(1));
                album.setTitle(cursor.getString(2));
                albums.add(album);
            }
            album = null;
            cursor = null;
        }

        return albums;
    }

    public void deleteAlbum(Album album) {
        this.db.delete(TABLE_ALBUM, KEY_ID + " = ? ", new String[]{String.valueOf(album.getId())});
    }

    public void deleteAllAlbums() {
        this.db.delete(TABLE_ALBUM, null, null);
    }

}
