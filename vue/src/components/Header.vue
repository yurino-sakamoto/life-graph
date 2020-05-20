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
      <button class="logOutButton" @click="logout()">
        ログアウト
      </button>
      <div class="userName">
        ユーザー名：{{ userName }}
      </div>
      <div class="authorityLevel">
        Authority:{{ authority }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      userName: '',
      authority: ''
    }
  },
  async mounted () { // ヘッダー表示と同時に起動
    const userId = this.$store.state.auth.userId// 変数userIdを定義。ログイン情報。省略
    await this.$store.dispatch('account/accountAction', { userId: userId })
    this.setAccount()// ファイル内のメソッド呼び出し
  },
  methods: {
    // dataのaccountにaccount.jsのstateの情報をsetする
    setAccount () {
      const stateAccount = this.$store.state.account.acountInfo
      this.userName = stateAccount.username
      const authority = stateAccount.name // 変数authorityを定義
      if (authority === 'ROLE_USER') { // roleがROLE_USERのとき
        this.authority = '一般ユーザー' // 一般ユーザーという値を返す
      } else if (authority === 'ROLE_ADMIN') {
        this.authority = '管理者'
      } else { // roleが上記以外のとき
        this.authority = 'オーナー'
      }
    },
    // ログアウト（authのstateのtokenを消す）
    logout () {
      this.$store.commit('auth/deleteToken')// token削除。auth.jsのmutation呼び出し。
      this.$store.commit('account/resetAccountInfo')// 上記の情報リセット。account.jsのmutation呼び出し。
      this.$router.push('/login')// ログイン画面に遷移
    }
  }
}
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
