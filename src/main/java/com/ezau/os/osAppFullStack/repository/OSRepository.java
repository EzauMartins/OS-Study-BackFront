package com.ezau.os.osAppFullStack.repository;

import com.ezau.os.osAppFullStack.model.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OSRepository extends JpaRepository<OS,Integer>{


}
