package com.example.manticore.jpa;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRTJPATest {

    @Autowired
    TestRTJPAService testRTJPAService;

    @Test
    @Order(1)
    public void testSave() {
        testRTJPAService.deleteAll();

        TestRTJPA testRTJPA = new TestRTJPA();
        testRTJPA.setTitle("List of Acer gaming laptops");
        testRTJPA.setContent("Predator Helios Nitro");
        testRTJPA.setGid(150);
        int n = testRTJPAService.save(testRTJPA);
        assertEquals(1, n);
    }

    @Test
    @Order(2)
    public void testFindAll() {
        List<TestRTJPA> testRTList = testRTJPAService.findMatched("List of Acer");
        assertEquals(1, testRTList.size());
        assertEquals("List of Acer gaming laptops", testRTList.get(0).getTitle());

        testRTList = testRTJPAService.findGidGt(5L);
        assertEquals(1, testRTList.size());
    }
}
