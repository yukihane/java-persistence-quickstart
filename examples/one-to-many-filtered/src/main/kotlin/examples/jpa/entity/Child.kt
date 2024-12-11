package examples.jpa.entity

import jakarta.persistence.*

@Entity
class Child (
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var age: Int,

    @ManyToOne
    var parent: Parent
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
