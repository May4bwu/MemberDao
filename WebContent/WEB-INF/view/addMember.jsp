<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>会員管理</title>
</head>
<body>
	<h1>会員追加</h1>
	<form action="" method="post">
		<table border="1">
			<tr>
				<th>名前</th>
				<td><c:if test="${not empty nameError}">
						<p>
							<c:out value="※${nameError}" />
						</p>
					</c:if> <input type="text" name="name" value="<c:out value="${name}" />" />
				</td>
			</tr>
			<tr>
				<th>年齢</th>
				<td><c:if test="${not empty ageError}">
						<p>
							<c:out value="※${ageError}" />
						</p>
					</c:if> <input type="text" name="age" value="<c:out value="${age}" />" />
				</td>
			</tr>
			<tr>
				<th>住所</th>
				<td><input type="text" name="address"
					value="<c:out value="${address}" />" /></td>
			</tr>
			<tr>
				<th>会員種別</th>
				<td><select name="typeId">
						<option value="1" <c:if test="${typeId == 1}">selected</c:if>>
							通常会員</option>
						<option value="2" <c:if test="${typeId == 2}">selected</c:if>>
							プレミアム会員</option>
				</select></td>
			</tr>
		</table>
		<p>
			<input type="submit" value="保存" />
		</p>
	</form>
</body>
</html>