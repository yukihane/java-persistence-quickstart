package examples.jpa.entity

import jakarta.persistence.*

@Entity
class Parent(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var age: Int,

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL], orphanRemoval = true)
    var children: MutableSet<Child> = mutableSetOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
