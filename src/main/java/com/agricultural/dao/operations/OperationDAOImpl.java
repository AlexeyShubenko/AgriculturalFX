package com.agricultural.dao.operations;

import com.agricultural.dao.HibernateUtil;
import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.domains.main.TechnologicalOperation;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alexey on 14.02.2017.
 */
public class OperationDAOImpl implements OperationsDAO {

    private static OperationDAOImpl instance = new OperationDAOImpl();

    private OperationDAOImpl() {
    }

    public static OperationDAOImpl getInstance() {
        return instance;
    }

    private EntityManager session;

    public void createOperation(String operationName) {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        TechnologicalOperation operation = new TechnologicalOperation();
        operation.setName(operationName);
        try{
            tx.begin();
            session.persist(operation);
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    public void deleteOperation(TechnologicalOperation operation) {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        try{
            tx.begin();
            session.createQuery("delete TechnologicalOperation where operationId=:id")
                    .setParameter("id",operation.getOperationId()).executeUpdate();
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }

    }

    public void editOperation(TechnologicalOperation operation) {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        try{
            tx.begin();
//            TechnologicalOperation oldOperation =
//                    (TechnologicalOperation) session.createQuery("from TechnologicalOperation where operationId=:id")
//                    .setParameter("id",operation.getOperationId())
////                    .setParameter("name",operation.getName())
//                    .getSingleResult();
//            oldOperation.setName(operation.getName());
            session.createQuery("update TechnologicalOperation set name=:name where operationId=:id")
                    .setParameter("id",operation.getOperationId())
                    .setParameter("name",operation.getName()).executeUpdate();
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    public List<TechnologicalOperation> getOperations() {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        List<TechnologicalOperation> allOperations = null;try{
            tx.begin();
            Query<TechnologicalOperation> query = (Query<TechnologicalOperation>) session.createQuery("from TechnologicalOperation");
            allOperations = query.list();
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }


        return allOperations;
    }

    public String[] getAllOperationsName(){
        ///отримаємо список всіх операцій
        List<TechnologicalOperation> arrayOperations = getOperations();
        String[] names = new String [arrayOperations.size()];
        ///записуєо назви операцій у масив
        for (int i=0;i<names.length;i++){
            names[i] = arrayOperations.get(i).getName();
        }
        return names;
    }

    @Override
    public TechnologicalOperation getOperationByName(String operationName) {
        session = HibernateUtil.getSessionFactory().createEntityManager();
        EntityTransaction tx = session.getTransaction();
        TechnologicalOperation operation = null;
        try{
            tx.begin();
            Query<TechnologicalOperation> query = (Query<TechnologicalOperation>) session.createQuery("from TechnologicalOperation where name=:name");
            query.setParameter("name",operationName);
            operation =  query.getSingleResult();
            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return operation;
    }

    @Override
    public boolean isExistOperation(String operationName) {
       TechnologicalOperation operation = getOperationByName(operationName);
       //if exist -> true
        return Objects.nonNull(operation);
    }


}
