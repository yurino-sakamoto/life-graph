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
        <p>
          <label class="label" for="ageAdd">年齢:
          </label>
          <input v-model="ageAdd" type="text">歳
        <!-- 入力即表示される -->
        <!-- <p>{{ ageAdd }}</p> -->
        </p>
        <div class="e">
          <p>
            <label class="label" for="ScoreAdd">満足度:
            </label>
            <input v-model="scoreAdd" type="text">％
          </p>
          <p>
            <label
              class="label"
              for="CommentAdd"
            >
              コメント:
            </label>
            <textarea
              v-model="commentAdd"
              placeholder="Add comments."
            />
          </p>
          <button
            class="clearForm"
            href="#!"
            @click="reset"
          >
            <div />
            Reset
          </button>
          <!-- =#! クリックしてもどこにも遷移させない場合に指定 -->
          <button
            class="addForm"
            href="#!"
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
      // storeにつなぐ代わり??
      ageAdd: null,
      scoreAdd: null,
      commentAdd: '' // '初期値はここに入れておく'
    }
  },
  computed: { // 編集する
  },
  methods: {
    reset () {
      this.age = ''
      this.score = ''
      this.comment = ''
      console.clear(this.ageAdd)
      console.clear(this.scoreAdd)
      console.clear(this.commentAdd)
    },
    add () {
      console.log(this.ageAdd)
      console.log(this.scoreAdd)
      console.log(this.commentAdd)
      const content = {
        age: this.age,
        score: this.score,
        comment: this.comment
      }
      this.$store.dispatch('chart/addContent', content)
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
.clearForm {
  background:#FE5F52;
  border-radius: 25px;
  padding: 6px;
  font-size: 12pt;
  color:#FFF;
}
.addForm {
  background:#FE5F52;
  border-radius: 25px;
  padding: 6px;
  font-size: 12pt;
  color:#FFF;
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
