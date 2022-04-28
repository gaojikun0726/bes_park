package com.core.common.tree;

import java.io.Serializable;
import java.util.List;

public class ISSPTreeNode implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String CHILD_NODE_EXIST = "1"; // 存在子节点
	public static final String CHILD_NODE_NONEXIST = "0"; // 不存在子节点

	private String nodeTreeId; // 树的节点Id，区别于数据库中保存的数据Id。--弃用！！！！
	private String pid;
	private String text; // 节点名称
	private String nodeType;// 节点类型
	private String nodeStatus;// 节点状态
	private String image;// 图片
	private String id;// 数据库中的id,树的节点Id
	private String rootId;
	private String level;//
	private String icon;// 节点图标
	private  String fnybh;//能源编号
	private boolean isLeaf;//是否为子节点
	private ISSPTreeNode parent;
	private List<ISSPTreeNode> nodes; // 子节点，可以用递归的方法读取
	private String nodeAttribution;//节点归属
	private String isChildNodeExist;// 是否存在子节点
	private Object extendAttr;// 扩展属性

	public ISSPTreeNode() {

	}

	public ISSPTreeNode(String id, String parentId, String text) {
		super();
		this.id = id;
		this.pid = parentId;
		this.text = text;
	}

	public String getFnybh() {
		return fnybh;
	}

	public void setFnybh(String fnybh) {
		this.fnybh = fnybh;
	}
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	public String getNodeTreeId() {
		return nodeTreeId;
	}

	public void setNodeTreeId(String nodeTreeId) {
		this.nodeTreeId = nodeTreeId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ISSPTreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<ISSPTreeNode> nodes) {
		this.nodes = nodes;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public ISSPTreeNode getParent() {
		return parent;
	}

	public void setParent(ISSPTreeNode parent) {
		this.parent = parent;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	public String getNodeAttribution() {
		return nodeAttribution;
	}

	public void setNodeAttribution(String nodeAttribution) {
		this.nodeAttribution = nodeAttribution;
	}

	public Object getExtendAttr()
	{
		return extendAttr;
	}

	public void setExtendAttr(Object extendAttr)
	{
		this.extendAttr = extendAttr;
	}

	public String getIsChildNodeExist() {
		return isChildNodeExist;
	}

	public void setIsChildNodeExist(String isChildNodeExist) {
		this.isChildNodeExist = isChildNodeExist;
	}
}