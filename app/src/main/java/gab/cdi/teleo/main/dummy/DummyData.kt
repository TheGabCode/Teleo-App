package gab.cdi.teleo.main.dummy

import gab.cdi.teleo.main.models.Program
import org.json.JSONObject

/**
 * Created by Default on 15/08/2018.
 */
class DummyData{

    fun initPrograms(){
        jsonProgramOne.put("programId","1")
        jsonProgramOne.put("programTitle","Gab's Elem Life")
        jsonProgramOne.put("programDescription","Joseph's quiet elementary school life in Don Bosco SXL.")
        jsonProgramOne.put("programNumber","3")

        jsonProgramTwo.put("programId","2")
        jsonProgramTwo.put("programTitle","Gab's HS Life")
        jsonProgramTwo.put("programDescription","Gab's crazy PGMNHS adventure with new found friends for life.")
        jsonProgramTwo.put("programNumber","2")

        jsonProgramThree.put("programId","3")
        jsonProgramThree.put("programTitle","Gab's Pre-Life Chapter")
        jsonProgramThree.put("programDescription","Gab's lesson-filled college life in UPLB")
        jsonProgramThree.put("programNumber","1")

        programs.add(Program(jsonProgramOne))
        programs.add(Program(jsonProgramTwo))
        programs.add(Program(jsonProgramThree))
    }



    companion object {
        val programs : ArrayList<Program> = ArrayList()
        val jsonProgramOne  = JSONObject()
        val jsonProgramTwo  = JSONObject()
        val jsonProgramThree  = JSONObject()


    }

}