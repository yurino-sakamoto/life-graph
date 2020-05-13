<template>
  <div class="search">
    <div class="searchTitle">
      life_graph
    </div>
    <!-- header -->
    <header>
      <h1 class="headline">
        <a>Search</a>
      </h1>
    </header>
    <div class="username">
      <form action="confirm.php" method="post">
        ユーザーネーム：<input type="text" name="yourname">
      </form>
    </div>
    <div class="kousin">
      <label for="num">更新日時：</label>
      <input id="num" type="number" name="kousin-num">
    </div>
    <div class="button">
      <input type="submit" value="Search">
    </div>
    <footer>
      <h2>
        <a>検索結果</a>
      </h2>
      <table id="sampleTable">
        <tr>
          <th>見出し</th>
          <thcmanSortBtn>a</thcmanSortBtn>
          <thcmanSortBtn>b</thcmanSortBtn>
          <thcmanSortBtn>c</thcmanSortBtn>
          <thcmanSortBtn>d</thcmanSortBtn>
          <thcmanSortBtn>e</thcmanSortBtn>
        </tr>
        <tr><th>行1</th><td>4</td><td>1</td><td>DEFGH</td><td>あいう</td><td>100</td></tr>
        <tr><th>行2</th><td>3</td><td>100</td><td>abc</td><td>かきく</td><td>10,000</td></tr>
        <tr><th>行3</th><td>5</td><td>20</td><td>ijkl</td><td>さしす</td><td>1,000</td></tr>
        <tr><th>行4</th><td>1</td><td>9</td><td>MNOP</td><td>たちつ</td><td>10</td></tr>
        <tr><th>行5</th><td>2</td><td>80</td><td>qr</td><td>なにぬ</td><td>100,000</td></tr>
      </table>
      <div class="nav-links">
        <a class="prev page-numbers" href="">«</a>
        <a class="page-numbers" href="">1</a>
        <span class="page-numbers current">2</span>
        <a class="page-numbers" href="">3</a>
        <span class="page-numbers dots">…</span>
        <a class="page-numbers" href="">10</a>
        <a class="next page-numbers" href="">»</a>
      </div>
    </footer>
  </div>
</template>

<script>

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
.username {
  padding: 5px;
}
.button {
  padding: 15px;
  /* box-shadow: 0 0 8px rgba(0,0,0, .4); */
}
.tsImgArea{
  line-height: 1;
}
/* 昇順降順 */
.tsImg{
  display    : inline-block;
  width      : 8px;
  height     : 6px;
  background : #eee;
  border     : 1px solid #777;
  margin     : 1px 3px;
  padding    : 3px;
  cursor     : pointer;
}
.tsImg:hover{
  background : #FFD700;
}
.tsImg path{
  fill       : #777;
}
#sampleTable {
  width          : 100%;
  border-collapse: collapse;         /* 境界線結合 */
  border-spacing : 0;                /* 罫線間余白 */
  font-size      : 9pt;              /* 文字サイズ */
}
#sampleTable th {
  text-align    : center;            /* 文字位置   */
  font-weight   : bold;              /* 太文字     */
  padding       : 6px 5px;          /* 余白       */
  white-space   : nowrap;
}
#sampleTable td {
  text-align    : center;            /* 文字位置   */
  padding       : 6px 5px;           /* 余白       */
  white-space   : nowrap;
}
/* ふったーCSS */
.nav-links{
  padding:2em;
  display:flex;
  justify-content:center;
  background:#f3f3f3;
}
a,span{
  width:50px;
  height:50px;
  margin:2px;
  line-height:50px;
  text-align:center;
  font-size:14px;
  font-weight:bold;
  text-decoration:none;
  background:#fff;
  color:#222;
}
a:hover{
  background:gold;
  border-radius:100%;
}
.current{
  background:gold;
  border-radius:100%;
}
.dots{
  background:none;
}

header {
  width: 100%;
  padding: 15px 0;
  margin: 0 auto;
  text-align: center;
}
.headline{
  font-size: 42px;
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
