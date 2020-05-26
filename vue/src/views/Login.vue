<template>
  <div class="loginSection">
    <div class="titleLogo">
      Life<br>
      Graph
    </div>
    <div class="logoParent">
      <img class="pageLogo" src="../assets/lifegraphLogo.png" alt="ロゴ">
    </div>
    <div v-if="loginPage" class="loginArea">
      <h2>LOGIN</h2>
      <div v-if="loginError" class="error">
        ユーザー名もしくはパスワードが不正です
      </div>
      <div class="textBox">
        <label for="username" />
        <input v-model="username" class="text" type="textbox" placeholder="UserName">
      </div>
      <div class="textBox">
        <label for="password" />
        <input v-model="password" class="text" type="password" placeholder="Password">
      </div>
      <button class="btn" @click="signup()">
        新規登録
      </button>
      <button class="btn" @click="login()">
        ログイン
      </button>
    </div>
    <div v-if="signupPage" class="signupArea">
      <h2>SIGN UP</h2>
      <div class="textBox">
        <label for="username" />
        <input v-model="username" class="text" type="textbox" placeholder="UserName">
        <br>
      </div>
      <div class="textBox">
        <label for="email" />
        <input v-model="email" class="text" type="email" placeholder="Mailaddress">
        <br>
      </div>
      <div class="textBox">
        <label for="password" />
        <input v-model="password" class="text" type="password" placeholder="Password">
      </div>
      <button class="upbtn" @click="switchingPage()">
        登録
      </button>
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      username: '',
      email: '',
      password: '',
      loginPage: true,
      signupPage: false
    }
  },
  computed: {
    token () {
      return this.$store.state.auth.token
    },
    loginError () {
      return this.$store.state.auth.error
    },
    success () {
      return this.$store.state.auth.success
    }
  },
  watch: {
    token (newToken) {
      this.$router.push('/top')
    },
    success () {
      this.loginDialog = true
      this.signupDialog = false
      this.errMessage = false
    }
  },
  created () {
    this.$store.commit('auth/clearError')
    this.$store.commit('chart/clearState')
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
    },
    signup () {
      this.loginPage = false
      this.signupPage = true
    },
    switchingPage () {
      this.$store.dispatch(
        'auth/signup',
        {
          username: this.username,
          email: this.email,
          password: this.password,
          role: ['user']
        }
      )
      this.loginPage = true
      this.signupPage = false
    }
  }
}
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css?family=Roboto');

.loginSection {
  background: #F3F3F9;
  height: 800px;
  background-size: cover;

  .titleLogo {
    color: #565452;
    font-size: 14em;
    line-height: 0.8em;
    letter-spacing: -4px;
    font-weight: 900;
    margin: -10px;
    padding: 30px 0 0 30px;
    animation-name: sample01;
    animation-duration:2s;
    animation-fill-mode: forwards;
  }

  @keyframes sample01 {
  0% {
  opacity: 0;
  transform: translateX(-30px);
  }
  50%{
    opacity: 1;
    transform: translateX(0);
    }
  100% {
  opacity:1;
  transform: translate(0px);
  }
}

.pageLogo {
  width: 500px;
  margin: 0 0 0 30px;
  position: relative;
  left: 180px;
  bottom: -30px;
  letter-spacing: -2px;
}

  .loginArea {background:#FFF;
    filter: drop-shadow(0px 4px 4px rgba(0,0,0,0.25));
    border-radius:18px;
    width:400px;
    height: 330px;
    text-align: center;
    padding: 15px;
    position: absolute;
    top: 240px;
    right: 120px;
    text-align: center;
  }

 .signupArea {
    background:#FFF;
    filter: drop-shadow(0px 4px 4px rgba(0,0,0,0.25));
    border-radius:18px;
    width:400px;
    height: 400px;
    text-align: center;
    padding: 15px;
    position: absolute;
    top: 240px;
    right: 120px;
    text-align: center;
  }

    h2 {
    text-align:center;
    font-size:1.4em;
    font-weight:600;
    color:#565452;
    margin-top: 8px;
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
    background:#7448FF;
    color:#fff;
    position: inline-block;
    text-align: center;
    font-weight: 900;
    filter: drop-shadow(0px 4px 4px rgba(0,0,0,0.25));
    border-radius: 40px;
    width: 130px;
    height: 50px;
    padding-bottom: 6px;
    border:0;
    font-size:1.25em;
    margin: 20px;
    text-shadow:1px 1px 0px rgba(0,0,0,.1);
    cursor: pointer;

    :active {
      top:3px;
      box-shadow:none;
    }

    :focus {
      outline: 0;
    }
  }

  .upbtn {
    background:#7448FF;
    color:#fff;
    position: inline-block;
    text-align: center;
    font-weight: 900;
    filter: drop-shadow(0px 4px 4px rgba(0,0,0,0.25));
    border-radius: 40px;
    position:relative;
    left: 20px;
    top: 20px;
    width: 130px;
    height: 50px;
    padding-bottom: 6px;
    border:0;
    font-size:1.25em;
    margin: 10px -90px 14px 100px;
    text-shadow:1px 1px 0px rgba(0,0,0,.1);
    cursor: pointer;

    :active {
      top:3px;
      box-shadow:none;
    }

    :focus {
      outline: 0;
    }
  }

  .error {
    font-size: 1em;
    font-weight: bold;
    color: red;
    width: 370px;
    text-align: center;
    letter-spacing: -1px;
  }

.textBox {
  flex-direction: column;
  align-items: center;
  width: 270px;
  position: relative;
  padding: 20px;
  margin-left: -120px;
  font-size: 20px;
  outline: none;
  color: #565452;
  font-weight: 500;
  border:0;
  border-radius: 30px;
  height: 45px;
}

.text {
  font-weight: 500;
  width: calc(100% - 2vw);
  height: 27px;
  padding: 0 auto;
  background-color: transparent;
  color: #565452;
  position: absolute;
  bottom: 0px;
  outline: none;
  border-style: solid;
  border-color: #E3E7EA;
  border-width: 0px 0px 2px 0px;
  font-size: 20px;
  -webkit-transition: border-color 0.45s linear;
  transition: border-color 0.45s linear;
}

  .textBox > input.text:focus {
    border-color: #7448FF;
  }

  .textBox .label {
    opacity: 0.1;
    pointer-events: none;
    z-index: 1;
    color: #C0C0C0;
    position: absolute;
    padding-left: 0.5vw;
    bottom: 3px;
    font-size: 20px;
    font-weight: 300;
    -webkit-transition-property: opacity bottom font-size;
    transition-property: opacity bottom font-size;
    -webkit-transition-duration: 0.45s;
    transition-duration: 0.45s;
    -webkit-transition-timing-function: cubic-bezier(.68,-0.55,.27,1.55);
    transition-timing-function: cubic-bezier(.68,-0.55,.27,1.55);
  }

  .textBox .error {
    position: absolute;
    margin-top: 48px;
    width: 100%;
    color: #e74c3c;
    text-align: center;
  }

  .textBox > input.text:focus ~ .label, .textBox > input.text:not([value=""]) ~ .label {
    opacity: 1;
    z-index: 1;
    bottom: 55%;
    font-size: 12px;
  }
  .textBox > input.text:focus ~ .label {
    color: #7448FF;
  }
}
</style>
