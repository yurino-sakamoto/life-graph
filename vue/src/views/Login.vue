<template>
  <div class="loginSection">
    <div class="login">
      <div class="loginID">
        <label for="username" />
        <input v-model="username" placeholder="UserName">
        <label for="password" />
        <input v-model="password" type="password" placeholder="Password">
      </div>
      <button class="btn" @click="login()">
        Sign in
      </button>
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

<style lang="scss" scoped>
.loginSection {
  background: #F3F3F9;
  height: 760px;

  .login {
    background:#7448FF;
    border-radius:6px;
    padding:40px;
    padding-top:30px;
    width:300px;
    margin:50px auto;
    box-shadow:15px 15px 0px rgba(0,0,0,.1);
    text-align: center;
    position: absolute;
    bottom: 170px;
    right: 200px;
  }

  label[for]{
    margin: 10px 90px 10px 20px;
    display: block;
  }

  input {
    width:250px;
    background:#f5f5f5;
    border:0;
    padding:20px;
    border-radius:6px;
    margin-bottom:10px;
    border:1px solid #eee;
    :focus {
      outline: none;
    }
  }

  .btn {
    position:relative;
    width:100%;
    padding:20px;
    border-radius:6px;
    border:0;
    background:#f26964;
    font-size:1.2em;
    color:#fff;
    margin-top: 20px;
    text-shadow:1px 1px 0px rgba(0,0,0,.1);
    box-shadow:0px 3px 0px #c1524e;
    cursor: pointer;
    :active {
      top:3px;
      box-shadow:none;
    }
  }
}
</style>
