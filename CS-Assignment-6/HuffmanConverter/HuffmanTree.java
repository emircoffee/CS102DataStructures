// Creates a Huffman Tree object which converts a heap of Huffman Nodes into a final tree and sets root to be the
// root of said tree
public class HuffmanTree {
  HuffmanNode root;

  // Constructor which takes the resulting node of turning a heap of nodes into a tree and setting the starting node to the root
  public HuffmanTree(HuffmanNode huff) {
    this.root = huff;
    
  }

  // Calls recursive method which will print The letter of a node and its values represented in binary
  public void printLegend() {
    printLegend(root, "");
  }

  // Recursive method which will continue to add 0s if it goes down a left child and 1s if it's a right child until it reaches
  // a node with just a letter and prints the full binary representation and the letter it equals
  private void printLegend(HuffmanNode t, String s) {
    if(t.letter.length() > 1) {
      printLegend(t.right, s + "0");
      printLegend(t.left, s + "1");
    }
    else {
      if (t.letter.equals("\n")) {
        System.out.print("'\\n'"+"="+s + " ");
      }
      else {
        System.out.print("'"+t.letter+"'"+"="+s + " ");
      }

    }
  }

  // Method which takes the legend string, parses it into a string array by splitting it on where the spaces of the string are,
  // then creates a binary heap of huffman node objects and converts each string and value pair into a node and inserts them
  // into the binary heap, returns the heap when finished
  public static BinaryHeap legendToHeap(String legend) {
    String[] parsed = legend.split("\\s+");
    BinaryHeap<HuffmanNode> legendHeap = new BinaryHeap<>(parsed.length/2 + 1);
    for(int i=0; i<parsed.length; i+=2){
      HuffmanNode node = new HuffmanNode(parsed[i], Integer.parseInt(parsed[i+1]));
      legendHeap.insert(node);
    }
    return legendHeap;
  }

  // Goes through the binary heap and removes the smallest values and sets them as the children of a new node which
  // is inserted back in, when finished the final node will be removed from the heap, a huffman tree will be constructed with
  // that node and returned
  public static HuffmanTree createFromHeap(BinaryHeap b) {
    while(b.getSize() > 1) {
      HuffmanNode node1 = (HuffmanNode)b.deleteMin();
      HuffmanNode node2 = (HuffmanNode)b.deleteMin();
      HuffmanNode nodeCombo = new HuffmanNode(node1, node2);
      b.insert(nodeCombo);
    }
    HuffmanNode finalNode = (HuffmanNode)b.deleteMin();
    return new HuffmanTree(finalNode);
  }

}