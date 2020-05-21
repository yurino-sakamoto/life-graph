<template>
  <div id="app">
    <router-view />
    <head>
      <meta name="viewport" content="width=device-width,initial-scale=1.0">
    </head>
  </div>
</template>

<style>
@media screen and (min-width:1024px) {
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif, Tahoma;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }
}
@media only screen and (min-width:768px) and (max-width:1024px) {
 #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }
}
</style>
