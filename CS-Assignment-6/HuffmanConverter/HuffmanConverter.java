import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;

// A Huffman Converter class which takes a text file and converts it
// to an array of characters and their frequencies to be converted into 
// a Huffman Tree and printed as well as comparing it to it's original
// text written in ASCII to see how many bits are used
public class HuffmanConverter {

  // The # of chars in the ASCII table dictates
  // the size of the count[] & code[] arrays.
  public static final int NUMBER_OF_CHARACTERS = 256;
  
  // the contents of our message...
  private final String contents;
  
  // the tree created from the msg
  private HuffmanTree huffmanTree;
  
  // tracks how often each character occurs
  private final int[] count;
  
  // the huffman code for each character
  private final String[] code;
  
  // stores the # of unique chars in contents
  private int uniqueChars = 0;
  
  /** Constructor taking input String to be converted */
  public HuffmanConverter(String input) {
    this.contents = input;
    this.count = new int[NUMBER_OF_CHARACTERS];
    this.code = new String[NUMBER_OF_CHARACTERS];
  }
  
  /**
  * Records the frequencies that each character of our
  * message occurs...
  * I.e., we use 'contents' to fill up the count[] list...
  */

  public void recordFrequencies() {
    char[] characters = new char[contents.length()];
    for (int i = 0; i < contents.length(); i++) {
      characters[i] = contents.charAt(i);
    }
    for (int i=0; i<characters.length; i++){
      count[(int)characters[i]] = 1;
      uniqueChars++;
      for (int j=i+1; j<characters.length; j++) {
        if(characters[i] == '0'){
          System.out.print("");
          //Do nothing
        }
        else if (characters[i] == characters[j]) {
          count[(int)characters[i]]++;
          characters[j] = '0';
          uniqueChars--;
        }
      }
    }
  }
  
  /**
  * Converts our frequency list into a Huffman Tree. We do this by
  * taking our count[] list of frequencies, and creating a binary
  * heap in a manner similar to how a heap was made in HuffmanTree's
  * fileToHeap method. Then, we print the heap, and make a call to
  * HuffmanTree.heapToTree() method to get our much desired
  * HuffmanTree object, which we store as huffmanTree.
  */
  public void frequenciesToTree() {
    HuffmanNode[] huffArray = new HuffmanNode[uniqueChars];
    int counter = 0;
    for(int i=0; i<count.length;i++) {
      if (count[i] == 0 || (char)i == '0') {
        System.out.print("");
        //Do nothing
      }
      else {
        HuffmanNode node = new HuffmanNode(Character.toString((char)i), count[i]);
        huffArray[counter] = node;
        counter++;
      }
    }
    BinaryHeap<HuffmanNode> bheap = new BinaryHeap<>(huffArray);
    bheap.printHeap();
    System.out.println();
    huffmanTree = HuffmanTree.createFromHeap(bheap);
    huffmanTree.printLegend();
    System.out.println();
    System.out.println();
  }
  
  /**
  * Iterates over the huffmanTree to get the code for each letter.
  * The code for letter i gets stored as code[i]... This method
  * behaves similarly to HuffmanTree's printLegend() method...
  * Warning: Don't forget to initialize each code[i] to ""
  * BEFORE calling the recursive version of treeToCode...
  */
  public void treeToCode() {
    Arrays.fill(code, "");
    treeToCode(huffmanTree.root, "");
  }
  
  /*
  * A private method to iterate over a HuffmanNode t using s, which
  * contains what we know of the HuffmanCode up to node t. This is
  * called by treeToCode(), and resembles the recursive printLegend
  * method in the HuffmanTree class. Note that when t is a leaf node,
  * t's letter tells us which index i to access in code[], and tells
  * us what to set code[i] to...
  */

  private void treeToCode(HuffmanNode t, String s) {
    if(t.letter.length() > 1) {
      treeToCode(t.right, s + "0");
      treeToCode(t.left, s + "1");
    }
    else {
      code[(int)t.letter.charAt(0)] = s;
    }
  }
  
  /**
  * Using the message stored in contents, and the huffman conversions
  * stored in code[], we create the Huffman encoding for our message
  * (a String of 0's and 1's), and return it...
  */
  public String encodeMessage() {
    StringBuilder enMsg = new StringBuilder();
    char[] characters = new char[contents.length()];
    for (int i = 0; i < contents.length(); i++) {
      characters[i] = contents.charAt(i);
    }
    for (char character : characters) {
      enMsg.append(code[(int) character]);
    }
    return enMsg.toString();
  }
  
  /**
  * Reads in the contents of the file named filename and returns
  * it as a String. The main method calls this method on args[0]...
  */
  public static String readContents(String filename) {
    File imFile = new File(filename);
    StringBuilder reCont = new StringBuilder();
    try {
      Scanner reader = new Scanner(imFile);
      while(reader.hasNextLine()) {
        String temp = reader.nextLine();
        reCont.append(temp);
        reCont.append("\n");
      }
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return reCont.toString();
  }
  
  /**
  * Using the encoded String argument, and the huffman codings,
  * re-create the original message from our
  * huffman encoding and return it...
  */
  public String decodeMessage(String encodedStr) {
    StringBuilder msg = new StringBuilder();
    for (int i=0; i<encodedStr.length();) {
      HuffmanNode walk = huffmanTree.root;
      while(walk.left != null && walk.right != null) {
        if (encodedStr.charAt(i) == '1') {
          walk = walk.left;
        }
        else {
          walk = walk.right;
        }
        i++;
      }
      msg.append(walk.letter);
    }
    return msg.toString();
  }
  
  /**
  * Uses args[0] as the filename, and reads in its contents. Then
  * instantiates a HuffmanConverter object, using its methods to
  * obtain our results and print the necessary output. Finally,
  * decode the message and compare it to the input file.<p>
  * NOTE: Example method provided below...
  */
  public static void main(String[] args) {
    //Run with file you want to use as command line arguments
    String fileName = args[0];
    HuffmanConverter huffCon = new HuffmanConverter(readContents(fileName));
    huffCon.recordFrequencies();
    huffCon.frequenciesToTree();
    huffCon.treeToCode();
    System.out.println("Huffman Encoding:");
    String encoded = huffCon.encodeMessage();
    System.out.println(encoded);
    System.out.println();
    System.out.println("Message size in ASCII encoding: " + huffCon.contents.length()*8);
    System.out.println("Message size in Huffman coding: " + encoded.length() );
    System.out.println();
    System.out.println(huffCon.decodeMessage(encoded));
  }
  
}