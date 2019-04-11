package mybatis;

import com.google.gson.Gson;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 参考
 * https://segmentfault.com/a/1190000002978047
 * */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyJsonHandler extends BaseTypeHandler<Object> {
    Gson gson = new Gson();

    /**
     * 自定义的json数据和类名的分隔符
     * */
    private static final char SPLIT = '/';

    private Object jsonToObject(String json) {
        if (json == null) {
            return null;
        }

        int index = json.lastIndexOf(SPLIT);

        if (index < 0) {
            return null;
        }

        String key = json.substring(index + 1);
        json = json.substring(0, index);

        Class<?> cls = null;
        try {
            cls = Class.forName(key);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Object ob = gson.fromJson(json, cls);
        return ob;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        if(o == null){
            preparedStatement.setString(i, null);
            return;
        }
        String json = gson.toJson(o);
        json  = json + SPLIT + o.getClass().getName();
        preparedStatement.setString(i, json);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String json = resultSet.getString(s);
        return jsonToObject(json);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String json = resultSet.getString(i);
        return jsonToObject(json);
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String json = callableStatement.getString(i);
        return jsonToObject(json);
    }
}
