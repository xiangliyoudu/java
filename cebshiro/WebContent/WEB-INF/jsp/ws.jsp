<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>index page</title>
</head>
<body>
	Welcome
	<br />
	<input id="text" type="text" />
	<button onclick="send()">发送消息</button>
	<hr />
	<button onclick="closeWebSocket()">关闭WebSocket连接</button>
	<hr />
	<div id="message"></div>
</body>

</html>
