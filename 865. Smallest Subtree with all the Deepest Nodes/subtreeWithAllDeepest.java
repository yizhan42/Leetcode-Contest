// 865. Smallest Subtree with all the Deepest Nodes

// Difficulty: Medium
// Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

// A node is deepest if it has the largest depth possible among any node in the entire tree.

// The subtree of a node is that node, plus the set of all descendants of that node.

// Return the node with the largest depth such that it contains all the deepest nodes in its subtree.

 

// Example 1:

// Input: [3,5,1,6,2,0,8,null,null,7,4]
// Output: [2,7,4]
// Explanation:
// We return the node with value 2, colored in yellow in the diagram.
// The nodes colored in blue are the deepest nodes of the tree.
// The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
// The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
// Both the input and output have TreeNode type.


//注意给所有树节点找parent的写法

//  * Definition for a binary tree node.
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedList;;

public class TreeNode {
   int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    Map<TreeNode, Integer> depth;
    int max_depth;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        depth = new HashMap();
        depth.put(null, -1);
        dfs(root, null);
        max_depth = -1;
        for (Integer d: depth.values())
            max_depth = Math.max(max_depth, d);

        return answer(root);
    }

    public void dfs(TreeNode node, TreeNode parent) {
        if (node != null) {
            depth.put(node, depth.get(parent) + 1);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    public TreeNode answer(TreeNode node) {
        if (node == null || depth.get(node) == max_depth)
            return node;
        TreeNode L = answer(node.left),
                 R = answer(node.right);
        if (L != null && R != null) return node;
        if (L != null) return L;
        if (R != null) return R;
        return null;
    }
}

