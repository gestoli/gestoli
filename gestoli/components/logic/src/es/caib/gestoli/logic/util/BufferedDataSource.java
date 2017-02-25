package es.caib.gestoli.logic.util;

import javax.activation.DataSource;
import java.io.*;

public class BufferedDataSource implements DataSource {


    private byte[] _data;
    private String _name;
    private String _type;


    public BufferedDataSource(byte[] data, String name, String type) {

        _data = data;
        _name = name;
        _type = type;
    }

    public String getContentType() {

        return _type;

    }

    public InputStream getInputStream() throws IOException {

        return new ByteArrayInputStream(_data);

    }

    public String getName() {

        return _name;

    }

    public OutputStream getOutputStream() throws IOException {

        OutputStream out = new ByteArrayOutputStream();
        out.write(_data);
        return out;

    }
}