package cookie.payment.mapper;

import cookie.payment.model.Payment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface PaymentMapper {
    @Select("select * from \"payment\"")
    List<Payment> readAll();

    @Select("select #{id} from \"payment\"")
    void paymentRead(Payment payment);

    @Update("UPDATE \"payment\" SET name=#{name}, cash =#{cash} WHERE id =#{id}")
    void paymentUpdate(Payment payment);

    @Insert("INSERT INTO \"payment\" (id, name, cash) VALUES(#{id}, #{name}, #{cash})")
    void paymentCreate(Payment payment);

    @Delete("DELETE FROM \"payment\" WHERE id=#{id}")
    void paymentDelete(int payment);
}
