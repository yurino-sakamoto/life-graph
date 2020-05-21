<template>
  <div class="search">
    <Header />
    <h1>Let`s Search LifeGraph</h1>
    <div class="form-item">
      <label for="username" />
      <input v-model="username" placeholder="UserName">
      <!-- 範囲指定しなければいけない↓ -->
      <label for="date" />
      <input v-model="date" type="date">
      <button class="btn" @click="login()">
        Search
      </button>
    </div>
    <footer>
      <h2>検索結果</h2>
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
import Header from '../components/Header.vue'

export default {
  name: 'App',
  components: {
    Header
  }
}
</script>

<style lang="scss" scoped>
.search {
  margin-top: 80px;
  background: radial-gradient(white, #E5E5E9);
  height: 100%;;

  h1 {
    text-align: center;
    padding: 30px;
    font-size: 50px;
    font-weight: bold;
  }

  h2 {
    text-align: center;
    padding: 20px;
    font-size: 40px;
    font-weight: bold;
  }

  .form-item {
    margin-bottom: 0.75em;
    width: 100%;
    margin: 3em auto;
    padding: 0 1em;
    max-width: 370px;
    position: relative;

    input {
      background: #fafafa;
      border-bottom: 2px solid #e9e9e9;
      font-family: 'Open Sans', sans-serif;
      font-size: 1em;
      height: 50px;
      width: 100%;
      font: 15px/24px sans-serif;
      box-sizing: border-box;
      padding: 0.3em;
      transition: 0.3s;
      letter-spacing: 1px;
      color: #aaaaaa;
      border: 1px solid #1b2538;
      border-radius: 4px;
      margin: .4rem 0;
    }
  }

  .form-item input:focus {
    border: 1px solid #da3c41;
    outline: none;
    box-shadow: 0 0 5px 1px rgba(218,60,65, .5);
  }

  .btn {
    position:relative;
    padding: 0.3em;
    width: 100%;
    max-width: 370px;
    height: 50px;
    border-radius:6px;
    border:0;
    background:#f26964;
    font-size:1.2em;
    color:#fff;
    text-shadow:1px 1px 0px rgba(0,0,0,.1);
    box-shadow:0px 3px 0px #c1524e;
    cursor: pointer;

      :active {
        top:3px;
        box-shadow:none;
      }
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

    :hover{
      background : #FFD700;
    }

    &path{
      fill: #777;
    }
  }

  #sampleTable {
    width          : 100%;
    border-collapse: collapse;         /* 境界線結合 */
    border-spacing : 0;                /* 罫線間余白 */
    font-size      : 9pt;              /* 文字サイズ */
    background-color: gray;
    position: relative;
    height: auto;
    min-height: 100%;

    &th {
      text-align    : center;            /* 文字位置   */
      font-weight   : bold;              /* 太文字     */
      padding       : 6px 5px;          /* 余白       */
      white-space   : nowrap;
    }
    &td {
      text-align    : center;            /* 文字位置   */
      padding       : 6px 5px;           /* 余白       */
      white-space   : nowrap;
    }
  }

  /* ふったーCSS */
  .nav-links{
    padding:2em;
    display:flex;
    justify-content:center;
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
}
</style>
