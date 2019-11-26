package com.amanaryan.firebase_test

class SaveUserData (

   val personName:String,

   val personEmail:String,

   val personId:String,

   val    personGivenName:String,

   val personFamilyName:String,

   val personPhoto:String
)
{
    constructor():this("","","","","","")
}