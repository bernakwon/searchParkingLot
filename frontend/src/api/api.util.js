import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'



const ApiUtil = {
  init () {
    Vue.use(VueAxios, axios)
    // Vue.axios.defaults.baseURL = API_CONFIG.EBMP_SSO_API_URL;

  },

  query (resource, params) {
    return Vue.axios.get(resource, params).catch(error => {
      throw new Error(`[ApiService]\nerror: ${error} \nresource: ${resource}\nparams: ${params}`)
    })
  },

  get (resource, slug = '') {
    let url = `${resource}/${slug}`

    if (slug == '') {
      url = `${resource}`
    }

    return Vue.axios.get(url).catch(error => {
      throw new Error(`[ApiService]\nerror: ${error} \nurl: ${url}`)
    })
  },

  post (resource, params) {
    if (Vue == undefined || Vue.axios == undefined) {
      return axios.post(`${resource}`, params)
    } else {
      return Vue.axios.post(`${resource}`, params)
    }
  },

  postIncludeUploadFile (resource, formData) {
    return Vue.axios.post(`${resource}`, formData, { headers: { 'Content-Type': 'multipart/form-data' } })
  },
   update(resource, slug, params) {
      return Vue.axios.put(`${resource}/${slug}`, params);
    },

    put(resource, params) {
      return Vue.axios.put(`${resource}`, params);
    },

    delete(resource) {
      return Vue.axios.delete(resource).catch(error => {
        throw new Error(`[RWV] ApiService ${error}`);
      });
    }
}

export default ApiUtil
