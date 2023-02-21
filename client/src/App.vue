<template>
  <div id="app">
    <Role />
  </div>
</template>

<script>
import User from './components/User.vue'
import Role from './components/Role.vue'
export default {
  name: 'App',
  components: {
    User, Role
  },
  data: function () {
    return {

    }
  },
}
</script>

<style>
#app {}

.table {
  width: 100%;
}
</style>
