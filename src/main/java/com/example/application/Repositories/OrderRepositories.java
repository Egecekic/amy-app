package com.example.application.Repositories;

import com.example.application.models.OrderHis;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepositories extends CrudRepository<OrderHis,Long> {
}
