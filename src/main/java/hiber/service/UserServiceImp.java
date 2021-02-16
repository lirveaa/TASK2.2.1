package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    //private Car car;
  @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }


    @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

    @Override
    public void addCar(Car car) {
        userDao.addCar(car);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> listCar() {
        return userDao.listCar();
    }

    @Override
    public User getUser(String model, int series) {
        return userDao.getUser(model,series);
    }

}
