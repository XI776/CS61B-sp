import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class Node {
        K  key;
        V value;
        Node  left;
        Node  right;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    private Node root;
    private int size;
    public BSTMap() {
        root = null;
        size = 0;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void put(K key, V value) {
        if(isEmpty()) {
            root = new Node(key, value);
        } else {
            Node temp = root;
            Node prev = null;
            while(temp != null) {
                prev = temp;
                if(temp.key.compareTo(key) < 0) {
                    temp = temp.right;
                } else  if(temp.key.compareTo(key) > 0) {
                    temp = temp.left;
                } else {
                    temp.value = value;
                    return;
                }
            }
            Node newNode = new Node(key, value);
            if(prev.key.compareTo(key) < 0) {
                prev.right = newNode;
            } else if(prev.key.compareTo(key) > 0) {
                prev.left = newNode;
            }
        }
        size++;
    }

    @Override
    public V get(K key) {
        Node temp = root;
        while(temp != null) {
            if(temp.key.compareTo(key) == 0) {
                return temp.value;
            } else if(temp.key.compareTo(key) > 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        Node temp = root;
        while(temp != null) {
            if(temp.key.compareTo(key) == 0) {
                return true;
            } else if(temp.key.compareTo(key) > 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    public Set<K> preOrderTraversal(Node node) {
        Stack<Node> stack = new Stack<>();

        Set<K> keys = new TreeSet<>();
        if(node != null) {
            stack.push(node);
        }

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            keys.add(curr.key);

            // 先压右子节点，后压左子节点
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return keys;
    }

    public List<K> preOrderTraversal() {
        List<K> keys = new ArrayList<>();
        if (root == null) return keys;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            keys.add(curr.key);

            // 先压右子节点，后压左子节点
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return keys;
    }


    @Override
    public Set<K> keySet() {
        return preOrderTraversal(root);
    }
    // 辅助函数：找到以node为根的最小节点
    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if(node.left == null) {
                return node.right;
            }
            if(node.right == null) {
                return node.left;
            }
            Node min = getMin(node.right);
            node.key = min.key;
            node.value = min.value;

            node.right = remove(node.right, node.key);
        }
        return node;
    }
    @Override
    public V remove(K key) {
        V value = get(key);
        if(value != null) {
            root = remove(root, key);
            size--;
        }
        return value;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyIterator();
    }
    private class MyIterator implements Iterator<K> {
        Iterator<K> iter;
        public MyIterator() {
            List<K> keys = preOrderTraversal();
            iter = keys.iterator();
        }
        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public K next() {
            return iter.next();
        }

    }
}
