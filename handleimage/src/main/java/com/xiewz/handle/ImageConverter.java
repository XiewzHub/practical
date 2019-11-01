package com.xiewz.handle;


import com.xiewz.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Xiewz
 */
public class ImageConverter {

    private static ImageConverter converter = new ImageConverter();

    private ImageConverter() {
    }

    public static ImageConverter getInstance() {
        return converter;
    }

    public String ImgToBase64(String filePath) {
        if (filePath == null || filePath.length() <= 0) {
            return "";
        }

        File f = new File(filePath);
        InputStream in = null;
        int length = (int) f.length();
        byte[] a = new byte[length];
        try {
            in = new FileInputStream(f);
            in.read(a);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] split = filePath.split("[.]");
        String sufix = split[split.length - 1];
        String base64Str = ImageUtil.convertBase64StrForBytes(a);
        base64Str = String.format("data:image/%s;base64,%s", sufix, base64Str);

        return base64Str;
    }

}
