package com.example.restful_web_services.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//automatically generate getter methods for all the fields in a class.
@Getter
//automatically generate setter methods for all the fields in a class.
@Setter
//particular class is a JPA entity
@Entity
//TO-DO object.
public class Todo {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String description;
    private Date targetDate;
    private boolean isDone;
    private String status;


    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", isDone=" + isDone +
                ", status='" + status + '\'' +
                '}';
    }
}
