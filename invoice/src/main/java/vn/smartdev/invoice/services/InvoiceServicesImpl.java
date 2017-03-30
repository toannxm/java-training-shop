package vn.smartdev.invoice.services;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import vn.smartdev.invoice.dao.entity.Invoice;import vn.smartdev.invoice.dao.entity.InvoiceDetail;import vn.smartdev.invoice.dao.entity.Status;import vn.smartdev.invoice.dao.model.CartModel;import vn.smartdev.invoice.dao.model.InvoiceModel;import vn.smartdev.invoice.exception.InvoiceNotFoundException;import vn.smartdev.invoice.dao.manager.InvoiceManager;import vn.smartdev.product.dao.entity.ProductDetail;import vn.smartdev.product.services.ProductDetailServices;import java.util.Calendar;import java.util.List;@Servicepublic class InvoiceServicesImpl implements InvoiceServices {    @Autowired    private InvoiceManager invoiceManager;    @Autowired    private InvoiceDetailServices invoiceDetailServices;    @Autowired    private ProductDetailServices productDetailServices;    @Override    public List<Invoice> getAll() throws Exception {        return invoiceManager.findAll();    }    @Override    public Invoice findById(String id) throws InvoiceNotFoundException {        return invoiceManager.findById(id);    }    @Override    public void save(InvoiceModel invoiceModel, List<CartModel> cartModels) throws Exception {        Invoice invoice = new Invoice();        invoice.setEmail(invoiceModel.getEmail());        invoice.setAddress(invoiceModel.getAddress());        invoice.setCity(invoiceModel.getCity());        invoice.setFirstName(invoiceModel.getFirstName());        invoice.setLastName(invoiceModel.getLastName());        invoice.setUsername(invoiceModel.getUsername());        invoice.setInvoiceDate(Calendar.getInstance().getTime());        invoice.setPhone(invoiceModel.getPhone());        invoice.setStatus(Status.NotYet);        invoiceManager.save(invoice);        for (CartModel cartModel : cartModels){            InvoiceDetail invoiceDetail = new InvoiceDetail();            invoiceDetail.setInvoice(invoice);            invoiceDetail.setAmount(cartModel.getProductDetail().getProductDetailPrice()*cartModel.getQuantity());            invoiceDetail.setProductDetail(cartModel.getProductDetail());            invoiceDetail.setQuantity(cartModel.getQuantity());            invoiceDetail.setDiscount(cartModel.getDiscount());            invoiceDetailServices.save(invoiceDetail);            ProductDetail productDetail = cartModel.getProductDetail();            int quantity = productDetail.getProductDetailQuantity() - cartModel.getQuantity();            productDetail.setProductDetailQuantity(quantity);            productDetailServices.saveProductDetail(productDetail);        }    }    @Override    public void delete(String id) throws InvoiceNotFoundException {        invoiceManager.delete(id);    }    @Override    public void updateInvoice(Invoice invoice) throws Exception {        invoiceManager.save(invoice);    }}