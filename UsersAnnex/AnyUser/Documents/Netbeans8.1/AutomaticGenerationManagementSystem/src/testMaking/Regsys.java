/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author 00499
 */
@Entity
@Table(name = "regsys", catalog = "test_swing_jdbc", schema = "")
@NamedQueries({
    @NamedQuery(name = "Regsys.findAll", query = "SELECT r FROM Regsys r"),
    @NamedQuery(name = "Regsys.findByTitle", query = "SELECT r FROM Regsys r WHERE r.title = :title"),
    @NamedQuery(name = "Regsys.findBySysdir", query = "SELECT r FROM Regsys r WHERE r.sysdir = :sysdir"),
    @NamedQuery(name = "Regsys.findByExtension", query = "SELECT r FROM Regsys r WHERE r.extension = :extension"),
    @NamedQuery(name = "Regsys.findByRemark", query = "SELECT r FROM Regsys r WHERE r.remark = :remark")})
public class Regsys implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "sysdir")
    private String sysdir;
    @Basic(optional = false)
    @Column(name = "extension")
    private String extension;
    @Basic(optional = false)
    @Column(name = "remark")
    private String remark;

    public Regsys() {
    }

    public Regsys(String title) {
        this.title = title;
    }

    public Regsys(String title, String sysdir, String extension, String remark) {
        this.title = title;
        this.sysdir = sysdir;
        this.extension = extension;
        this.remark = remark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldTitle = this.title;
        this.title = title;
        changeSupport.firePropertyChange("title", oldTitle, title);
    }

    public String getSysdir() {
        return sysdir;
    }

    public void setSysdir(String sysdir) {
        String oldSysdir = this.sysdir;
        this.sysdir = sysdir;
        changeSupport.firePropertyChange("sysdir", oldSysdir, sysdir);
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        String oldExtension = this.extension;
        this.extension = extension;
        changeSupport.firePropertyChange("extension", oldExtension, extension);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        String oldRemark = this.remark;
        this.remark = remark;
        changeSupport.firePropertyChange("remark", oldRemark, remark);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (title != null ? title.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regsys)) {
            return false;
        }
        Regsys other = (Regsys) object;
        if ((this.title == null && other.title != null) || (this.title != null && !this.title.equals(other.title))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testMaking.Regsys[ title=" + title + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
