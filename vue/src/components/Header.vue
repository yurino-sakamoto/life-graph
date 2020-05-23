<template>
  <div class="headderSection">
    <div class="logoParent">
      <img class="pageLogo" src="../assets/logo.png" alt="ロゴ">
    </div>
    <div class="nav">
      <div class="navTop">
        <router-link to="/top">
          Top
        </router-link>
      </div>
      <div class="navTop">
        <router-link to="/edit">
          Edit
        </router-link>
      </div>
      <div class="navTop">
        <router-link to="/search">
          Search
        </router-link>
      </div>
      <div class="navTop">
        <router-link to="/reference">
          Reference
        </router-link>
      </div>
    </div>
    <router-view />
    <ul>
      <li>
        ユーザー : {{ username }}
      </li>
      <li>
        権限 : {{ authority }}
      </li>
      <span tag="button" class="btn" @click="logout()">
        ログアウト
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
    await this.$store.dispatch('account/accountAction', userId)
    this.setAccount()// ファイル内のメソッド呼び出し
  },
  methods: {
    // dataのaccountにaccount.jsのstateの情報をsetする
    setAccount () {
      const stateAccount = this.$store.state.account.acountInfo // ストア全体のstateの中のaccount.jsの中のacountInfoというstateの値
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
      // token削除。auth.jsのmutation呼び出し。
      this.$store.commit('auth/deleteToken')
      // 上記の情報リセット。account.jsのmutation呼び出し。
      this.$store.commit('account/resetAccountInfo')
      // ログイン画面に遷移
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="scss" scoped>

.headderSection{
  height: 80px;
  width: 100%;
  background-color: #FFF;
  position: fixed;
  top: 0;
  z-index: 1
}

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

.navTop {

}

  // li{
  //   font-size: 1.3rem;
  //   color: white;
  //   text-shadow: 1px 1px 3px #000;
  //   text-decoration: none;
  //   display: inline-block;
  //   transition: .3s;
  //   padding: 15px 20px 5px  20px;
  //   position: relative;
  //   top: 0px;

  ul{
    text-align: right;

    li{
      color: white;
      text-shadow: 1px 1px 3px #000;
      padding: 15px;
      width: 130px;
      font-size: 16px;
      font-weight: bold;
      display: block;
      background-image: linear-gradient(-90deg, #232526, #414345);
      border-radius: 3px;
      text-shadow: -1px -1px rgba(255, 255, 255, 0.44), 1px 1px rgba(0, 0, 0, 0.38);
      text-align: center;
      margin:20px;
    }
  }

  .btn{
  background:#7448FF;
  text-align: center;
  font-weight: 900;
  filter: drop-shadow(0px 4px 4px rgba(0,0,0,0.25));
  border-radius: 40px;
  display:inline-block;
  padding: 12px;
  border:0;
  font-size: 1em;
  text-shadow:1px 1px 0px rgba(0,0,0,.1);
  cursor: pointer;
  }
</style>
