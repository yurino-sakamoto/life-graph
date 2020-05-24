<template>
  <div class="referenceSection">
      <Header />
      <h1>
        There is only one success
        - to be able to spend you life in your own way.
      </h1>
      <h2>
        成功の道はただ一つ、自分だけの生き方を見つけることだ ―Christopher Morley
      </h2>
      <div class="referenceModule">
      <Chart v-bind="UserId" class="referenceChart" />
    </div>
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
  background: #F3F3F9;
  height: 700px;
}

.referenceModule {
  background:#FFF;
  color: #565452;
  border-radius: 20px;
  width: 800px;
  height: 450px;
  font-size: 12pt;
  word-break: break-all;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

.referenceChart{
  width: 730px;
  height: auto;
  position: relative;
  top: 20px;
  right: 6px;
  text-align: center;
  margin: auto;
}

.searchButtonParent{
  text-align: left;
}

.searchButton{
  width: 50px;
  margin: 50px 0px 0px 100px;
}

h1 {
  text-align: center;
  position: relative;
  top: -25px;
  margin: 0 0 0 30px;
  font-size: 30px;
  line-height: 0.8em;
  letter-spacing: -1px;
}

h2 {
  font-size: 20px;
  color: #565452;
  text-align: center;
  margin: 0 0 30px 0;
}

.resultChart {
  width: 730px;
  height: auto;
  position: relative;
  top: 20px;
  right: 6px;
  text-align: center;
  margin: auto;
}
</style>
