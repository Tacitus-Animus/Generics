package generics.CompareAndBounds.multipleBounds;

import java.io.Closeable;
import java.io.IOException;
import java.nio.CharBuffer;

public class ReadWrite {
	//The first bound is used for erasure; A Readable would be returned if this method was not void and T would be returned.
	//This is used for maintaining legacy code where Object was returned with non-generic code; 
	//Example being <T extends Object & Comparable<? super T>, T would be returned as Object, not Comparable.
	//Generics are implemented by erasure as said before;
	//it compiles in almost the same way as code written without.
	public static <S extends Readable & Closeable, T extends Appendable & Closeable> void copy(S source, T target, int size) throws IOException {
		try {
			var buffer = CharBuffer.allocate(size);
			int i = source.read(buffer); //source reads to the buffer.
			while(i >= 0) {
				buffer.flip(); //prepare buffer for writing
				target.append(buffer);
				buffer.clear(); //prepare buffer for reading
				i = source.read(buffer);
			}
		} finally {
			source.close();
			target.close();
		}
	}
	
}
