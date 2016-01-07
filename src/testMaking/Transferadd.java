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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 00499
 */
@Entity
@Table(name = "transferadd", catalog = "unix", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transferadd.findAll", query = "SELECT t FROM Transferadd t"),
    @NamedQuery(name = "Transferadd.findById", query = "SELECT t FROM Transferadd t WHERE t.id = :id"),
    @NamedQuery(name = "Transferadd.findByRemark", query = "SELECT t FROM Transferadd t WHERE t.remark = :remark"),
    @NamedQuery(name = "Transferadd.findBySendMailIdDate", query = "SELECT t FROM Transferadd t WHERE t.sendMailIdDate = :sendMailIdDate"),
    @NamedQuery(name = "Transferadd.findBySettingDate", query = "SELECT t FROM Transferadd t WHERE t.settingDate = :settingDate"),
    @NamedQuery(name = "Transferadd.findByWorkStartDate", query = "SELECT t FROM Transferadd t WHERE t.workStartDate = :workStartDate"),
    @NamedQuery(name = "Transferadd.findByMastertranstestId", query = "SELECT t FROM Transferadd t WHERE t.mastertranstestId = :mastertranstestId"),
    @NamedQuery(name = "Transferadd.findByMembersstartdate", query = "SELECT t FROM Transferadd t WHERE t.membersstartdate = :membersstartdate"),
    @NamedQuery(name = "Transferadd.findByMembersid", query = "SELECT t FROM Transferadd t WHERE t.membersid = :membersid"),
    @NamedQuery(name = "Transferadd.findByNOWTEAMid", query = "SELECT t FROM Transferadd t WHERE t.nOWTEAMid = :nOWTEAMid"),
    @NamedQuery(name = "Transferadd.findByWILLTEAMid", query = "SELECT t FROM Transferadd t WHERE t.wILLTEAMid = :wILLTEAMid")})
public class Transferadd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "remark", length = 255)
    private String remark;
    @Column(name = "sendMailIdDate")
    @Temporal(TemporalType.DATE)
    private Date sendMailIdDate;
    @Column(name = "settingDate")
    @Temporal(TemporalType.DATE)
    private Date settingDate;
    @Column(name = "workStartDate")
    @Temporal(TemporalType.DATE)
    private Date workStartDate;
    @Column(name = "MASTERTRANSTEST_ID")
    private Integer mastertranstestId;
    @Column(name = "Members_start_date")
    @Temporal(TemporalType.DATE)
    private Date membersstartdate;
    @Column(name = "Members_id")
    private Integer membersid;
    @Column(name = "NOW_TEAM_id")
    private Integer nOWTEAMid;
    @Column(name = "WILL_TEAM_id")
    private Integer wILLTEAMid;

    public Transferadd() {
    }

    public Transferadd(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getSendMailIdDate() {
        return sendMailIdDate;
    }

    public void setSendMailIdDate(Date sendMailIdDate) {
        this.sendMailIdDate = sendMailIdDate;
    }

    public Date getSettingDate() {
        return settingDate;
    }

    public void setSettingDate(Date settingDate) {
        this.settingDate = settingDate;
    }

    public Date getWorkStartDate() {
        return workStartDate;
    }

    public void setWorkStartDate(Date workStartDate) {
        this.workStartDate = workStartDate;
    }

    public Integer getMastertranstestId() {
        return mastertranstestId;
    }

    public void setMastertranstestId(Integer mastertranstestId) {
        this.mastertranstestId = mastertranstestId;
    }

    public Date getMembersstartdate() {
        return membersstartdate;
    }

    public void setMembersstartdate(Date membersstartdate) {
        this.membersstartdate = membersstartdate;
    }

    public Integer getMembersid() {
        return membersid;
    }

    public void setMembersid(Integer membersid) {
        this.membersid = membersid;
    }

    public Integer getNOWTEAMid() {
        return nOWTEAMid;
    }

    public void setNOWTEAMid(Integer nOWTEAMid) {
        this.nOWTEAMid = nOWTEAMid;
    }

    public Integer getWILLTEAMid() {
        return wILLTEAMid;
    }

    public void setWILLTEAMid(Integer wILLTEAMid) {
        this.wILLTEAMid = wILLTEAMid;
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
        if (!(object instanceof Transferadd)) {
            return false;
        }
        Transferadd other = (Transferadd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testMaking.Transferadd[ id=" + id + " ]";
    }
    
}
