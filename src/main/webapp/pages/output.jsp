<%@ page import="java.util.*" %>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table border="1" bgcolor="green">
<tr>
<th>EMPNO</th>
<th>ENAME</th>
<th>SAL</th>

</tr>
<c:if test="${!empty alien2}">
<c:forEach items="${alien2}" var="e">
<tr>
<td><c:out value="${e.id}"/></td>
<td><c:out value="${e.name}"/></td>
<td><c:out value="${e.lang}"/></td>

</tr>
</c:forEach>
</c:if>
</table>




</body>

</html>