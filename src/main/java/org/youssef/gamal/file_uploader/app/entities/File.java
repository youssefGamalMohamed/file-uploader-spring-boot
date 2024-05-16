package org.youssef.gamal.file_uploader.app.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "file_type_id", referencedColumnName = "id")
    private Type type;

    @Lob
    private byte[] data;

}
