package com.nsu.basics;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class ArchiveReader {
    static BufferedReader getBufferedReaderForCompressedFile(String filePath) throws FileNotFoundException, CompressorException {
        FileInputStream fin = new FileInputStream(filePath);
        BufferedInputStream bis = new BufferedInputStream(fin);
        CompressorInputStream input = new CompressorStreamFactory().createCompressorInputStream(bis);
        return new BufferedReader(new InputStreamReader(input));
    }
}
