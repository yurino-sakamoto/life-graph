<template>
  <div class="topSection">
    <Header />
    <h1>This is your LifeGraph</h1>
    <div class="topModule">
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
  },
  created () {
    this.$store.commit('chart/clear')
  }
}
</script>

<style scoped>

.topSection {
  margin-top: 80px;
  background: radial-gradient(white, #E5E5E9);
  height: 700px;
}

/* .topModule {
  background:#FFF;
  color: #565452;
  border-radius: 20px;
  width: 800px;
  height: 1000px;
  font-size: 12pt;
  word-break: break-all;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
} */

h1 {
  text-align: left;
  margin: 10px 0 0 40px;
  font-size: 70px;
  font-weight: 800;
  line-height: 0.8em;
  letter-spacing: -1px;
}

.topChart {
  width: 700px;
  height: auto;
  text-align: center;
  margin: auto;
}

</style>
