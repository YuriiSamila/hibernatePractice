package org.example.entities;

import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.Clob;

@Entity
@Table(name = "lob")
public class ClobBlob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "t_type")
    private Clob clob;

    @Lob
    @Column(name = "b_type")
    private Blob blob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clob getClob() {
        return clob;
    }

    public void setClob(Clob clob) {
        this.clob = clob;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

}
