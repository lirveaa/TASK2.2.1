package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
@EnableTransactionManagement
public class UserDaoImp implements UserDao {
   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void addCar(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCar() {
      TypedQuery<Car>query = sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   public User getUser(String model, int series){
      String HQL = "FROM Car c LEFT JOIN FETCH c.user WHERE c.model=:modelName AND c.series=:seriesNum";
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(HQL, Car.class);
      query.setParameter("modelName", model);
      query.setParameter("seriesNum",series);
      Car carResult = query.getSingleResult();
      return carResult.getUser();
   }


}
