<template>
  <div class="headderSection">
    <div class="logoParent">
      <router-link to="/login">
        <img class="pageLogo" src="../assets/logo.png" alt="ロゴ">
      </router-link>
    </div>
    <div class="nav">
      <router-link to="/login">
        <li>Login</li>
      </router-link>|
      <router-link to="/edit">
        <li>Edit</li>
      </router-link>|
      <router-link to="/search">
        <li>Search</li>
      </router-link>|
      <router-link to="/reference">
        <li>Reference</li>
      </router-link>|
    </div>
    <router-view />
    <ul class="acount">
      <li class="userName">
        User Name：{{ username }}
      </li>
      <li class="authority">
        Authority:{{ authority }}
      </li>
      <li>
        <span tag="button" class="btn" @click="logout()">
          Log Out
        </span>
      </li>
    </ul>
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

<style lang="scss" scoped>

.headderSection{
  height: 80px;
  width: 100%;
  background-color: rgba(34, 49, 52, 0.9);
  position : fixed;
  top : 0;

.pageLogo{
  width: 50px;
  margin: 13px 0px 0px 10px;
  text-align: left;
}

.nav {
  position : fixed;
  top : 0;
  left: 35%;
  z-index : 10;
  font-size: 1.3rem;
  color: white;
  font-weight: bold;
  text-shadow: 1px 1px 3px #000;
  text-decoration: none;
  transition: .3s;
  padding: 15px 20px 5px  20px;
  display: inline-block;
}

  li{
    font-size: 1.3rem;
    color: white;
    font-weight: bold;
    text-shadow: 1px 1px 3px #000;
    text-decoration: none;
    display: inline-block;
    transition: .3s;
    padding: 15px 20px 5px  20px;
    position: relative;
    top: 0px;
    display: inline-block;

    :hover {
      opacity: 0.5;
    }

    ::after {
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

    :hover::after{
      width: 100%;
    }
  }

  .acount{
    text-align: right;

    .acount li{
      line-height: 30px;
      margin-right: 35px;
      margin-top: 80px;
      padding: 8px;
      width: auto;
      font-size: 18px;
      display: inline-block;
      text-decoration: none;
      background-image: linear-gradient(-90deg, #FF006E, #FFD500);
      border-color: transparent;
      border-radius: 3px;/*角の丸み*/
      font-weight: bold;
      text-shadow: -1px -1px rgba(255, 255, 255, 0.44), 1px 1px rgba(0, 0, 0, 0.38);
      text-align: center;
    }
  }

  .btn{
    display: inline-block;
    width: 80px;
    height: auto;
    text-align: center;
    font-size: 16px;
    color: #FFF;
    text-decoration: none;
    font-weight: bold;
    padding: 12px 24px;
    border-radius: 4px;
    background-image: linear-gradient(-90deg, #FF006E, #FFD500);
    transition: .5s;
    background-size: 100%;
    position : fixed;
    top : 0;
    left: 90%;
    z-index : 10;

    :hover {
      background-position: right center;
    }
  }
}
</style>
