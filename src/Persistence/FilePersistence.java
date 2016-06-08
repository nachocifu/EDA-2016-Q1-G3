package Persistence;


import java.io.*;

public class FilePersistence<E> implements Persistence<E> {

    //File used for saving by this implementation of Persistance
    private String FILE = "src/Storage/filePersistance.ser";

    @Override
    public boolean save(E obj) {

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
        }catch(IOException i) {
            return null;
        }catch(ClassNotFoundException c) {
            return null;
        }

        return e;
    }

}
