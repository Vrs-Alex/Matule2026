package vrsalex.matule.infrastructure.persisntence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String = "",

    @Column(name = "subtitle", nullable = false)
    var subtitle: String = "",

    @Column(name = "price", nullable = false)
    var price: String = "",

    @Column(name = "description", nullable = false , length = 2000)
    var description: String = "",

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false, foreignKey = ForeignKey(name = "fk_product_category"))
    var category: CategoryEntity? = null
)