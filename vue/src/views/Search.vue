<template>
  <div class="search">
    <Header />
    <h1>Let's Search LifeGraph !</h1>
    <div class="form-item">
      <label for="username" />
      <input v-model="likeName" placeholder="UserName">
      from
      <label for="date" />
      <input v-model="startDate" type="date">
      to
      <label for="date" />
      <input v-model="finishDate" type="date">
      <button class="btn" @click="searchGraphData()">
        検索 <img class="searchimg" src="../assets/searchWhite.png">
      </button>
    </div>
    <div v-if="showResult" class="result">
      <h2>Search Results</h2>
      <p>並び替えができます</p>
      <div class="sortButton">
        <button class="sort" @click="sortByName()">
          ユーザー名
        </button>
        <button class="sort" @click="sortByDateUp()">
          更新日時（降順）
        </button>
        <button class="sort" @click="sortByDateDown()">
          更新日時（昇順）
        </button>
      </div>
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
            <button class="resultBtn" @click="userReference(searchItem.user_id)">
              参照
            </button>
            <button v-if="!authCheck" class="resultBtn" @click="deleteGraphData(searchItem.id)">
              削除
            </button>
          </tr>
        </tbody>
      </table>
    </div>
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
    },
    sortByName () {
      this.searchItems.sort(function (a, b) {
        if (a.userName > b.userName) return -1
      })
    },
    sortByDateUp () {
      this.searchItems.sort(function (a, b) {
        if (a.updated_at > b.updated_at) return -1
        if (a.updated_at < b.updated_at) return 1
        return 0
      })
    },
    sortByDateDown () {
      this.searchItems.sort(function (a, b) {
        if (a.updated_at < b.updated_at) return -1
        if (a.updated_at > b.updated_at) return 1
        return 0
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.search {
  padding-top: 80px;
  background: #F3F3F9;
  min-height: 700px;
  height: 100%;
  overflow: hidden;

  h1 {
    top: -25px;
    text-align: center;
    padding: 30px;
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
    color: #565452;
    font-weight: bold;

    input {
      background: #fafafa;
      border-bottom: 2px solid #e9e9e9;
      font-family: 'Open Sans', sans-serif;
      font-size: 1em;
      height: 45px;
      width: 100%;
      font: 15px/24px sans-serif;
      box-sizing: border-box;
      padding: 0.3em;
      transition: 0.3s;
      letter-spacing: 1px;
      color: #565452;
      border: 1px solid #CCCCCC;
      border-radius: 4px;
      margin: .4rem 0;
    }

    :focus {
      border: 1px solid #da3c41;
      outline: none;
      box-shadow: 0 0 5px 1px rgba(218,60,65, .5);
    }

    .searchimg{
      width: 15px;
      height: 15px;
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
    font-weight: bold;
    text-shadow:1px 1px 0px rgba(0,0,0,.1);
    box-shadow:0px 3px 0px #c1524e;
    cursor: pointer;

      :active {
        top:3px;
        box-shadow:none;
      }
    }

  .sortButton {
      display: block;
      margin-bottom: 60px;
      white-space: nowrap;
      height: 60px;
    }

  .result {
    margin: 2em 0;
    padding: 0.5em 1em;
    border: solid 3px #565452;
    border-radius: 8px;
    width: 60%;
    text-align: center;
    margin-left: auto;
    margin-right: auto;
    padding-bottom: 30px;

    h2 {
      padding: 20px;
      font-size: 40px;
      font-weight: bold;
      color: #565452;
    }

  p {
    font-size: 20px;
    color: #565452;
    font-weight: bold;
    border-bottom:2px solid #ccc;
    width: 70%;
    margin: 20px auto;
    padding-bottom: 20px;
    text-align: center;
  }

    table {
      color: #565452;
      margin-left: auto;
      margin-right: auto;
    }

    thead {
      white-space: nowrap;
    }
  }

  $btn-bg: #f26964;
  $btn-hover-bg: gray;
  $btn-color: #fff;
  $btn-hover-color: #000;

.sort {
    padding: 1em 1.5em 2em 1.5em;
    width: 30%;
    background-color: $btn-bg;
    cursor: pointer;
    border-radius: 8px;
    overflow: hidden;
    color: rgba(0, 0, 0, 0);
    text-shadow: 0 0 0 $btn-color, 0 3rem 0 $btn-hover-color;
    transition: background-color 150ms, text-shadow 200ms;
    font-size: 1.1rem;
    box-shadow: 0 0px 20px gray;

    &:hover {
        background-color: $btn-hover-bg;
        text-shadow: 0 -3rem 0 $btn-color, 0 0 0 $btn-hover-color;
    }
  }

  .resultBtn {
    margin: 20px;
    font-weight: bold;
  }
}
</style>
