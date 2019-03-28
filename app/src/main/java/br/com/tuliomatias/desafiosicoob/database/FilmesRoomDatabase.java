package br.com.tuliomatias.desafiosicoob.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.tuliomatias.desafiosicoob.models.Filme;

@Database(entities = {Filme.class}, version = 1)
public abstract class FilmesRoomDatabase extends RoomDatabase {
    public abstract FilmesDao filmesDao();

    private static volatile FilmesRoomDatabase INSTANCE;


    public static FilmesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FilmesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FilmesRoomDatabase.class, "filmes_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
