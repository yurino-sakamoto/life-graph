<template>
  <div class="headderSection">
    <div class="logoParent">
      <router-link to="/">
        <img class="pageLogo" src="../assets/logo.png" alt="ロゴ">
      </router-link>
    </div>
    <div class="nav">
      <router-link to="/login">
        Login
      </router-link>|
      <router-link to="/edit">
        Edit
      </router-link>|
      <router-link to="/search">
        Search
      </router-link>|
      <router-link to="/result">
        Result
      </router-link>|
    </div>
    <router-view />
    <div class="userName_authorityLevel">
      <div class="userName">
        {{userName}}
      </div>
      <div class="authorityLevel">
        <p v-if="authority==general">一般ユーザー</p>
        <p v-else-if="authority==admin">管理者</p>
        <p v-else>オーナー</p>
      </div>
    </div>
  </div>
</template>

<script>
// export default {
//   data: {
//     userName: '小澤コウタ'
//     user: 'general'
//   },
//   computed: {
//     Name () {
//       const userName = //javaで取得する場所
//       return this.userName
//     }
//   },
//   computed: {
//     Authority () {
//       const authority = //javaで取得する場所
//       return this.authority
//     }
//   }
// }

</script>

<style scoped>

.headderSection{
  height: 70px;
  background-color: lightgrey;
}

.logoParent{
  text-align: left;
}

.pageLogo{
  width: 50px;
  margin: 13px 0px 0px 10px;
}

.userName_authorityLevel {
  position: relative;
  bottom: 64px;
  padding: 0pt 15pt 0pt 0pt;
  text-align: right;
}

.userName{
  font-weight: bold;
  font-size: 1.5rem;
  margin: 0pt 0pt 0pt 0pt;
}

.authorityLevel{
  margin: 0pt 0pt 0pt 0pt;
}

.nav{
  position: absolute;
  top:800px;
  font-size: 0.5rem;
}

</style>

<!--
取得した名前を表示
if文
-->
