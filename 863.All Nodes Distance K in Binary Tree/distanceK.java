//本题 是一道将 DFS 和 BFS 结合的题，精华在于 在遍历树的每一层时加入  null 节点，用null 节点来分隔每一层，好方法。


// Difficulty : Medium
// We are given a binary tree (with root node root), a target node, and an integer value K.

// Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

// Example 1:

// Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

// Output: [7,4,1]

// Explanation: 
// The nodes that are a distance 2 from the target node (with value 5)
// have values 7, 4, and 1.



// Note that the inputs "root" and "target" are actually TreeNodes.
// The descriptions of the inputs above are just serializations of these objects.
 

// Note:

// The given tree is non-empty.
// Each node in the tree has unique values 0 <= node.val <= 500.
// The target node is a node in the tree.
// 0 <= K <= 1000.



// Approach 1: Annotate Parent
// Intuition

// If we know the parent of every node x, we know all nodes that are distance 1 from x. We can then perform a breadth first search from the target node to find the answer.

// Algorithm

// We first do a depth first search where we annotate every node with information about it's parent.

// After, we do a breadth first search to find all nodes a distance K from the target.



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Map<TreeNode, TreeNode> parent;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        parent = new HashMap(); // 因为下面 dfs() 函数中也用到 parent 这个 Hashmap，所以该语句拆成两句话
        dfs(root, null);

        Queue<TreeNode> queue = new LinkedList();
        queue.add(null);
        queue.add(target);
        //queue.add(null); 与 queue.add(target);不能调换顺序

        Set<TreeNode> seen = new HashSet(); // 加入Set的目的是防止在树的遍历中有元素被重复遍历，
        // 比如上图中，与5距离为1的点2，再往下找与点2距离为1的点，就不应该再包含1了。

        seen.add(null);//这句的目的是因为 queue中有null节点，要保证queue和seen中元素一致，否则当 node.left = null 时，if (!seen.contains(node.left)) 
        // 等句会报错：
        // Exception in thread "main" java.lang.NullPointerException
        // at Solution.distanceK(Solution.java:32)
        // at __DriverSolution__.__helper__(__Driver__.java:11)
        // at __Driver__.main(__Driver__.java:55)
        // 但seen.add(null);和seen.add(target);谁先谁后无关。
        seen.add(target);

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == K) {
                    List<Integer> ans = new ArrayList();
                    for (TreeNode n: queue)
                        ans.add(n.val);
                    return ans;
                }
                queue.offer(null);
                dist++;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }

        return new ArrayList<Integer>();
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
}