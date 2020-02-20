import Vue from 'vue'
import VueRouter from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Template from "../views/Template"

Vue.use(VueRouter)

export const router = new VueRouter({
  mode : 'history',
  routes: [
    {
      path: '/',
      name: 'Template',
      component: Template
    },
    {
      path: '/hello',
      name: 'Hello',
      component: HelloWorld
    },
  ]
})
