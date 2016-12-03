package vn.smartdev.invoicetest;import org.fest.assertions.Assertions;import org.junit.Before;import org.junit.Test;import org.junit.runner.RunWith;import org.mockito.*;import org.mockito.invocation.InvocationOnMock;import org.mockito.runners.MockitoJUnitRunner;import org.mockito.stubbing.Answer;import vn.smartdev.invoice.dao.entity.Invoice;import vn.smartdev.invoice.dao.repository.InvoiceRepository;import vn.smartdev.invoice.manager.InvoiceServiceImpl;import java.util.List;/** * Created by Nguyen on 30/11/2016. */@RunWith(MockitoJUnitRunner.class)public class InvoiceTest extends PrepareInvoice{    @InjectMocks    InvoiceServiceImpl invoiceServiceImpl;    @Mock    InvoiceRepository invoiceRepository;    @Before    public void setUp()    {        MockitoAnnotations.initMocks(this);        invoiceMap = prepareinvoice(10);        Mockito.when(invoiceRepository.findAll()).thenAnswer(new Answer<List<Invoice>>() {            @Override            public List<Invoice> answer(InvocationOnMock invocationOnMock) throws Throwable {                List<Invoice> listInvoice = getListinvoice(invoiceMap);                return listInvoice;            }        });        Mockito.when(invoiceRepository.findOne("invoice 1")).thenReturn(new Invoice("invoice of 1"));    }    @Test    public void shouldReturn10WhenGetListinvoice(){        List<Invoice> invoices = invoiceServiceImpl.getAll();        Assertions.assertThat(invoices.size()).isEqualTo(10);    }    @Test    public void shouldReturninvoice2WhenGetinvoiceByUsername(){        Invoice invoice = invoiceServiceImpl.findById("invoice 1");        Assertions.assertThat(invoice.getUsername()).isEqualTo("invoice of 1");    }    @Test    public void shouldReturninvoiceOf1WhenDeleteinvoiceByUsername(){        ArgumentCaptor<Invoice> invoiceStringArgumentCaptor = ArgumentCaptor.forClass(Invoice.class);        Invoice invoice = new Invoice("invoice of 1");        invoiceServiceImpl.deleteByinvoice(invoice);        Mockito.verify(invoiceRepository, Mockito.times(1)).delete(invoiceStringArgumentCaptor.capture());        Invoice invoiceCheck = invoiceStringArgumentCaptor.getValue();        Assertions.assertThat(invoiceCheck.getUsername()).isEqualTo("invoice of 1");    }    @Test    public void shouldReturninvoiceOf2WhenSaveinvoiceByUsername(){        ArgumentCaptor<Invoice> invoiceStringArgumentCaptor = ArgumentCaptor.forClass(Invoice.class);        Invoice invoice = new Invoice("invoice of 2");        invoiceServiceImpl.save(invoice);        Mockito.verify(invoiceRepository, Mockito.times(1)).save(invoiceStringArgumentCaptor.capture());        Invoice invoiceCheck = invoiceStringArgumentCaptor.getValue();        Assertions.assertThat(invoiceCheck.getUsername()).isEqualTo("invoice of 2");    }}