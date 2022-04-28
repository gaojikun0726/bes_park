package com.core.common.tree;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ISSPTreeBuilder {

	@Deprecated
	@SuppressWarnings("unchecked")
	public static List<ISSPTreeNode> buildTree(List<ISSPTreeNode> dirs) {
		List<ISSPTreeNode> roots = getRoots(dirs);
		List<ISSPTreeNode> notRoots = (List<ISSPTreeNode>) CollectionUtils.subtract(dirs, roots);
		for (ISSPTreeNode root : roots) {
			root.setNodes(getChildren(root, notRoots));
		}
		return roots;
	}

	/**
	 * 获取根节点
	 *
	 * @param treeNodes
	 * @return
	 */
	private static List<ISSPTreeNode> getRoots(List<ISSPTreeNode> treeNodes) {
		List<ISSPTreeNode> results = new ArrayList<ISSPTreeNode>();
		for (ISSPTreeNode treeNode : treeNodes) {
			boolean isRoot = true;
			for (ISSPTreeNode comparedOne : treeNodes) {
				if (treeNode.getPid() != null && treeNode.getPid().equals(comparedOne.getId())) {
					isRoot = false;
					break;
				}
			}
			if (isRoot) {
				results.add(treeNode);
				treeNode.setRootId(treeNode.getId());
			}
		}
		return results;
	}

	/**
	 * 组织树形结构
	 *
	 * @param treeNodes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static List<ISSPTreeNode> getChildren(ISSPTreeNode root, List<ISSPTreeNode> allNodes) {
        List<ISSPTreeNode> children = new ArrayList<ISSPTreeNode>();
        for (ISSPTreeNode comparedOne : allNodes) {
            if (comparedOne.getPid().equals(root.getId())) {
//                comparedOne.setParent(root);
            	String level = root.getLevel();
            	if(level == null){level = "0";}
                comparedOne.setLevel(String.valueOf(Integer.parseInt(level)+ 1));
                children.add(comparedOne);
            }
        }
        List<ISSPTreeNode> notChildren = (List<ISSPTreeNode>) CollectionUtils.subtract(allNodes, children);
        for (ISSPTreeNode child : children) {
            List<ISSPTreeNode> tmpChildren = getChildren(child, notChildren);
            if (tmpChildren == null || tmpChildren.size() < 1) {
                child.setLeaf(true);
            } else {
                child.setLeaf(false);
            }
            child.setNodes(tmpChildren);
        }
        return children;
    }

	/**
	 * 将对象中的属性放到节点中
	 *
	 * @param list
	 * @return List<ISSPTreeNode>
	 */
	public static List<ISSPTreeNode> getTreeJson(List<IBaseTree> list) {
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();
		for (IBaseTree tree : list) {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setNodeTreeId(tree.getCId());
			node.setId(tree.getCId());
			node.setPid(tree.getPId());
			node.setText(tree.getCMc());
			node.setLevel(tree.getCJs());
			nodes.add(node);
		}
		return nodes;
	}
}
