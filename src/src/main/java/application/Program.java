package application;

import models.Dao.DepartmentDao;
import models.Dao.SellerDao;
import models.entities.Department;
import models.entities.Seller;
import models.factory.DaoFactory;

import static models.factory.DaoFactory.createDepartmentDao;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        DepartmentDao departmentDao = createDepartmentDao();

    }
}
