package pham.honestbee.usertracking.vo

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity


/**
 * Created by Allen on 2019-01-22.
 */
@Entity(
        primaryKeys = ["id", "company_name", "address_suite"]
)
data class User(var id: Int, var name: String?, var username: String?,
                var email: String?,
                @field:Embedded(prefix = "address_")
                var address: Address,
                var phone: String?, var website: String?,
                @field:Embedded(prefix = "company_")
                var company: Company) {
    data class Company(var name: String, var catchPhrase: String?, var bs: String?)

    data class Address(var street: String?, var suite: String, var city: String?,
                       var zipcode: String?,
                       @field:Embedded(prefix = "geo_")
                       var geo: Geo?)

    data class Geo(var lat: String?, var lng: String?)
}