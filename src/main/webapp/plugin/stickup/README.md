stickUp
=======
a jQuery plugin

A simple plugin that "sticks" an element to the top of the browser window while scrolling past it, always keeping it in view. This plugin works on multi-page sites, but has additional features for one-pager layouts.

Please Visit the <a href="http://lirancohen.github.io/stickUp">GitHub Page</a> for installation instructions.

<h1> stick up 插件升级（可以理解为v2.0）</h1>
<h2>作出如下修改：</h2>
<ul>
	<li>舍弃parts参数，更改为在html标签中指定data-menu属性的形式</li>
</ul>
<h2>添加如下功能：</h2>
<ul>
	<li>支持回调函数，如afterStick等</li>
	<li>支持多次调用（原版本下，仅保存最后一次调用信息，即不支持多次调用）</li>
	<li>引入严格模式</li>
</ul>
<h2>调用接口如下</h2>
<ul>
	<li><span>dataProperty：</span>默认值：data-menu，用于标注menu-item中的（此值与html结构关联，用于替代以前的parts参数）</li>
	<li><span>itemClass: </span>标注菜单“项”，在库中通过此参数查找菜单项</li>
	<li> <span>itemHover:　</span>单页网页中，网页滚动到菜单项指定区域时，会为该菜单项添加该类 </li>
	<li> <span>marginTop: </span> 用于指定滚动元素到上边距 </li>
	<li><span>beforeStick: </span> 回调函数，菜单栏固定前触发</li>
	<li><span>afterStick: </span> 回调函数，菜单栏固定后触发</li>
	<li><span>beforeUnstick: </span> 回调函数，菜单栏解除固定前触发</li>
	<li><span>afterUnstick: </span> 回调函数，菜单栏解除固定后触发</li>
</ul>
<span>如果有任何问题，请尽快联系我；如果可能，请尽量阅读源码，不多，才150多行</span>