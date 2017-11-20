<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:set var="context" value="${pageContext.request.contextPath}"/>
<spring:message code="secondDomain" var="secondDomain"/>

<script>
    var contextUrl = '${pageContext.request.contextPath}' + '/' + '${secondDomain}' + '/products';
</script>

<div>
    <div class="container-fluid">
        <div class="col-md-12">Products</div>
        <table class="table">
            <tr>
                <div class="col-md-8">
                    <th class="col-md-3">Supplier</th>
                    <th class="col-md-3">Model</th>
                    <th class="col-md-1">Quantity</th>
                    <th class="col-md-3">Price</th>
                    <th class="col-md-1"></th>
                    <th class="col-md-1"></th>
                </div>
            </tr>
            <script>
                function callAlert(id) {
                    alert(productId);
                }
            </script>
            <c:forEach var="product" items="${products}" varStatus="status">
                <tr class="info">
                    <div class="col-md-12">
                        <td class="col-md-3" id="productSupplier${product.productId}">${product.supplier}</td>
                        <td class="col-md-3" id="productModel${product.productId}">${product.model}</td>
                        <td id="productCount${product.productId}"
                            class="col-md-1">${basket.getCurrentQuantity(product)}</td>
                        <td class="col-md-3" id="productPrice${product.productId}">${product.price}</td>
                        <security:authorize access="hasRole('ROLE_USER')">
                            <td class="col-md-1"><input id="${product.productId}" class="btn-primary addProductBtn"
                                                        type="button" title="Add 1 to basket" value="+"/></td>
                            <td class="col-md-1"><input id="${product.productId}" class="btn-primary reduceProductBtn"
                                                        type="button" title="Remove 1 from basket" value="-"/></td>
                        </security:authorize>
                        <security:authorize access="hasRole('ROLE_ADMIN')">
                            <form method="post">
                                <td class="col-md-3"><input type="text" value="${product.supplier}" name="supplier"/>
                                </td>
                                <td class="col-md-3"><input type="text" value="${product.model}" name="model"/></td>
                                <td class="col-md-1"></td>
                                <td class="col-md-3"><input type="text" value="${product.price}" name="price"/></td>
                                <input type="hidden" value="${product.productId}" name="productId">
                                <td class="col-md-1"><input id="${product.productId}" class="btn-primary putProductBtn"
                                                            type="submit" title="Update product" value="Update"
                                                            formaction="${context}/${secondDomain}/products/update"/>
                                </td>
                                <td class="col-md-1"><input id="${product.productId}"
                                                            class="btn-primary deleteProductBtn" type="submit"
                                                            title="Delete product" value="Delete"
                                                            formaction="${context}/${secondDomain}/products/delete"/>
                                </td>
                                <c:if test="${not empty param.page}">
                                    <input type="hidden" value="${param.page}" name="pageNumber">
                                </c:if>

                            </form>
                        </security:authorize>

                    </div>
                </tr>
            </c:forEach>
        </table>

        <nav aria-label="Page navigation example">
            <ul class="pagination">

                <c:set var="paramPage" value="${param.page}"/>
                <c:choose>
                    <c:when test="${not empty paramPage and paramPage gt 0}">
                        <c:url var="thisUrlPrevious" value="products">
                            <c:param name="page" value="${paramPage-1}"></c:param>
                        </c:url>
                    </c:when>
                    <c:otherwise>
                        <c:url var="thisUrlPrevious" value="products"/>
                    </c:otherwise>
                </c:choose>
                <li class="page-item"><a class="page-link" href="${thisUrlPrevious}">Previous</a></li>

                <c:if test="${totalPages gt 0}">
                    <c:forEach begin="0" end="${totalPages-1}" varStatus="loop">
                        <c:url var="thisUrlforEach" value="products">
                            <c:param name="page" value="${loop.index}"></c:param>
                        </c:url>
                        <li class="page-item"><a class="page-link" href="${thisUrlforEach}">${loop.index}</a></li>
                    </c:forEach>
                </c:if>

                <c:choose>
                    <c:when test="${not empty paramPage}">
                        <c:choose>
                            <c:when test="${paramPage lt (totalPages-1)}">
                                <c:url var="thisUrlNext" value="products">
                                    <c:param name="page" value="${paramPage + 1}"></c:param>
                                </c:url>
                            </c:when>
                            <c:otherwise>
                                <c:url var="thisUrlNext" value="products">
                                    <c:param name="page" value="${paramPage}"></c:param>
                                </c:url>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <c:url var="thisUrlNext" value="products">
                            <c:param name="page" value="1"></c:param>
                        </c:url>
                    </c:otherwise>
                </c:choose>
                <li class="page-item"><a class="page-link" href="${thisUrlNext}">Next</a></li>
            </ul>
        </nav>

        <security:authorize access="hasRole('ROLE_USER')">
            <form action="${context}/${secondDomain}/orders/add" method="post">

                <button class="btn btn-block btn-primary" type="submit">${orderButtonName}</button>
            </form>
        </security:authorize>

        <security:authorize access="hasRole('ROLE_ADMIN')">
            <div class="container">
                <div class="row">
                    <form class="form" method="post" action="${context}/${secondDomain}/products/add">
                        <div class="col-xs-12">
                            <div class="form-inline">
                                <input type="text" size="50" required="required" name="supplier" placeholder="Supplier">
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="form-group">
                                <input type="text" size="50" required="required" name="model" placeholder="Model">
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="form-group">
                                <input type="number" size="10" required="required" name="price" placeholder="Price">
                            </div>
                        </div>
                        <c:if test="${not empty param.page}">
                            <input type="hidden" value="${param.page}" name="pageNumber">
                        </c:if>
                        <button class="btn btn-block btn-primary" type="submit">Add product</button>
                    </form>
                </div>
            </div>
        </security:authorize>

    </div>


</div>