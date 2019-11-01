package com.xiewz;

import com.xiewz.handle.ImageConverter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String filePath = "C:\\Users\\xiewenzhuang\\Desktop\\北京分银行.jpg";
        ImageConverter instance = ImageConverter.getInstance();

        String base64 = instance.ImgToBase64(filePath);

        System.out.println(base64);

    }
}
