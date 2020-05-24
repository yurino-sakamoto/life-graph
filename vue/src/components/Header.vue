<template>
  <div class="headderSection">
    <div class="logoParent">
      <img class="pageLogo" src="../assets/lifegraphLogo.png" alt="ロゴ">
    </div>
    <div class="nav">
      <router-link to="/top" class="link" @click="resetData">
        Top
      </router-link>
      <router-link to="/edit" class="link" @click="resetData">
        Edit
      </router-link>
      <router-link to="/search" class="link" @click="resetData">
        Search
      </router-link>
    </div>
    <router-view />
    <ul>
      <li>
        User : {{ username }}
      </li>
      <li>
        Authority : {{ authority }}
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
      const stateAccount = this.$store.state.account.accountInfo // ストア全体のstateの中のaccount.jsの中のacountInfoというstateの値
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
    resetData () { // ヘッダーのボタンクリックでグラフのデータ初期化。
      this.$store.commit('chart/resetContents')
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
  z-index: 3;
  height: 80px;
  width: 100%;
  background-color: white;
  position : fixed;
  top : 0;
  .pageLogo{
  width: 60px;
  position: absolute;
  left: 10px;
  margin: 20px 10px 10px 10px;
}
  .nav {
    position : fixed;
    top : 0;
    left: 100px;
    z-index : 10;
    font-size: 1.2rem;
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
      :hover::after {
        width: 100%;
      }
      .link {
        padding: 0 30px 20px 0;
        color: #565452;
        font-size: 32px;
        text-shadow: none;
        text-decoration: none;
      }
  }
    ul{
      text-align: right;
      li{
        color: #565452;
        width: 130px;
        font-size: 16px;
        font-weight: bold;
        display: block;
        border-radius: 3px;
        text-align: center;
        margin: 0 0 0 1090px;
        white-space: nowrap;
        text-align: left;
      }
    }
  .btn{
    display: inline-block;
    width: 70px;
    height: auto;
    text-align: center;
    font-size: 14px;
    color: #FFF;
    text-decoration: none;
    font-weight: bold;
    padding: 12px 24px;
    border-radius: 30px;
    background: #7448FF;
    transition: .5s;
    background-size: 100%;
    position : fixed;
    cursor: pointer;
    top : 20px;
    left: 90%;
    z-index : 10;
      :hover {
      background-color: #8566ce;
      color: #FFF;
      }

  }
}
</style>
