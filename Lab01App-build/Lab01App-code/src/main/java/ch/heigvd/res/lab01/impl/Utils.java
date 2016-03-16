package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments.
   *
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    int lineSepIndex = lines.length();
    int lineSepLength = 0;
    boolean foundSeparator = false;

    // Try to find separators in the string. If different separators exist in the file, it will take
    // the first that occurs. The orders of the "if" case is made to avoid to choose a "\n" that would be a part of a
    // "\r\n" or "\r" of a "\r\n"

    int tempIndex = lines.indexOf("\r\n");

    if( tempIndex != -1) {
        lineSepIndex = tempIndex;
        lineSepLength = 2;
        foundSeparator = true;
    }

    tempIndex= lines.indexOf('\n');

    if( tempIndex != -1 && tempIndex < lineSepIndex ) {
        lineSepIndex = tempIndex;
        lineSepLength = 1;
        foundSeparator = true;
    }

    tempIndex = lines.indexOf('\r');

    if( tempIndex != -1 && tempIndex < lineSepIndex ) {
        lineSepIndex = tempIndex;
        lineSepLength = 1;
        foundSeparator = true;
    }

    // If no separator has been found, then set lineSepIndex to 0, in order to return an empty first cell in stringArray
    if(!foundSeparator) {
        lineSepIndex = 0;
    }

    String[] stringArray = {lines.substring(0, lineSepIndex + lineSepLength),
                        lines.substring(lineSepIndex+lineSepLength)};

    return stringArray;
  }

}
