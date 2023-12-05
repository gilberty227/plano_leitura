package br.com.planoleitura.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.planoleitura.domian.model.Book
import br.com.planoleitura.domian.model.Calendar
import br.com.planoleitura.domian.model.Chapter
import br.com.planoleitura.domian.model.Day
import br.com.planoleitura.domian.model.Order
import br.com.planoleitura.domian.model.Plan
import br.com.planoleitura.domian.model.User

@Database(
    entities = [Plan:: class, Book:: class, Calendar:: class, Chapter:: class,
    Day:: class, Order:: class , User:: class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract val planDao: PlanDao
    abstract val bookDao: BookDao
    abstract val calendarDao: CalendarDao
    abstract val chapterDao: ChapterDao
    abstract val dayDao: DayDao
    abstract val orderDao: OrderDao
    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "reading_plan_db"
    }
}