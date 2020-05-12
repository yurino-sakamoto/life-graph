<template>
  <div class="top">
    <Header />
    <div class="topSection">
      <h1 class="mainMessage">
        人生は数値で測れない。
      </h1>
      <div class="searchButtonParent">
        <button type="submit">
          <img class="searchButton" src="../assets/logo.png" alt="検索">
        </button>
      </div>
      <div class="registerButtonParent">
        <button type="submit">
          <img class="registerButton" src="../assets/logo.png" alt="検索">
        </button>
      </div>
      <chart />
    </div>
  </div>
</template>

<script>

import Header from '../components/Header.vue'

export default {
  name: 'App',
  components: {
    Header
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

</style>
