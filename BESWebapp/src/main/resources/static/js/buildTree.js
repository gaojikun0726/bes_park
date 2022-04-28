/**
 * xiepufeng
 * 构造树型结构数据
 * @param {*} data 源数据
 * @param {*} id  指定源数据中具有上下级关系的id值
 * @param {*} parentId 指定源数据中具有上下级关系的父id值
 * @param {*} children 指定生成树的子节点名称
 * @param {*} dataName 指定源数据中需要树上显示的名称
 * @param {*} treeName 指定树上显示名称的属性名称
 * @param rootId
 * @param children
 */

function buildTree(options)
{
    if (!options)
    {
        return;
    }

    let data =  options.data;

    if (!data)
    {
        return;
    }

    let id = options.id || 'id';
    let parentId = options.parentId || 'parentId';
    let children = options.children || 'children';
    let dataName = options.dataName || 'name';
    let treeName = options.treeName || 'name';

  // 对源数据深度克隆
  const cloneData = JSON.parse(JSON.stringify(data));

  if (!Array.isArray(cloneData)) {
    return;
  }
  const dataMap = new Map();

  for (let i = 0; i < cloneData.length; i++) {
      dataMap.put(cloneData[i][id], cloneData[i]);
    if (cloneData[i][treeName] === undefined)
    {
        cloneData[i][treeName] = cloneData[i][dataName]
    }
  }

  const treeData = [];

    dataMap.values().forEach((item) =>
    {
        const parentNode = item[parentId];
        if (!dataMap.get(parentNode))
        {
            treeData.push(item)
        } else
        {
            const parentItem = dataMap.get(parentNode);
            if (!Array.isArray(parentItem[children]))
            {
                parentItem[children] = []
            }

            parentItem[children].push(item);

        }

    });

  return treeData
}
