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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 00499
 */
@Entity
@Table(name = "teams", catalog = "unix", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name_j"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teams.findAll", query = "SELECT t FROM Teams t"),
    @NamedQuery(name = "Teams.findById", query = "SELECT t FROM Teams t WHERE t.id = :id"),
    @NamedQuery(name = "Teams.findByCode", query = "SELECT t FROM Teams t WHERE t.code = :code"),
    @NamedQuery(name = "Teams.findByNameJ", query = "SELECT t FROM Teams t WHERE t.nameJ = :nameJ"),
    @NamedQuery(name = "Teams.findByDist", query = "SELECT t FROM Teams t WHERE t.dist = :dist"),
    @NamedQuery(name = "Teams.findByRemark", query = "SELECT t FROM Teams t WHERE t.remark = :remark")})
public class Teams implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code", length = 5)
    private String code;
    @Basic(optional = false)
    @Column(name = "name_j", nullable = false, length = 80)
    private String nameJ;
    @Column(name = "dist", length = 5)
    private String dist;
    @Column(name = "remark", length = 200)
    private String remark;

    public Teams() {
    }

    public Teams(Integer id) {
        this.id = id;
    }

    public Teams(Integer id, String nameJ) {
        this.id = id;
        this.nameJ = nameJ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameJ() {
        return nameJ;
    }

    public void setNameJ(String nameJ) {
        this.nameJ = nameJ;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        if (!(object instanceof Teams)) {
            return false;
        }
        Teams other = (Teams) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testMaking.Teams[ id=" + id + " ]";
    }
    
}
