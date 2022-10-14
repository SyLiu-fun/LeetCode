package problems;

//236
//PostOrder
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        TreeNode lr = lowestCommonAncestor(root.left, p, q);
        TreeNode rr = lowestCommonAncestor(root.right, p, q);
        // 如果左右子树都有返回，说明左右两边同时找到了结果，该结点是两个结点的最近公共祖先
        if(lr != null && rr != null) return root;
        // 如果当前结点是一个目标结点，如果左子树或右子树中查到了另一个结点，该节点是最近公共祖先
        // 如果左子树或者右子树没有查到结果，说明最近公共祖先在该结点上方，需要返回查询到的结果
        if(root.val == p.val || root.val == q.val) return root;
        else {
            // 如果该结点不是目标结点，如果左右子树都没查到结果，说明不在该结点形成的树上，返回null
            if(lr == null && rr == null) return null;
            // 如果已经查到了目标结点，不管已经找到了一个还是两个，返回查询到的结果
            return lr == null ? rr : lr;
        }
    }
}
