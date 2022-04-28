package com.framework.common.utils;


import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author xiepufeng
 * @date 2020/11/19 15:47
 */
public class CompressUtil
{

    /**
     * 压缩 zip
     * @param sourcePathList
     * @param storePath
     * @param fileName
     * @return
     * @throws IOException
     */
    public static boolean zip(
            List<String> sourcePathList,
            String storePath,
            String fileName) throws IOException
    {
        if (fileName == null)
        {
            return false;
        }

        if (sourcePathList == null || sourcePathList.isEmpty())
        {
            return false;
        }

        if (storePath == null)
        {
            return false;
        }

        // 用于存放压缩文件的文件夹
        File compress = new File(storePath);
        // 如果文件夹不存在，进行创建
        if (!compress.isDirectory())
        {
            if (!compress.mkdirs())
            {
                return false;
            }

        }

        File generateFile = new File(compress.getAbsolutePath() + File.separator + fileName + ".zip");

        // 压缩输出流
        ZipOutputStream out;
        try
        {
            out = new ZipOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(generateFile)));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }

        boolean flag = false;

        for (String sourcePath : sourcePathList)
        {
            File sourceFile = new File(sourcePath);

            // 压缩文件的路径或者文件不存在
            if (!sourceFile.isFile())
            {
                continue;
            }

            flag = true;

            // 输入流
            BufferedInputStream in;
            try
            {
                in = new BufferedInputStream(new FileInputStream(sourceFile));
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
                continue;
            }

            // 标记要打包的条目
            try
            {
                out.putNextEntry(new ZipEntry(sourceFile.getName()));
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            // 进行写操作
            int len;
            byte[] bytes = new byte[1024];
            while ((len = in.read(bytes)) > 0)
            {
                try
                {
                    out.write(bytes, 0, len);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            // 关闭输入流
            try
            {
                in.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        // 关闭 输出流
        try
        {
            out.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        if (!flag)
        {
            generateFile.delete();
        }

        return flag;
    }
}
