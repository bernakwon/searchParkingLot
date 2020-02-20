import Vue from 'vue'
import App from './App.vue' // 최초 진입점
import {router} from './routes/index.js' // vue-router
import {store} from './store/index.js' // vuex
/*from './api/api.util' // Etners API Util
import VeeValid
import ApiUtil ate from 'vee-validate' // validation library
*/

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
}).$mount('#app')
