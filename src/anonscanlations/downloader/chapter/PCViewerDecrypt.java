/*
 * Coded by /a/non, for /a/non
 */

package anonscanlations.downloader.chapter;

import java.io.*;

/**
 *
 * @author Nagato and /a/non
 */
public class PCViewerDecrypt
{
    public static void decrypt(byte[] data)
    {
        for(int i = 0; i < data.length - 4; i++)
        {
            if(i == (data.length - 5))
                data[i] = (byte)(data[i] ^ (data[i + 1]
                                        ^ data[i + 2]
                                        ^ data[i + 3]
                                        ^ data[i + 4]));
            else
                data[i] = (byte)(data[i] ^ data[i + 1]);
        }
    }

    public static void encrypt(byte[] data)
    {
        data[data.length - 5] = (byte)(data[data.length - 5]
                                        ^ (data[data.length - 4]
                                            ^ data[data.length - 3]
                                            ^ data[data.length - 2]
                                            ^ data[data.length - 1]));
        for(int i = data.length - 6; i >= 0; i--)
        {
            data[i] = (byte)(data[i] ^ data[i + 1]);
        }
    }

    public static void decryptFile(File inputFile, File outputFile) throws IOException
    {
        FileInputStream fis = new FileInputStream(inputFile);
        FileOutputStream fos = new FileOutputStream(outputFile);

        long length = inputFile.length();

        byte[] data = new byte[(int)length];
        int offset = 0, read = 0;
        while(offset < data.length
               && (read = fis.read(data, offset, data.length - offset)) >= 0)
        {
            offset += read;
        }
        fis.close();

        decrypt(data);

        fos.write(data, 8, (int)(length - 8));
        fos.close();
    }

    public static void encryptFile(File inputFile, File outputFile) throws IOException
    {
        FileInputStream fis = new FileInputStream(inputFile);
        FileOutputStream fos = new FileOutputStream(outputFile);

        long length = inputFile.length();

        byte[] data = new byte[(int)length];
        int offset = 0, read = 0;
        while(offset < data.length
               && (read = fis.read(data, offset, data.length - offset)) >= 0)
        {
            offset += read;
        }
        fis.close();

        encrypt(data);

        fos.write(data, 8, (int)(length - 8));
        fos.close();
    }
}
