<template>
  <div class="editorSection">
    <Header />
    <span class="editTitle">
      編集
    </span><br>
    <span class="editButton">
      <router-link to="/Top">
        Back
      </router-link>
    </span>
    <div class="editInfo">
      <p>グラフ情報入力</p>
      <div>
        <h>
          <label class="label" for="ageAdd" />年齢:
          <input v-model="ageAdd" type="text">歳
          <!-- 入力即表示される -->
          <!-- <p>{{ ageAdd }}</p> -->
        </h>
        <div class="e">
          <h>
            <label class="label" for="ScoreAdd">満足度:</label>
            <input v-model="scoreAdd" type="text">％
          </h>
          <h>
            <label class="label" for="CommentAdd">コメント:</label>
            <input v-model="commentAdd" type="text" placeholder="Add comments.">
          </h>
          <button class="clearForm" @click="reset">
            Reset
          </button>
          <button class="addForm" @click="add">
            Add
          </button>
        </div>
      </div>
    </div>
    <div class="editGraph">
      <div v-if="loaded" class="chart">
        <Chart />
      </div>
    </div>
  </div>
</template>

<script>
import Chart from '../components/Chart'

export default {
  components: {
    Chart
  },
  data () { // これでfunctionの役割
    // データの整理
    return {
      // storeにつなぐ代わり
      ageAdd: null,
      scoreAdd: null,
      commentAdd: '' // '初期値はここに入れておく'
    }
  },
  computed: { // チャートのレンダリングの時、読み込んでから表示できるようになるのと同じ
    loaded () {
      // console.log(this.$store.chart.age)
      return this.$store.state.chart.loaded // 梶くんのコード泣
    }
  },
  mounted () { // チャートのレンダリングの時、読み込んでから表示できるようになるのと同じ
    this.$store.dispatch('chart/load') // 梶くんのコード泣
  },
  methods: {
    reset () {
      // console.clear(this.ageAdd)
      // console.clear(this.scoreAdd)
      // console.clear(this.commentAdd)
      // storeに送るためにここにdispatchを
    },
    add () {
      console.log(this.ageAdd)
      console.log(this.scoreAdd)
      console.log(this.commentAdd)
      // storeに送るためにここにdispatchを
    }
  }
}
</script>

<style>
.editorSection {
  background: linear-gradient(to bottom, rgba(133,199,183,0.95),#FFF);
  width: 100%;
  height: 1000px;
  margin: 0 auto;
  text-align: center;
  padding: 30px;
  display: inline-block;
}
.editorTitle {
  color: #565452;
  font-size: 30px;
}
.editButton {
  background: #FFF;
  color: #565452;
}
li {
  list-style: none;
  text-align: center;
  width: 240px;
}
.editInfo {
  background:#FFF;
  border-radius: 25px;
  color: #565452;
  width: 40%;
  height: 400px;
  font-size: 12pt;
  word-break: break-all;
  margin: 30px auto;
  padding: 20px;
  text-align: center;
  filter: drop-shadow(10px 10px 10px rgba(0,0,0,0.2))
}
.cancelButton {
  padding: 6px;
}
.addButton{
  padding: 6px;
}
.editGraph {
  background:#FFF;
  color: #565452;
  border-radius: 25px;
  width: 50%;
  height: 400px;
  font-size: 12pt;
  word-break: break-all;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
  filter: drop-shadow(10px 10px 10px rgba(0,0,0,0.2))
}
</style>
