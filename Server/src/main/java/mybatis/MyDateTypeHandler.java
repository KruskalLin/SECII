package mybatis;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 参考
 * http://blog.csdn.net/u012702547/article/details/54562619
 */
@MappedJdbcTypes({JdbcType.VARCHAR})
@MappedTypes({LocalDateTime.class})
public class MyDateTypeHandler extends BaseTypeHandler<LocalDateTime> {

    private LocalDateTime longToLocalDT(long milsec) {
        LocalDateTime localDT = Instant.ofEpochMilli(milsec).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDT;
    }

    private long LocalDTToLong(LocalDateTime localDT) {
        long localDTInMilli = localDT.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return localDTInMilli;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, LocalDateTime localDateTime, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i, LocalDTToLong(localDateTime));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, String s) throws SQLException {
        long milsec = resultSet.getLong(s);

        return longToLocalDT(milsec);
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, int i) throws SQLException {
        long milsec = resultSet.getLong(i);
        return longToLocalDT(milsec);
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long milsec = callableStatement.getLong(i);
        return longToLocalDT(milsec);
    }
}