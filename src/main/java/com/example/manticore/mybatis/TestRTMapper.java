package com.example.manticore.mybatis;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TestRTMapper {

	@Insert("INSERT INTO testrt(title, content, gid) VALUES( #{title}, #{content},#{gid})")
	@Options(useGeneratedKeys = true, flushCache = Options.FlushCachePolicy.TRUE)
	public int saveTestRT(TestRT testRT);

	@Select("SELECT * FROM testrt WHERE MATCH(#{matcher})")
	List<TestRT> findTestRT(String matcher);

	@Select("SELECT * FROM testrt WHERE MATCH('list of acer') AND gid > #{gidGT}  ORDER BY gid DESC")
	List<TestRT> findGidGtAndOrderByGid(Long gidGT);

	@Delete("TRUNCATE TABLE testrt")
	Integer truncate();

	@Insert("COMMIT")
	Integer commit();

	@Insert("ROLLBACK")
	Integer rollback();
}
