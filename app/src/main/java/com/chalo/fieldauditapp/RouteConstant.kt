package com.chalo.fieldauditapp

object RouteConstant {
    fun getRouteData():ArrayList<ItemViewsModel>{
        // create an arraylist of type employee class
        val routeList=ArrayList<ItemViewsModel>()
        val rt1=ItemViewsModel("S-349","Vashi junction - Andheri east")
        routeList.add(rt1)
        val rt2=ItemViewsModel("32","Vashi junction - Andheri east")
        routeList.add(rt2)
        val rt3=ItemViewsModel("97","Vashi junction - Andheri east")
        routeList.add(rt3)

        return  routeList
    }
}