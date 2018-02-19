package com.zyka.spring.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATION_ID_SEQ")
    @SequenceGenerator(name="OPERATION_ID_SEQ", sequenceName = "OPERATION_ID_SEQ")
    private long id;

    @JoinColumn(name="task_id")
    @ManyToOne(optional = false)
    private Task task;

    @NotNull
    private String name;

    public Operation(Task task, String name) {
        this.task = task;
        this.name = name;
    }
}
