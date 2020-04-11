package pro.sisit.adapter.impl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import pro.sisit.adapter.IOAdapter;

public abstract class CSVAdapter<T> implements IOAdapter<T> {

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
        line = getLineForRead(index, countLine);
        String[] parametrs;
        String splitElement = ";";
        parametrs = line.split(splitElement);
        return (T) fileReaderCommon(parametrs);
    }

    private String getLineForRead(int index, int countLine) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (countLine == index) {
                reader.reset();
                break;
            } else {
                countLine += 1;
            }
        }
        return line;
    }

    public abstract T fileReaderCommon(String[] parametrs);

    @Override
    public int append(T entity) throws IOException {
        String line = convertString(entity);
        writer.write(line);
        writer.newLine();
        writer.flush();
        int index = 0;
        return getLineForWrite(index);
    }

    private int getLineForWrite(int index) throws IOException {
        while ((reader.readLine()) != null) {
            index += 1;
        }
        return index - 1;
    }

    public abstract String convertString(T entity);
}
