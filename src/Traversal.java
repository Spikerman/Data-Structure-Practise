import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Author: Spikerman
 * Created Date: 5/31/18
 */
public class Traversal {

    // imagine cur as the argument in the recursive function
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();
            list.add(node.val);
            cur = node.right;
        }
        return list;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                list.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode tmp = cur.left;
                while (tmp.right != null && tmp.right != cur) {
                    tmp = tmp.right;
                }
                if (tmp.right == null) { // go deeper
                    tmp.right = cur;
                    cur = cur.left;
                } else { // cur has been backtracked
                    list.add(cur.val);
                    tmp.right = null;
                    cur = cur.right;
                }
            }
        }
        return list;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        if (cur != null) stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            list.add(cur.val);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return list;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                list.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode tmp = cur.left;
                while (tmp.right != null && tmp.right != cur) {
                    tmp = tmp.right;
                }
                if (tmp.right == null) {
                    list.add(cur.val);
                    tmp.right = cur;
                    cur = cur.left;
                } else {
                    tmp.right = null;
                    cur = cur.right;
                }
            }
        }
        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        if (cur != null) stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            list.addFirst(cur.val);
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }
        return list;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.right == null) {
                list.addFirst(cur.val);
                cur = cur.left;
            } else {
                TreeNode tmp = cur.right;
                while (tmp.left != null && tmp.left != cur) {
                    tmp = tmp.left;
                }
                if (tmp.left == null) {
                    list.addFirst(cur.val);
                    tmp.left = cur;
                    cur = cur.right;
                } else {
                    tmp.left = null;
                    cur = cur.left;
                }
            }
        }
        return list;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
