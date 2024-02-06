package com.kristal.springmongo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class TodoModel {
    @Id
    private String id;
    private String todo;
    private String description;
    private Boolean isCompleted;
    private Date createdAt;
    private Date updatedAt;
}
