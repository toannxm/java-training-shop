package vn.smartdev.invoice.services;import vn.smartdev.invoice.dao.entity.InvoiceDetail;import vn.smartdev.invoice.exception.InvoiceDetailNotFoundException;import vn.smartdev.invoice.exception.InvoiceNotFoundException;import java.util.List;public interface InvoiceDetailServices {    List<InvoiceDetail> getAll() throws Exception;    void save(InvoiceDetail invoiceDetail) throws Exception;    void delete(String invoiceDetailId) throws InvoiceDetailNotFoundException;    List<InvoiceDetail> getByInvoiceId(String invoiceId) throws InvoiceDetailNotFoundException, InvoiceNotFoundException;}