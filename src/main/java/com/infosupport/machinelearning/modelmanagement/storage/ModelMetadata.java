package com.infosupport.machinelearning.modelmanagement.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "models")
public class ModelMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int version;
    private String name;
    private Date dateCreated;

    public ModelMetadata(String name, int version, Date dateCreated) {
        this.version = version;
        this.name = name;
        this.dateCreated = dateCreated;
    }
}
