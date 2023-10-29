package com.example.demo.src.cat;

import com.example.demo.src.cat.model.GetCatRes;
import com.example.demo.src.cat.model.PostCatReq;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CatDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetCatRes> getCats() {
        String getOrderDetailsQuery = "select * from coupang.CAT where status !=?";
        return this.jdbcTemplate.query(getOrderDetailsQuery,
                (rs, rowNum) -> new GetCatRes(
                        rs.getInt("id"),
                        rs.getString("img_id"),
                        rs.getString("url")),
                "INACTIVE"
        );
    }

    public List<GetCatRes> getCatsByImageId(Integer ImageId) {
        String getCatsByImageIdQuery = "select * from coupang.CAT where img_id =? ";
        Integer getCatsByImageIdParams = ImageId;
        return this.jdbcTemplate.query(getCatsByImageIdQuery,
                (rs, rowNum) -> new GetCatRes(
                        rs.getInt("id"),
                        rs.getString("img_id"),
                        rs.getString("url")),
                getCatsByImageIdParams);
    }

    public GetCatRes getCat(int ImageId) {
        String getCatQuery = "select * from coupang.CAT where img_id = ?";
        int getCatParams = ImageId;
        return this.jdbcTemplate.queryForObject(getCatQuery,
                (rs, rowNum) -> new GetCatRes(
                        rs.getInt("id"),
                        rs.getString("img_id"),
                        rs.getString("url")),
                getCatParams);
    }


    public int createCat(PostCatReq postCatReq) {
        String createCatQuery = "insert into coupang.CAT (img_id, url) VALUES (?,?)";
        Object[] createCatParams = new Object[]{
                postCatReq.getImg_id(),
                postCatReq.getUrl()};
        this.jdbcTemplate.update(createCatQuery, createCatParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
    }


    public int deleteCat(int catId) {
        String deleteOrderDetailNameQuery = "update coupang.CAT set status = ? where id = ? ";
        Object[] deleteOrderDetailNameParams = new Object[]{"INACTIVE", catId};

        return this.jdbcTemplate.update(deleteOrderDetailNameQuery, deleteOrderDetailNameParams);
    }


}
