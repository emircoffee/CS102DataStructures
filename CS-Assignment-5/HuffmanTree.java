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
        printLegend(t.left, s + "0");
        printLegend(t.right, s + "1");
    }
    else {
      System.out.print(t.letter+"="+s);
      System.out.println();
    }
  }

  // Method which takes the legend string, parses it into a string array by splitting it on where the spaces of the string are,
  // then creates a binary heap of huffman node objects and converts each string and value pair into a node and inserts them
  // into the binary heap, returns the heap when finished
  public static BinaryHeap legendToHeap(String legend) {
    String[] parsed = legend.split("\\s+");
    BinaryHeap<HuffmanNode> legendHeap = new BinaryHeap(parsed.length/2 + 1);
    for(int i=0; i<parsed.length; i+=2){
      HuffmanNode node = new HuffmanNode(parsed[i], Double.parseDouble(parsed[i+1]));
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
    HuffmanTree tree = new HuffmanTree(finalNode);
    return tree;
  }

  // Main method where legend string is inserted (hard coded for now) and converted to a heap and then printed,
  // Then a huffman tree object from that heap is created and printed out
  public static void main(String[] args) {
    BinaryHeap bheap = HuffmanTree.legendToHeap("A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2");
    bheap.printHeap();
    HuffmanTree htree = HuffmanTree.createFromHeap(bheap);
    htree.printLegend();
    
  }
}