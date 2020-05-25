<template>
  <div class="search">
    <Header />
    <h1>Let`s Search LifeGraph</h1>
    <div class="form-item">
      <label for="username" />
      <!--↓↓ 名前入力 ↓↓-->
      <input v-model="username" placeholder="UserName">
      <label for="date" />
      <input v-model="dateFrom" type="date">
      〜〜
      <label for="date" />
      <input v-model="toDate" type="date">
      <button class="btn" @click="searchGraphData()">
        Search
      </button>
    </div>
    <!-- 最後にv-if="serchShow()"を入れてメソッドが動いたら表示という風にする -->
    <div v-if="showResult" class="result">
      <h2>Search Results</h2>
      <table>
        <thead>
          <tr>
            <th />
            <th>ユーザー名</th>
            <th>日時</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(searchItem, index) in searchItems" :key="index">
            <td>{{ index + 1 }}</td>
            <td>{{ searchItem.userName }}</td>
            <td>{{ searchItem.updated_at | moment }}</td>
            <button @click="userReference(searchItem.user_id)">
              参照
            </button>
            <button v-if="!authCheck" @click="deleteGraphData(searchItem.user_id)">
              削除
            </button>
          </tr>
        </tbody>
      </table>
    </div>
    <footer>
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
import moment from 'moment'

export default {
  name: 'App',
  components: {
    Header
  },
  filters: {
    moment: function (date) {
      return moment(date).format('yyyy/MM/DD')
    }
  },
  data () {
    return {
      username: '',
      dateFrom: '',
      toDate: '',
      isActive: false,
      load: true,
      editIndex: -1
    }
  },
  computed: {
    authCheck () {
      return this.$store.state.account.accountInfo.name === 'ROLE_USER'
    },
    showResult () {
      return this.$store.state.search.showResult
    },
    searchItems () {
      return this.$store.state.search.searchItems
    }
  },
  created () {
    this.$store.commit('search/loadFalse')
    this.$store.commit('chart/loadFalse')
  },
  methods: {
    searchGraphData () {
      const data = {
        userName: this.username,
        startDate: this.dateFrom,
        finishDate: this.toFrom
      }
      // console.log('API叩く前')
      this.$store.dispatch('search/searchAPI', data)
      // console.log('API叩いた')
      // const SearchName = this.$store.state.search
      // const SearchUpdateTime =this.$store.state.search
    },
    userReference (userId) {
      this.$router.push({ name: 'Reference', params: { userId: userId } })
      // 画面遷移メソッド
    },
    // TODO 管理者関連のの記述
    deleteGraphData (userId) {
      this.$store.dispatch('search/deleteGraphData', userId)
    }
  }
}
</script>

<style lang="scss" scoped>
.search {
  margin-top: 80px;
  background: radial-gradient(white, #E5E5E9);
  height: 100%;

  h1 {
    text-align: center;
    padding: 30px;
    font-size: 50px;
    font-weight: bold;
  }

  .form-item {
    margin-bottom: 0.75em;
    width: 100%;
    margin: 3em auto;
    padding: 0 1em;
    max-width: 370px;

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

      :focus {
        border: 1px solid #da3c41;
        outline: none;
        box-shadow: 0 0 5px 1px rgba(218,60,65, .5);
      }
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

  .result {
    margin: 2em 0;
    padding: 0.5em 1em;
    border: solid 3px #565452;
    border-radius: 8px;
    width: 100%;
    max-width: 600px;
    text-align: center;
    margin-left: 28%;

    h2 {
      padding: 20px;
      font-size: 40px;
      font-weight: bold;
      color: #565452;
    }
  }
  // .tsImgArea{
  //   line-height: 1;
  // }
  // /* 昇順降順 */
  // .tsImg{
  //   display    : inline-block;
  //   width      : 8px;
  //   height     : 6px;
  //   background : #eee;
  //   border     : 1px solid #777;
  //   margin     : 1px 3px;
  //   padding    : 3px;
  //   cursor     : pointer;

  //   :hover{
  //     background : #FFD700;
  //   }

  //   &path{
  //     fill: #777;
  //   }
  // }

  // #sampleTable {
  //   width          : 100%;
  //   border-collapse: collapse;         /* 境界線結合 */
  //   border-spacing : 0;                /* 罫線間余白 */
  //   font-size      : 9pt;              /* 文字サイズ */
  //   background-color: gray;
  //   position: relative;
  //   height: auto;
  //   min-height: 100%;

  //   &th {
  //     text-align    : center;            /* 文字位置   */
  //     font-weight   : bold;              /* 太文字     */
  //     padding       : 6px 5px;          /* 余白       */
  //     white-space   : nowrap;
  //   }
  //   &td {
  //     text-align    : center;            /* 文字位置   */
  //     padding       : 6px 5px;           /* 余白       */
  //     white-space   : nowrap;
  //   }
  // }

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
