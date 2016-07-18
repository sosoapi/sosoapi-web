<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/summernote/css/summernote.css" rel="stylesheet" />
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="bootstrap-admin-with-small-navbar">
    <div class="container cust-container">
		<div class="row">
            <!-- MAIN SECTION -->
            <div class="col-md-10">
    	        <div class="row">
        	        <div class="col-lg-12">
                        <!-- CONTENT SECTION -->
                        	<div id="summernote">Hello Summernote</div>
                        <!-- END CONTENT SECTION -->
                    </div>
                </div>
            </div>
            <!-- END MAIN SECTION -->
        </div>
    </div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
	<script type="text/javascript" src="plugin/summernote/js/summernote.min.js"></script>
	<script type="text/javascript" src="plugin/summernote/lang/summernote-zh-CN.js"></script>
	<script type="text/javascript" src="plugin/summernote/plugin/summernote-ext-preview.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#summernote').summernote({
				height: 300, 
				minHeight: null,
				maxHeight: null,
				focus: true,
				lang: 'zh-CN', // default: 'en-US'
				toolbar: [
			          ['style', ['style']],
			          //['font', ['bold', 'italic', 'underline', 'clear']],
			          ['font', ['bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'clear']],
			          ['fontname', ['fontname']],
			          ['fontsize', ['fontsize']],
			          ['color', ['color']],
			          ['para', ['ul', 'ol', 'paragraph']],
			          ['height', ['height']],
			          ['table', ['table']],
			          ['insert', ['link','picture','hr']],
			          ['insert', ['link','hr']],
			          ['view', ['fullscreen', 'codeview']],
			          ['ext', ['preview']]
			  	]
			});
		});
	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
