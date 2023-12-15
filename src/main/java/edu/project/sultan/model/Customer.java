package edu.project.sultan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer implements Serializable {
    @Id

    private String id;


    private String fullName;


    private String email;

    private String password;


    @PrePersist
    public void ensureId() {
        if (id == null) {
            id = String.valueOf(UUID.randomUUID());
        }
    }


}