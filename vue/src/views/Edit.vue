<template>
  <div class="editSection">
    <div>
      <Header />
    </div>
    <div id="editForm">
      <h1>編集</h1>
      <div id="input">
        <table id="field">
          <tr>
            <th class="th1" scope="row">
              <label for="age">
                年齢(歳)
              </label>
            </th>
            <th>
              <td>
                <input
                  id="ageInput"
                  ref="editor"
                  v-model="age"
                  type="number"
                  style="font-size:20px"
                  autocomplete="off"
                  placeholder="必須"
                  maxlength="3"
                  @keyup.enter="changeContents"
                >
              </td>
            </th>
          </tr>
          <tr>
            <th class="th1" scope="row">
              <label for="score">
                満足度(%)
              </label>
            </th>
            <td>
              <input
                id="scoreInput"
                ref="editor"
                v-model="score"
                type="number"
                style="font-size:20px"
                autocomplete="off"
                placeholder="必須"
                maxlength="3"
              >
            </td>
          </tr>
          <tr>
            <th class="th1" scope="row">
              <label for="comment">
                コメント
              </label>
            </th>
            <td>
              <textarea
                id="commentInput"
                ref="editor"
                v-model="comment"
                style="font-size:16px"
                cols="30"
                rows="5"
                placeholder="内容を入力してください。"
                maxlength="255"
              />
            </td>
          </tr>
        </table>
        <div class="warning">
          <br><p>※スコアは-100から100の範囲で指定してください</p>
          <p>※コメントは250文字以内で入力してください</p><br>
        </div>
        <button
          class="resetButton"
          @click="removetext(age,score,comment)"
        >
          リセット
        </button>
        <button
          class="button"
          :disabled="!inputCheck || ageCheck || scoreCheck || ageExistCheck"
          :class="{'disabled': ageCheck || scoreCheck || !inputCheck}"
          @click="add"
        >
          {{ changeButtonText }}
        </button>
        <div v-if="ageCheck" class="ageCheck">
          年齢は1〜100で入力してください
        </div>
        <div v-if="scoreCheck" class="scoreCheck">
          満足度は-100〜100で入力してください
        </div>
        <div v-if="editError" class="editError">
          更新に失敗しました
        </div>
        <div class="listInfo">
          <table class="tableRecord">
            <thead>
              <tr>
                <th class="th2">
                  年齢(歳)
                </th>
                <th class="th2">
                  満足度(%)
                </th>
                <th class="th2">
                  コメント
                </th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(content,index) in contents"
                :key="index"
              >
                <td class="addRecord">
                  {{ content.age }}
                </td>
                <td class="addRecord">
                  {{ content.score }}
                </td>
                <td class="commentTable">
                  {{ content.comment }}
                </td>
                <button
                  class="deleteButton"
                  @click="deleteContents(index)"
                >
                  削除
                </button>
                <button
                  class="editButton"
                  @click="edit(index)"
                >
                  修正
                </button>
              </tr>
            </tbody>
          </table>
          <div class="marginConfig" />
          <button
            class="button, reload"
            @click="update()"
          >
            更新
          </button>
        </div>
      </div>
      <div class="editGraph">
        <div
          class="chart"
        >
          <Chart refs="chart" />
        </div>
      </div><br><br>
    </div>
  </div>
