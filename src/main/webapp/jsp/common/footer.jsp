<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- <div class="navbar navbar-footer navbar-fixed-bottom"> -->
<input type="hidden" id="sysReqToken" value="${Cfg.SYS_REQ_TOKEN}"></input>
<input type="hidden" id="copyFlag" value="${Cfg.COPY_FLAG}"></input>
<div id="loading"></div>
<div class="navbar navbar-footer">
	<div class="container">
        <div class="row">
            <div class="col-lg-12">
               <footer role="contentinfo">
                   <p class="left"><a href="" class="text-muted">SosoApi</a></p>
                   <%--
                   		<p class="right">© 2015 <a href="http://www.miitbeian.gov.cn/" target="_blank" class="text-muted">闽ICP备15013674号</a></p>
                   --%>
                   <p class="right">© 2015</p>
               </footer>
            </div>
        </div>
    </div>
</div>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- <script type="text/javascript" src="plugin/html5shiv.js"></script> -->
<!-- <script type="text/javascript" src="plugin/respond.min.js"></script> --> 
<!--[if lt IE 9]>
   <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
   <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<!-- <script type="text/javascript" src="plugin/jquery-2.0.3.min.js"></script> -->
<script src="//cdn.bootcss.com/jquery/2.0.3/jquery.min.js"></script>

<!-- <script type="text/javascript" src="plugin/bootstrap/js/bootstrap.min.js"></script> -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- <script type="text/javascript" src="plugin/bootstrap/js/twitter-bootstrap-hover-dropdown.min.js"></script> -->
<script src="//cdn.bootcss.com/bootstrap-hover-dropdown/2.0.10/bootstrap-hover-dropdown.min.js"></script>

<!-- <script type="text/javascript" src="plugin/validator/js/bootstrapValidator.min.js"></script> -->
<script src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>

<!-- <script type="text/javascript" src="plugin/form/js/jquery.form.js"></script> -->
<script src="//cdn.bootcss.com/jquery.form/3.51/jquery.form.min.js"></script>

<!-- <script type="text/javascript" src="plugin/chosen/chosen.jquery.min.js"></script> -->
<script src="//cdn.bootcss.com/chosen/1.4.2/chosen.jquery.min.js"></script>

<!-- <script type="text/javascript" src="plugin/summernote/js/summernote.min.js"></script> -->
<script src="//cdn.bootcss.com/summernote/0.6.16/summernote.min.js"></script>
<script type="text/javascript" src="plugin/summernote/lang/summernote-zh-CN.js"></script>
<script type="text/javascript" src="plugin/summernote/plugin/summernote-ext-preview.js"></script>
	
<script type="text/javascript" src="plugin/bootbox/ext/js/bootboxExt.js"></script>

<!-- <script type="text/javascript" src="plugin/jGrowl/js/jquery.jgrowl.min.js"></script> -->
<script src="//cdn.bootcss.com/jquery-jgrowl/1.4.3/jquery.jgrowl.min.js"></script>

<!-- 优化，直接用table处理 -->
<!-- <script type="text/javascript" src="plugin/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="plugin/datatables/ext/js/dataTablesExt.js"></script> -->

<!-- <script type="text/javascript" src="plugin/bootstrap/js/bootstrap-admin-theme-change-size.js"></script> -->
<script type="text/javascript" src="plugin/spin.js-2.3.2/spin.min.js"></script>

<script type="text/javascript" src="js/common/common.js?version=1.0.4"></script>
<script type="text/javascript" src="js/common/setting.js?version=1.0.3"></script>

<!-- baidu统计 -->
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?b6d830db139defa52e47fb1dc0018c06";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
