package room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patient_table")
class Patient {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "patient_id")
    var patientId: Long = 0
        get() = field

    @ColumnInfo(name = "patient_phone_number")
    var patientPhoneNumber: String = ""

    @ColumnInfo(name = "patient_toke_number")
    var tokenNumber: Int = 0
        get() = field

    @ColumnInfo(name = "patient_sms_status")
    var isSmsSend: Boolean = false
}