package Persistence;


import Parser.*;
import java.util.Collection;


public interface Persistence<E> {

    Boolean save(Collection<E> obj, Parser<E> parser);

    Collection<E> load(Parser<E> parser, GraphManager manager);

}
