<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員管理</title>
<body>
	<h1>会員一覧</h1>
	<p><a href="logout">ログアウト</a></p>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>名前</th>
			<th>年齢</th>
			<th>住所</th>
			<th>会員種別</th>
			<th>登録日</th>
			<th>編集</th>
			<th>削除</th>
		</tr>
		<c:forEach items="${memberList}" var="member">
			<tr>
				<td><c:out value="${member.id}" /></td>
				<td><c:out value="${member.name}" /></td>
				<td><c:out value="${member.age}" /></td>
				<td><c:out value="${member.address}" /></td>
				<td><c:out value="${member.typeName}" /></td>
				<td><c:out value="${member.created}" /></td>
							    <td><a href="editMember?id=<c:out value="${member.id}" />">編集</a></td>
				<td><a href="deleteMember?id=<c:out value="${member.id}" />">削除</a></td>
			</tr>
		</c:forEach>
	</table>
	<p>
	<a href="addMember">会員追加</a>
	</p>
</body>
</html>
