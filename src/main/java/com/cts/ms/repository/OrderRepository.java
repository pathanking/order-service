package com.cts.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cts.ms.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
