package vn.smartdev.invoice.dao.model;import org.hibernate.validator.constraints.Email;import org.hibernate.validator.constraints.NotBlank;import vn.smartdev.invoice.dao.entity.InvoiceDetail;import vn.smartdev.invoice.dao.entity.Status;import javax.persistence.Column;import javax.persistence.EnumType;import javax.persistence.Enumerated;import javax.persistence.OneToMany;import java.util.Date;import java.util.List;import java.util.UUID;/** * Created by Nguyen on 12/12/2016. */public class InvoiceModel {    private static final long serialVersionUID = 1L;    private String address;    private String email;    private String phone;    private String username;    private String firstName;    private String lastName;    private String city;    private Status status;    private Date invoiceDate;    private List<InvoiceDetail> invoiceDetails;    public InvoiceModel() {    }    public InvoiceModel(String username) {        this.username = username;    }    public InvoiceModel(String address, String email, String phone, String username, String firstName, String lastName, String city, Status status, Date invoiceDate, List<InvoiceDetail> invoiceDetails) {        this.address = address;        this.email = email;        this.phone = phone;        this.username = username;        this.firstName = firstName;        this.lastName = lastName;        this.city = city;        this.status = status;        this.invoiceDate = invoiceDate;        this.invoiceDetails = invoiceDetails;    }    public String getAddress() {        return address;    }    public void setAddress(String address) {        this.address = address;    }    public String getEmail() {        return email;    }    public void setEmail(String email) {        this.email = email;    }    public String getPhone() {        return phone;    }    public void setPhone(String phone) {        this.phone = phone;    }    public String getUsername() {        return username;    }    public void setUsername(String username) {        this.username = username;    }    public String getFirstName() {        return firstName;    }    public void setFirstName(String firstName) {        this.firstName = firstName;    }    public String getLastName() {        return lastName;    }    public void setLastName(String lastName) {        this.lastName = lastName;    }    public String getCity() {        return city;    }    public void setCity(String city) {        this.city = city;    }    public Status getStatus() {        return status;    }    public void setStatus(Status status) {        this.status = status;    }    public Date getInvoiceDate() {        return invoiceDate;    }    public void setInvoiceDate(Date invoiceDate) {        this.invoiceDate = invoiceDate;    }    public List<InvoiceDetail> getInvoiceDetails() {        return invoiceDetails;    }    public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {        this.invoiceDetails = invoiceDetails;    }}