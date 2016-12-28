package vn.smartdev.controller;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.ModelAttribute;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;import vn.smartdev.category.dao.entity.Category;import vn.smartdev.category.services.CategoryServices;import vn.smartdev.invoice.dao.model.CartModel;import vn.smartdev.product.dao.entity.Discount;import vn.smartdev.product.dao.entity.Product;import vn.smartdev.product.dao.entity.ProductDetail;import vn.smartdev.product.services.DiscountServices;import vn.smartdev.product.services.ProductDetailServices;import vn.smartdev.product.services.ProductServices;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpSession;import java.sql.Date;import java.util.ArrayList;import java.util.List;/** * Created by Nguyen on 11/12/2016. */@Controller@RequestMapping(value = "/")public class CartController {    @Autowired    private CategoryServices categoryServices;    /**     * Simply selects the home view to render by returning its name.     */    @Autowired    ProductDetailServices productDetailServices;    @Autowired    DiscountServices discountServices;    @Autowired    ProductServices productServices;    @RequestMapping(value = "/cart/removeCartInHome", method = RequestMethod.GET)    public String removeCartInHome(@RequestParam(value = "product_id") String id, HttpSession session) {        @SuppressWarnings("unchecked")        List<CartModel> carts = (List<CartModel>) session.getAttribute("cartSession");        if (carts != null) {            int index = isExisting(id, session);            carts.remove(index);        }        int countItem = (int) session.getAttribute("countItem");        countItem = countItem - 1;        session.setAttribute("countItem", countItem);        session.setAttribute("total", getTotal(carts));        session.setAttribute("discount", getDiscount(carts));        session.setAttribute("cartSession", carts);        return "cartPage";    }    @RequestMapping(value = "/cart/remove", method = RequestMethod.GET)    public String cartRemove(@RequestParam(value = "product_id") String id, HttpSession session) {        @SuppressWarnings("unchecked")        List<CartModel> carts = (List<CartModel>) session.getAttribute("cartSession");        int countItem = 0;        if (!carts.isEmpty()) {            int index = isExisting(id, session);            carts.remove(index);            countItem = (int) session.getAttribute("countItem");            countItem = countItem - 1;        }        session.setAttribute("countItem", countItem);        session.setAttribute("total", getTotal(carts));        session.setAttribute("discount", getDiscount(carts));        session.setAttribute("cartSession", carts);        return "cartPage";    }    @RequestMapping(value = "/cart/update", method = RequestMethod.POST)    public String cartUpdate(HttpServletRequest request, HttpSession session) {        @SuppressWarnings("unchecked")        List<CartModel> carts = (List<CartModel>) session.getAttribute("cartSession");        if (carts.isEmpty()) {            return "cartPage";        } else {            String[] quantity = request.getParameterValues("quantity");            int countItem = (int) session.getAttribute("countItem");            for (int i = 0; i < carts.size(); i++) {                carts.get(i).setQuantity(Integer.parseInt(quantity[i]));            }            session.setAttribute("countItem", countItem);            session.setAttribute("total", getTotal(carts));            session.setAttribute("discount", getDiscount(carts));            session.setAttribute("cartSession", carts);            return "cartPage";        }    }    @RequestMapping(value = "/cart", method = RequestMethod.GET)    public String cart(HttpSession session) {        int discount = 0;        int countItem = 0;        int total = 0;        if (session.getAttribute("cartSession") == null) {            session.setAttribute("total", total);            session.setAttribute("discount", discount);            session.setAttribute("countItem", countItem);        }        return "cartPage";    }    @ModelAttribute("categories")    public List<Category> listAllCategory() {        List<Category> categories = categoryServices.getListCategory();        return categories;    }    @ModelAttribute("products")    public List<Product> listAllProduct() {        List<Product> products = productServices.getListProduct();        return products;    }    @RequestMapping(value = "/cart/addCart", method = RequestMethod.GET)    public String cartAdd(@RequestParam("product_id") String id,                          HttpSession session) {        ProductDetail productDetail = productDetailServices.getProductDetail(id);        Product product = productServices.getProduct(productDetail.getProduct().getId());        Discount discountEntity = discountServices.findByProduct(product);        int countItem = (int) session.getAttribute("countItem");        int discount = 0;        int quantity = 0;        List<CartModel> carts = new ArrayList<CartModel>();        if (session.getAttribute("cartSession") == null) {            quantity++;            countItem++;            if (discountEntity != null) {                discount = discountEntity.getDiscount();            }            carts.add(new CartModel(productDetail, quantity, discount));        } else {            carts = (List<CartModel>) session.getAttribute("cartSession");            quantity++;            if (discountEntity != null && getTimeCurrent(discountEntity.getStartDate(), discountEntity.getEndDate())) {                discount = discountEntity.getDiscount();            }            int index = isExisting(id, session);            if (index == -1) {                countItem++;                carts.add(new CartModel(productDetail, quantity, discount));            } else {                carts.get(index).setQuantity(carts.get(index).getQuantity() + 1);            }        }        session.setAttribute("countItem", countItem);        session.setAttribute("total", getTotal(carts));        session.setAttribute("discount", getDiscount(carts));        session.setAttribute("cartSession", carts);        return "redirect:/";    }    private int isExisting(String id, HttpSession session) {        @SuppressWarnings("unchecked")        List<CartModel> carts = (List<CartModel>) session.getAttribute("cartSession");        for (int i = 0; i < carts.size(); i++) {            if (carts.get(i).getProductDetail().getId().equals(id)) {                return i;            }        }        return -1;    }    public int getDiscount(List<CartModel> list) {        int discount = 0;        for (CartModel cart : list) {            discount += cart.getQuantity() * cart.getProductDetail().getProductDetailPrice() * cart.getDiscount();        }        return discount / 100;    }    public int getTotal(List<CartModel> list) {        int total = 0;        for (CartModel cart : list) {            total += cart.getQuantity() * cart.getProductDetail().getProductDetailPrice();        }        return total;    }    public boolean getTimeCurrent(Date startDate, Date endDate) {        Date today = new Date(System.currentTimeMillis());        if(today.before(endDate) && today.after(startDate)){            return true;        } else {            return false;        }    }}