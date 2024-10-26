package com.exam.exam.Services;

import com.exam.exam.Models.Attempt;
import com.exam.exam.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttemptService {
    public List<Attempt> getAllAttempts();
    public Attempt getAttemptById(Long id);
    public Attempt addAttempt(Attempt attempt);

    public List<Attempt> getByUserName(User username);
    public List<Attempt> getByUserAndDate(User user);
    public List<Attempt> getByUserAndMaxMarks(User user);
}
