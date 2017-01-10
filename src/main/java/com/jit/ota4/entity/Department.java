package com.jit.ota4.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/1/8.
 */
@Entity
@Table(name ="department")
@NamedQuery(name="Department.findDepByDepNO",query="select d from Department d where d.depNo=?1")
public class Department {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name="keyid")
    private String keyId;

    @Column(name="depname")
    private String depName;

    @Column(name="depno")
    private String depNo;



    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo;
    }

    public Department() {
    }
}
