package br.com.planoleitura.domian.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.planoleitura.R

@Entity(tableName = "plan")
data class Plan(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val durationMonth: Int,
    val countChapterDay: Int
) {

    /*var imgPlan: Map<Int, Int> = mutableMapOf(
        1 to R.drawable.ic_launcher_foreground,
        2 to R.drawable.ic_launcher_foreground,
        3 to R.drawable.ic_launcher_foreground,
        4 to R.drawable.ic_launcher_foreground,
        5 to R.drawable.ic_launcher_foreground,
        )

    var colorPlan: Map<Int, Int> = mutableMapOf(
        1 to R.color.black,
        2 to R.color.black,
        3 to R.color.black,
        4 to R.color.black,
        5 to R.color.black,
    )*/
}
