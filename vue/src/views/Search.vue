<template>
  <div class="search">
    <div class="searchTitle">
      life_graph
    </div>
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
      Home
    </router-link>|
    <router-link to="/about">
      About
    </router-link>

    <div>
      <form method="get" action="http://localhost:8081/" target="blank">
        <input type="text" name="q" size="31" maxlength="255" value="">
        <input type="submit" name="btng" value="Search">
        <input type="hidden" name="hl" value="ja">
        <input type="hidden" name="sitesearch" value="web-officer.com">
      </form>
    </div>
    <body>
      <!-- header -->
      <header>
        <h1 class="headline">
          <a>Search</a>
        </h1>
      </header>
    </body>
    <!-- <img class="pagelogo" src"" alt="Logo"> -->
    <!-- <div>
      <label for="site-search">Article Site:</label>
      <input type="search" id="site-search" name="q"
             aria-label="Search through site content">
    </div>
    <button>Search</button> -->
    <table>
      <tr><th>No.</th><th>username</th><th>updatedate</th> </tr>
      <tr><td>1</td> <td>tagi</td><td>20200512</td> </tr>
      <tr><td>2</td> <td>okazaki</td><td>20200511</td> </tr>
      <tr><td>3</td> <td>sakamoto</td><td>20200430</td> </tr>
    </table>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.searchTitle{
    font-family: initial;
    text-align: center;
    font-size: 40px;
    height: 0px;
    margin: 100px;
    padding:0px 0px 0px 0px;
}
.table th{
  padding : 20px 10px;
}

.table td{
  padding : 20px 10px;
}
table{
  width: 100%;
}

th{
  width: 30%;
}

td{
  width: 30%;
}
.site-search{
    font-size:px;
    margin: 0 auto;
    text-align: center;
}
.label {
    display: block;
    font: 1rem 'Fira Sans', sans-serif;
    text-align: center;
}

header {
  width: 100%;
  padding: 15px 0;
  margin: 0 auto;
  text-align: center;
}
.headline{
  font-size: 32px;
}
.nav-list {
  text-align: center;
  padding: 10px 0;
  margin: 0 auto;
}
.nav-list-item {
  list-style: none;
  display: inline-block;
  margin: 0 20px;
}

input,
label {
    margin: .4rem 0;
}

</style>
