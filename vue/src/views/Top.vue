<template>
  <div class="top">
    <Header />
    <div class="topSection">
      <h1 class="mainMessage">
        人生は数値で測れない。
      </h1>
      <div class="searchButtonParent">
        <router-link to="/search">
          <img class="searchButton" src="../assets/logo.png" alt="検索">
        </router-link>
      </div>
      <div class="registerButtonParent">
        <router-link to="/edit">
          <img class="registerButton" src="../assets/logo.png" alt="検索">
        </router-link>
      </div>
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

.topSection{

}
.searchButtonParent{
  text-align: left;
}
.searchButton{
  width: 40px;
  margin: 0px 0px 0px 100px;
}

.registerButtonParent{
  text-align: right;
}

.registerButton{
  position: relative;
  bottom: 42px;
  width: 40px;
  margin: 0px 100px 0px 0px;
}

.mainMessage{
  margin: 50px;
}

.topChart{
  width: 500px;
  margin: 0px auto;
}

</style>

<!--chartを描画-->
