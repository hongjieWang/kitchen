package com.kitchen.sdk.log4j;

import com.kitchen.sdk.log4j.spi.ThrowableRenderer;

import java.io.*;
import java.util.ArrayList;

public final class DefaultThrowableRenderer implements ThrowableRenderer {
    public String[] doRender(Throwable throwable) {
        return render(throwable);
    }

    public static String[] render(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
        } catch (RuntimeException ex) {
        }
        pw.flush();
        LineNumberReader reader = new LineNumberReader(new StringReader(sw.toString()));
        ArrayList lines = new ArrayList();
        try {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {
            if (ex instanceof java.io.InterruptedIOException)
                Thread.currentThread().interrupt();
            lines.add(ex.toString());
        }
        String[] tempRep = new String[lines.size()];
        lines.toArray(tempRep);
        return tempRep;
    }
}
