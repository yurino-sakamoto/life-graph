<template>
  <div class="loginSection">
    <div class="pageTitle">
      人生グラフ
    </div>
    <!--
    <img class="pageLogo" src="../assets/logo.png" alt="Logo">
    -->
    <div class="loginID">
      <!-- <label for="password">username:
        <input type="text" name="uid" size="40" maxlength="32" autocomplete="OFF"> -->
      <label for="username" />
      <input v-model="username" type="username" name="username" placeholder="UserName">
      <label for="password" />
      <input v-model="password" type="password" name="password" placeholder="Password">
    </div>
    <button class="button-panel" @click="login()">
      Sign in
    </button>
    <!-- <div class="loginButton">
      <router-link to="/">
        <input type="submit" value="Login">
      </router-link>
    </div> -->
  </div>
</template>

<script>
export default {
  data () {
    return {
      username: '',
      password: ''
    }
  },
  computed: {
    token () {
      return this.$store.state.auth.token
    }
  },
  watch: {
    token (newToken) {
      this.$router.push('/top')
    }
  },
  methods: {
    login () {
      this.$store.dispatch(
        'auth/create',
        {
          username: this.username,
          password: this.password
        }
      )
    }
  }
}
</script>

<style scoped>
.loginSection{
  height: 280px;
  background-color: blanchedalmond;
  text-align: center;
  margin: 100px;
}

.pageTitle{
  font-size: 2rem;
  padding: 50px 0px 0px 0px;
  margin: 0px 0px 30px 0px;
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
