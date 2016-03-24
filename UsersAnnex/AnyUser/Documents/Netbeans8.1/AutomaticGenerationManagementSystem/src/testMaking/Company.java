/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 00499
 */
@Entity
@Table(name = "company", catalog = "unix", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByNumberB", query = "SELECT c FROM Company c WHERE c.numberB = :numberB"),
    @NamedQuery(name = "Company.findByNameB", query = "SELECT c FROM Company c WHERE c.nameB = :nameB"),
    @NamedQuery(name = "Company.findByTelephone", query = "SELECT c FROM Company c WHERE c.telephone = :telephone")})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUMBER_B", nullable = false)
    private Integer numberB;
    @Column(name = "NAME_B", length = 255)
    private String nameB;
    @Column(name = "TELEPHONE", length = 255)
    private String telephone;
    @OneToMany(mappedBy = "companyNumberB")
    private Collection<Employee> employeeCollection;

    public Company() {
    }

    public Company(Integer numberB) {
        this.numberB = numberB;
    }

    public Integer getNumberB() {
        return numberB;
    }

    public void setNumberB(Integer numberB) {
        this.numberB = numberB;
    }

    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @XmlTransient
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numberB != null ? numberB.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.numberB == null && other.numberB != null) || (this.numberB != null && !this.numberB.equals(other.numberB))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testMaking.Company[ numberB=" + numberB + " ]";
    }
    
}
