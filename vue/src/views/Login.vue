<template>
  <div class="loginSection">
    <div class="titleLogo">
      Life<br>
      Graph
    </div>
    <div class="logoParent">
      <img class="pageLogo" src="../assets/lifegraphLogo.png" alt="ロゴ">
    </div>
    <div class="loginArea">
      <div class="login">
        <h2>ログイン</h2>
        <div class="loginID">
          <div class="textBox">
            <label class="label1">ユーザー名</label>
            <input v-model="username" class="text" type="textbox">
            <br>
          </div>
          <div class="textBox">
            <label class="label2">パスワード</label>
            <input v-model="password" class="text" type="password">
          </div>
        </div>
        <button class="btn" @click="login()">
          ログイン
        </button>
      </div>
    </div>
    <div v-if="loginError" class="error">
      ERROR!
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
    },
    loginError () {
      // console.log(this.$store.state.auth.error)
      return this.$store.state.auth.error
    }
  },
  watch: {
    token (newToken) {
      this.$router.push('/top')
    }
  },
  created () {
    this.$store.commit('auth/clearError')
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
@import url('https://fonts.googleapis.com/css?family=Roboto');
$set-prefix: '' , -moz- , -webkit-;

.loginSection {
  height: 740px;
  background-size: cover;
  background: #DDD6F3;
  animation: colour 10s linear infinite;

  @keyframes colour{
    0%{
      background: #FFFFFF;
    }

    10%{
      background: #F4F5FE;
    }

    30%{
      background: #F3F3F9;
    }

    50%{
      background: #DDD6F3;
    }

    70%{
      background: #F3F3F9;
    }

    90%{
      background: #F4F5FE;
    }

    100%{
      background: #FFFFFF;
    }
  }

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
    width: 460px;
    margin: 0 0 0 30px;
    position: fixed;
    left: 250px;
    bottom: -70px;
    letter-spacing: -2px;
  }

  .login {
    background:#FFF;
    filter: drop-shadow(0px 4px 4px rgba(0,0,0,0.25));
    border-radius:18px;
    width:400px;
    height: 300px;
    text-align: center;
    padding: 15px;
    position: absolute;
    top: 260px;
    right: 130px;
  }

  h2 {
    text-align:center;
    font-size:1.4em;
    font-weight:600;
    color:#565452;
    margin-top: 8px;
  }

  .loginArea {
    text-align: center;
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
  position:relative;
  left: 20px;
  top: 18px;
  width: 130px;
  height: 50px;
  padding-bottom: 6px;
  border:0;
  font-size:1.25em;
  margin: 10px -90px 14px 100px;
  text-shadow:1px 1px 0px rgba(0,0,0,.1);
  cursor: pointer;

  :hover {
    background-color: #8566ce;
    color: #FFF;
  }
}

.error {
  position: relative;
  left: 1048px;
  bottom: 160px;
  font-size: 1.2em;
  font-weight: 600;
  color: #F6FB17;
  background: #565452;
  width: 78px;
  text-align: center;
}

.textBox {
  display: block;
  flex-direction: column;
  align-items: center;
  width:250px;
  position: relative;
  text-align: center;
  padding: 15px 15px 15px 0;
  font-size: 20px;
  outline: none;
  color: #565452;
  font-weight: 500;
  border:0;
  border-radius: 30px;
  height: 45px;
}

.textBox .text {
  font-weight: 500;
  width: calc(100% - 2vw);
  height: 27px;
  padding-left: 0.5vw;
  background-color: transparent;
  color: #565452;
  position: absolute;
  bottom: 0px;
  outline: none;
  border-style: solid;
  border-color: #E3E7EA;
  border-width: 0px 0px 2px 0px;
  font-size: 20px;
  transition: border-color 0.45s linear;
}

.label1 {
  position: inline-block;
  margin:-10px 0 80px -100px;
  font-size: 16px;
}

.label2 {
  position: inline-block;
  margin:-10px 0 80px -100px;
  font-size: 16px;
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
// .textBox ::-webkit-input-placeholder{
//   color: #2B2B2B;
//   font-size: 20px;
//   font-weight: 200;
//   font-family: 'Roboto', sans-serif;
// }

// .textBox ::-moz-placeholder {
//   color: #2B2B2B;
//   font-size: 20px;
//   font-weight: 200;
//   font-family: 'Roboto', sans-serif;
// }
}
</style>
