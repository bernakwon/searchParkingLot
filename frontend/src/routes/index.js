import Vue from 'vue'
import VueRouter from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import ParkingLotSearch from "../views/ParkingLotSearch"

Vue.use(VueRouter)

export const router = new VueRouter({
  mode : 'history',
  routes: [
    {
      path: '/',
      name: 'ParkingLotSearch',
      component: ParkingLotSearch
    }
  ]
})
