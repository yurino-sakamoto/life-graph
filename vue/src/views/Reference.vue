<template>
  <div class="referenceSection">
    <div class="referenceGraph">
      <Header />
      <Chart v-bind="UserId" class="referenceChart" />
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
      UserId: parseInt(this.$route.params.userId)
      // 不明：これで検索された人のIDに結びつくのはなぜか？（search終了後）
    }
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
