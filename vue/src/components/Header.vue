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
      <router-link to="/reference">
        Reference
      </router-link>|
    </div>
    <router-view />
    <div class="userName_authorityLevel">
      <p>Click here to log out↓</p>
      <button class="btn btn-lg btn-fill" @click="logout">
        Log Out
      </button>
      <div class="userName">
        ユーザー名：{{ userName }}
      </div>
      <div class="authorityLevel">
        <p v-if="authority=='1'">
          一般ユーザー
        </p>
        <p v-else-if="authority=='2'">
          管理者
        </p>
        <p v-else>
          オーナー
        </p>
      </div>
    </div>
  </div>
</template>

<script>
// export default {
//   data () {
//     return {
//       userName: '小澤コウタ',
//       authority: '1'
//     }
//   },
//   mounted () {  //ヘッダー表示と同時にdispatch
//     this.$store.dispatch('accountAction');
//   },
//   computed: {
//     Name () {
//       this.userName = this.$store.getters.acountName
//       return this.userName
//     },
//     Authority () {
//       this.authority = this.$store.getters.acountAuthority
//       return this.authority
//     }
//   }
//   //   ,logout () {
//   //     this.$store.commit('auth/deleteToken')  //authのstateのtokenを消す
//   //     this.$router.push('/login')             //tokenが消されたあとログイン画面に遷移する
//   // }
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
