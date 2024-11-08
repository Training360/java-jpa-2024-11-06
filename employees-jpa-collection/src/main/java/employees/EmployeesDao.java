package employees;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EmployeesDao {

    private EntityManagerFactory emf;

    public EmployeesDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Employee employee) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();

                System.out.println("before persist: " + em.contains(employee));

                em.persist(employee);

                System.out.println("after persist: " + em.contains(employee));

                em.getTransaction().commit();
            }
    }

    public List<Employee> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select e from Employee e", Employee.class).getResultList();
        }
    }

    public List<Employee> findAllWithNicknames() {
        try (EntityManager em = emf.createEntityManager()) {
            List<Employee> employees = em.createQuery("select e from Employee e left join fetch e.nicknames", Employee.class).getResultList();
//            for (Employee e: employees) {
//                e.getNicknames().size();
//            }
            return employees;
        }
    }

    public Employee findById(long id) {
        try (EntityManager em = emf.createEntityManager()) {
            Employee employee = em.find(Employee.class, id);
            return employee;
        }
    }

    public Employee findByIdWithNicknames(long id) {
        try (EntityManager em = emf.createEntityManager()) {
//            Employee employee = em.find(Employee.class, id);
            // Azért hívom meg, hogy betöltse a collectiont
//            employee.getNicknames().size();

            return em.createQuery("select e from Employee e left join fetch e.nicknames where e.id = :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }



    public void approve(ApproveCommand command) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Employee entity = em.find(Employee.class, command.employeeId());
            entity.setEmployeeStatus(EmployeeStatus.ACCEPTED);
            em.getTransaction().commit();
        }
    }

    public void update(Employee employee) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Employee entity = em.find(Employee.class, employee.getId());
            entity.setName(employee.getName());
            em.getTransaction().commit();
        }
    }

    public void delete(long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, id);
            em.remove(employee);
            em.getTransaction().commit();
        }
    }
}
