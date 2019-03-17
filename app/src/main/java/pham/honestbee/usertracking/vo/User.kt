package pham.honestbee.usertracking.vo


/**
 * Created by Allen on 2019-01-22.
 */
data class User(var id: Int, var name: String?, var username: String?,
                var email: String?, var address: Address?,
                var phone: String?, var website: String?, var company: Company?)

data class Company(var name: String?, var catchPhrase: String?, var bs: String?)