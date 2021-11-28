package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;

import java.util.Date;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(1L);
            Assert.assertNotNull(role);
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById2() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById2(1L);
            Assert.assertNotNull(role);
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAll();
            Assert.assertNotNull(roleList);
            Assert.assertTrue(roleList.size() > 0);
            Assert.assertNotNull(roleList.get(0).getRoleName());

        } finally {

            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void insert() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
            role.setId(3L);
            role.setRoleName("测试人员");
            role.setEnabled(1);
            role.setCreateBy(1L);
            role.setCreateTime(new Date());

            roleMapper.insert(role);

        } finally {
            sqlSession.commit();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void insert2() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
            role.setRoleName("开发人员");
            role.setEnabled(1);
            role.setCreateBy(1L);
            role.setCreateTime(new Date());

            roleMapper.insert2(role);

        } finally {
            sqlSession.commit();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void insert3() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
            role.setRoleName("需求人员");
            role.setEnabled(1);
            role.setCreateBy(1L);
            role.setCreateTime(new Date());

            roleMapper.insert3(role);

        } finally {
            sqlSession.commit();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void updateById() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
            role.setId(6L);
            role.setRoleName("需求人员2");
            role.setEnabled(2);
            role.setCreateBy(1L);
            role.setCreateTime(new Date());

            roleMapper.updateById(role);

        } finally {
            sqlSession.commit();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void deleteById() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            roleMapper.deleteById(6L);

        } finally {
            sqlSession.commit();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
