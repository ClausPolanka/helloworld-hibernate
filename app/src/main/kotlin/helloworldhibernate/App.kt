package helloworldhibernate

import jakarta.persistence.*

class App {
    val greeting: String
        get() {
            val config = org.hibernate.cfg.Configuration()
            config.configure()
                .addAnnotatedClass(Message::class.java)
            val serviceRegistry = org.hibernate.boot.registry.StandardServiceRegistryBuilder()
                .applySettings(config.properties)
                .build()
            val sf = config.buildSessionFactory(serviceRegistry)
            val messages = sf.use {
                val s = sf.openSession()
                s.beginTransaction()
                val msg = Message(text = "before")
                s.persist(msg)
                s.transaction.commit()

                s.beginTransaction()
                val c = s.criteriaBuilder.createQuery(Message::class.java)
                c.from(Message::class.java)
                val messages = s.createQuery(c).resultList
                s.transaction.commit()
                messages
            }
            messages.forEach { println(it) }
            return "Hello World! number of messages: '${messages.size}'"
        }
}

fun main() {
    println(App().greeting)
}

@Entity
class Message(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val text: String,
) {
    override fun toString(): String {
        return "Message(id=$id, text='$text')"
    }
}
