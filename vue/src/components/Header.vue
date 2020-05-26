<template>
  <div class="headderSection">
    <div class="logoParent">
      <img class="pageLogo" src="../assets/lifegraphLogo.png" alt="ロゴ">
      <span class="topLabel">
        Life Graph
      </span>
    </div>
    <div class="nav">
      <router-link to="/top" class="link">
        <img class="topLogo" src="../assets/home.png">
        Top
      </router-link>
      <router-link to="/edit" class="link">
        <img class="topLogo" src="../assets/edit.png">
        Edit
      </router-link>
      <router-link to="/search" class="link">
        <img class="topLogo" src="../assets/search.png">
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
  async mounted () {
    const userId = this.$store.state.auth.userId
    await this.$store.dispatch('account/accountAction', userId)
    this.setAccount()
  },
  methods: {
    setAccount () {
      const stateAccount = this.$store.state.account.accountInfo
      this.username = stateAccount.username
      const authority = stateAccount.name
      if (authority === 'ROLE_USER') {
        this.authority = 'User'
      } else if (authority === 'ROLE_ADMIN') {
        this.authority = 'Admin'
      } else {
        this.authority = 'Owner'
      }
    },
    logout () {
      this.$store.commit('auth/deleteToken')
      this.$store.commit('account/resetAccountInfo')
      this.$router.push('/login')
      this.$store.commit('chart/clearState')
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

  .topLabel {
    color: #565452;
    font-size: 26px;
    font-weight: 600;
    position : fixed;
    left: 98px;
    top: 28px;
  }

  .topLogo {
    width: 26px;
  }
  .nav {
    position : fixed;
    top : 10px;
    left: 260px;
    z-index : 10;
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
        font-size: 28px;
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
