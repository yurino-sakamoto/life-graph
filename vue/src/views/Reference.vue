<template>
  <div class="referenceSection">
    <div class="referenceGraph">
      <Header />
      <Chart
        v-if="loaded"
        :reference-mode="true"
        class="referenceChart"
      />
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
  data () {
    return {
      referenceId: parseInt(this.$route.params.userId)
      // アクセス前のページでuserIdを指定している。parseIntはint型変換。これをチャートがバインド。
    }
  },
  computed: {
    loaded () {
      return this.$store.state.chart.loaded
    }
  },
  created () {
    this.$store.dispatch('chart/getReference', this.referenceId)
  }
}
</script>

<style scoped>
.referenceSection {
  background: #F3F3F9;
  height: 760px;
}

.referenceGraph {
  background: #FFF;
  color: #565452;
  border-radius: 20px;
  width: 70%;
  height: 80%;
  font-size: 12pt;
  word-break: break-all;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
  filter: drop-shadow(10px 10px 10px rgba(0,0,0,0.2))
}

.referenceChart{
  padding: 100px 12px 8px 6px;
  margin:20px;
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