</template>
<!--refs="？？？？"でチャートを操作できるようにする-->
<script>
import Header from '../components/Header.vue'
import Chart from '../components/Chart.vue'
export default {
  name: 'Edit',
  components: {
    Header,
    Chart
  },
  data () {
    return {
      age: null,
      score: null,
      comment: '',
      isActive: false,
      contents: [], // ここに情報あると空のデータが存在してしまう
      load: true,
      editIndex: -1 // 初期値-1だと
    }
  },
  computed: {
    loaded () {
      return this.$store.state.chart.loaded
    },
    changeButtonText () {
      return this.editIndex === -1 ? '追加' : '完了'
    },
    inputCheck () {
      return this.age && this.score
    },
    ageCheck () {
      return this.age < 0 || this.age > 100
    },
    scoreCheck () {
      return this.score < -100 || this.score > 100
    },
    ageExistCheck () {
      return this.contents.find(content => content.age === this.age)
    },
    editError () {
      return this.$store.state.chart.error
    }
  },
  created () {
    this.$store.commit('chart/clearError')
    this.$store.dispatch('chart/addContent', this.$store.state.auth.userId)
  },
  mounted () {
    this.setContents()
  },
  methods: {
    setContents () {
      this.contents = this.$store.state.chart.contents.slice()
    },
    // テキストボックス値削除
    removetext: function (removeitem) {
      this.age = ''
      this.score = ''
      this.comment = ''
    },
    add () {
      this.isActive = true
      if (this.editIndex === -1) { // ifの時addボタンの挙動
        this.contents.push(
          {
            age: this.age,
            score: this.score,
            comment: this.comment
          })
      } else { // elseの時editボタンの挙動
        this.contents.splice(
          this.editIndex,
          1,
          {
            age: this.age,
            score: this.score,
            comment: this.comment
          }
        )// splice配列の最初？配列から要素を削除・追加して組み替え。１は第２引数で削除する数
        this.editIndex = -1
      }
      this.age = ''
      this.score = ''
      this.comment = ''
    },
    edit (index) {
      this.editIndex = index
      this.age = this.contents[index].age // indexに応じたageが出てくる
      this.score = this.contents[index].score
      this.comment = this.contents[index].comment
      this.$refs.editor.focus() // フォーカスを設定
    },
    deleteContents (index) {
      this.contents.splice(index, 1)
    },
    update () {
      this.$store.dispatch(
        'chart/addData',
        this.contents
      )
      const currentParentId = this.$store.state.chart.parentId
      const currentUserId = this.$store.state.auth.userId
      const apiContents = {
        parentId: currentParentId,
        userId: currentUserId,
        children: this.$store.state.chart.contents
      }
      // console.log('API叩く前')
      this.$store.dispatch('chart/editContent', apiContents)
      // console.log('API叩いた')
      // console.log(apiContents)
      // const userId = this.$store.state.auth.userId
      // this.$store.dispatch('chart/addContent', userId)
      console.log('レフ図の前')
      this.$refs.chart.createChart()
    } // refs="chart"を付けたコンポーネントのcreateChart()メソッドを起動する。
  }
}
</script>

<style>
.editSection {
  background: #F3F3F9;
  width: 100%;
  height: 900px;
  text-align: center;
  /* padding:100px; */
}

.warning {
  font-size: 12px;
  text-align: left;
  line-height: 60%;
  left: 340px;
  bottom: 90px;
  position: relative;
}

#input {
  float: left;
  background:#FFF;
  border-radius: 20px;
  color: #565452;
  width: 620px;
  font-size: 12pt;
  word-break: break-all;
  margin:0 2px 0 50px;
  padding: 20px;
  text-align: center;
  filter: drop-shadow(10px 10px 10px rgba(0,0,0,0.2))
}

#ageInput {
  border: none;
  outline: none;
  width: 70px;
  height: 32px;
  border-radius: 10%;
  background: #F2F3F4;
  color: #565452;
}

#scoreInput {
  border: none;
  outline: none;
  width: 70px;
  height: 32px;
  border-radius: 10%;
  background: #F2F3F4;
  color: #565452;
}

#commentInput {
  border: none;
  outline: none;
  width: 220px;
  height: 80px;
  border-radius: 10%;
  background:#F2F3F4;
  color: #565452;
}

.marginConfig {
  height: 90px;
  background: #FFF;
}

