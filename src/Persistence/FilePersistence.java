package Persistence;


import java.io.*;

public class FilePersistence<E> implements Persistence<E> {

    //File used for saving by this implementation of Persistance
    private String FILE = "src/Persistence/filePersistance.ser";

    @Override
    public Boolean save(E obj) {

        try {
            FileOutputStream fileOut = new FileOutputStream(FILE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            return true;
        } catch ( FileNotFoundException i ) {
            return false;
        } catch( IOException i ) {
            return false;
        }

    }

    @Override
    public E load() {
        E e = null;
        try
        {
            FileInputStream fileIn = new FileInputStream(FILE);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (E) in.readObject();
            in.close();
            fileIn.close();
            return e;
        }catch(IOException i) {
            return e;
        }catch(ClassNotFoundException c) {
            return e;
        }
    }

}
