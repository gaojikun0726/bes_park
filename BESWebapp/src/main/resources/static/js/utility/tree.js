/**
 * @author xiepufeng
 */
var Tree = (function ( $ ) {

    function Tree( obj ) {

        if(null == obj){
            console.warn( '无有效参数！' );
            return;
        }

        this.container = obj.container;
        this.idKey = obj.idKey || 'id';
        this.pIdKey = obj.pIdKey || 'pId';
        this.name = obj.name || 'name';
        this.callback = obj.callback;
        this.setting = obj.setting;
        this.tree = this.init();
    }

    /**
     * 初始化
     */
    Tree.prototype.init = function () {

        var container = this.container;

        if( typeof container !== 'string' ){
            console.warn( '参数错误！' );
            return;
        }

        var idKey = this.idKey;
        var pIdKey = this.pIdKey;
        var name = this.name;
        var callback = this.callback;

        if( typeof idKey !== 'string' || typeof pIdKey !== 'string' ){
            console.warn( '参数错误！' );
            return;
        }

        var setting = {
            view: {
                dblClickExpand: true,
                showLine: false,
                selectedMulti: false,
                fontCss: function ( treeId, treeNode ) {
                    return ( treeNode.highlight ) ? {color:'#A60000', 'font-weight':'bold'} : {color:'#333', 'font-weight':'normal'};
                }
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey: idKey,
                    pIdKey: pIdKey,
                    rootPId: null
                },
                key: {
                    name: name // zTree 节点数据保存节点名称的属性名称。
                }
            },

            callback: {
                beforeClick: function( treeId, treeNode ) {

                    if( typeof callback === 'function' ){
                        callback( treeNode );
                    }

                }
            }
        };

        if( this.setting ){
            $.extend(true, setting, this.setting);
        }

        var tree = $.fn.zTree.init( $( '#' + container ), setting, [] );

        return tree;

    };

    /**
     * 加载数据
     * @param data
     */
    Tree.prototype.loadData = function ( data ) {

        if( data === null ){
            console.warn( '无效的参数！' );
        }

        var tree = this.tree;

        tree.addNodes( null, data );

    };

    var nodes = [];

    /**
     * 模糊搜索(输入框搜索)
     * @param container 输入框id
     * @param criteria  匹配的属性名称
     */
    Tree.prototype.search = function ( container, criteria ) {

        if( typeof container !== 'string' || typeof criteria !== 'string'){
            console.warn( '参数错误！' );
        }
        

        var tree = this.tree;

        var value = $( '#' + container ).val();

        if( !value || !value.trim()){

            nodes.forEach(function ( node ) {
                node.highlight = false;
               tree.updateNode( node );
            });

            return;
        }

        if( nodes.length ){
            nodes.forEach(function ( node ) {
                node.highlight = false;
                tree.updateNode( node );
            });
        }

        nodes = tree.getNodesByParamFuzzy( criteria, value, null );
        nodes.forEach(function ( node ) {
            tree.expandNode( node.getParentNode(), true );
            node.highlight = true;
            tree.updateNode( node );
        });
    };

    /**
     * 模糊搜索搜索
     * @param container 输入框id
     * @param criteria  匹配的属性名称
     */
    Tree.prototype.searchFuzzy = function ( value, criteria ) {

        if( typeof criteria !== 'string' || typeof value !== 'string' ){
            console.warn( '参数错误！' );
        }

        var tree = this.tree;

        if( nodes.length ){
            nodes.forEach(function ( node ) {
                node.highlight = false;
                tree.updateNode( node );
            });
        }

        nodes = tree.getNodesByParamFuzzy( criteria, value, null );

        nodes.forEach(function ( node ) {
            tree.expandNode( node.getParentNode(), true );
            node.highlight = true;
            tree.updateNode( node );
        });

    };

    /**
     * 精确搜索
     * @param value 搜索值
     * @param criteria  匹配的属性名称
     */
    Tree.prototype.searchPrecise = function (value, criteria)
    {
        if( typeof criteria !== 'string' || typeof value !== 'string' ){
            console.warn( '参数错误！' );
        }

        var tree = this.tree;

        if( nodes.length ){
            nodes.forEach(function ( node ) {
                node.highlight = false;
                tree.updateNode( node );
            });
        }

        var node = tree.getNodeByParam(criteria , value);

        if (!node)
        {
            return;
        }

        tree.expandNode( node.getParentNode(), true );
        node.highlight = true;
        tree.updateNode( node );

        nodes.push(node);

    };

    /**
     * 清除选中的节点
     */
    Tree.prototype.clearCheckedNode = function ()
    {
        if( nodes.length ){

            var tree = this.tree;

            nodes.forEach(function ( node ) {
                node.highlight = false;
                tree.updateNode( node );
            });
        }
    };
 
    return Tree;
}( $ ));

