/**
 * xiepufeng
 * 事件发布订阅
 * @type {{subscribe: (function(*, *=): PubSub), publish: PubSub.publish, unsubscribe: PubSub.unsubscribe}}
 */

var PubSub = {
    /**
     * 定义订阅函数
     * @param event
     * @param callback
     * @returns {PubSub}
     */
    subscribe: function (event, callback, childEvent)
    {

        var calls = this._callbacks || (this._callbacks = {});

        if(!calls[event])
        {
            calls[event] = new Map();

            if (typeof this.subscribeEvent === 'function')
            {
                this.subscribeEvent(event);
            }
        }

        calls[event].put(childEvent || callback.name, callback);

        return this;
    },
    /**
     * 定义发布函数
     */
    publish: function ()
    {
        // 将arguments 对象，转换为真正的数组
        var args = Array.prototype.slice.call(arguments, 0);
        // 拿出第一个参数，即信道名称（事件名称）
        var ev = args.shift();
        var map;
        var calls = this._callbacks;
        // 如果不存在 _callbacks 对象则返回
        if (!calls)
        {
            return this;
        }
        //  如果不包含给定事件所对应的数组，同样返回
        if (!(map = calls[ev]))
        {
            return this;
        }

        // 依次触发事件对应的回调函数
        map.values().forEach(v => {
            v.apply(null, args);
        })

        return this;
    },
    /**
     * 取消订阅
     * 不传参数，取消全部订阅
     * @param event
     * @param childEvent
     * @returns {boolean}
     */
    unsubscribe: function (event, childEvent)
    {
        var calls = this._callbacks;

        if (!event && !childEvent)
        {

            for (var key in calls)
            {
                delete calls[key];

                if (typeof this.unsubscribeEvent === 'function')
                {
                    this.unsubscribeEvent(event);
                }
            }

            return true;

        } else if (calls[event])
        {
            if (!childEvent)
            {
                delete calls[event];

                if (typeof this.unsubscribeEvent === 'function')
                {
                    this.unsubscribeEvent(event);
                }

                return true;
            }

            var eventMap = calls[event];

            var childEventName = childEvent.name || childEvent;

            if (!eventMap || eventMap.size === 0)
            {
                delete calls[event];

                if (typeof this.unsubscribeEvent === 'function')
                {
                    this.unsubscribeEvent(event);
                }

                return true;
            }

            if (eventMap.get(childEventName))
            {
                eventMap.remove(childEventName);
            }

            if (eventMap.isEmpty())
            {
                delete calls[event];

                if (typeof this.unsubscribeEvent === 'function')
                {
                    this.unsubscribeEvent(event);
                }
            }

            return true;

        }else if (!event && childEvent)
        {

            var childEventName = childEvent.name || childEvent;

            for (var key in calls)
            {
                var eventMap = calls[key];

                var childEvent = eventMap.get(childEventName);

                if (childEvent)
                {
                    eventMap.remove(childEventName);
                }

                if (eventMap.isEmpty())
                {
                    delete calls[key];

                    if (typeof this.unsubscribeEvent === 'function')
                    {
                        this.unsubscribeEvent(key);
                    }
                }
            }

            return true;

        }

    },

    /**
     * 添加订阅事件
     */
    subscribeEvent: null,

    /**
     * 取消订阅事件
     */
    unsubscribeEvent: null
};

/**
 * 根据 菜单id取消页面订阅事件
 * @param menuId
 */
PubSub.unsubscribePage = function (menuId)
{
    if (!menuId)
    {
        return;
    }

    switch (menuId)
    {
        // 设备配置页面取消所有订阅事件
        case '20180612202412790ec05e17666b0540':
            PubSub.unsubscribe(null, basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTreeSingle);
            break;
        // 区域管理页面（设计器页面）取消所有订阅事件
        case '20200526090719649ef5f6234f276b0f':
            PubSub.unsubscribe(null, 'listConditionerCallback');
            PubSub.unsubscribe(null, 'lowConditionerCallback');
            PubSub.unsubscribe(null, 'channelCallback');
            PubSub.unsubscribe(null, 'sceneCallback');
            PubSub.unsubscribe(null, 'pointBtnCallback');
            PubSub.unsubscribe(null, 'tempListCallback');
            PubSub.unsubscribe(null, 'tempImgCallback');
            PubSub.unsubscribe(null, 'imgCallback');
            PubSub.unsubscribe(null, 'textboxCallback');
            PubSub.unsubscribe(null, 'labelEnergyEfficiencyCallback');
            PubSub.unsubscribe(null, 'labelCallback');
            PubSub.unsubscribe(null, 'flowCallback');
            PubSub.unsubscribe(null, 'energyEfficiencyPieOneCallback');
            PubSub.unsubscribe(null, 'energyEfficiencyOneCallback');
            PubSub.unsubscribe(null, 'energyEfficiencyCallback');
            PubSub.unsubscribe(null, 'curtainWinCallback');
            break;
    }


}

PubSub.subscribeEvent = function (event)
{
    $.ajax({
        type: "post",
        url: _ctx + "/view/basedatamanage/pubsubmanage/subscribe",
        data:({
            event: event
        }),

        success: function (result) {

        },
        error: function (nodeData) {
            swal( nodeData.msg,"", "error");
        }
    });
}

PubSub.unsubscribeEvent = function (event)
{
    $.ajax({
        type: "post",
        url: _ctx + "/view/basedatamanage/pubsubmanage/unsubscribe",
        data:({
            event: event
        }),

        success: function (result) {

        },
        error: function (nodeData) {
            swal( nodeData.msg,"", "error");
        }
    });
}


//test

//1
//订阅
// PubSub.subscribe( 'go', function( data ) {
//     console.log( data );
// });
//
// //发布
//
// PubSub.publish('go','data') ;
//
// //取消订阅
// PubSub.unsubscribe('go') ;

//2
//订阅
// PubSub.subscribe( 'go', function( data ) {
//     console.log( data + 1 );
// });
//
// PubSub.subscribe( 'go', function( data ) {
//     console.log( data + 2 );
// });
//
// //发布
//
// PubSub.publish('go','data') ;
//
// //取消订阅
// PubSub.unsubscribe('go') ;


//3
//订阅
// PubSub.subscribe( 'go', function( data ) {
//     console.log( data );
// });
//
// PubSub.subscribe( 'to', function( data ) {
//     console.log( data );
// });
//
// //发布
//
// PubSub.publish('go','go') ;
//
// PubSub.publish('to','to') ;
//
// //取消所有订阅
// PubSub.unsubscribe() ;

//4
//订阅
// PubSub.subscribe( 'go', function( data1, data2 ) {
//     console.log( data1, data2);
// });
//
// PubSub.subscribe( 'to', function( data ) {
//     console.log( data );
// });
//
// //发布
//
// PubSub.publish('go','go1', 'go2') ;
//
// PubSub.publish('to','to') ;
//

