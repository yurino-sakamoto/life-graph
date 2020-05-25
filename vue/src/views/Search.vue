<template>
  <div class="search">
    <Header />
    <h1>Let's Search LifeGraph !</h1>
    <div class="form-item">
      <label for="username" />
      <input v-model="likeName" placeholder="UserName">
      <label for="date" />
      <input v-model="startDate" type="date">
      〜〜
      <label for="date" />
      <input v-model="finishDate" type="date">
      <button class="btn" @click="searchGraphData()">
        Search
      </button>
    </div>
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
            <button v-if="!authCheck" @click="deleteGraphData(searchItem.id)">
              削除
            </button>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- <footer>
      <div class="nav-links">
        <a class="prev page-numbers" href="">«</a>
        <a class="page-numbers" href="">1</a>
        <span class="page-numbers current">2</span>
        <a class="page-numbers" href="">3</a>
        <span class="page-numbers dots">…</span>
        <a class="page-numbers" href="">10</a>
        <a class="next page-numbers" href="">»</a>
      </div>
    </footer> -->
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
      likeName: '',
      startDate: '',
      finishDate: '',
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
    this.$store.commit('search/clereSearchItems')
  },
  methods: {
    searchGraphData () {
      const data = {
        likeName: this.likeName,
        startDate: this.startDate,
        finishDate: this.finishDate
      }
      this.$store.dispatch('search/searchAPI', data)
    },
    userReference (userId) {
      this.$router.push({ name: 'Reference', params: { userId: userId } })
    },
    // TODO 管理者関連のの記述
    deleteGraphData (parentId) {
      if (confirm('本当に削除してよろしいですか？')) {
        this.$store.dispatch('search/deleteGraphData', parentId)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.search {
  margin-top: 80px;
  background: #F3F3F9;
  height: 100%;

  h1 {
    text-align: left;
    top: -25px;
    margin: 0 0 0 10px;
    padding: 30px;
    margin: 0 0 0 30px;
    font-size: 60px;
    font-weight: 800;
    line-height: 0.8em;
    letter-spacing: -1px;
  }

  h2 {
    text-align: center;
    padding: 20px;
    font-size: 40px;
    color: #565452;
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
    padding: 0.3em;
    margin-right: auto;
    margin-left : auto;
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
  // .nav-links{
  //   padding:2em;
  //   display:flex;
  //   justify-content:center;
  // }
  // a,span{
  //   width:50px;
  //   height:50px;
  //   margin:2px;
  //   line-height:50px;
  //   text-align:center;
  //   font-size:14px;
  //   font-weight:bold;
  //   text-decoration:none;
  //   background:#fff;
  //   color:#222;
  // }
  // a:hover{
  //   background:gold;
  //   border-radius:100%;
  // }
  // .current{
  //   background:gold;
  //   border-radius:100%;
  // }
  // .dots{
  //   background:none;
  // }
  // .headline{
  //   font-size: 42px;
  // }
  // .nav-list {
  //   text-align: center;
  //   padding: 10px 0;
  //   margin: 0 auto;
  // }
  // .nav-list-item {
  //   list-style: none;
  //   display: inline-block;
  //   margin: 0 20px;
  // }
}
</style>
