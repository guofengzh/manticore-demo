package com.example.manticore.jpa;

import org.apache.ibatis.annotations.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestRTJPARepository extends JpaRepository<TestRTJPA, Long> {

    @Modifying
    @Query(value = "INSERT INTO testrt(title, content, gid) VALUES( :#{#testRT.title}, :#{#testRT.content}, :#{#testRT.gid})", nativeQuery = true)
    @Options(useGeneratedKeys = true, flushCache = Options.FlushCachePolicy.TRUE)
    public int saveTestRT(TestRTJPA testRT);

    @Query(value = "SELECT * FROM testrt WHERE MATCH(:matcher)", nativeQuery = true)
    List<TestRTJPA> findTestRT(String matcher);

    @Query(value = "SELECT * FROM testrt WHERE MATCH('list of acer') AND gid > :gidGT  ORDER BY gid DESC", nativeQuery = true)
    List<TestRTJPA> findGidGtAndOrderByGid(Long gidGT);

    @Modifying
    @Query(value = "TRUNCATE TABLE testrt", nativeQuery = true)
    void deleteAll();
}
