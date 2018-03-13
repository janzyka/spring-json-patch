package com.zyka.spring.test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Enumeration of task types
 */
@Data
@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_ID_SEQ")
    @SequenceGenerator(name="TASK_ID_SEQ", sequenceName = "TASK_ID_SEQ")
    private long id;

    /**
     * Short name
     */
    @NotNull
    private String name;

    @NotNull
    private double effort;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp someDate;

    public Task(String name, double effort) {
        this.name = name;
        this.effort = effort;
    }
}
