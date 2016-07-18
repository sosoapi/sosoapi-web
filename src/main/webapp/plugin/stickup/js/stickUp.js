// 问题：不支持一个页面中多个元素固定（已实现）
//		 不支持固定方向的设置
//		 不支持回调事件（已实现）
//		 单页模式下，调整的定位是基于part参数的（重构后是基于html元素的data-menu）
//		 目前而言，许多操作是实时的——比如每次都重新查找、计算DOM
// 		 这种方法对网页变动比较大的情景是非常适用的，但比较耗费性能
//
//		 目前为止，缺乏测试
jQuery(function($) {
    "use strict";

    var Context = function() {},
        _ctxList = {},
        lastScrollTop = 0;
    Context.prototype = {
        // 配置参数
        dataProperty: 'data-menu',
        itemClass: '',
        itemHover: '',
        marginTop: 0,
        beforeStick: null,
        afterStick: null,
        beforeUnstick: null,
        afterUnstick: null,

        // 预留参数
        region: 'top',

        // 自动计算值
        // 私有参数
        _selector: '',
        _jqDom: null,
        _menuItems: [],
        _height: 0,
        _parentMarginTop: 0,
        _top: 0,
        _marginBottom: 0,
        onScroll: function(scrollDir, varscroll) {
            var contentView = null,
                testView = null,
                _me = this;

            // 计算并给适当元素添加 itemHover 类
            if ( !! _me._menuItems && _me._menuItems.length > 0) {
                var offset = null,
                    contentTop = 0,
                    tmp_menuTarget = null;
                for (var i = 0; i < _me._menuItems.length; i++) {
                    tmp_menuTarget = $('#' + $(_me._menuItems[i]).attr(_me.dataProperty));
                    offset = tmp_menuTarget.offset();
                    contentTop = !! offset ? offset.top : 0;

                    // 之前這裡定義了一個bottomView
                    // 会在每次执行这个地方的时候都去创建一个函数
                    // 实际上是很没必要的性能损耗，所以这里将代码移动下面
                    if (scrollDir == 'down' &&
                        varscroll > contentTop - 50 &&
                        varscroll < contentTop + 50) {
                        _me._jqDom.find('.' + _me.itemClass).removeClass(_me.itemHover);
                        _me._jqDom.find('.' + _me.itemClass + ':eq(' + i + ')').addClass(_me.itemHover);
                    }
                    if (scrollDir == 'up') {
                        // 这里就是原来的bottomView代码
                        contentView = tmp_menuTarget.height() * 0.4;
                        testView = contentTop - contentView;
                        if (varscroll > testView) {
                            _me._jqDom.find('.' + _me.itemClass).removeClass(_me.itemHover);
                            _me._jqDom.find('.' + _me.itemClass + ':eq(' + i + ')').addClass(_me.itemHover);
                        } else if (varscroll < 50) {
                            _me._jqDom.find('.' + _me.itemClass).removeClass(_me.itemHover);
                            _me._jqDom.find('.' + _me.itemClass + ':eq(0)').addClass(_me.itemHover);
                        }
                    }
                }
            }

            // 固定菜单栏目，使之固定（fixed）
            if (_me._top < varscroll + _me.marginTop) {
                if ( !! _me.beforeStick) _me.beforeStick.call(_me);
                _me._jqDom.addClass('isStuck');
                if ( !! _me.afterStick) _me.afterStick.call(_me);
                _me._jqDom.next().closest('div').css({
                    'margin-top': _me._height + _me._marginBottom + _me._parentMarginTop + 'px'
                }, 10);
                _me._jqDom.css("position", "fixed");
                _me._jqDom.css({
                    top: '0px'
                }, 10);
            };

            // 菜單欄目，使之不固定（relative）
            if (varscroll + _me.marginTop < _me._top) {
                if ( !! _me.beforeUnstick) _me.beforeUnstick.call(_me);
                _me._jqDom.removeClass('isStuck');
                if ( !! _me.afterUnstick) _me.afterUnstick.call(_me);
                _me._jqDom.next().closest('div').css({
                    'margin-top': _me._parentMarginTop + 'px'
                }, 10);
                _me._jqDom.css("position", "relative");
            };
        }
    };
    Context._init_ = function(dom, option, instance) {
        instance = instance || new Context();

        var _me = instance,
            objn = 0;
        _me._jqDom = $(dom);

        //getting options
        if ( !! option) {
            _me._menuItems = _me._jqDom.find('[' + _me.dataProperty + ']');

            if (option.topMargin != null) {
                if (option.topMargin == 'auto') {
                    _me.marginTop = parseInt(_me._jqDom.css('margin-top'));
                } else {
                    if (isNaN(option.topMargin) && option.topMargin.search("px") > 0) {
                        _me.marginTop = parseInt(option.topMargin.replace("px", ""));
                    } else if (!isNaN(parseInt(option.topMargin))) {
                        _me.marginTop = parseInt(option.topMargin);
                    } else {
                        console.log("incorrect argument, ignored.");
                        _me.marginTop = 0;
                    }
                }
            } else {
                _me.marginTop = 0;
            }
            _me.itemClass = option.itemClass;
            _me.itemHover = option.itemHover;
        } else {
            console.log('warm:needs arguments');
        }

        _me.dataProperty = option.dataProperty || _me.dataProperty;
        _me.region = option.region || _me.region;
        _me._height = parseInt(_me._jqDom.height());
        _me._marginBottom = parseInt(_me._jqDom.css('margin-bottom'));
        _me._parentMarginTop = parseInt(_me._jqDom.next().closest('div').css('margin-top'));
        _me._top = parseInt(_me._jqDom.offset().top);

        _ctxList[_me._selector] = _me;
    };

    // 初始化各类参数，即解析options的值
    $.fn.stickUp = function(options) {
        if (!_ctxList[this.selector])
            Context._init_(this, options);
    }

    // 頁面滾動事件
    $(document).on('scroll', function() {
        var scrollOffest = parseInt($(document).scrollTop()),
            scrollDir = scrollOffest > lastScrollTop ? 'down' : 'up';

        for (var i in _ctxList) {
            _ctxList[i].onScroll(scrollDir, scrollOffest);
        }
        lastScrollTop = scrollOffest;
    });
});