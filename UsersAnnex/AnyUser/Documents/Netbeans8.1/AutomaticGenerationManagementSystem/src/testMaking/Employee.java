/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 00499
 */
@Entity
@Table(name = "employee", catalog = "unix", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByNumber", query = "SELECT e FROM Employee e WHERE e.number = :number"),
    @NamedQuery(name = "Employee.findByCompanyNumber", query = "SELECT e FROM Employee e WHERE e.companyNumber = :companyNumber"),
    @NamedQuery(name = "Employee.findByMail", query = "SELECT e FROM Employee e WHERE e.mail = :mail"),
    @NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE e.name = :name")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUMBER", nullable = false)
    private Integer number;
    @Column(name = "COMPANY_NUMBER")
    private Integer companyNumber;
    @Column(name = "MAIL", length = 255)
    private String mail;
    @Column(name = "NAME", length = 255)
    private String name;
    @JoinColumn(name = "COMPANY_NUMBER_B", referencedColumnName = "NUMBER_B")
    @ManyToOne
    private Company companyNumberB;

    public Employee() {
    }

    public Employee(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(Integer companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompanyNumberB() {
        return companyNumberB;
    }

    public void setCompanyNumberB(Company companyNumberB) {
        this.companyNumberB = companyNumberB;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (number != null ? number.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.number == null && other.number != null) || (this.number != null && !this.number.equals(other.number))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testMaking.Employee[ number=" + number + " ]";
    }
    
}
