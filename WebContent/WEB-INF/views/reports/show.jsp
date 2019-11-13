<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null }">
                <h2>日報　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${report.employee.name }" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value='${report.report_date }'  pattern='yyyy-MM-dd' /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${report.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${report.created_at }" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                        </tr>
                         <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${report.updated_at }" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                        </tr>
                    </tbody>
                </table><br /><br/>


                <h2>コメント　一覧</h2>
                <table id="comment_list">
                    <tbody>
                        <tr>
                            <th class="comment_name">氏名</th>
                            <th class="comment_date">日付</th>
                            <th class="comment_tatile">タイトル</th>
                            <th class="comment_action">操作</th>
                        </tr>
                        <c:forEach var="comment" items="${comments }" varStatus="status">
                            <tr class="row${status.count % 2 }">
                                <td class="comment_name"><c:out value="${comment.name}" /></td>
                                <td class="comment_data"><fmt:formatDate value='${comment.comment_date }' pattern='yyy-MM-dd'/></td>
                                <td class="comment_tatile">${comment.title }</td>
                                <td class="comment_action"><a href="<c:url value='/comments/show?id=${comment.id }' />">詳細を見る</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div id="pagination">
                    （全 ${comments_count } 件） <br />
                    <c:forEach var="i" begin="1" end="${((comments_count - 1 ) / 15 ) + 1 }" step="1">
                        <c:choose>
                            <c:when test="${i == page }">
                                <c:out value="${i }" />&nbsp;
                            </c:when>
                            <c:otherwise>
                                <a href="<c:url value='/reports/show?page=${i }' />"><c:out value="${i }" /></a>&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>


                <c:if test="${sessionScope.login_employee.id == report.employee.id }">
                    <p><a href="<c:url value='/reports/edit?id=${report.id}' />">この日報を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/comments/new' />">コメントを入力する</a></p>
        <p><a href="<c:url value='/reports/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>