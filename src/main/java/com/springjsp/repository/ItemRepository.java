package com.springjsp.repository;

import com.springjsp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    @Query(value = "select * from Item",nativeQuery = true)
    List<Item> findAllByJQl();

    @Query(value = "SELECT * FROM Item i WHERE i.id = :id",nativeQuery = true)
    Item findItemByJQl(@Param("id") Long id);
}
