<template>
  <div class="referenceSection">
    <Header />
    <Chart v-bind="referenceId" class="referenceChart" />
  </div>
</template>
<!--4~8,30~34行目いらんかも。テスト設計には必要とあるが、ヘッダーにボタンがある。とりあえず放置。-->

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
  }
}
</script>

<style scoped>

.referenceSection {
  margin-top: 80px;
  background: radial-gradient(white, #E5E5E9);
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
