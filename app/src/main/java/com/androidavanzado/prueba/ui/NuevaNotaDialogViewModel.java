package com.androidavanzado.prueba.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.androidavanzado.prueba.repository.NotaRepository;
import com.androidavanzado.prueba.room.NotaEntity;

import java.util.List;

public class NuevaNotaDialogViewModel extends AndroidViewModel {
    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository notaRepository;

    public NuevaNotaDialogViewModel(Application application) {
        super(application);

        notaRepository = new NotaRepository(application);
        allNotas = notaRepository.getAll();
    }

    // El fragment que necesita recibir la nueva lista de datos
    public LiveData<List<NotaEntity>> getAllNotas() { return allNotas; }

    // El fragment que inserte una nueva nota, deberá comunicarlo a este ViewModel
    public void insertarNota(NotaEntity nuevaNotaEntity) { notaRepository.insert(nuevaNotaEntity); }

    public void updateNota(NotaEntity notaEntity) { notaRepository.update(notaEntity); }
}
