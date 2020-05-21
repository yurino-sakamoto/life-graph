<template>
  <div class="referenceSection">
    <Header />
    <div class="searchButtonParent">
      <router-link to="/search">
        <img class="searchButton" src="../assets/logo.png" alt="検索">
      </router-link>
    </div>
    <Chart class="referenceChart" />
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

.referenceSection {
  background: #F3F3F9;
  width: 100%;
  height: 800px;
  margin-top: 80px;
  height: 800px;
}

.searchButtonParent{
  text-align: left;
}

.searchButton{
  width: 50px;
  margin: 50px 0px 0px 100px;
}

.resultChart{
  width: 500px;
  margin: 0px auto;
}
</style>
