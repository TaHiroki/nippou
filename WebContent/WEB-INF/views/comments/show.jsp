<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${comment != null}">
                <h2>コメント 詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td>
                                <c:out value="${comment.name}" />
                            </td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td>
                                <fmt:formatDate value="${comment.comment_date}" pattern="yyyy-MM-dd" />
                            </td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre class="pre"><c:out value="${comment.comment}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${comment.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${comment.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_employee.name == comment.name}">
                    <p><a href="<c:url value="/comments/edit?id=${comment.id}" />">このコメントを編集する</a></p>
                    <form method="POST" action="<c:url value="/comments/destroy" />">
                        <input type="submit" value="削除"  onclick="confirmDestroy();"/>
                        <input type="hidden" name="_token" value="${_token }" />
                        <input type="hidden" name="id" value="${comment.id }" />
                    </form>
                    <script>
                        function confirmDestroy() {
                            if (confirm("本当に削除してよろしいですか？")) {
                                document.forms[1].submit();
                            }
                        }
                    </script>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/reports/show" />">一覧に戻る</a></p>

    </c:param>
</c:import>