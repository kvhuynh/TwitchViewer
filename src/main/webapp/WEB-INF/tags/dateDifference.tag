<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="h" %>
<%@ attribute name="commentDate" required="true" %>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date"/>    
<%-- <fmt:formatDate value="${now}" pattern="MM/dd/yyyy" /> --%>
<%-- (1000 * 60 * 60 * 24) --%>
<c:set var="timeDifference" value="${(now.time - commentDate)}"/>
<c:choose>
    <%-- check minutes --%>
    <c:when test="${timeDifference >= 60000 && timeDifference < 3600000}">
        <c:choose>           
            <c:when test="${timeDifference / (1000 * 60) > 2}">
                <span title="${commentDate}"><fmt:parseNumber value="${timeDifference / (1000 * 60)}" integerOnly="true" /> minutes ago </span>
            </c:when>
            <c:otherwise>
                <span title="${commentDate}"><fmt:parseNumber value="${timeDifference / (1000 * 60)}" integerOnly="true" /> minute ago </span>
            </c:otherwise>
        </c:choose>
    </c:when>

    <%-- check hours --%>
    <c:when test="${timeDifference >= 3600000 && timeDifference < 86400000 }">
        <c:choose>           
            <c:when test="${timeDifference / (1000 * 60 * 60) > 2}">
                <span title="${commentDate}"><fmt:parseNumber value="${timeDifference / (1000 * 60 * 60)}" integerOnly="true" /> hours ago </span>
            </c:when>
            <c:otherwise>
                <span title="${commentDate}"><fmt:parseNumber value="${timeDifference / (1000 * 60 * 60)}" integerOnly="true" /> hour
                ago </span>
            </c:otherwise>
        </c:choose>
    </c:when>

    <%-- checks days --%>
    <c:when test="${timeDifference >= 86400000 && timeDifference < 604800000}">
        <c:choose>           
            <c:when test="${timeDifference / (1000 * 60 * 60 * 24) > 2}">
                <span title="${commentDate}"><fmt:parseNumber value="${timeDifference / (1000 * 60 * 60 * 24)}" integerOnly="true" /> days ago </span>
            </c:when>
            <c:otherwise>
                <span title="${commentDate}"><fmt:parseNumber value="${timeDifference / (1000 * 60 * 60 * 24)}" integerOnly="true" /> day ago </span>
            </c:otherwise>
        </c:choose>
    </c:when>

    <%-- check weeks --%>
    <c:when test="${timeDifference >= 604800000 && timeDifference <  2628000000}">
        <c:choose>           
            <c:when test="${timeDifference / (1000 * 60 * 60 * 24 * 7) > 2}">
                <span title="${commentDate}"><fmt:parseNumber value="${timeDifference / (1000 * 60 * 60 * 24 * 7)}" integerOnly="true" /> weeks ago </span>
            </c:when>
            <c:otherwise>
                <span title="${commentDate}"><fmt:parseNumber value="${timeDifference / (1000 * 60 * 60 * 24 * 7)}" integerOnly="true" /> week ago </span>
            </c:otherwise>
        </c:choose>
    </c:when>

    <%-- check months --%>
    <c:when test="${timeDifference >= 2628000000 && timeDifference <  31540000000}">
        <c:choose>           
            <c:when test="${timeDifference / (1000 * 60 * 60 * 24 * 7) > 2}">
                <span title="${commentDate}"><fmt:parseNumber value="${timeDifference / (1000 * 60 * 60 * 24 * 7)}" integerOnly="true" /> months ago </span>
            </c:when>
            <c:otherwise>
                <span title="${commentDate}"><fmt:parseNumber value="${timeDifference / (1000 * 60 * 60 * 24 * 7)}" integerOnly="true" /> month ago </span>
            </c:otherwise>
        </c:choose>
    </c:when>

    <%-- check years --%>
    <c:when test="${timeDifference >= 31540000000}">
        <c:choose>           
            <c:when test="${timeDifference / (1000 * 60 * 60 * 24 * 7) > 2}">
                <span title="${commentDate}"><fmt:parseNumber value="${timeDifference / (1000 * 60 * 60 * 24 * 7)}" integerOnly="true" /> years ago </span>
            </c:when>
            <c:otherwise>
                <span title="${commentDate}"><fmt:parseNumber value="${timeDifference / (1000 * 60 * 60 * 24 * 7)}" integerOnly="true" /> year ago </span>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <span title="${commentDate}">Just now</span>
    </c:otherwise>
</c:choose>


