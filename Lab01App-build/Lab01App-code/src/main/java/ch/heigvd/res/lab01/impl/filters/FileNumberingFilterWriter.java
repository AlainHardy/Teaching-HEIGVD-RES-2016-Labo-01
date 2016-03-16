package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import ch.heigvd.res.lab01.impl.Utils;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    private int counter = 0; // total count of line
    private boolean previousCharWasBsR = true; // used to process the \r\n chain
    private boolean isItFristWrite = true; // used to know if it is the first time writing in the file

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for(int i = 0; i < len; i++) {
        this.write(cbuf[i+off]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    // If it is the first time the writer writes in the file or if the previous character was \r the actual one is not \n
    // then write a new number with a tabulation
    if((previousCharWasBsR && c != '\n') || isItFristWrite) {
      isItFristWrite = false;
      previousCharWasBsR = false;
      out.write(++counter+"\t");
    }

    out.write(c);

    // If the actual character, set a variable to true to remember the \r, in order to check later if the next
    // character is \n, and if not displaying a new number
    if(c == '\r') {
      previousCharWasBsR = true;
    }
    // If it is the character of carriage return, then prepare a new line with a number
    else if (c == '\n'){
      out.write(++counter+"\t");
      previousCharWasBsR = false;
    }
  }

}
