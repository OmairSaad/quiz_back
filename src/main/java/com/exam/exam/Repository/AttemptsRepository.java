package com.exam.exam.Repository;

import com.exam.exam.Models.Attempt;
import com.exam.exam.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptsRepository extends JpaRepository<Attempt,Long> {
    public List<Attempt> findByUser(User user);
    public List<Attempt> findByUserOrderByTotalMarksDesc(User user);
    public List<Attempt> findByUserOrderByDateDesc(User user);
}
