package com.Ramaldanov.webcatalog.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@Table(name = "t_files")
@NoArgsConstructor
public class UsersFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String author;
    private Integer year;
    private String articleName;
    @ElementCollection
    private List<String> keyWords;

    private String creatorUsername;
    private String pathToFile;
    private String URI;
}
