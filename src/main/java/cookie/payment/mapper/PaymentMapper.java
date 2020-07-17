package cookie.payment.mapper;

import cookie.payment.model.Payments;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaymentMapper {
    @Select("select * from \"payment\"")
    List<Payments> findAll();

    @Update("UPDATE \"payment\" SET name=#{name}, cash =#{cash} WHERE id =#{id}")
    void updatePayments(Payments payment);

    @Insert("INSERT INTO \"payment\" (id, name, cash) VALUES(#{id}, #{name}, #{cash})")
    void paymentCreate(Payments payment);

    @Delete("DELETE FROM \"payment\" WHERE id=#{id}")
    void paymentDelete(Payments payment);
}
