package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.Date;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用selectById方法，查询id=1的用户
            SysUser user = userMapper.selectById(1L);
            //user 不为空
            Assert.assertNotNull(user);
            //userName = admin
            Assert.assertEquals("admin", user.getUserName());
        } finally {
            //不要忘记关闭sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testSelectAll() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用selectAll方法，查询所有用户
            List<SysUser> userList = userMapper.selectAll();
            //user 不为空
            Assert.assertNotNull(userList);
            //用户数量大于0个
            Assert.assertTrue(userList.size() > 0);
        } finally {
            //不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void selectRolesByUserId() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用selectAll方法，查询所有用户
            List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
            //user 不为空
            Assert.assertNotNull(roleList);
            //用户数量大于0个
            Assert.assertTrue(roleList.size() > 0);
            for (SysRole role : roleList) {
                System.out.println(role);
            }

        } finally {
            //不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            //正常情况下应该读入一张图片存到byte数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            //将新建的对象插入数据库中，特别注意这里的返回值result是执行的SQL影响的行数
            int result = userMapper.insert(user);
//            sqlSession.commit();
            //只插入1条数据
            Assert.assertEquals(1, result);
            //id为null,没有给id赋值，并且没有配置回写id的值
            Assert.assertNull(user.getId());
        } finally {
            //为了不影响数据库中的数据导致其他测试失败，这里选择回滚
            //由于默认的 sqlSessionFactory.openSession() 是不自动提交的，
            //因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testInsert2() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            //正常情况下应该读入一张图片存到byte数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            //将新建的对象插入数据库中，特别注意这里的返回值result是执行的SQL影响的行数
            int result = userMapper.insert2(user);
            Assert.assertEquals(1, result);
            //id为null,没有给id赋值，并且没有配置回写id的值
            System.out.println(user.getId());
            Assert.assertNull(user.getId());
        } finally {
            //为了不影响数据库中的数据导致其他测试失败，这里选择回滚
            //由于默认的 sqlSessionFactory.openSession() 是不自动提交的，
            //因此不手动执行 commit 也不会提交到数据库
            sqlSession.commit();
//            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询1个user对象
            SysUser user = userMapper.selectById(1L);
            //当前userName为admin
            Assert.assertEquals("admin", user.getUserName());
            //修改用户名
            user.setUserName("admin_test");
            //修改邮箱
            user.setUserEmail("test@mybatis.tk");
            //更新数据，特别注意，这里的返回值result是执行的SQL影响的行数
            int result = userMapper.updateById(user);
            //只更新1条数据
            Assert.assertEquals(1, result);
            // 根据当前id查询修改后的数据
            user = userMapper.selectById(1L);
            //修改后的名字是admin_test
            Assert.assertEquals("admin_test", user.getUserName());
        } finally {
            //为了不影响数据库中的数据导致其他测试失败，这里选择回滚
            //由于默认的 sqlSessionFactory.openSession() 是不自动提交的，
            //因此不手动执行 commit 也不会提交到数据库
//            sqlSession.commit();
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询1个user对象,根据id=1查询
            SysUser user1 = userMapper.selectById(1L);
            //现在还能查出user对象
            Assert.assertNotNull(user1);
            //调用方法删除
            Assert.assertEquals(1, userMapper.deleteById(1L));
            //再次查询，这时应该没有值，为null
            Assert.assertNull(userMapper.selectById(1L));

            //使用SysUser参数再进行一次测试，根据id=1001查询
            SysUser user2 = userMapper.selectById(1001L);
            //现在还能查询出user对象
            Assert.assertNotNull(user2);
            //调用方法删除，注意这里使用参数为user2
            Assert.assertEquals(1, userMapper.deleteById(user2));
            //再次查询，这时应该没有值，为null
            Assert.assertNull(userMapper.selectById(1001L));
            //使用SysUser参数再进行一次测试

        } finally {
            //为了不影响数据库中的数据导致其他测试失败，这里选择回滚
            //由于默认的 sqlSessionFactory.openSession() 是不自动提交的，
            //因此不手动执行 commit 也不会提交到数据库
//            sqlSession.commit();
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testSelectRolesByUserIdAndRoleEnabled() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            SysRole role = new SysRole();
            role.setEnabled(1);
            //调用selectRolesByUserIdAndRoleEnabled方法查询用户的角色
//            List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
            List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled(user, role);
            //结果不为空
            Assert.assertNotNull(userList);
            //角色数量大于0个
            Assert.assertTrue(userList.size() > 0);

        } finally {
            //为了不影响数据库中的数据导致其他测试失败，这里选择回滚
            //由于默认的 sqlSessionFactory.openSession() 是不自动提交的，
            //因此不手动执行 commit 也不会提交到数据库
//            sqlSession.commit();
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
