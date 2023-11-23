package com.example.manticore.mybatis;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TestRTService {
    private final TestRTMapper testRTMapper;

    public TestRTService(TestRTMapper testRTMapper) {
        this.testRTMapper = testRTMapper;
    }

    @Transactional
    public int save(TestRT testRT) {
        return testRTMapper.saveTestRT(testRT);
    }

    @Transactional
    public List<TestRT> findMatched(String textMatcher) {
        return testRTMapper.findTestRT(textMatcher);
    }

    @Transactional
    public List<TestRT> findGidGt(Long gid) {
        return testRTMapper.findGidGtAndOrderByGid(gid);
    }

    public int truncate() {
        return testRTMapper.truncate();
    }

    public int commit() {
        return testRTMapper.commit();
    }
}
