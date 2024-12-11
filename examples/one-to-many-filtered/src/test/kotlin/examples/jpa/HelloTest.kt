package examples.jpa

import examples.jpa.entity.Child
import examples.jpa.entity.Parent
import jakarta.persistence.Persistence
import jakarta.persistence.Tuple
import org.hibernate.SessionFactory
import org.hibernate.testing.transaction.TransactionUtil.doInHibernate
import org.hibernate.testing.transaction.TransactionUtil.doInJPA
import org.junit.jupiter.api.Test


class HelloTest {

    private val entityManagerFactory = Persistence.createEntityManagerFactory("example.unit")

    private val sessionFactory = entityManagerFactory.unwrap(SessionFactory::class.java)

    @Test
    fun testApp() {
        doInJPA(this::entityManagerFactory) { em ->
            val p1 = Parent("John Doe", 40)
            val c1 = Child("Alice", 10, p1)
            val c2 = Child("Bob", 12, p1)
            p1.children.add(c1)
            p1.children.add(c2)
            em.persist(p1)

            val p2 = Parent("Other", 35)
            val c3 = Child("Exec", 10, p2)
            p2.children.add(c3)
            em.persist(p2)
        }

        doInHibernate<Unit>(this::sessionFactory) { session ->
            val queryStr = """
                select p as pa, c as ch from Parent p
                left join p.children c with c.name = 'Alice'
            """.trimIndent()
            val result = session.createQuery(queryStr, Tuple::class.java).resultList
            result.forEach { t ->
                val p = t.get("pa", Parent::class.java)
                val c = t.get("ch", Child::class.java)
                println("Parent: ${p?.name}, Child: ${c?.name}")
            }
        }
    }
}
