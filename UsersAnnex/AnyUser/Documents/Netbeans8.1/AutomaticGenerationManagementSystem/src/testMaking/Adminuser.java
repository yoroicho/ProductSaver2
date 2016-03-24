/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 00499
 */
@Entity
@Table(name = "adminuser", catalog = "unix", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adminuser.findAll", query = "SELECT a FROM Adminuser a"),
    @NamedQuery(name = "Adminuser.findById", query = "SELECT a FROM Adminuser a WHERE a.id = :id"),
    @NamedQuery(name = "Adminuser.findByCompanyName", query = "SELECT a FROM Adminuser a WHERE a.companyName = :companyName"),
    @NamedQuery(name = "Adminuser.findByCompanyNameAlphabet", query = "SELECT a FROM Adminuser a WHERE a.companyNameAlphabet = :companyNameAlphabet"),
    @NamedQuery(name = "Adminuser.findByCompanyNameHiragana", query = "SELECT a FROM Adminuser a WHERE a.companyNameHiragana = :companyNameHiragana"),
    @NamedQuery(name = "Adminuser.findByEmail", query = "SELECT a FROM Adminuser a WHERE a.email = :email"),
    @NamedQuery(name = "Adminuser.findByEndDay", query = "SELECT a FROM Adminuser a WHERE a.endDay = :endDay"),
    @NamedQuery(name = "Adminuser.findByFamilyName", query = "SELECT a FROM Adminuser a WHERE a.familyName = :familyName"),
    @NamedQuery(name = "Adminuser.findByFamilyNameAlphabet", query = "SELECT a FROM Adminuser a WHERE a.familyNameAlphabet = :familyNameAlphabet"),
    @NamedQuery(name = "Adminuser.findByFamilyNameHiragana", query = "SELECT a FROM Adminuser a WHERE a.familyNameHiragana = :familyNameHiragana"),
    @NamedQuery(name = "Adminuser.findByGivenName", query = "SELECT a FROM Adminuser a WHERE a.givenName = :givenName"),
    @NamedQuery(name = "Adminuser.findByGivenNameAlphabet", query = "SELECT a FROM Adminuser a WHERE a.givenNameAlphabet = :givenNameAlphabet"),
    @NamedQuery(name = "Adminuser.findByGivenNameHiragana", query = "SELECT a FROM Adminuser a WHERE a.givenNameHiragana = :givenNameHiragana"),
    @NamedQuery(name = "Adminuser.findByPassword", query = "SELECT a FROM Adminuser a WHERE a.password = :password"),
    @NamedQuery(name = "Adminuser.findByStartDay", query = "SELECT a FROM Adminuser a WHERE a.startDay = :startDay")})
public class Adminuser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "companyName", nullable = false, length = 120)
    private String companyName;
    @Column(name = "companyNameAlphabet", length = 240)
    private String companyNameAlphabet;
    @Column(name = "companyNameHiragana", length = 120)
    private String companyNameHiragana;
    @Column(name = "email", length = 120)
    private String email;
    @Column(name = "endDay")
    @Temporal(TemporalType.DATE)
    private Date endDay;
    @Column(name = "familyName", length = 60)
    private String familyName;
    @Column(name = "familyNameAlphabet", length = 120)
    private String familyNameAlphabet;
    @Column(name = "familyNameHiragana", length = 60)
    private String familyNameHiragana;
    @Column(name = "givenName", length = 60)
    private String givenName;
    @Column(name = "givenNameAlphabet", length = 120)
    private String givenNameAlphabet;
    @Column(name = "givenNameHiragana", length = 60)
    private String givenNameHiragana;
    @Column(name = "password", length = 100)
    private String password;
    @Column(name = "startDay")
    @Temporal(TemporalType.DATE)
    private Date startDay;

    public Adminuser() {
    }

    public Adminuser(Integer id) {
        this.id = id;
    }

    public Adminuser(Integer id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNameAlphabet() {
        return companyNameAlphabet;
    }

    public void setCompanyNameAlphabet(String companyNameAlphabet) {
        this.companyNameAlphabet = companyNameAlphabet;
    }

    public String getCompanyNameHiragana() {
        return companyNameHiragana;
    }

    public void setCompanyNameHiragana(String companyNameHiragana) {
        this.companyNameHiragana = companyNameHiragana;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyNameAlphabet() {
        return familyNameAlphabet;
    }

    public void setFamilyNameAlphabet(String familyNameAlphabet) {
        this.familyNameAlphabet = familyNameAlphabet;
    }

    public String getFamilyNameHiragana() {
        return familyNameHiragana;
    }

    public void setFamilyNameHiragana(String familyNameHiragana) {
        this.familyNameHiragana = familyNameHiragana;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getGivenNameAlphabet() {
        return givenNameAlphabet;
    }

    public void setGivenNameAlphabet(String givenNameAlphabet) {
        this.givenNameAlphabet = givenNameAlphabet;
    }

    public String getGivenNameHiragana() {
        return givenNameHiragana;
    }

    public void setGivenNameHiragana(String givenNameHiragana) {
        this.givenNameHiragana = givenNameHiragana;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adminuser)) {
            return false;
        }
        Adminuser other = (Adminuser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testMaking.Adminuser[ id=" + id + " ]";
    }
    
}
