<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="breadcrumb">
    <div class="container">
        <div class="breadcrumb-inner">
            <ul class="list-inline list-unstyled">
                <li><a href="#">Home</a></li>
                <li class='active'>Shopping Cart</li>
            </ul>
        </div><!-- /.breadcrumb-inner -->
    </div><!-- /.container -->
</div>
<!-- /.breadcrumb -->
<div class="body-content outer-top-xs">
    <div class="container">
        <div class="row ">
            <div class="shopping-cart">
                <div class="shopping-cart-table ">
                    <div class="table-responsive">
                        <form action="${contextPath }/cart/update" method="POST">
                            <table class="table" id="shopping-cart-table">
                                <thead>
                                <tr>
                                    <th class="cart-remove item">Remove</th>
                                    <th class="cart-description item">Image</th>
                                    <th class="cart-product-name item">Product Name</th>
                                    <th class="cart-qty item">Quantity</th>
                                    <th class="cart-sub-total item">Price</th>
                                    <th class="cart-sub-total item">Discount</th>
                                    <th class="cart-total last-item">Total</th>
                                </tr>
                                </thead><!-- /thead -->
                                <tfoot>
                                <tr>
                                    <td colspan="7">
                                        <div class="shopping-cart-btn">
							    <span class="">
								    <a href="/" class="btn btn-upper btn-primary outer-left-xs">Continue Shopping</a>
                                    <c:if test="${not empty sessionScope.cartSession}">
								    <input type="submit" value="Update shopping cart"
                                           class="btn btn-upper btn-primary pull-right outer-right-xs"/>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </c:if>
                                </span>
                                        </div><!-- /.shopping-cart-btn -->
                                    </td>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach var="cart" items="${sessionScope.cartSession}">
                                    <tr>
                                        <td class="remove-item"><a
                                                href="${contextPath }/cart/remove?product_id=${cart.productDetail.id}"
                                                title="cancel" class="icon"><i
                                                class="fa fa-trash-o"></i></a></td>
                                        <td class="cart-image">
                                            <a class="entry-thumbnail" href="detail.html">
                                                <img src="/uploaded-image/${cart.productDetail.productImages.get(0).url}"
                                                     alt="">
                                            </a>
                                        </td>
                                        <td class="cart-product-name-info">
                                            <h4 class='cart-product-description'><a
                                                    href="detail.html">${cart.productDetail.productDetailName}</a>
                                            </h4>
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <div class="rating rateit-small"></div>
                                                </div>
                                                <div class="col-sm-8">
                                                    <div class="reviews">
                                                        (06 Reviews)
                                                    </div>
                                                </div>
                                            </div><!-- /.row -->
                                            <div class="cart-product-info">
                                                <span class="product-color">COLOR:<span>Blue</span></span>
                                            </div>
                                        </td>
                                        <td class="cart-product-quantity">
                                            <div class="quant-input">
                                                <input style="padding-left: 10px;" type="number" name="quantity" id="quantity" min="1" max="${cart.productDetail.productDetailQuantity}" name="quantity"
                                                       value="${cart.quantity}">
                                            </div>
                                        </td>
                                        <td class="cart-product-sub-total"><span
                                                class="cart-sub-total-price">${cart.productDetail.productDetailPrice} $</span>
                                        </td>
                                        <td class="cart-product-sub-total"><span
                                                class="cart-sub-total-price">${cart.discount} %</span>
                                        </td>
                                        <td class="cart-product-grand-total"><span
                                                class="cart-grand-total-price">${cart.productDetail.productDetailPrice * cart.quantity - (cart.productDetail.productDetailPrice * cart.quantity * cart.discount) / 100} $</span>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody><!-- /tbody -->
                            </table><!-- /table -->
                        </form>
                    </div>
                </div><!-- /.shopping-cart-table -->
                <div class="col-md-4 col-sm-12 estimate-ship-tax">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                <span class="estimate-title">Estimate shipping and tax</span>
                                <p>Enter your destination to get shipping and tax.</p>
                            </th>
                        </tr>
                        </thead><!-- /thead -->
                        <tbody>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label class="info-title control-label">Country <span>*</span></label>
                                    <select class="form-control unicase-form-control selectpicker">
                                        <option>--Select options--</option>
                                        <option>India</option>
                                        <option>SriLanka</option>
                                        <option>united kingdom</option>
                                        <option>saudi arabia</option>
                                        <option>united arab emirates</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="info-title control-label">State/Province <span>*</span></label>
                                    <select class="form-control unicase-form-control selectpicker">
                                        <option>--Select options--</option>
                                        <option>TamilNadu</option>
                                        <option>Kerala</option>
                                        <option>Andhra Pradesh</option>
                                        <option>Karnataka</option>
                                        <option>Madhya Pradesh</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="info-title control-label">Zip/Postal Code</label>
                                    <input type="text" class="form-control unicase-form-control text-input"
                                           placeholder="">
                                </div>
                                <div class="pull-right">
                                    <button type="submit" class="btn-upper btn btn-primary">GET A QOUTE</button>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div><!-- /.estimate-ship-tax -->

                <div class="col-md-4 col-sm-12 estimate-ship-tax">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                <span class="estimate-title">Discount Code</span>
                                <p>Enter your coupon code if you have one..</p>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <input type="text" class="form-control unicase-form-control text-input"
                                           placeholder="You Coupon..">
                                </div>
                                <div class="clearfix pull-right">
                                    <button type="submit" class="btn-upper btn btn-primary">APPLY COUPON</button>
                                </div>
                            </td>
                        </tr>
                        </tbody><!-- /tbody -->
                    </table><!-- /table -->
                </div><!-- /.estimate-ship-tax -->

                <div class="col-md-4 col-sm-12 cart-shopping-total">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                <div class="cart-grand-total">
                                    <h5 style="text-align: left"> Before : </h5> <span class="inner-left-md"
                                                                                       style="text-align: right">${sessionScope.total} $</span>
                                </div>
                                <div class="cart-grand-total">
                                    <h5 style="text-align: left"> Discount : </h5> <span class="inner-left-md"
                                                                                         style="text-align: right"> - ${sessionScope.discount} $</span></h5>
                                </div>
                                <div class="cart-grand-total">
                                    <h5 style="text-align: left"> Toal : </h5><span class="inner-left-md"
                                                                                    style="text-align: right;">${sessionScope.total - (sessionScope.discount)} $</span></h5>
                                </div>
                            </th>
                        </tr>
                        </thead><!-- /thead -->
                        <tbody>
                        <tr>
                            <td>
                                <div class="cart-checkout-btn pull-right">
                                    <a type="submit"
                                       onclick="checkOut()"
                                            <c:if test="${not empty sessionScope.cartSession}">
                                                href="${contextPath}/checkout"
                                            </c:if>
                                       class="btn btn-primary checkout-btn">PROCCED TO CHEKOUT</a>
                                    <span class="">Checkout with multiples address!</span>
                                </div>
                            </td>
                        </tr>
                        </tbody><!-- /tbody -->
                    </table><!-- /table -->
                </div><!-- /.cart-shopping-total -->
            </div><!-- /.shopping-cart -->
        </div> <!-- /.row -->
    </div><!-- /.container -->
</div>
<!-- /.body-content -->
<script>
    function checkOut() {
        if(${empty sessionScope.cartSession}) {
            return alert('Cart null !');
        }
    }
</script>