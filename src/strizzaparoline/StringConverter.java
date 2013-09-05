/*
 * StringConverter.java
 *
 * Created on 1 marzo 2007, 14.30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package strizzaparoline;

import java.io.*;
import java.nio.charset.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 *
 * @author ferrara
 */
public class StringConverter {
  Charset charset;
  CharsetDecoder decoder;
  CharsetEncoder encoder;

  public StringConverter(String selectedCharset) {
    charset = Charset.forName(selectedCharset);
    decoder = charset.newDecoder();
    encoder = charset.newEncoder();
  }

  public String encode(String in_str) throws CharacterCodingException {
    return encode(in_str, in_str.length());
  }

  public String encode(String in_str, int maxDim) throws
      CharacterCodingException {
    String out_str = null;
    ByteBuffer buffer_out;
    CharBuffer buffer_in = CharBuffer.wrap(in_str);
    byte[] b = new byte[maxDim];

    int dim = in_str.length();
    if (dim > maxDim) {
      dim = maxDim;

    }

    buffer_out = encoder.encode(buffer_in);

    for (int i = 0; i < dim; i++) {
      b[i] = buffer_out.get(i);
    }

    out_str = new String(b, 0, maxDim);

    return out_str;
  }

  public static void main(String[] args) {
    String str_in = new String("sono una stringa");
    String str_out;
    StringConverter converter = new StringConverter("UTF-8");
    

    System.out.println("La stringa non converita e': " + str_in);
    try {
      str_out = converter.encode(str_in, 50);
    }
    catch (CharacterCodingException e) {
      System.out.println("ERRORE");
      str_out = null;
    }

    System.out.println("La stringa converita e': " + str_out);

  }
}
