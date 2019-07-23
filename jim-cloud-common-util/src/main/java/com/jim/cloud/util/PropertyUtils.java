package com.jim.cloud.util;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties文件工具类
 *
 * @author jib
 * @date 2019/7/22 11:05
 */
public class PropertyUtils {

    /**
     * 读取properties文件
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static Properties readProperty(InputStream in) {
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
        return prop;
    }
}
