package com.wz.springframework.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @package: com.wz.springframework.support
 * @Author:
 * @Date: 2018/5/26 0026 上午 1:59
 */
public  abstract class BaseReader {


    BaseReader baseReader;
    Properties pros = null;

    public BaseReader(String locations) {
        InputStream inputStream = null;

        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(locations.replace("classpath:", ""));
            pros = new Properties();
            pros.load(inputStream);
            // pros.getProperty("package");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream) inputStream.close();
            } catch (IOException e2) {
                e2.getStackTrace();
            }
        }
    }





}
