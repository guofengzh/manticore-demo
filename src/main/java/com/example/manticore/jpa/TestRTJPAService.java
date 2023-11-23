package com.example.manticore.jpa;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TestRTJPAService {
    private final TestRTJPARepository testRTJPARepository;

    public TestRTJPAService(TestRTJPARepository testRTJPARepository) {
        this.testRTJPARepository = testRTJPARepository;
    }

    @Transactional
    public int save(TestRTJPA testRT) {
        return testRTJPARepository.saveTestRT(testRT);
    }

    @Transactional
    public List<TestRTJPA> findMatched(String textMatcher) {
        return testRTJPARepository.findTestRT(textMatcher);
    }

    @Transactional
    public List<TestRTJPA> findGidGt(Long gid) {
        return testRTJPARepository.findGidGtAndOrderByGid(gid);
    }

    @Transactional
    public void deleteAll() {
        testRTJPARepository.deleteAll();
    }
}
