package pham.honestbee.usertracking.db


import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import pham.honestbee.usertracking.vo.User

/**
 * Main database description.
 */
@Database(
        entities = [
            User::class],
        version = 1,
        exportSchema = false
)
abstract class UserTrackingDb : RoomDatabase() {

    abstract fun userDao(): UserDao
}
