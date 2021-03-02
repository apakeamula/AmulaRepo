 package com.fbn.db.jpa;

 import com.fbn.db.jpa.exceptions.NonexistentEntityException;
 import com.fbn.db.jpa.exceptions.PreexistingEntityException;
 import java.io.Serializable;
 import java.util.List;
 import javax.persistence.EntityManager;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.EntityNotFoundException;
 import javax.persistence.Query;
 import javax.persistence.criteria.CriteriaQuery;
 import javax.persistence.criteria.Root;
 import org.apache.log4j.Logger;

 public class RiaDtlTblJpaController
         implements Serializable  {
     private static final Logger logFile = Logger.getLogger(RiaDtlTblJpaController.class);
    
     public RiaDtlTblJpaController(EntityManagerFactory emf)  {
         this.emf = emf;
            }
    
        private EntityManagerFactory emf = null;
    
     public EntityManager getEntityManager()  {
         return this.emf.createEntityManager();
            }
    
     public void create(RiaDtlTbl riaDtlTbl)
             throws PreexistingEntityException, Exception {
         logFile.info("Start Create RiaDtlTbl!!");
         EntityManager em = null;
         try {
             em = getEntityManager();
             em.getTransaction().begin();
             em.persist(riaDtlTbl);
             logFile.info("Persist RiaDtlTbl Insert statement completed");
             em.getTransaction().commit();
             logFile.info("commit RiaDtlTbl Insert statement completed");
                    } catch (Exception ex) {
             if (findRiaDtlTbl(riaDtlTbl.getOrderno()) != null)  {
                 logFile.info("RiaDtlTbl " + riaDtlTbl + " already exists.", ex);
                 throw new PreexistingEntityException("RiaDtlTbl " + riaDtlTbl + " already exists.", ex);
                            }
             throw ex;
                    } finally {
             if (em != null) {
                 em.close();
                            }
                    }
            }
    
     public void edit(RiaDtlTbl riaDtlTbl)
             throws NonexistentEntityException, Exception  {
         EntityManager em = null;
         logFile.info("Edit RiaDtlTbl Code");
         try  {
             em = getEntityManager();
             em.getTransaction().begin();
             riaDtlTbl = (RiaDtlTbl) em.merge(riaDtlTbl);
             logFile.info("Merge RiaDtlTbl Insert statement completed  " + riaDtlTbl.getBatchId());
             em.getTransaction().commit();
             logFile.info("Commit RiaDtlTbl Insert statement completed  " + riaDtlTbl.getBatchId());
                    }catch (Exception ex)  {
             String msg = ex.getLocalizedMessage();
             if ((msg == null) || (msg.length() == 0)) {
                 String id = riaDtlTbl.getOrderno();
                 if (findRiaDtlTbl(id) == null)  {
                     logFile.info("The riaDtlTbl with id " + id + " no longer exists. Exception --" + ex.getMessage());
                     throw new NonexistentEntityException("The riaDtlTbl with id " + id + " no longer exists.");
                                    }
                            }
             throw ex;
                    }  finally {
             if (em != null) {
                 em.close();
                            }
                    }
            }
    
     public void destroy(String id)
             throws NonexistentEntityException  {
         EntityManager em = null;
         logFile.info("Destroy RiaDtlTbl with id  " + id);
         try {
             em = getEntityManager();
             em.getTransaction().begin();
             RiaDtlTbl riaDtlTbl;
             try {
                 riaDtlTbl = (RiaDtlTbl) em.getReference(RiaDtlTbl.class, id);
                 riaDtlTbl.getOrderno();
                 logFile.info("Get RiaDtlTbl record with id  " + id + "successful");
                            } catch (EntityNotFoundException enfe) {
                 logFile.info("The riaDtlTbl with id " + id + " no longer exists." + enfe.getMessage());
                 throw new NonexistentEntityException("The riaDtlTbl with id " + id + " no longer exists.", enfe);
                            }
             em.remove(riaDtlTbl);
             em.getTransaction().commit();
             logFile.info("Remove RiaDtlTbl record with id  " + id + "successful");
                    }  finally  {
             if (em != null) {
                 em.close();
                            }
                    }
            }
    
     public List<RiaDtlTbl> findRiaDtlTblEntities()  {
         return findRiaDtlTblEntities(true, -1, -1);
            }
    
     public List<RiaDtlTbl> findRiaDtlTblEntities(int maxResults, int firstResult) {
         return findRiaDtlTblEntities(false, maxResults, firstResult);
            }
    
     private List<RiaDtlTbl> findRiaDtlTblEntities(boolean all, int maxResults, int firstResult)  {
         EntityManager em = getEntityManager();
         try {
             CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
             cq.select(cq.from(RiaDtlTbl.class));
             Query q = em.createQuery(cq);
             if (!all) {
                 q.setMaxResults(maxResults);
                 q.setFirstResult(firstResult);
                            }
             logFile.info("List of all entities in RiaTbl -- " + q.getResultList().size());
             return q.getResultList();
                    } finally  {
             em.close();
                    }
            }
    
     public RiaDtlTbl findRiaDtlTbl(String id)  {
         EntityManager em = getEntityManager();
         try  {
             return (RiaDtlTbl) em.find(RiaDtlTbl.class, id);
                    } finally  {
             em.close();
                    }
            }
    
     public int getRiaDtlTblCount()  {
         EntityManager em = getEntityManager();
         try{
             CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
             Root<RiaDtlTbl> rt = cq.from(RiaDtlTbl.class);
             cq.select(em.getCriteriaBuilder().count(rt));
             Query q = em.createQuery(cq);
             logFile.info("Get Ria Dtl Tbl Count -- " + ((Long) q.getSingleResult()).intValue());
             return ((Long) q.getSingleResult()).intValue();
                    } finally  {
             em.close();
                    }
            }
     }
