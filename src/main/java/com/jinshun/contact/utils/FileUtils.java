package com.jinshun.contact.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtils extends org.apache.commons.io.FileUtils {
	
	public static String getFileSuffixName(String fileName) {
		int index = fileName.lastIndexOf(".");
		return index > -1 ? fileName.substring(index + 1, fileName.length()) : fileName;
	}
	
	public static String getFilePrefixName(String fileName) {
		fileName = getFileName(fileName);
		int index = fileName.lastIndexOf(".");
		return index > -1 ? fileName.substring(0, index) : fileName;
	}
	
	public static String getFileName(String filePath) {
		int index = filePath.lastIndexOf("/");
		if (index < 0) index = filePath.lastIndexOf("\\");
		return index > -1 ? filePath.substring(index + 1, filePath.length()) : filePath;
	}
	
	public static void uncompress(String inFile, String outFile) {
		FileInputStream in = null;

		try {
			in = new FileInputStream(inFile);
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			uncompress(bytes, outFile);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void uncompress(byte[] bytes, String outFile) {
		try {
			ZipInputStream zin = new ZipInputStream(new ByteArrayInputStream(bytes));
			BufferedInputStream bin = new BufferedInputStream(zin);
			File Fout = null;
			ZipEntry entry;
			while ((entry = zin.getNextEntry()) != null && !entry.isDirectory()) {
				Fout = new File(outFile, entry.getName());
				if (!Fout.exists()) {
					(new File(Fout.getParent())).mkdirs();
				}
				FileOutputStream out = new FileOutputStream(Fout);
				BufferedOutputStream bout = new BufferedOutputStream(out);
				int b;
				while ((b = bin.read()) != -1) {
					bout.write(b);
				}
				bout.close();
				out.close();
			}
			bin.close();
			zin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
