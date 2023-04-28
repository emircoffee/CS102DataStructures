// Creates a Huffman Node object which will hold information about the letter, and it's corresponding value.
// Can take up to two child nodes which are the left and right nodes and can compare nodes based on frequency
public class HuffmanNode implements Comparable {
  public String letter;
  public Double frequency;
  public HuffmanNode left, right;

  // Constructor which sets the letter and frequency of a node
  public HuffmanNode(String letter, Double frequency) {
    this.letter = letter;
    this.frequency = frequency;
    left = null;
    right = null;
  }

  // Constructor that sets children of the node and updates the data of this node to be summation of children nodes
  public HuffmanNode(HuffmanNode left, HuffmanNode right) {
    this.left = left;
    this.right = right;
    letter = left.letter + right.letter;
    frequency = left.frequency + right.frequency;
  }

// CompareTo class which compares two node objects based on frequency value
  public int compareTo(Object o) {
    HuffmanNode huff = (HuffmanNode) o;
    return this.frequency.compareTo(huff.frequency);
  }

  // toString method which prints the node like: <V, 1.0>
  public String toString() {
    return "<"+letter+", "+frequency+">";
  }
  
}