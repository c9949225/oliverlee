package net.oliver.olframework.util.filesystem.rar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.FileHeader;


/**
 * �ݲ�֧�� ѹ�������� Ŀ¼
 * 
 * @author u
 *
 */
public class JunRarUtil
{

    public JunRarUtil()
    {
    }

    public static void main(String args[])
    {
        String filename = "D:\\С��˽��settings.rar";
        File f = new File(filename);
        Archive a = null;
        try
        {
            a = new Archive(f);
        }
        catch(RarException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        if(a != null)
        {
//            a.getMainHeader().print();
        	int i =0;
            for(FileHeader fh = a.nextFileHeader(); fh != null; fh = a.nextFileHeader())
            {
                try
                {
                    File out = new File((new StringBuilder("D:\\testrar\\").append(fh.getFileNameString().trim()).toString()));
                    System.out.println(out.getAbsolutePath());
                    FileOutputStream os = new FileOutputStream(out);
                    a.extractFile(fh, os);
                    os.close();
                }
                catch(FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch(RarException e)
                {
                    e.printStackTrace();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
