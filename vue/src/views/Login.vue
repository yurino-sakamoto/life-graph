<!--【参考】https://www.alaxala.com/jp/techinfo/archive/manual/AX2400S/HTML/11_7/CFGUIDE2/0189.HTM-->

<template>
  <div class="loginSection">
    <div class="pageTitle">
      人生グラフ♪
    </div>
    <img class="pageLogo" src="../assets/logo.png" alt="Logo">
    <div class="loginID">
      <label for="password">username:
        <input type="text" name="uid" size="40" maxlength="32" autocomplete="OFF">
      </label>
    </div>
    <div class="loginPassword">
      <label for="password">password:
        <input type="password" name="pwd" size="40" maxlength="32" autocomplete="OFF">
      </label>
    </div>
    <router-link to="/">
      <input type="submit" value="Login">
    </router-link>
  </div>
</template>

<script>
//
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

.loginID,
.loginPassword{
  margin: 10px 90px 10px 20px;
}
</style>

<!--ログイン-->
