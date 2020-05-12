<template>
  <div class="loginSection">
    <div class="pageTitle">
      人生グラフ♪
    </div>
    <img class="pageLogo" src="../assets/logo.png" alt="Logo">
    <div class="loginID">
      <input v-model="textInput" type="text">
      <p>{{ textInput }}</p>
    </div>
    <div class="loginPassword">
      <input v-model="textInput2" type="text">
      <p>{{ textInput2 }}</p>
    </div>
  </div>
</template>

<script>
// export default {
//   data: {
//     return{
//       textInput:'aa',
//     }
//   }
// }
</script>

<style scoped>

.loginSection{
  height: 280px;
  background-color: blanchedalmond;
  text-align: center;
  margin: 100px;
}

.pageTitle{
  padding: 50px 0px 0px 0px;
  margin: 0px 0px 10px 0px;
}

.pageLogo{
  margin: 10px 0px 10px 0px;
  width: 50px;
}

.loginID{
  margin: 10px 0px 10px 0px;
}

.loginPassword{
  margin: 10px 0px 10px 0px;
}

</style>

<!--ログイン-->
