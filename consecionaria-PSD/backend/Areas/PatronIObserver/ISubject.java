package backend.Areas.PatronIObserver;

public interface ISubject {
    void registrar(IAreaObserver observer);
    void eliminar(IAreaObserver observer);
    void notificar();
}
