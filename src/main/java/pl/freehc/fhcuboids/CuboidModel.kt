package pl.freehc.fhcuboids

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Cuboids")
data class CuboidModel(
        @Id
        @GeneratedValue
        var ID: Long = 0,
        var maX: Double = 0.0,
        var miX: Double = 0.0,
        var maZ: Double = 0.0,
        var miZ: Double = 0.0,
        var jajX: Double = 0.0,
        var jajZ: Double = 0.0,
        var jajY: Double = 0.0,
        var jajYaw: Float = 0.0f,
        var jajPitch: Float = 0.0f,

        @Column(columnDefinition = "tinyint(1) default 0")
        var isWarpPurchased: Boolean = false,

        var warpX: Double = 0.0,
        var warpZ: Double = 0.0,
        var warpY: Double = 0.0,
        var warpYaw: Float = 0.0f,
        var warpPitch: Float = 0.0f,

        @Type(type = "uuid-char")
        var ownerUUID: UUID? = null,
        var ownerNickname: String? = null,
        var expireTime: Long = 0,
        var createdTime: Long = 0,
        var size: Int = 0,

        @Column(columnDefinition = "double precision default '0'")
        var pricePaid: Double = 0.0,

        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "Cuboids_friends")
        @OrderColumn(name = "pos")
        @Type(type = "uuid-char")
        var friendsUUID: List<UUID>? = null
)
