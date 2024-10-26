package com.exam.exam.Services.Impl;

import com.exam.exam.Exceptions.UserBadReq;
import com.exam.exam.Models.Attempt;
import com.exam.exam.Models.User;
import com.exam.exam.Repository.AttemptsRepository;
import com.exam.exam.Services.AttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class AttemptServiceImpl implements AttemptService {
    @Autowired
    private AttemptsRepository attemptsRepository;

    @Override
    public List<Attempt> getAllAttempts() {
        return  attemptsRepository.findAll();
    }

    @Override
    public Attempt getAttemptById(Long id) {
        return attemptsRepository.findById(id).orElseThrow(()-> new UserBadReq("not found! "+id));
    }

    @Override
    public Attempt addAttempt(Attempt attempt) {
        attempt.setDate(LocalDateTime.now());
        return attemptsRepository.save(attempt);
    }

    @Override
    public List<Attempt> getByUserName(User username) {
        return attemptsRepository.findByUser(username);
    }

    @Override
    public List<Attempt> getByUserAndDate(User user) {
        return attemptsRepository.findByUserOrderByDateDesc(user);
    }

    @Override
    public List<Attempt> getByUserAndMaxMarks(User user) {
        return  attemptsRepository.findByUserOrderByTotalMarksDesc(user);
    }


}
