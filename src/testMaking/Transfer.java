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
@Table(name = "transfer", catalog = "unix", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t"),
    @NamedQuery(name = "Transfer.findById", query = "SELECT t FROM Transfer t WHERE t.id = :id"),
    @NamedQuery(name = "Transfer.findByRemark", query = "SELECT t FROM Transfer t WHERE t.remark = :remark"),
    @NamedQuery(name = "Transfer.findBySendMailIdCompletionConfirmationDate", query = "SELECT t FROM Transfer t WHERE t.sendMailIdCompletionConfirmationDate = :sendMailIdCompletionConfirmationDate"),
    @NamedQuery(name = "Transfer.findBySendMailIdDate", query = "SELECT t FROM Transfer t WHERE t.sendMailIdDate = :sendMailIdDate"),
    @NamedQuery(name = "Transfer.findBySettingCompletionConfirmationDate", query = "SELECT t FROM Transfer t WHERE t.settingCompletionConfirmationDate = :settingCompletionConfirmationDate"),
    @NamedQuery(name = "Transfer.findBySettingDate", query = "SELECT t FROM Transfer t WHERE t.settingDate = :settingDate"),
    @NamedQuery(name = "Transfer.findByWorkStartCompletionConfirmationDate", query = "SELECT t FROM Transfer t WHERE t.workStartCompletionConfirmationDate = :workStartCompletionConfirmationDate"),
    @NamedQuery(name = "Transfer.findByWorkStartDate", query = "SELECT t FROM Transfer t WHERE t.workStartDate = :workStartDate"),
    @NamedQuery(name = "Transfer.findByMembersstartdate", query = "SELECT t FROM Transfer t WHERE t.membersstartdate = :membersstartdate"),
    @NamedQuery(name = "Transfer.findByMembersid", query = "SELECT t FROM Transfer t WHERE t.membersid = :membersid"),
    @NamedQuery(name = "Transfer.findByNOWTEAMid", query = "SELECT t FROM Transfer t WHERE t.nOWTEAMid = :nOWTEAMid"),
    @NamedQuery(name = "Transfer.findByWILLTEAMid", query = "SELECT t FROM Transfer t WHERE t.wILLTEAMid = :wILLTEAMid"),
    @NamedQuery(name = "Transfer.findByGuid", query = "SELECT t FROM Transfer t WHERE t.guid = :guid")})
public class Transfer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "remark", length = 255)
    private String remark;
    @Column(name = "sendMailIdCompletionConfirmationDate")
    @Temporal(TemporalType.DATE)
    private Date sendMailIdCompletionConfirmationDate;
    @Column(name = "sendMailIdDate")
    @Temporal(TemporalType.DATE)
    private Date sendMailIdDate;
    @Column(name = "settingCompletionConfirmationDate")
    @Temporal(TemporalType.DATE)
    private Date settingCompletionConfirmationDate;
    @Column(name = "settingDate")
    @Temporal(TemporalType.DATE)
    private Date settingDate;
    @Column(name = "workStartCompletionConfirmationDate")
    @Temporal(TemporalType.DATE)
    private Date workStartCompletionConfirmationDate;
    @Column(name = "workStartDate")
    @Temporal(TemporalType.DATE)
    private Date workStartDate;
    @Column(name = "Members_start_date")
    @Temporal(TemporalType.DATE)
    private Date membersstartdate;
    @Column(name = "Members_id")
    private Integer membersid;
    @Column(name = "NOW_TEAM_id")
    private Integer nOWTEAMid;
    @Column(name = "WILL_TEAM_id")
    private Integer wILLTEAMid;
    @Column(name = "GUID", length = 50)
    private String guid;

    public Transfer() {
    }

    public Transfer(Integer id) {
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

    public Date getSendMailIdCompletionConfirmationDate() {
        return sendMailIdCompletionConfirmationDate;
    }

    public void setSendMailIdCompletionConfirmationDate(Date sendMailIdCompletionConfirmationDate) {
        this.sendMailIdCompletionConfirmationDate = sendMailIdCompletionConfirmationDate;
    }

    public Date getSendMailIdDate() {
        return sendMailIdDate;
    }

    public void setSendMailIdDate(Date sendMailIdDate) {
        this.sendMailIdDate = sendMailIdDate;
    }

    public Date getSettingCompletionConfirmationDate() {
        return settingCompletionConfirmationDate;
    }

    public void setSettingCompletionConfirmationDate(Date settingCompletionConfirmationDate) {
        this.settingCompletionConfirmationDate = settingCompletionConfirmationDate;
    }

    public Date getSettingDate() {
        return settingDate;
    }

    public void setSettingDate(Date settingDate) {
        this.settingDate = settingDate;
    }

    public Date getWorkStartCompletionConfirmationDate() {
        return workStartCompletionConfirmationDate;
    }

    public void setWorkStartCompletionConfirmationDate(Date workStartCompletionConfirmationDate) {
        this.workStartCompletionConfirmationDate = workStartCompletionConfirmationDate;
    }

    public Date getWorkStartDate() {
        return workStartDate;
    }

    public void setWorkStartDate(Date workStartDate) {
        this.workStartDate = workStartDate;
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testMaking.Transfer[ id=" + id + " ]";
    }
    
}
