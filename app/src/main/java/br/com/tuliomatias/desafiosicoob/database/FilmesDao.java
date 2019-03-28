package br.com.tuliomatias.desafiosicoob.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.tuliomatias.desafiosicoob.models.Filme;

@Dao
public interface FilmesDao {
    @Insert
    void insert(Filme filme);

    @Query("DELETE FROM FILMES")
    void deleteAll();

    @Query("SELECT * from FILMES")
    List<Filme> getAllWords();

    @Delete
    void delete(Filme filme);

}
