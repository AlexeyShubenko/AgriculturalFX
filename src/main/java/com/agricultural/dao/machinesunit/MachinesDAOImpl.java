package com.agricultural.dao.machinesunit;

import com.agricultural.dao.HibernateUtil;
import com.agricultural.domains.main.MachineTractorUnit;
import com.agricultural.exceptions.InternalDBException;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Alexey on 14.02.2017.
 */
public class MachinesDAOImpl implements MachinesDAO {

    private static MachinesDAOImpl instance = new MachinesDAOImpl();

    private MachinesDAOImpl() {
    }

    public static MachinesDAOImpl getInstance() {
        return instance;
    }

    private EntityManager session;

    public Long createMachine(String machineName) throws InternalDBException {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        MachineTractorUnit machine = new MachineTractorUnit();
        machine.setName(machineName);
        Long id;
        try{
            tx.begin();
            session.persist(machine);
            id = machine.getMachineId();
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new InternalDBException("Машино тракторний агрегат не був добавлений! Помилка бази даних!");
        }finally {
            session.close();
        }
        return id;
    }

    public void deleteMachine(MachineTractorUnit machine) throws InternalDBException {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        try{
            tx.begin();
            session.createQuery("delete MachineTractorUnit where machine_id=:id")
                    .setParameter("id",machine.getMachineId()).executeUpdate();
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new InternalDBException("Машино тракторний агрегат не був добавлений! Помилка бази даних!");
        }finally {
            session.close();
        }
    }

    public void editMachine(MachineTractorUnit tractor) throws InternalDBException {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        try{
            tx.begin();
            session.merge(tractor);
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new InternalDBException("Машино тракторний агрегат не був добавлений! Помилка бази даних!");
        }finally {
            session.close();
        }
    }

    public ArrayList<MachineTractorUnit> getMachines() {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        ArrayList<MachineTractorUnit> allMachines = null;
        try{
            tx.begin();
            Query<MachineTractorUnit> query = (Query<MachineTractorUnit>) session.createQuery("from MachineTractorUnit");
            allMachines =  (ArrayList<MachineTractorUnit>) query.list();
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return allMachines;
    }

    public String[] getAllMachinesName(){
        ArrayList<MachineTractorUnit> arrayMachines = getMachines();
        String[] names = new String [arrayMachines.size()];
        for (int i=0;i<names.length;i++){
            names[i] = arrayMachines.get(i).getName();
        }
        return names;
    }

    @Override
    public MachineTractorUnit getMachineByName(String machineName) {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        MachineTractorUnit machine = null;
        try{
            tx.begin();
            Query<MachineTractorUnit> query =
                    (Query<MachineTractorUnit>) session.createQuery("from MachineTractorUnit where name=:name");
            query.setParameter("name",machineName);
            machine =  query.getSingleResult();
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return machine;
    }

    @Override
    public boolean isExistMachine(String machineName) {
        MachineTractorUnit machine = getMachineByName(machineName);
        //if exist -> true
        return Objects.nonNull(machine);
    }

}
