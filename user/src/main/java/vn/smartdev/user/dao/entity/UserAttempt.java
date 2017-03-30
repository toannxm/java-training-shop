package vn.smartdev.user.dao.entity;import vn.smartdev.core.jpa.auditing.AbstractAuditableEntity;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.Table;import java.io.Serializable;import java.util.Date;import java.util.UUID;@Entity@Table(name="user_attempts")public class UserAttempt extends AbstractAuditableEntity<String> implements Serializable {    private static final long serialVersionUID = 1L;    private int attempts = 0;    private Date lastModified;    //bi-directional many-to-one association to UserEntity    @Column(name="username")    private String username;    public UserAttempt() {        setId(UUID.randomUUID().toString());    }    public UserAttempt(String username, int attempts, Date lastModified) {        setId(UUID.randomUUID().toString());        this.username = username;        this.attempts = attempts;        this.lastModified = lastModified;    }    public UserAttempt( String username, int attempts) {        super();        this.username = username;        this.attempts = attempts;    }    public int getAttempts() {        return this.attempts;    }    public void setAttempts(int attempts) {        this.attempts = attempts;    }    public Date getLastModified() {        return this.lastModified;    }    public void setLastModified(Date lastModified) {        this.lastModified = lastModified;    }    public String getUsername() {        return username;    }    public void setUsername(String username) {        this.username = username;    }}