package com.example.ConcesionariaPDS.models.Areas.Observer;

public interface ISubject {
    void registrar(IAreaObserver observer);
    void eliminar(IAreaObserver observer);
    void notificar();
}
