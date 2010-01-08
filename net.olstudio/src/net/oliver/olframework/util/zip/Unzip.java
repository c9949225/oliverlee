package net.oliver.olframework.util.zip;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {
	public static void main(String[] args) {
		String unzipfile = "d:\\test\\test.zip"; //解压缩的文件名
		
		try {
			File olddirec = new File(unzipfile); //解压缩的文件路径(为了获取路径)
			ZipInputStream zin = new ZipInputStream(new FileInputStream(unzipfile));
			
			ZipEntry entry;
			//创建文件夹
			while ((entry = zin.getNextEntry()) != null) {
				
				if (entry.isDirectory()) {
					File directory = new File(olddirec.getParent(), entry.getName());
					if (!directory.exists())
						if (!directory.mkdirs())
							System.exit(0);
					zin.closeEntry();
				}
				
				if (!entry.isDirectory()) {
					File myFile = new File(entry.getName());
					FileOutputStream fout = new FileOutputStream("d:\\test\\" + myFile.getPath());
					DataOutputStream dout = new DataOutputStream(fout);
					byte[] b = new byte[1024];
					int len = 0;
					while ((len = zin.read(b)) != -1) {
						dout.write(b, 0, len);
					}
					dout.close();
					fout.close();
					zin.closeEntry();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
}
