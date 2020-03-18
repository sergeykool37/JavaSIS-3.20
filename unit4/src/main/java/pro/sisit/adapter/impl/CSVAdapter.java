package pro.sisit.adapter.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import pro.sisit.adapter.IOAdapter;
import pro.sisit.model.Author;
import pro.sisit.model.Book;

// 1. TODO: написать реализацию адаптера

public class CSVAdapter<T> implements IOAdapter<T> {

    private Class<T> entityType;
    private BufferedReader reader;
    private BufferedWriter writer;

    public CSVAdapter(Class<T> entityType, BufferedReader reader,
                      BufferedWriter writer) {

        this.entityType = entityType;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public T read(int index) throws IOException {
        int countLine = 0;
        String line;
        reader.mark(500);
        while ((line = reader.readLine()) != null) {
           // System.out.println("Index-->" + countLine);
           // System.out.println("Line-->" + line);
            if (countLine == index) {
                //System.out.println("---------------");
                reader.reset();
                break;
            } else {
                countLine += 1;
            }
        }

        String[] parametrs;
        String splitElement = ";";
       // System.out.println("Index-->" + countLine);
        //System.out.println("LINE--->" + line);
        parametrs = line.split(splitElement);
        if (parametrs.length == 4) {
            return (T) FileReadBook(parametrs);
        } else {
            return (T) FileReadAuthor(parametrs);
        }

    }

    private Author FileReadAuthor(String[] parametrs) {
        Author newAuthor = new Author(parametrs[0], parametrs[1]);

        return newAuthor;
    }

    private Book FileReadBook(String[] parametrs) {
        Book newBook = new Book(parametrs[0], parametrs[1], parametrs[2], parametrs[3]);
        return newBook;
    }

    @Override
    public int append(T entity) throws IOException {
        String line = entity.toString();
        writer.write(line);
        writer.newLine();
        writer.flush();
        int index = 0;
        while ((reader.readLine()) != null) {
            index += 1;
        }
            return index-1;

    }
}
