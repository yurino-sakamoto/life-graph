<template>
  <div class="topSection">
    <Header />
    <h1>This is your LifeGraph !</h1>
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
    this.$store.dispatch('chart/addContent', this.$store.state.auth.userId)
  }
}
</script>

<style scoped>

.topSection {
  margin-top: 80px;
  background: #F3F3F9;
  height: 720px;
}

.topModule {
  background:#FFF;
  color: #565452;
  border-radius: 20px;
  width: 800px;
  height: 480px;
  font-size: 12pt;
  word-break: break-all;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

h1 {
  text-align: center;
  position: relative;
  top: -25px;
  margin: 0 0 0 30px;
  font-size: 50px;
  font-weight: 800;
  line-height: 0.8em;
  letter-spacing: -1px;
  color: #565452;
}

.topChart {
  width: 760px;
  height: 460px;
  position: relative;
  top: 20px;
  right: 6px;
  text-align: center;
  margin: auto;
}

</style>
