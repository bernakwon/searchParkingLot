<template>
  <div id="page_wrapper">


    <router-view></router-view>
    <vue-progress-bar></vue-progress-bar>

  </div>
</template>

<script>

  export default {
    name      : "defaultLayout",
    components: {

    },
    data(){
      return{

      }
    },
    methods:{
    }
  }
</script>

<style>
</style>
