package org.hrd.spring.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.hrd.spring.entities.Tag;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository {
	
	@Insert("<script>"
			+ " INSERT INTO article)tags ("
			+ " article_id,"
			+ " tag_id,"
			+ ") VALUES "
			+ "<foreach collection='tags' item='t' separator=','>"
			+ "("
			+ " #{a},"
			+ " #{t.id}"
			+ ")"
			+ "</foreach>"
			+ "</script>")
	boolean saveTags(@Param("tags") List<Tag> tags, @Param("a") int articleId);
}
