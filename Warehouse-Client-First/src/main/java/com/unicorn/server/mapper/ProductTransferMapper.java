package com.unicorn.server.mapper;

import com.unicorn.server.model.ProductTransfer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Create by xuantang
 * @date on 1/9/18
 */
@Mapper
@Service
public interface ProductTransferMapper {

    @Select("SELECT id, num, name, time FROM product_transfer, product" +
            "WHERE p_id = product.id && date > #{start} && date < #{end} && state = #{state}")
    List<ProductTransfer> getProductTransfers(@Param("start") Timestamp start,
                                              @Param("end") Timestamp end,
                                              @Param("state") int state);

    @Insert("INSERT INTO product_transfer(p_id, num, time, type, state) " +
            "VALUES(#{p_id}, #{num}, #{time}, #{type}, #{state})")
    int addProductTransfers(ProductTransfer productTransfer);

    @Update("UPDTAE product_transfer SET state = #{state} WHERE id = #{id}")
    void updateProductTransfer(@Param("id") int id,
                               @Param("state") int state);
}
