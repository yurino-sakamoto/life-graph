<template>
  <div id="app">
    <div id="nav">
      <router-link to="/login">
        Login
      </router-link>|
      <router-link to="/top">
        Top
      </router-link>|
      <router-link to="/edit">
        Edit
      </router-link>|
      <router-link to="/search">
        Search
      </router-link>|
      <router-link to="/result">
        Result
      </router-link>|
      <router-link to="/home">
        <!-- ここから、参考に残しておきました。 -->
        Home
      </router-link>|
      <router-link to="/about">
        About
      </router-link>           <!-- ここまで、参考に残しておきました。 -->
    </div>
    <router-view />
  </div>
</template>

<style>
#app {
  color: #2c3e50;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

#nav {
  padding: 30px;
}

#nav a {
  color: #2c3e50;
  font-weight: bold;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
