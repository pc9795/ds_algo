package gfg.ds.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 06-01-2019 18:56
 **/
public class N_ryTree {
    public N_ryNode root;

    public N_ryTree(int rootData) {
        this.root = new N_ryNode(rootData);
        this.root.parent = this.root;
    }

    /**
     * Created By: Prashant Chaubey
     * Created On: 06-01-2019 18:57
     **/
    public static class N_ryNode {
        public int data;
        public List<N_ryNode> children;
        public int level;
        public N_ryNode parent;

        public N_ryNode(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof N_ryNode)) {
                return false;
            }
            N_ryNode other = (N_ryNode) obj;
            return (other.data == this.data) && (other.children.equals(this.children));
        }

        @Override
        public String toString() {
            return "" + this.data;
        }
    }
}