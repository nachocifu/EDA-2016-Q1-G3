package Persistence;


public interface Persistence<E> {

    public boolean save(E obj);

    public E load();

}
