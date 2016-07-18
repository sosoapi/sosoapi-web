ie下进行调试
1、请求json url并解析由SwaggerClient.prototype.build处理，经过SwaggerHttp.prototype.execute处理http请求获取文档json
2、json解析由swagger-ui.js中的Resolver.prototype.resolve = function (spec, arg1, arg2, arg3)开始
3、接口排序和渲染入库由SwaggerUi.Views.MainView处理
4、接口的解析从var Operation开始
5、对象的解析从var Model开始
其中，默认响应的Model Schema部分由Model.prototype.createJSONSample解析
               Model 部分由Model.prototype.getMockSignature解析
4、渲染由SwaggerUi.Views.OperationView中的render处理
5、执行在线测试由Operation.prototype.execute执行，参数拼接在Operation.prototype.urlify中处理
6、接口分组渲染SwaggerUi.Views.MainView.render中执行，在SwaggerClient.prototype.buildFromSpec初始化分组信息

扩展部分
默认响应改造
1、SchemaMarkup.schemaToJSON新增对类型为cust_json和复合类型的处理，同时Model Schema展示新增类型和描述信息
2、SchemaMarkup.schemaToHTML新增对类型为cust_json和复合类型的处理，同时Model Schema展示新增类型和描述信息

其他响应改造
1、SwaggerUi.Views.OperationView.render新增对类型为cust_json和复合类型的处理
2、SwaggerUi.Views.StatusCodeView.render新增对cust_json和复合类型的处理

请求改造
1、var Operation新增对类型为cust_json的处理，具体位置如下
param.signature = this.getModelSignature(innerType, this.models).toString();
param.sampleJSON = this.getModelSampleJSON(innerType, this.models);

在线执行
1、请求协议默认采用方法定义的，然后才使用文档级别
   Operation.prototype.urlify中对请求url拼接改造

2、请求url拼接，处理多个"?"bug
   Operation.prototype.urlify
   
国际化支持

注意事项：
1、模块名称不能与文档的第一级元素key相同，否则可能出现"Cannot assign to read only property 'help' of XXXXX"


