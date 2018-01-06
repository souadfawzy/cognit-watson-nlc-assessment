<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">

<title>Natural Language Classifier Assessment</title>

</head>
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function classify(e) {

		$("#question").val(e.text);
		/*First call only do a btn click because div doesnt exist*/
		if ($("#resultDV").html() == undefined) {

			$('#btn').click();

		} else {
			classifyAjax(e.text);
		}
	}

	function classifyAjax(question) {

		$
				.ajax({
					type : "POST",
					url : window.location + "/ajax",
					ContentType : 'String',
					data : {
						question : question
					},
					success : function(response) {

						$("#resultDV").html('<h4>' + response + '</h4>');

					},
					error : function(e) {

						$("#errorDV")
								.html(
										'<font color="red">There was a problem with the request, please check</font>');
					}
				});
	}
</script>
</head>

<body>

	<div class="container">
		<div class="row text-center">
			<h3>
				<a
					href="https://www.ibm.com/watson/services/natural-language-classifier/">IBM
					Watson Natural Language Classifier</a>
			</h3>
		</div>
		<div id="errorDV">
			<font color="red">${errorMessage}</font>
		</div>


		<h3>Ask a question</h3>
		<form method="post" class="form-group">

			<table>
				<tr>
					<td><input type="text" id="question" name="question" size=80
						class="form-control" required="required"
						value="<%=request.getParameter("question") != null ? request.getParameter("question") : ""%>"
						placeholder="Enter your question or Try a sample question below"></td>
					<td><span></span><input type="submit" value="Ask"
						class="btn btn-success" id='btn'></td>
				</tr>
			</table>

			<div class="page-header">
				<h4>Sample questions</h4>
				<p>
					<a onclick="classify(this);">What is the capital of UAE?</a>
				</p>
				<p>
					<a onclick="classify(this);">What is IBM Watson?</a>
				</p>
				<p>
					<a onclick="classify(this);">What is the temperature outside today?</a>
				</p>
			</div>



			<c:if test="${classifierClass!=null && !classifierClass.isEmpty()}">
				<div class="panel panel-default" style="width: 52%;">
					<div class="panel-heading">Answer</div>
					<div class="panel-body" id="resultDV">
						<h4>${classifierClass}</h4>
					</div>
				</div>
			</c:if>
		</form>
	</div>

</body>
</html>
