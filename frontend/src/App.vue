<template>
  <div id="app">
    <vue-extend-layouts />
  </div>
</template>


<script>
  /* Layout Libraries 설정*/
  import VueExtendLayouts from 'vue-extend-layout'
  export default {
    name: 'App',
    components: { VueExtendLayouts }
  }
</script>


<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
