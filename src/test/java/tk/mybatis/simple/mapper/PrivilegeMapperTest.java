package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class PrivilegeMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {

            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
            privilegeMapper.selectById(1L);

        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }

    }
}
