package com.example.manticore.mybatis;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRTMybatisTest {

    @Autowired
    TestRTService testRTService;

    @Test
    @Order(1)
    public void testSave() {
        int i = testRTService.truncate();
        assertEquals(0, i);

        List<TestRT> emptyList = testRTService.findMatched("Helios");
        assertEquals(0, emptyList.size());

        TestRT testRT = new TestRT();
        testRT.setTitle("List of Acer gaming laptops");
        testRT.setContent("Predator Helios Nitro");
        testRT.setGid(150);
        int n = testRTService.save(testRT);
        assertEquals(1, n);
    }

    @Test
    @Order(2)
    public void testFindAll() {

        List<TestRT> testRTList = testRTService.findMatched("List of Acer");
        assertEquals(1, testRTList.size());
        assertEquals("List of Acer gaming laptops", testRTList.get(0).getTitle());

        testRTList = testRTService.findGidGt(5L);
        assertEquals(1, testRTList.size());
    }
}
