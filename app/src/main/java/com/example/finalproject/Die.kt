/* 
Course Name - JAV-1001 - 11354 - App Development for Android - 202301 - 001
Student name - Gaurang Naik
Student # A00250808
==============================================================================================
Lab 3  Classes/Objects
=============================================================================================== 
*/

package com.example.finalproject
import kotlin.random.Random

public class Die {
    
    //defining properties
   var type: String
   var numberOfSides: Int
    
   set(value) {
        if(value == 0) //in case if size is 0 then it will be set to 1

            field = value + 1;
        else
            field = value;  
    } 

   var currentSideUp: Int = 1
    set(value) {
        if(value == 0) //in case if size is 0 then it will be set to 1
            field = value + 1;
        else
            field = value;  
        } 

    /*A 0 argument constructor Default type/name: "d6"; default number of sides: 6; random side up generated from the roll method*/
    constructor() {
        this.type  = "d6"
        this.numberOfSides = 6
        Roll()
    }

    /*A 1 argument constructor Number of sides is the parameter. Default type/name: "d{number of sides}" random side up generated from the roll method */
    constructor(sides:Int){
        this.numberOfSides = sides
        this.type = "d"+numberOfSides.toString()
        Roll()
    }

    /*A 2 argument constructor Both the number of sides and the type are parameters; random side up generated from the roll method */ 
     constructor(sides:Int ,type:String){
        this.numberOfSides = sides
        this.type = type
        Roll()
    }

    //A roll method to generate a random value
    fun Roll(){
      this.currentSideUp = Random.nextInt(this.numberOfSides)
    }
}
