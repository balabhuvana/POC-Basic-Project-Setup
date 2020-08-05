package room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPatient(patient: Patient?): Long

    @Query("SELECT * from patient_table where patient_phone_number = :phoneNumber LIMIT 1")
    fun getPatientRecord(phoneNumber: String): Patient

    @Query("SELECT * from patient_table")
    fun getPatientList(): LiveData<List<Patient>>

    @Query("SELECT * from patient_table")
    fun getPatientListNoLiveData(): List<Patient>

    @Update
    fun updatePatientSmsStatus(patient: Patient?)

}