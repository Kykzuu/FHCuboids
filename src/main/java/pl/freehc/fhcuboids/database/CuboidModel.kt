package pl.freehc.fhcuboids.database

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity(name = "CuboidModel")
@Table(name = "Cuboids")
data class CuboidModel(
        @Id
        @GeneratedValue
        @Column(name = "id")
        var ID: Long = 0,
        @Column(name = "configName")
        var ConfigName: String? = null,
        @Column(name = "max")
        var maX: Double = 0.0,
        @Column(name = "mix")
        var miX: Double = 0.0,
        @Column(name = "maz")
        var maZ: Double = 0.0,
        @Column(name = "miz")
        var miZ: Double = 0.0,
        @Column(name = "jajx")
        var jajX: Double = 0.0,
        @Column(name = "jajz")
        var jajZ: Double = 0.0,
        @Column(name = "jajy")
        var jajY: Double = 0.0,
        @Column(name = "jajyaw")
        var jajYaw: Float = 0.0f,
        @Column(name = "jajpitch")
        var jajPitch: Float = 0.0f,

        @Type(type = "uuid-char")
        @Column(name = "owneruuid")
        var ownerUUID: UUID? = null,
        @Column(name = "ownernickname")
        var ownerNickname: String? = null,
        @Column(name = "createdtime")
        var createdTime: Long = 0,
        @Column(name = "size")
        var size: Int = 0,

        @Column(name = "name")
        var name: String? = null,

        @Column(columnDefinition = "double precision default '0'", name = "pricepaid")
        var pricePaid: Double = 0.0,

        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "Cuboids_friends")
        @OrderColumn(name = "pos")
        @Type(type = "uuid-char")
        var friendsUUID: List<UUID>? = null
)
