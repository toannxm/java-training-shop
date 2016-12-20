package vn.smartdev.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.smartdev.category.dao.entity.Category;
import vn.smartdev.category.manager.CategoryServices;
import vn.smartdev.invoice.dao.model.CartModel;
import vn.smartdev.invoice.dao.entity.Invoice;
import vn.smartdev.invoice.dao.entity.InvoiceDetail;
import vn.smartdev.invoice.dao.model.InvoiceModel;
import vn.smartdev.invoice.manager.InvoiceDetailService;
import vn.smartdev.invoice.manager.InvoiceService;
import vn.smartdev.product.dao.entity.Product;
import vn.smartdev.product.manager.ProductServices;
import vn.smartdev.product.manager.SendEmailSevices;

@Controller
@RequestMapping(value = "/")
public class CheckoutController {
	private static Logger logger = LoggerFactory.getLogger(CheckoutController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	private CategoryServices categoryServices;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	SendEmailSevices sendEmailSevices;
	@Autowired
	private ProductServices productServices;

	@Autowired
	InvoiceDetailService invoiceDetailService;
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(ModelMap modelMap) {
		modelMap.addAttribute("invoiceModel", new InvoiceModel());
		modelMap.addAttribute("invoice",new Invoice());
		return "checkoutPage";
	}
	@ModelAttribute
	public void listAllCategory(ModelMap modelMap){
		List<Category> listCategory = categoryServices.getListCategory();
		List<Product> listProduct = productServices.getListProduct();
		modelMap.addAttribute("listCategory", listCategory);
		modelMap.addAttribute("listProduct",listProduct);
	}
	@RequestMapping(value = "/confirmCheckout", method = RequestMethod.POST)
	public String checkoutAdd(@ModelAttribute InvoiceModel invoiceModel,
			BindingResult bindingResult,
			ModelMap modelMap, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {
		@SuppressWarnings("unchecked")
		List<CartModel> carts = (List<CartModel>)session.getAttribute("cartSession");
		String username = request.getUserPrincipal().getName();
		if (bindingResult.hasErrors()) {
			// handle error
			logger.error("===Got error");
			return "checkoutPage";
		} else {
			logger.info("=== No error");
			// insert into database
		}
		try {
			invoiceModel.setUsername(username);
//			Invoice invoiceAdd = new Invoice(Calendar.getInstance().getTime(),
//					email, phone, "1", username, firstName, lastName, address, city, null);
			invoiceService.save(invoiceModel,carts);
			sendEmailSevices.sendEmail(invoiceModel.getEmail());
			session.removeAttribute("cartSession");
			session.removeAttribute("countItem");
			session.removeAttribute("total");
		} catch (NullPointerException e) {
			// TODO: handle exception
			return "cartPage";
		}
		return "redirect:/";
	}
}