package com.infosupport.machinelearning.modelmanagement.models;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Entity
@Table(name="UPLOADED_MODELS")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="model_name", length=100, nullable = false)
    private String name;
    @Column(name = "filepath", nullable = false)
    private String filepath;
    @Column(name = "version", nullable = false)
    private int version;
    @Column(name = "date_published", nullable = false)
    private Date date;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFilepath() {
        return filepath;
    }

    public int getVersion() {
        return version;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", filepath='" + filepath + '\'' +
                ", version=" + version +
                ", date=" + date +
                '}';
    }

    public Model() {
    }

    public Model(String name) {
        this.name = name;
    }

    public Model(String name, String filepath, int version, Date date) {
        this.name = name;
        this.filepath = filepath;
        this.version = version;
        this.date = date;
    }
}
