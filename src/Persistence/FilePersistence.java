package Persistence;


import Parser.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class FilePersistence<E> implements Persistence<E> {

    @Override
    public Boolean save(Collection<E> obj, Parser<E> parser) {
        BufferedWriter writer;
        try {

            writer = Files.newBufferedWriter(Paths.get(parser.getDEFAULT_SAVE()));

            for (E each : obj)
                writer.write(parser.saveFormat(each));

            writer.close();
            return true;
        } catch ( FileNotFoundException i ) {
            return false;
        } catch( IOException i ) {
            return false;
        }

    }

    @Override
    public Collection<E> load(Parser<E> parser, GraphManager manager) {
        List<E> e = new LinkedList<E>();
        BufferedReader reader;
        String line;
        String file = parser.getDEFAULT_SAVE();

        try {

            reader = Files.newBufferedReader(Paths.get(file));

            line = reader.readLine();
            while (line != null) {
                parser.parse(line.split("#"), manager);
                line = reader.readLine();
            }


            reader.close();
            return e;
        }catch(IOException i) {
            return e;
        }catch (Exception r) {
            return e;
        }
    }

}
