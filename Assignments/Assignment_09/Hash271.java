public class Hash271 {

    /** Default size for foundation array */
    private static final int DEFAULT_SIZE = 4;

    /** Foundation array of node objects */
    Node[] foundation;
     //Track the current number of nodes in the hash table
    private int nodeCount;
    //Threshold for resizing the hash table
    private double loadFactorThreshold;
    

    /** Basic constructor */
    public Hash271(int size, double loadFactorThreshold) {
        this.foundation = new Node[size];
        //Initialize node count to 0
        this.nodeCount = 0; 
        //Set the load factor threshold
        this.loadFactorThreshold = loadFactorThreshold; 
    } // basic constructor

    /** Default constructor */
    public Hash271() {
        //Use default size and load factor threshold
        this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR_THRESHOLD); 
    } // default constructor

    /**
     * Map an integer number to one of the positions of the underlying array. This
     * will come handy we need to find the place to chain a node.
     * 
     * @param value int to map to one of the array positions
     * @return int with the integer division remainder between the input value and
     *         the length of the array
     */
    private int computeArrayPosition(int value) {
        return value % this.foundation.length;
    } // method computeArrayPosition

    /**
     * Chain a node to the underlying array
     * 
     * @param node Node to chain to the underlying array
     */
    public void put(Node node) {
        // Operate only is node is not null
        if (node != null) {
            // Check if rehashing is needed based on the current load factor
            if (getLoadFactor() > loadFactorThreshold) {
                //Rehash the foundation array if load factor exceeds threshold
                rehash();
            }
            // Use the node's hashcode to determine is position in
            // the underlying array
            int destination = computeArrayPosition(node.hashCode());
            // If the position in the array is occupied by another node,
            // place that node under the new node we wish to insert
            if (this.foundation[destination] != null) {
                node.setNext(this.foundation[destination]);
            }
            // Put the new node to the array position
            this.foundation[destination] = node;
            nodeCount++; //Increment node count
        }
    } // method put

    /**
     * Wrapper for put(Node). Accepts a string, creates a Node object and passes it
     * to the put(Node) method.
     * 
     * @param string String to create a node for, then chain that node to the
     *               underlying array.
     */
    public void put(String string) {
        if (string != null && string.length() > 0) {
            Node node = new Node(string);
            this.put(node);
        }
    } // method put

    /**
     * Resize and rehash the foundation array when the load factor exceeds the threshold
     */
    private void rehash() {
        //Save the current foundation array
        Node[] oldFoundation = this.foundation;
        //Create a new foundation array with double the size
        this.foundation = new Node[oldFoundation.length * 2];
        //Reset node count
        nodeCount = 0;
        // Iterate through each node in the old foundation array
        for (Node node : oldFoundation) {
            while (node != null) {
                Node next = node.getNext();
                node.setNext(null);
                // Reinsert node into the new foundation array
                this.put(node);
                node = next;
            }
        }
    }

    /** String representation of this object */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.foundation.length; i++) {
            sb.append(String.format("[ %03d ]: ", i));
            Node current = this.foundation[i];
            while (current != null) {
                sb.append(String.format("<%s> ", current.toString()));
                current = current.getNext();
            }
            sb.append("\n");
        }
        return sb.toString();
    } // method toString

    /** Driver code */
    public static void main(String[] args) {
        Hash271 h = new Hash271();
        h.put(new Node("Java"));
        h.put(new Node("Python"));
        h.put(new Node("Lisp"));
        h.put(new Node("Fortran"));
        h.put(new Node("Prolog"));
        h.put(new Node("Cobol"));
        h.put(new Node("C++"));
        h.put(new Node("C"));
        h.put(new Node("C#"));
        System.out.println(h);
    }
}
