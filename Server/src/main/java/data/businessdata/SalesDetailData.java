package data.businessdata;

import dataService.businessdataService.SalesDetailDataService;
import mapper.SalesDetailPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.SalesDetailPO;
import util.SalesDetailInfo;

import java.util.List;

public class SalesDetailData implements SalesDetailDataService{

    @Override
    public List<SalesDetailPO> select(SalesDetailInfo salesDetailInfo){

        List<SalesDetailPO> list;
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            SalesDetailPOMapper mapper = sqlSession.getMapper(SalesDetailPOMapper.class);
            list = mapper.select(salesDetailInfo);
        }
        return list;
    }
}
