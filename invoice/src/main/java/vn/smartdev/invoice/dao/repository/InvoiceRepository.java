package vn.smartdev.invoice.dao.repository;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.jpa.repository.Query;import org.springframework.stereotype.Repository;import vn.smartdev.invoice.dao.entity.Invoice;import java.util.List;@Repositorypublic interface InvoiceRepository extends JpaRepository<Invoice, String> {    List<Invoice> findByUsername(String username);}