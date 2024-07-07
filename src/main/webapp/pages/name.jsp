<%@ page import="java.util.*" %>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table border="1" bgcolor="blue">
<tr>
<th>EMPNO</th>
<th>ENAME</th>


</tr>
<c:if test="${!empty name}">

<tr>
<td><c:out value="${id}"/></td>
<td><c:out value="${name}"/></td>
<td><c:out value="${lang}"/></td>


</tr>

</c:if>
</table>




</body>

</html>