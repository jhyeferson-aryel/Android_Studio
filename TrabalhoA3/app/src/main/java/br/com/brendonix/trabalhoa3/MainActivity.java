package br.com.brendonix.trabalhoa3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.brendonix.model.Album;

public class MainActivity extends AppCompatActivity {

    TextView txtResult = null;
    Button btnObtRst = null, btnSlvSql = null, btnExcluir;

    AlbumDAO albumDAO = null;
    ArrayList<Album> albuns = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtResult = findViewById(R.id.txtResult);
        this.btnObtRst = findViewById(R.id.btnObtRst);
        this.btnSlvSql = findViewById(R.id.btnSlvSql);
        this.btnExcluir = findViewById(R.id.btnExcluir);

        this.txtResult.setMovementMethod(new ScrollingMovementMethod());

        this.albumDAO = new AlbumDAO(this);

        this.albuns = this.albumDAO.getAllAlbums();

        if (this.albuns == null || this.albuns.size() == 0) {
            this.txtResult.setText("Sem resultados no SQLite.\nConsultar API.");
        } else {
            this.mostrarAlbums();
        }

        this.btnObtRst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterAlbums();
            }
        });

        this.btnSlvSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (albuns != null && albuns.size() > 0) {
                    // Adicionando album por album.
                    for (Album album : albuns) albumDAO.addAlbum(album);
                    Toast.makeText(getBaseContext(), "Registros salvos no SQLite!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Não há registros para serem salvos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                albumDAO.deleteAllAlbums();
                Toast.makeText(getBaseContext(), "Registros excluídos do SQLite!", Toast.LENGTH_SHORT).show();
                txtResult.setText("Sem resultados no SQLite.\nConsultar API.");
                txtResult.scrollTo(0,0);
            }
        });
    }

    private void obterAlbums() {

        this.albumDAO = albumDAO == null ? new AlbumDAO(this) : albumDAO;

        this.albuns = albumDAO.getAllAlbums();

        Toast.makeText(getBaseContext(), "Obtendo Albuns da API!", Toast.LENGTH_SHORT).show();

        if (this.albuns == null || this.albuns.size() == 0) {
            ApiHelper apiHelper = new ApiHelper(getResources().getString(R.string.public_api));
            apiHelper.loadResult(new Runnable() {
                @Override
                public void run() {
                    albuns = Statics.albums;
                    mostrarAlbums();
                }
            });
        }
    }

    public void mostrarAlbums() {

        // Construtor de String.
        StringBuilder builder = new StringBuilder();

        if (this.albuns != null && this.albuns.size() > 0) {
            // Percorrendo albuns.
            for (Album album : this.albuns) {
                builder.append(String.format("ID: %s\n", album.getId()));
                builder.append(String.format("UserID: %s\n", album.getUserId()));
                builder.append(String.format("Title: %s\n", album.getTitle()));
                builder.append("---------------------------\n\n");
            }
        } else {
            builder.append("Sem resultados no SQLite.\nConsultar API.");
        }

        // Mostrando no TextView.
        this.txtResult.setText(builder.toString());
    }


}
