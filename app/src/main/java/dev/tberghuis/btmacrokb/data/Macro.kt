package dev.tberghuis.btmacrokb.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity
data class Macro(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val title: String,
  val payload: String
)

@Dao
interface MacroDao {
  @Query("SELECT * FROM Macro")
  fun getAll(): Flow<List<Macro>>

  @Query("SELECT * FROM Macro where id = :id")
  suspend fun getById(id: Long): Macro?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(macro: Macro): Long

  @Update
  suspend fun update(macro: Macro)

  @Delete
  suspend fun delete(macro: Macro)
}

@Database(entities = [Macro::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun macroDao(): MacroDao
}

@Volatile
private var INSTANCE: AppDatabase? = null
private val lock = Any()

private fun getDatabase(context: Context): AppDatabase {
  return INSTANCE ?: synchronized(lock) {
    val instance = Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      "autotyper.db"
    )
      .fallbackToDestructiveMigration()
      .build()
    INSTANCE = instance
    instance
  }
}

val Context.appDatabase: AppDatabase
  get() = getDatabase(this)