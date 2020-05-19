<template>
  <div class="editorSection">
    <Header />
    <div class="editTitle">
      編集
    </div>
    <div class="backButon">
      <router-link to="/Top">
        Back
      </router-link>
    </div>
    <div class="editInfo">
      <div>
        <div class="edit">
          <p>
            <label class="label">年齢:
            </label>
            <input
              v-model="ageAdd"
              type="number"
            >歳
          <!-- 入力即表示される -->
          <!-- <p>{{ ageAdd }}</p> -->
          </p>
          <p>
            <label
              class="label"
              for="ScoreAdd"
            >満足度:
            </label>
            <input
              v-model="scoreAdd"
              type="number"
            >％
          </p>
          <p>
            <label
              class="label"
              for="CommentAdd"
            >
              コメント:
            </label>
            <input
              v-model="commentAdd"
              type="String"
            >
          </p>
            <button
              class="clearForm"
              @click="reset"
            >
              <!-- =#! いる？ クリックしてもどこにも遷移させない場合に指定 -->
              Reset
            </button>
            <button
              class="addForm"
              @click="add"
            >
              Add
            </button>
        </div>
      </div>
    </div>
    <div class="editGraph">
      <div
        v-if="true"
        class="chart"
      >
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
      ageAdd: null,
      scoreAdd: null,
      commentAdd: ''
    }
  },
  computed: {
    changeButtonText () {
      return this.editIndex === -1 ? '追加' : '編集'
    }
  },
  methods: {
    reset () {
      this.age = ''
      this.score = ''
      this.comment = ''
      // console.clear(this.ageAdd)
      // console.clear(this.scoreAdd)
      // console.clear(this.commentAdd)
    },
    add () {
      console.log(this.ageAdd)
      console.log(this.scoreAdd)
      console.log(this.commentAdd)
      const content = {
        age: this.ageAdd,
        score: this.scoreAdd,
        comment: this.commentAdd
      }
      this.$store.dispatch('chart/addContent', content) // chart/module化したときの約束(storeの階層化)。namespacedしてるから
      this.age = '' // add後に消す
      this.score = ''
      this.comment = ''
    }
  }
}
</script>

<style>
.editorSection {
  background: #F3F3F9;
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
.backButton {
  border: none;
  outline: none;
  background:#FE5F52;
  border-radius: 10px;
  padding: 6px;
  font-size: 12pt;
  color:#FFF;
}
li {
  list-style: none;
  text-align: center;
  width: 240px;
}
.editInfo {
  background:#FFF;
  border-radius: 20px;
  color: #565452;
  width: 40%;
  height: 400px;
  font-size: 12pt;
  word-break: break-all;
  margin: 10px auto;
  padding: 20px;
  text-align: center;
  filter: drop-shadow(10px 10px 10px rgba(0,0,0,0.2))
}
.clearForm {
  border: none;
  outline: none;
  background:#FE5F52;
  border-radius: 10px;
  margin: 10px auto;
  padding: 6px;
  font-size: 12pt;
  color:#FFF;
}
.addForm {
  border: none;
  outline: none;
  background:#FE5F52;
  border-radius: 10px;
  margin: 20px auto;
  padding: 6px;
  font-size: 12pt;
  color:#FFF;
}
.editGraph {
  background:#FFF;
  color: #565452;
  border-radius: 20px;
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
