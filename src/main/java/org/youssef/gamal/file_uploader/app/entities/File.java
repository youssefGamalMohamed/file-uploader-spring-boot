package org.youssef.gamal.file_uploader.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "file_type_id", referencedColumnName = "id")
    @JsonBackReference
    private Type type;

    @Column(columnDefinition = "LONGBLOB")
    @Lob
    private byte[] data;

}
