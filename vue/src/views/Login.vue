<template>
  <div class="loginSection">
    <div>
      <Header />
    </div>
    <div class="titleLogo">
      Life<br>
      Graph
    </div>
    <div class="loginPage">
      <div class="login">
        <h1>ログイン</h1>
        <div class="loginID">
          <label for="username" />
          <input v-model="username" class="loginFrom" type="username" placeholder="UserName">
          <label for="password" />
          <input v-model="password" class="loginFrom" type="password" placeholder="Password">
        </div>
        <button class="btn" @click="login()">
          Sign in
        </button>
      </div>
    </div>
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

<style>
.loginSection {
  background: #F3F3F9;
}

.titleLogo{
  color: #565452;
  font-size: 10em;
  line-height: 0.8em;
  letter-spacing: -1px;
  font-weight: 900;
  margin: 30px 0 0 30px
}

.loginPage {
  height: 770px
}

.login {
  background:#FFF;
  filter: drop-shadow(0px 4px 4px rgba(0,0,0,0.25));
  border-radius:6px;
  padding:40px;
  padding-top:30px;
  width:300px;
  margin:50px auto;
  text-align: center;
  position: absolute;
  bottom: 170px;
  right: 200px;
}

h1 {
  text-align:center;
  font-size: 0.8em;
  font-weight:700;
  color:#565452;
  margin-bottom:24px;
}

label[for]{

  margin: 10px 90px 10px 20px;
  display: block;
}

.input {
  width:250px;
  background:#f5f5f5;
  border:0;
  padding:20px;
  border-radius:6px;
  margin-bottom:10px;
  border:1px solid #eee;
}

.focus {
  outline: none;
  }

.btn {
  background:#7448FF;
  color:#fff;
  filter: drop-shadow(0px 4px 4px rgba(0,0,0,0.25));
  border-radius:5px;
  position:relative;
  width:100%;
  padding:20px;
  border:0;
  font-size:1.2em;
  margin-top: 20px;
  text-shadow:1px 1px 0px rgba(0,0,0,.1);
  cursor: pointer;
}

.active {
  top:3px;
  box-shadow:none;
}
</style>