.editGraph {
  float: right;
  background:#FFF;
  color: #565452;
  border-radius: 20px;
  width: 600px;
  height: 400px;
  font-size: 12pt;
  word-break: break-all;
  margin: 0 50px 0 -2px;
  padding: 20px;
  text-align: center;
  filter: drop-shadow(10px 10px 10px rgba(0,0,0,0.2))
}

.field {
  margin: 0 0 30px 0;
}

.tableRecord {
  margin: 20px 0 0 0;
}

.th1 {
  text-align: left;
  padding: 0 2px 8px 0;
  width: 80px
}

.th2 {
  text-align: left;
  padding: 0 10px;
  width: 80px;
}

.listInfo {
  margin: 30px 0 0 0;
}

td {
  text-align: left;
  padding: 0 5px;
  width: 64px;
}

.addRecord {
  padding: 10px 0;
  text-align: center;
}

.commentTable {
  width: 240px;
  padding: 5px 0 5px 16px;
  text-align: left;
}

tr {
  height: auto;
  /* margin: 200px 0; */
}

h1 {
  color: #565452;
  font-size: 40px;
  padding-top: 80px;
  text-align: left;
  margin: 20px 0 30px 40px;
}
.resetButton {
  width: 100px;
  height: 40px;
  border: none;
  outline: none;
  background:rgb(204,204,204);
  color: #FFF;
  border-radius: 30px;
  right: 136px;
  top: 180px;
  position: absolute;
  padding: 4px 8px;
  font-size: 12pt;
  cursor: pointer;
  /* .hover {
    background-color: #8566ce;
    color: #FFF;
    } */
}

/* 追加→完了 */
.button {
  width: 100px;
  height: 40px;
  border: none;
  outline: none;
  background:#FE5F52;
  color: #FFF;
  border-radius: 30px;
  right: 30px;
  top: 180px;
  position: absolute;
  padding: 4px 8px;
  font-size: 12pt;
  cursor: pointer;
  /* .hover {
    background-color: #8566ce;
    color: #FFF;
    } */
}

.button.disabled {
  background: #ffbab3;
  cursor: pointer;
}

.deleteButton {
  position: absolute;
  right: 96px;
  width: 60px;
  border: none;
  outline: none;
  background:rgb(204,204,204);
  color: #FFF;
  padding: 4px;
  border-radius: 30px;
  font-size: 12pt;
  cursor: pointer;
  /* .hover {
    background-color: #8566ce;
    color: #FFF;
    } */
}

.editButton {
  position : absolute;
  right : 30px;
  width: 60px;
  border: none;
  outline: none;
  background:#FE5F52;
  color: #FFF;
  padding: 4px;
  border-radius: 30px;
  font-size: 12pt;
  cursor: pointer;
  /* :hover {
    background-color: #8566ce;
    color: #FFF;
    } */
}

/* 更新 */
button {
  width: 90px;
  height: 40px;
  border: none;
  outline: none;
  background:#FE5F52;
  color: #FFF;
  border-radius: 30px;
  margin: 0 5px;
  padding: 4px 8px;
  font-size: 12pt;
  cursor: pointer;
  /* :hover {
    background-color: #8566ce;
    color: #FFF;
    } */
}

.reload {
  position : absolute;
  bottom : 30px;
  right : 30px;
}

.chart {
  padding: 8px 12px 8px 6px;
  z-index: 90;
}

/* 入力フォーム */

/* バリデーション */
.ageCheck {
  position: relative;
  left: 40px;
  font-size: 1em;
  color: #F6FB17;
  background: #565452;
  width: 126px;
  text-align: center;
  margin: 2px;
}

.scoreCheck {
  position: relative;
  left: 40px;
  font-size: 1em;
  color: #F6FB17;
  background: #565452;
  width: 140px;
  text-align: center;
  margin: 2px;
}

.editError {
  position: relative;
  left: 40px;
  font-size: 1em;
  color: #F6FB17;
  background: #565452;
  width: 160px;
  text-align: center;
  margin: 2px;
}
</style>
