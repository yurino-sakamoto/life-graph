<template>
  <div class="headderSection">
    <div class="logoParent">
      <router-link to="/login">
        <img class="pageLogo" src="../assets/logo.png" alt="ロゴ">
      </router-link>
    </div>
    <div class="nav">
      <router-link to="/top" class="topLink">
        Top
      </router-link>|
      <router-link to="/edit" class="topLink">
        Edit
      </router-link>|
      <router-link to="/search" class="topLink">
        Search
      </router-link>|
      <router-link to="/reference" class="topLink">
        Reference
      </router-link>
    </div>
    <router-view />
    <ul>
      <li>
        User Name : {{ username }}
      </li>
      <li>
        Authority : {{ authority }}
      </li>
      <span tag="button" class="btn" @click="logout()">
        Log Out
      </span>
    </ul>
  </div>
</template>

<script>
export default {
  data () {
    return {
      username: '',
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
      this.username = stateAccount.username
      const authority = stateAccount.name // 変数authorityを定義
      if (authority === 'ROLE_USER') { // roleがROLE_USERのとき
        this.authority = 'User' // 一般ユーザーという値を返す
      } else if (authority === 'ROLE_ADMIN') {
        this.authority = 'Admin'
      } else { // roleが上記以外のとき
        this.authority = 'Owner'
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

<style>

.headerSection{
  height: 80px;
  width: 100%;
  background-color: #FFF;
  position: fixed;
  top: 0;
  border-radius: 0px 0px 10px 10px;
}

.pageLogo{
  width: 50px;
  text-align: left;
}

.nav {
  position : fixed;
  top : 0;
  left: 35%;
  z-index : 10;
  font-size: 1.3rem;
  color: #565452;
  font-size: 16px;
  font-weight: bold;
  display: block;
  text-align: center;
  margin:20px;
  text-decoration: none;
  transition: .3s;
  padding: 15px 80px 5px  80px;
  display: inline-block;
}

.topLink {
  margin: 15px 800px 5px 800px;
}

.hover {
  opacity: 0.5;
}

.after {
  position: absolute;
  bottom: 0;
  left: 50%;
  content: '';
  width: 0;
  height: 2px;
  background-color: white;
  transition: .3s;
  transform: translateX(-50%);
}

.hover {
  width: 100%;
}

li {
  font-size: 1.3rem;
  color: white;
  text-decoration: none;
  display: inline-block;
  transition: .3s;
  padding: 15px 20px 5px  20px;
  position: relative;
  top: 0px;
}

ul{
  text-align: right
}

li{
  color: #565452;
  padding: 15px;
  width: 130px;
  font-size: 16px;
  font-weight: bold;
  display: block;
  border-radius: 3px;
  text-align: center;
  margin:20px;
}

.btn{
  display: inline-block;
  width: 70px;
  height: auto;
  text-align: center;
  font-size: 16px;
  color: #FFF;
  text-decoration: none;
  font-weight: bold;
  padding: 12px 24px;
  border-radius: 4px;
  background: #7448FF;
  transition: .5s;
  background-size: 100%;
  position : fixed;
  cursor: pointer;
  top : 0;
  left: 90%;
  z-index : 10;
}

hover {
  background-position: right center;
}
</style>
