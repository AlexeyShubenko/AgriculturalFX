package com.agricultural.dao.tractordrivers;

import com.agricultural.dao.HibernateUtil;
import com.agricultural.domains.gectarniyvirobitok.DriverDataHectare;
import com.agricultural.domains.gectarniyvirobitok.HectareTable;
import com.agricultural.domains.hoursvirobitok.DriverDataHour;
import com.agricultural.domains.hoursvirobitok.HourTable;
import com.agricultural.domains.main.DateAndInformation;
import com.agricultural.domains.main.Employee;
import com.agricultural.service.DetailInformationService;
import com.agricultural.service.InformationHectareService;
import com.agricultural.service.InformationHourService;
import com.agricultural.service.impl.DetailInformationServiceImpl;
import com.agricultural.service.impl.InformationHectareServiceImpl;
import com.agricultural.service.impl.InformationHourServiceImpl;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alexey on 14.02.2017.
 */
public class TractorDriverDaoImpl implements TractorDriverDao {

    private static TractorDriverDaoImpl instance = new TractorDriverDaoImpl();

    private TractorDriverDaoImpl() {
    }

    public static TractorDriverDaoImpl getInstance() {
        return instance;
    }
    private DetailInformationService detailService = DetailInformationServiceImpl.getInstance();

    private EntityManager session;

    private InformationHectareService infoHectareService = InformationHectareServiceImpl.getInstance();
    private InformationHourService infoHourService = InformationHourServiceImpl.getInstance();

    @Override
    public void createOrUpdateTractorDriver(Employee employee) {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        try{
            tx.begin();
            session.merge(employee);
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteTractorDriver(Employee driver) {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        System.out.println("in delete method");

        List<DateAndInformation> dateAndInformation = infoHectareService.getListDateAndAllInformationByDriverId(driver.getId());
        for(DateAndInformation d: dateAndInformation) {
            infoHectareService.deleteDateAndInformation(d.getDate_id());
        }


        List<HectareTable> hectareTables = new ArrayList<>();
        for (HectareTable hectaretable:hectareTables){
            infoHectareService.deleteHectareHourTable(hectaretable.getHect_id(),"hectare");
        }
        List<HourTable> hourTables = new ArrayList<>();
        for (HourTable hourTable:hourTables){
            infoHectareService.deleteHectareHourTable(hourTable.getHour_id(),"hour");
        }

        List<DriverDataHectare> driverDataHectares = new ArrayList<>();
        if(driverDataHectares!=null)
        for (int i = 0; i < hectareTables.size(); i++) {
            hectareTables.get(i).setHectareData(null);
            for (int j = 0; j < driverDataHectares.size(); j++) {
                driverDataHectares = infoHectareService.getDriverDataHectareByHectareTableId(hectareTables.get(i).getHect_id());
                infoHectareService.deleteDriverDataHectare(driverDataHectares.get(i));
                detailService.deleteDetailDataHectare(driverDataHectares.get(j).getData_id());
            }
        }

        List<DriverDataHour> driverDataHours = new ArrayList<>();
        if(driverDataHours!=null)
        for (int i = 0; i < hourTables.size(); i++) {
            hectareTables.get(i).setHectareData(null);
            for (int j = 0; j < driverDataHours.size(); j++) {
                driverDataHours = infoHourService.getDriverDataHourByHourTableId(hectareTables.get(i).getHect_id());
                infoHourService.deleteDriverDataHour(driverDataHours.get(i));
                detailService.deleteDetailDataHour(driverDataHours.get(j).getData_id());
            }
        }
        try{
            tx.begin();

            session.createQuery("delete Employee  where id=:id").setParameter("id",driver.getId()).executeUpdate();


            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void editTractorDriver(Employee driver) {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        try{
            tx.begin();

            session.createQuery("update Employee empl set empl.name=:name, " +
                    "empl.position=:position, " +
                    "empl.wageRate=:wageRate " +
                    " where  empl.id=:id")
                    .setParameter("name",driver.getName())
                    .setParameter("position",driver.getPosition())
                    .setParameter("wageRate",driver.getWageRate())
                    .setParameter("id",driver.getId())
                    .executeUpdate();

            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public List<Employee> getTractorDrivers() {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        List<Employee> allEmployees = null;
        try{
            tx.begin();
            allEmployees = session.createQuery("from Employee").getResultList();
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return allEmployees;
    }

    @Override
    public Employee getTractorDriverByName(String name) {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        Employee driver = null;
        try{
            tx.begin();
            Query<Employee> query = (Query<Employee>) session.createQuery("from Employee where name=:name");
            query.setParameter("name",name);
            driver = query.getSingleResult();
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }

        return driver;
    }

    @Override
    public Employee getEmployeeById(Long driverId) {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        Employee employee = null;

        try{
            tx.begin();
            employee = session.getReference(Employee.class,driverId);
//            employee.getWorkplace();
//            System.out.println(employee.getWorkplace().toString());
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }

        return employee;
    }

    @Override
    public String[] getAllTractorDriversName(){
        ///отримаємо список всіх операцій
        List<Employee> arrayEmployees = getTractorDrivers();
        String[] names = new String [arrayEmployees.size()];
        ///записуєо назви операцій у масив
        for (int i=0;i<names.length;i++){
            names[i] = arrayEmployees.get(i).getName();
        }
        return names;
    }

    @Override
    public boolean isExistEmployee(String emplName, String emplPosition) {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        Employee employee = null;

        try{
            tx.begin();
            employee = session.createQuery("from Employee e where e.name=:name and " +
                    "e.position=:position", Employee.class).
                    setParameter("name", emplName)
                    .setParameter("position",emplPosition)
                    .getSingleResult();
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
        //not null -> exist
        return Objects.isNull(employee);
    }


}
