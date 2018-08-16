package gab.cdi.teleo.main.dummy

import gab.cdi.teleo.main.models.Program
import gab.cdi.teleo.main.models.Region
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Default on 15/08/2018.
 */
class DummyData{
    companion object {
        val programs : ArrayList<Program> = ArrayList()
        val regions : ArrayList<Region> = ArrayList()
        val countries : HashMap<String,ArrayList<Region.Country>> = HashMap()
    }


    fun initPrograms(){
        val jsonProgramOne  = JSONObject()
        val jsonProgramTwo  = JSONObject()
        val jsonProgramThree  = JSONObject()

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

    fun initRegions(){
        val jsonRegionOne  = JSONObject() //Region
        val jsonRegionTwo  = JSONObject()
        val jsonRegionThree  = JSONObject()

        val jsonRegionOneCountries = JSONArray() //Region.regionCountries
        val jsonRegionTwoCountries = JSONArray()
        val jsonRegionThreeCountries = JSONArray()

        val jsonRegionOneCountryOne = JSONObject() //Region.Country
        val jsonRegionOneCountryTwo = JSONObject()
        val jsonRegionTwoCountryOne = JSONObject()
        val jsonRegionTwoCountryTwo = JSONObject()
        val jsonRegionThreeCountryOne = JSONObject()


        jsonRegionOne.put("regionId","as")
        jsonRegionOne.put("regionName","Asia")

        jsonRegionTwo.put("regionId","nm")
        jsonRegionTwo.put("regionName","North America")

        jsonRegionThree.put("regionId","pac")
        jsonRegionThree.put("regionName","Australia and Pacific Islands")

        jsonRegionOneCountryOne.put("countryId","ph")
        jsonRegionOneCountryOne.put("regionId","as")
        jsonRegionOneCountryOne.put("countryName","Philippines")

        jsonRegionOneCountryTwo.put("countryId","sk")
        jsonRegionOneCountryTwo.put("regionId","as")
        jsonRegionOneCountryTwo.put("countryName","South Korea")

        jsonRegionOneCountries.put(jsonRegionOneCountryOne)
        jsonRegionOneCountries.put(jsonRegionOneCountryTwo)

        jsonRegionTwoCountryOne.put("countryId","usa")
        jsonRegionTwoCountryOne.put("regionId","nm")
        jsonRegionTwoCountryOne.put("countryName","United States of America")

        jsonRegionTwoCountryTwo.put("countryId","cad")
        jsonRegionTwoCountryTwo.put("regionId","nm")
        jsonRegionTwoCountryTwo.put("countryName","Canada")

        jsonRegionTwoCountries.put(jsonRegionTwoCountryOne)
        jsonRegionTwoCountries.put(jsonRegionTwoCountryTwo)

        jsonRegionThreeCountryOne.put("countryId","aus")
        jsonRegionThreeCountryOne.put("regionId","pac")
        jsonRegionThreeCountryOne.put("countryName","Australia")

        jsonRegionThreeCountries.put(jsonRegionThreeCountryOne)

        jsonRegionOne.put("regionCountries",jsonRegionOneCountries)
        jsonRegionTwo.put("regionCountries",jsonRegionTwoCountries)
        jsonRegionThree.put("regionCountries",jsonRegionThreeCountries)

        regions.add(Region(jsonRegionOne))
        regions.add(Region(jsonRegionTwo))
        regions.add(Region(jsonRegionThree))

        val countriesArrayListOne : ArrayList<Region.Country> = ArrayList()
        countriesArrayListOne.add(Region.Country(jsonRegionOneCountryOne))
        countriesArrayListOne.add(Region.Country(jsonRegionOneCountryTwo))
        countries.put(jsonRegionOne.optString("regionId"),countriesArrayListOne)

        val countriesArrayListTwo : ArrayList<Region.Country> = ArrayList()
        countriesArrayListTwo.add(Region.Country(jsonRegionTwoCountryOne))
        countriesArrayListTwo.add(Region.Country(jsonRegionTwoCountryTwo))
        countries.put(jsonRegionTwo.optString("regionId"),countriesArrayListTwo)

        val countriesArrayListThree : ArrayList<Region.Country> = ArrayList()
        countriesArrayListThree.add(Region.Country(jsonRegionThreeCountryOne))
        countries.put(jsonRegionThree.optString("regionId"),countriesArrayListThree)




    }



}