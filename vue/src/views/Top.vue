<template>
  <div class="topSection">
    <Header />
    <div class="top">
      <h1>This is your LifeGraph</h1>
      <chart class="topChart" />
    </div>
  </div>
</template>

<script>

import Header from '../components/Header.vue'
import Chart from '../components/Chart.vue'

export default {
  name: 'App',
  components: {
    Header,
    Chart
  }
}

</script>

<style scoped>

.topSection {
  background: #F3F3F9;
  width: 100%;
  height: 800px;
  margin-top: 80px;
  height: 700px
}

h1 {
  text-align: center;
  padding: 30px;
  font-size: 70px;
  font-weight: bold;
}

.topChart{
  width: 1000px;
  text-align: center;
  margin: auto;
}

</style>
