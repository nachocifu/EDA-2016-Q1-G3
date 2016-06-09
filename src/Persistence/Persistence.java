package Persistence;


public interface Persistence<E> {

    Boolean save(E obj);

    E load();

}
