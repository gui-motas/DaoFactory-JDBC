package models.factory;

import models.Dao.DepartmentDao;
import models.Dao.SellerDao;
import models.entities.Department;
import models.impl.DepartmentDaoJDBC;
import models.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC();
    }

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC();
    }

}
