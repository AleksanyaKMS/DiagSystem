package com.example.diagsystem

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "data_base", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query1 = "DROP TABLE IF EXISTS users;"
        val query2 = "DROP TABLE IF EXISTS models;"
        val query3 = "DROP TABLE IF EXISTS measurements;"
        val query4 = "DROP TABLE IF EXISTS analysis;"
        val query5 = "CREATE TABLE users (" +
                "user_id INTEGER NOT NULL UNIQUE," +
                " login TEXT UNIQUE," +
                " PRIMARY KEY(user_id)" +
                ");"
        val query6 = "CREATE TABLE models (" +
                "model_id INTEGER NOT NULL UNIQUE," +
                " engine TEXT," +
                " fuel INTEGER," +
                " mass INTEGER," +
                " max_taho INTEGER," +
                " mark TEXT," +
                " per_1 TEXT," +
                " per_2 TEXT," +
                " per_3 TEXT," +
                " per_4 TEXT," +
                " per_5 TEXT," +
                " per_6 TEXT," +
                " PRIMARY KEY(model_id)" +
                ");"
        val query7 = "CREATE TABLE measurements (" +
                "meas_id INTEGER NOT NULL UNIQUE," +
                " short_cor INTEGER," +
                " long_cor INTEGER," +
                " expenses INTEGER," +
                " distance INTEGER," +
                " speed INTEGER," +
                " PRIMARY KEY(meas_id)" +
                ");"
        val query8 = "CREATE TABLE analysis (" +
                "analysis_id INTEGER NOT NULL UNIQUE," +
                " user INTEGER," +
                " car INTEGER," +
                " measurement INTEGER," +
                " dated TEXT," +
                " FOREIGN KEY(measurement) REFERENCES measurements(meas_id)," +
                " FOREIGN KEY(car) REFERENCES models(model_id)," +
                " FOREIGN KEY(user) REFERENCES users(user_id)," +
                " PRIMARY KEY(analysis_id)" +
                ");"
        val query9 = "INSERT INTO models VALUES(" +
                "1," +
                " \"1.6\"," +
                " 50," +
                " 1100," +
                " 6000," +
                " \"Lada\"," +
                " \"3.636\"," +
                " \"1.950\"," +
                " \"1.357\"," +
                " \"0.941\"," +
                " \"0.784\"," +
                " \"3.530\"" +
                ");"
        val query10 = "INSERT INTO models VALUES(" +
                "2," +
                " \"1.6\"," +
                " 50," +
                " 1200," +
                " 6000," +
                " \"Kia\"," +
                " \"4.400\"," +
                " \"2.726\"," +
                " \"1.834\"," +
                " \"1.392\"," +
                " \"1.000\"," +
                " \"3.440\"" +
                ");"
        val query11 = "INSERT INTO models VALUES(" +
                "3," +
                " \"1.6\"," +
                " 50," +
                " 1135," +
                " 6000," +
                " \"Hyundai\"," +
                " \"4.400\"," +
                " \"2.726\"," +
                " \"1.834\"," +
                " \"1.392\"," +
                " \"1.000\"," +
                " \"3.440\"" +
                ");"
        val query12 = "INSERT INTO models VALUES(" +
                "4," +
                " \"2.0\"," +
                " 56," +
                " 1700," +
                " 6000," +
                " \"Haval\"," +
                " \"3.688\"," +
                " \"2.684\"," +
                " \"1.679\"," +
                " \"1.022\"," +
                " \"0.830\"," +
                " \"3.598\"" +
                ");"
        val query13 = "INSERT INTO models VALUES(" +
                "5," +
                " \"1.6\"," +
                " 50," +
                " 1156," +
                " 6000," +
                " \"Renault\"," +
                " \"3.727\"," +
                " \"2.048\"," +
                " \"1.393\"," +
                " \"1.029\"," +
                " \"0.820\"," +
                " \"3.545\"" +
                ");"
        val query14 = "INSERT INTO models VALUES(" +
                "6," +
                " \"1.6\"," +
                " 55," +
                " 1246," +
                " 6000," +
                " \"Volkswagen\"," +
                " \"3.450\"," +
                " \"1.940\"," +
                " \"1.290\"," +
                " \"0.970\"," +
                " \"0.760\"," +
                " \"3.300\"" +
                ");"
        val query15 = "INSERT INTO models VALUES(" +
                "7," +
                " \"1.5\"," +
                " 45," +
                " 1430," +
                " 6000," +
                " \"Geely\"," +
                " \"3.182\"," +
                " \"1.895\"," +
                " \"1.250\"," +
                " \"0.909\"," +
                " \"0.703\"," +
                " \"3.083\"" +
                ");"
        val query16 = "INSERT INTO models VALUES(" +
                "8," +
                " \"2.7\"," +
                " 68," +
                " 2050," +
                " 6000," +
                " \"УАЗ\"," +
                " \"4.155\"," +
                " \"2.265\"," +
                " \"1.428\"," +
                " \"1.000\"," +
                " \"0.880\"," +
                " \"3.827\"" +
                ");"
        db!!.execSQL(query1)
        db.execSQL(query2)
        db.execSQL(query3)
        db.execSQL(query4)
        db.execSQL(query5)
        db.execSQL(query6)
        db.execSQL(query7)
        db.execSQL(query8)
        db.execSQL(query9)
        db.execSQL(query10)
        db.execSQL(query11)
        db.execSQL(query12)
        db.execSQL(query13)
        db.execSQL(query14)
        db.execSQL(query15)
        db.execSQL(query16)
        db.execSQL("INSERT INTO users VALUES(0, \"admin\");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    fun addUser(user: User){
        val values = ContentValues()
        values.put("user_id", user.userId)
        values.put("login", user.login)

        val dbr = this.readableDatabase
        val db = this.writableDatabase

        val result = dbr.rawQuery("SELECT * FROM users WHERE user_id = '${user.userId}'", null)
        if(result.moveToFirst()){
            db.execSQL("DELETE FROM users WHERE user_id = '${user.userId}'")
        }

        db.insert("users", null, values)

        db.close()
    }

    fun addModel(model: Model){
        val values = ContentValues()
        values.put("model_id", model.modelId)
        values.put("engine", model.engine)
        values.put("fuel", model.fuel)
        values.put("mass", model.mass)
        values.put("max_taho", model.taho)
        values.put("mark", model.mark)
        values.put("per_1", model.per1)
        values.put("per_2", model.per2)
        values.put("per_3", model.per3)
        values.put("per_4", model.per4)
        values.put("per_5", model.per5)
        values.put("per_6", model.per6)

        val db = this.writableDatabase
        db.insert("models", null, values)

        db.close()
    }

    fun addMeas(measurement: Measurement){
        val values = ContentValues()
        values.put("meas_id", measurement.measId)
        values.put("short_cor", measurement.shortCor)
        values.put("long_cor", measurement.longCor)
        values.put("expenses", measurement.expenses)
        values.put("distance", measurement.distance)
        values.put("speed", measurement.speed)

        val db = this.writableDatabase
        db.insert("measurements", null, values)

        db.close()
    }

    fun addAn(analysis: Analysis){
        val values = ContentValues()
        values.put("analysis_id", analysis.analysisId)
        values.put("user", analysis.user)
        values.put("car", analysis.car)
        values.put("measurement", analysis.measurement)
        values.put("dated", analysis.date)

        val db = this.writableDatabase
        db.insert("analysis", null, values)

        db.close()
    }

    fun getUser(login: String) : Boolean{
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM users WHERE login = '$login'", null)
        return result.moveToFirst()
    }

    fun getModel(mark: String) : Boolean{
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM models WHERE mark = '$mark'", null)
        return result.moveToFirst()
    }

    fun getModelAll(mark: String, engine: String, fuel: String, mass: String, taho: String) : Boolean{
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM models WHERE mark = '$mark' AND engine = '$engine' AND fuel = '$fuel' AND mass = '$mass' AND max_taho = '$taho';", null)
        return result.moveToFirst()
    }

    fun getModelId() : Int{
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM models ORDER BY model_id DESC", null)
        cursor.moveToFirst()
        val idOut: Int = cursor.getInt(cursor.getColumnIndexOrThrow("model_id"))
        cursor.close()
        return  idOut
    }

    fun deleteModel(mark: String){
        val db = this.writableDatabase

        db.delete("models", "mark = '$mark'", null)

        db.close()
    }

    fun getAllModels() : List<Model> {
        val modelList = mutableListOf<Model>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM models", null)

        while(cursor.moveToNext()){
            val mark = cursor.getString(cursor.getColumnIndexOrThrow("mark"))
            val engine = cursor.getString(cursor.getColumnIndexOrThrow("engine"))
            val fuel = cursor.getInt(cursor.getColumnIndexOrThrow("fuel"))
            val mass = cursor.getInt(cursor.getColumnIndexOrThrow("mass"))
            val taho = cursor.getInt(cursor.getColumnIndexOrThrow("max_taho"))
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("model_id"))
            val per1 = cursor.getString(cursor.getColumnIndexOrThrow("per_1"))
            val per2 = cursor.getString(cursor.getColumnIndexOrThrow("per_2"))
            val per3 = cursor.getString(cursor.getColumnIndexOrThrow("per_3"))
            val per4 = cursor.getString(cursor.getColumnIndexOrThrow("per_4"))
            val per5 = cursor.getString(cursor.getColumnIndexOrThrow("per_5"))
            val per6 = cursor.getString(cursor.getColumnIndexOrThrow("per_6"))

            val model = Model(id, engine, fuel, mass, taho, mark, per1, per2, per3, per4, per5, per6)
            modelList.add(model)
        }
        cursor.close()
        db.close()
        return modelList
    }

    fun deleteAnalysis(id: Int){
        val db = this.writableDatabase

        db.delete("analysis", "analysis_id = '$id'", null)

        db.close()
    }

    fun deleteMeas(id: Int){
        val db = this.writableDatabase

        db.delete("measurements", "meas_id = '$id'", null)

        db.close()
    }

    fun getAllAnalysis(userId: Int) : List<Errors> {
        val analysisList = mutableListOf<Errors>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT measurements.short_cor, measurements.long_cor, measurements.expenses, measurements.distance, measurements.speed, analysis.user, models.mark, analysis.dated, analysis.analysis_id, meas_id\n" +
                "FROM analysis\n" +
                "JOIN measurements ON measurements.meas_id = analysis.measurement\n" +
                "JOIN models ON models.model_id = analysis.car\n" +
                "WHERE analysis.user = '$userId';", null)

        while(cursor.moveToNext()){
            val shortCor = cursor.getInt(cursor.getColumnIndexOrThrow("short_cor"))
            val longCor = cursor.getInt(cursor.getColumnIndexOrThrow("long_cor"))
            val expenses = cursor.getInt(cursor.getColumnIndexOrThrow("expenses"))
            val distance = cursor.getInt(cursor.getColumnIndexOrThrow("distance"))
            val speed = cursor.getInt(cursor.getColumnIndexOrThrow("speed"))
            val user = cursor.getInt(cursor.getColumnIndexOrThrow("user"))
            val mark = cursor.getString(cursor.getColumnIndexOrThrow("mark"))
            val date = cursor.getString(cursor.getColumnIndexOrThrow("dated"))
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("analysis_id"))
            val idM = cursor.getInt(cursor.getColumnIndexOrThrow("meas_id"))

            val errors = Errors(shortCor, longCor, expenses, distance, speed, mark, date, user, id, idM)
            analysisList.add(errors)
        }
        cursor.close()
        db.close()
        return analysisList
    }

    fun getMeasId() : Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM measurements ORDER BY meas_id DESC", null)
        cursor.moveToFirst()
        if(!cursor.moveToFirst()){
            val zeroOut = 0
            cursor.close()
            return  zeroOut
        }
        else {
            val idOut: Int = cursor.getInt(cursor.getColumnIndexOrThrow("meas_id"))
            cursor.close()
            return idOut
        }
    }

    fun getAnId() : Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM analysis ORDER BY analysis_id DESC", null)
        cursor.moveToFirst()
        if(!cursor.moveToFirst()){
            val zeroOut = 0
            cursor.close()
            return  zeroOut
        }
        else {
            val idOut: Int = cursor.getInt(cursor.getColumnIndexOrThrow("analysis_id"))
            cursor.close()
            return idOut
        }
    }

    fun getUserId() : Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users ORDER BY user_id DESC", null)
        cursor.moveToFirst()
        if(!cursor.moveToFirst()){
            val zeroOut = 0
            cursor.close()
            return  zeroOut
        }
        else {
            val idOut: Int = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"))
            cursor.close()
            return idOut
        }
    }

    fun getModelPer(mark: String, per: Int) : String{
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM models WHERE mark = '$mark'", null)
        var outRes: String = "2"
        result.moveToFirst()
        if(per == 1) outRes = result.getString(result.getColumnIndexOrThrow("per_1"))
        if(per == 2) outRes = result.getString(result.getColumnIndexOrThrow("per_2"))
        if(per == 3) outRes = result.getString(result.getColumnIndexOrThrow("per_3"))
        if(per == 4) outRes = result.getString(result.getColumnIndexOrThrow("per_4"))
        if(per == 5) outRes = result.getString(result.getColumnIndexOrThrow("per_5"))
        if(per == 6) outRes = result.getString(result.getColumnIndexOrThrow("per_6"))
        return outRes
    }

    fun getUserIdName(name: String) : Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE login = '$name'", null)
        cursor.moveToFirst()
        if(!cursor.moveToFirst()){
            val zeroOut = 0
            cursor.close()
            return  zeroOut
        }
        else {
            val idOut: Int = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"))
            cursor.close()
            return idOut
        }
    }

    fun getEntUser(id: Int) : String{
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE user_id = '$id'", null)
        cursor.moveToFirst()
        if(!cursor.moveToFirst()) {
            return "admin"
        }
        else{
            return cursor.getString(cursor.getColumnIndexOrThrow("login"))
        }
    }

    fun getUserObj(id: Int): User{
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE user_id = '$id'",null)
        cursor.moveToFirst()
        if(!cursor.moveToFirst()){
            return User(0, "admin")
        }
        else{
            val usId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"))
            val lgn = cursor.getString(cursor.getColumnIndexOrThrow("login"))
            return User(usId, lgn)
        }
    }

    fun getEntModel(id: Int) : Model{
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM models WHERE model_id = '$id'", null)
        cursor.moveToFirst()
        if(!cursor.moveToFirst()) {
            return Model(1, "1.6", 50, 1100, 6000, "Lada", "3.636", "1.95", "1.357", "0.941", "0.784", "3.53")
        }
        else{
            val mark = cursor.getString(cursor.getColumnIndexOrThrow("mark"))
            val engine = cursor.getString(cursor.getColumnIndexOrThrow("engine"))
            val fuel = cursor.getInt(cursor.getColumnIndexOrThrow("fuel"))
            val mass = cursor.getInt(cursor.getColumnIndexOrThrow("mass"))
            val taho = cursor.getInt(cursor.getColumnIndexOrThrow("max_taho"))
            val per1 = cursor.getString(cursor.getColumnIndexOrThrow("per_1"))
            val per2 = cursor.getString(cursor.getColumnIndexOrThrow("per_2"))
            val per3 = cursor.getString(cursor.getColumnIndexOrThrow("per_3"))
            val per4 = cursor.getString(cursor.getColumnIndexOrThrow("per_4"))
            val per5 = cursor.getString(cursor.getColumnIndexOrThrow("per_5"))
            val per6 = cursor.getString(cursor.getColumnIndexOrThrow("per_6"))
            return Model(id, engine, fuel, mass, taho, mark, per1, per2, per3, per4, per5, per6)
        }
    }

    fun getModelIdName(name: String) : Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM models WHERE mark = '$name'", null)
        cursor.moveToFirst()
        if(!cursor.moveToFirst()){
            val zeroOut = 0
            cursor.close()
            return  zeroOut
        }
        else {
            val idOut: Int = cursor.getInt(cursor.getColumnIndexOrThrow("model_id"))
            cursor.close()
            return idOut
        }
    }
}