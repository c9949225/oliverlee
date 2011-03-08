package net.oliver.olframework.util.compress;

import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Try {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 * @throws DataFormatException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException, DataFormatException {
		// java.util.zip.Deflater & Inflater
		// demo from Sun:

		// Encode a String into bytes
		String inputString = "blahblahblah??sdfsdfsdfewrtertedtrhgdfhfghfgh";
		byte[] input = inputString.getBytes("iso-8859-1");

		// Compress the bytes
		byte[] output = new byte[100];
		Deflater compresser = new Deflater();
		compresser.setInput(input);
		compresser.finish();
		// ·µ»Ø‰º¿s
		int compressedDataLength = compresser.deflate(output);
		byte[] finalresult = new byte[compressedDataLength];
		System.arraycopy(output, 0, finalresult, 0, compressedDataLength);
		
		System.out.println(finalresult.length);
		
		// Decompress the bytes
		Inflater decompresser = new Inflater();
		decompresser.setInput(output, 0, compressedDataLength);
		byte[] result = new byte[100];
		int resultLength = decompresser.inflate(result);
		decompresser.end();
		System.out.println(resultLength);
		
		// Decode the bytes into a String
		String outputString = new String(result, 0, resultLength, "iso-8859-1");
		System.out.println(outputString);

	}

}
