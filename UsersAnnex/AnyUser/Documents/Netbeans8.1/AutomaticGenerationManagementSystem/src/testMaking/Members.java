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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "members", catalog = "unix", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Members.findAll", query = "SELECT m FROM Members m"),
    @NamedQuery(name = "Members.findById", query = "SELECT m FROM Members m WHERE m.membersPK.id = :id"),
    @NamedQuery(name = "Members.findByStartDate", query = "SELECT m FROM Members m WHERE m.membersPK.startDate = :startDate"),
    @NamedQuery(name = "Members.findByEndDate", query = "SELECT m FROM Members m WHERE m.endDate = :endDate"),
    @NamedQuery(name = "Members.findByName", query = "SELECT m FROM Members m WHERE m.name = :name"),
    @NamedQuery(name = "Members.findByCardId", query = "SELECT m FROM Members m WHERE m.cardId = :cardId"),
    @NamedQuery(name = "Members.findByWebId", query = "SELECT m FROM Members m WHERE m.webId = :webId"),
    @NamedQuery(name = "Members.findByHiragana", query = "SELECT m FROM Members m WHERE m.hiragana = :hiragana"),
    @NamedQuery(name = "Members.findByAlphabet", query = "SELECT m FROM Members m WHERE m.alphabet = :alphabet"),
    @NamedQuery(name = "Members.findByEmailadd", query = "SELECT m FROM Members m WHERE m.emailadd = :emailadd")})
public class Members implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MembersPK membersPK;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 120)
    private String name;
    @Column(name = "card_id", length = 45)
    private String cardId;
    @Column(name = "web_id", length = 45)
    private String webId;
    @Column(name = "hiragana", length = 120)
    private String hiragana;
    @Column(name = "alphabet", length = 120)
    private String alphabet;
    @Column(name = "emailadd", length = 120)
    private String emailadd;

    public Members() {
    }

    public Members(MembersPK membersPK) {
        this.membersPK = membersPK;
    }

    public Members(MembersPK membersPK, String name) {
        this.membersPK = membersPK;
        this.name = name;
    }

    public Members(int id, Date startDate) {
        this.membersPK = new MembersPK(id, startDate);
    }

    public MembersPK getMembersPK() {
        return membersPK;
    }

    public void setMembersPK(MembersPK membersPK) {
        this.membersPK = membersPK;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public String getEmailadd() {
        return emailadd;
    }

    public void setEmailadd(String emailadd) {
        this.emailadd = emailadd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (membersPK != null ? membersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Members)) {
            return false;
        }
        Members other = (Members) object;
        if ((this.membersPK == null && other.membersPK != null) || (this.membersPK != null && !this.membersPK.equals(other.membersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testMaking.Members[ membersPK=" + membersPK + " ]";
    }
    
}
