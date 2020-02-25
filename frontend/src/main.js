import Vue from 'vue'
import VueProgressBar from 'vue-progressbar'
import App from './App.vue' // 최초 진입점
import {router} from './routes/index.js' // vue-router
import {store} from './store/index.js' // vuex
import '../node_modules/@fortawesome/fontawesome-free/css/all.css';
import '../node_modules/vue-ads-pagination/dist/vue-ads-pagination.css';



import VueAdsPagination, { VueAdsPageButton } from 'vue-ads-pagination';
Vue.use(VueAdsPagination)
Vue.use(VueAdsPageButton)
Vue.config.productionTip = false
Vue.use(VueProgressBar)

new Vue({
  el: '#app',
  router,
  store,
  components: {
    App
 },
  template: '<App/>'
}).$mount('#app')
