<template>
  <div id="page_wrapper">
    <header>
      <h1><a style="cursor: pointer"><img src="../assets/logo.png" width="135" height="38" alt="로고"></a></h1>

    </header>

    <router-view></router-view>


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
