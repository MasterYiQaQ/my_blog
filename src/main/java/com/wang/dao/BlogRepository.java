package com.wang.dao;

import com.wang.pojo.Blog;
import com.wang.pojo.Tag;
import com.wang.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> , JpaSpecificationExecutor<Blog> {

    @Query("select b from t_blog b where b.recommend=true ")
    List<Blog> findTop(Pageable pageable);

    @Query("select function('date_format',b.updatedate,'%Y') as years from t_blog b group by years order by function('date_format',b.updatedate,'%Y') desc")
    List<String> findGroupYear();

    @Query("select b from t_blog b where function('date_format',b.updatedate,'%Y')=?1")
    List<Blog> findByYear(String year);
}
