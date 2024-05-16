package org.youssef.gamal.file_uploader.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youssef.gamal.file_uploader.app.entities.File;

@Repository
public interface FileRepo extends JpaRepository<File, Long> {

}
