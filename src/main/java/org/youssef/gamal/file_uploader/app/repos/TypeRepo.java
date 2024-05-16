package org.youssef.gamal.file_uploader.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youssef.gamal.file_uploader.app.entities.Type;

@Repository
public interface TypeRepo extends JpaRepository<Type, Long> {

}
