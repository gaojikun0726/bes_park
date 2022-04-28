package org.ace.business.handler;

import org.ace.business.constant.JsonAttr;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author xiepufeng
 */
public class CrcUtil
{
    /**
     * 计算CRC16校验码
     *
     * @param bytes
     * @return
     */
    public static int getCRC(byte[] bytes)
    {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++)
        {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++)
            {
                if ((CRC & 0x00000001) != 0)
                {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        return CRC;
    }

    /**
     * 在消息中添加 crc 校验参数
     * @param msg
     * @return
     */
    public static String addVerifyCRC(String msg)
    {
        if (null == msg || msg.isEmpty())
        {
            return null;
        }
        msg = msg.substring(0, msg.length() - 1);
        byte[] msgByte;
        try
        {
            msgByte = msg.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
        int dataLen = msgByte.length;
        int crc = CrcUtil.getCRC(msgByte);
        StringBuilder sbMsg = new StringBuilder();
        sbMsg.append(msg)
                .append(',')
                .append('\"')
                .append(JsonAttr.DATA_LEN)
                .append('\"')
                .append(':')
                .append(dataLen)
                .append(',')
                .append('\"')
                .append(JsonAttr.CRC)
                .append('\"')
                .append(':')
                .append(crc)
                .append('}');

        return sbMsg.toString();
    }


    /**
     * 通过 crc 校验消息是否正确
     * @return boolean
     */
    public static boolean verifyCRC(String content)
    {
        if (null == content || content.isEmpty())
        {
            return false;
        }

        byte[] contentByte;
        try
        {
            contentByte = content.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return false;
        }

        int index = content.lastIndexOf("dataLen");

        if (index < 0)
        {
            return false;
        }

        int indexSplit = index - 2;

        String verifyPart;
        try
        {
            verifyPart = content.substring(indexSplit);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }



        String[] verifyPartSplit = verifyPart.split("\"|:|,|}");

        try
        {
            // 客户端传入的数据长度标志数
            int dataLen = Integer.valueOf(verifyPartSplit[4]);

            byte[] checkPartBytes = Arrays.copyOf(contentByte, dataLen);

            // 客户端传入 CRC 校验码标志数
            int remoteCRC = Integer.valueOf(verifyPartSplit[8]);

            // 根据数据生成的 CRC 校验码
            int localCRC = getCRC(checkPartBytes);

            if (remoteCRC != localCRC)
            {
                return false;
            }

        }catch (NumberFormatException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    /*public static boolean verifyCRC(String content)
    {
        if (null == content || content.isEmpty())
        {
            return false;
        }


        int index = content.lastIndexOf(JsonAttr.DATA_LEN);

        if (index < 0)
        {
            return false;
        }

        int indexSplit = index - 2;

        String checkPart;
        String verifyPart;
        try
        {
            checkPart = content.substring(0, indexSplit);
            verifyPart = content.substring(indexSplit);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        byte[] checkPartBytes = checkPart.getBytes();

        String[] verifyPartSplit = verifyPart.split("\"|:|,|}");

        try
        {
            // 客户端传入的数据长度标志数
            *//*int dataLen = Integer.valueOf(verifyPartSplit[4]);

            if (checkPartBytes.length != dataLen)
            {
                return false;
            }*//*

            // 客户端传入 CRC 校验码标志数
            int remoteCRC = Integer.valueOf(verifyPartSplit[8]);

            // 根据数据生成的 CRC 校验码
            int localCRC = CrcUtil.getCRC(checkPartBytes);

            if (remoteCRC != localCRC)
            {
                return false;
            }

        }catch (NumberFormatException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }*/
}
