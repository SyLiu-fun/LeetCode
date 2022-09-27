package problems;

//297
//先序遍历
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}

public class SerializeBinaryTree {
    int idx = 0;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root == null){
            sb.append("null").append(",");
            return sb.toString();
        }
        sb.append(root.val).append(",");
        sb.append(serialize(root.left)).append(serialize(root.right));
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        return help(nodes);
    }

    public TreeNode help(String[] data){
        if(data[idx].equals("null")) {
            idx++;
            return null;
        }
        TreeNode rt = new TreeNode(Integer.parseInt(data[idx++]));
        rt.left = help(data);
        rt.right = help(data);
        return rt;
    }
}
