package com.sharebookssystem.pan.dao;

import com.sharebookssystem.model.User;
import com.sharebookssystem.pan.myinterface.UserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {
    @Resource(name = "sessionFactory")
    SessionFactory sessionFactory;

    @Override
    public List queryUserByN_I_P(User user, int page) {
        Session session = null;
        String hql = "from User as u where u.userName like ? and u.userIdentity like ? and " +
                "u.userPermission between ? and ? " +
                "order by u.userId desc";

        try {
            session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery(hql);


            //设置HQL中查询语句的参数
            if (user.getUserName() == null) query.setParameter(0, "%");
            else query.setParameter(0, "%" + user.getUserName() + "%");
            if (user.getUserIdentity() == null) query.setParameter(1, "%");
            else query.setParameter(1, "%" + user.getUserIdentity() + "%");
            if (user.getUserPermission() == -1) query.setParameter(3, 4);
            else query.setParameter(3, user.getUserPermission());
            query.setParameter(2, 0);

            //根据分页设置从哪里开始返回数据
            page = page * 10;
            query.setFirstResult(page);
            query.setMaxResults(10);

            List<User> list = query.list();

            transaction.commit();
            session.close();

            System.out.println("执行了一次查询");

            if (list.size() == 0) return null;
            else return list;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查询用户失败");
            session.close();
            return null;
        }
    }

    @Override
    public int updateByUser(User user) {
        Session session = null;

        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.update(user);

            transaction.commit();
            session.close();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("更新用户失败");
            session.close();
            return 0;
        }
    }

    @Override
    public int saveByUser(User user) {
        Session session = null;
        int sure = 0;

        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            sure = (int)session.save(user);

            transaction.commit();
            session.close();
            return sure;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("新增用户失败");
            session.close();
            return sure;
        }
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
