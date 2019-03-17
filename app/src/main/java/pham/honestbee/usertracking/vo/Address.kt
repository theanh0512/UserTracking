package pham.honestbee.usertracking.vo

/**
 * Created by Pham on 18/3/2019.
 */
data class Address(var street: String?, var suite: String?, var city: String?,
                   var zipcode: String?, var geo: Geo?)
data class Geo(var lat: String?, var lng: String?)