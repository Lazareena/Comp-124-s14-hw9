package edu.macalester.comp124.textgenerator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Utilities for HW9
 * @author Shilad Sen
 */
public class Utils {

    public static String getText(String url) throws IOException {
        String[] parts = url.split("/");
        File file = new File(parts[parts.length - 1]);
        if (file.isFile()) {
            return readFileToString(file);
        }

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);
        String text = method.getResponseBodyAsString();
        writeStringToFile(text, file);
        return text;
    }

    private static String readFileToString(File file)  throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream input = new FileInputStream(file);
        byte[] buffer = new byte[65592];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return output.toString("utf-8");
    }

    private static void writeStringToFile(String text, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(text);
        writer.close();
    }
}
