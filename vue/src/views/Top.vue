<template>
  <div class="top">
    <Header />
    <div class="topSection">
      <h1>This is your LifeGraph</h1>
      <chart v-bind="UserId" class="topChart" />
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
      UserId: this.$store.state.accountInfo.id
      // account.jsのstateに取得したログイン者のidのチャートを描画する
    }
  }
}
</script>

<style scoped>

.top {
  margin-top: 80px;
  background: radial-gradient(white, #E5E5E9);
  height: 700px;
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
