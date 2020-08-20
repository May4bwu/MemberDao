<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員管理</title>
</head>
<body>
	<h1>会員管理システム ログイン</h1>

	<c:if test="${not empty error}">
		<p>ログインIDかパスワードが正しくありません。</p>
	</c:if>

	<form action="" method="post">
		<p>
			<input type="text" name="loginId" placeholder="ログインID" />
		</p>
		<p>
			<input type="password" name="loginPass" placeholder="パスワード" />
		</p>
		<p>
			<input type="submit" value="ログイン" />
		</p>
	</form>
</body>
</html>