package generics.CompareAndBounds.multipleBounds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

import fp.executeAroundMethod.FileWriterEAM;

public class TestReadWrite {

	public static void main(String[] args) throws IOException {
		
		fp.executeAroundMethod.FileWriterEAM.use("Source.txt", writer -> writer.writeStuff("Hello World"));
																					
		//Generics here permits classes to extends both readable, appendable, and closeable.
		//Most sub-classes implement both through a reader or writer; why not simplify it?
		//You can't rule out someone implementing readable/appendable/closeable, but not sub-classing a reader/writer.
		ReadWrite.copy(new FileReader("Source.txt"), new FileWriter(new File("Target0.txt")), 16);
		
		ReadWrite.copy(new BufferedReader(new FileReader("Source.txt")), new BufferedWriter(new FileWriter(new File("Target1.txt"))), 32);
		
		ReadWrite.copy(new LineNumberReader(new FileReader("Source.txt")), new FileWriter(new File("Target2.txt")), 64);
		
	}

}
