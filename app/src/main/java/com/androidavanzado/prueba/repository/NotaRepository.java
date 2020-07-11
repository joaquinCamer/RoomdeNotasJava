package com.androidavanzado.prueba.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.androidavanzado.prueba.room.NotaRoomDatabase;
import com.androidavanzado.prueba.room.NotaDao;
import com.androidavanzado.prueba.room.NotaEntity;

import java.util.List;

public class NotaRepository {
    private NotaDao notaDao;
    private LiveData<List<NotaEntity>> allNotas;
    private LiveData<List<NotaEntity>> allNotasFavoritas;

    public NotaRepository(Application application) {
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);
        notaDao = db.notaDao();
        allNotas = notaDao.getAll();
        allNotasFavoritas = notaDao.getAllFavoritas();
    }

    //metodo para obtener todas las notas del data base
    public LiveData<List<NotaEntity>> getAll() { return allNotas; }


    //metodo para obtener especficos previa marcacion con boleano
    public LiveData<List<NotaEntity>> getAllFavs() { return allNotasFavoritas; }



    // insertar nota nueva

    public void insert (NotaEntity nota) {
        new insertAsyncTask(notaDao).execute(nota);
    }

    private static class insertAsyncTask extends AsyncTask<NotaEntity, Void, Void> {
        private NotaDao notaDatoAsyncTask;

        insertAsyncTask(NotaDao dao) {
            notaDatoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDatoAsyncTask.insert(notaEntities[0]);
            return null;
        }
    }
//actualizar con nota nueva
    public void update (NotaEntity nota) {
        new updateAsyncTask(notaDao).execute(nota);
    }

    private static class updateAsyncTask extends AsyncTask<NotaEntity, Void, Void> {
        private NotaDao notaDatoAsyncTask;

        updateAsyncTask(NotaDao dao) {
            notaDatoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDatoAsyncTask.update(notaEntities[0]);
            return null;
        }
    }
}
