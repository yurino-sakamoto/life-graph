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
                  id="age"
                  ref="editor"
                  v-model="age"
                  type="number"
                  autocomplete="off"
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
                id="score"
                ref="editor"
                v-model="score"
                type="number"
                autocomplete="off"
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
                id="comment"
                ref="editor"
                v-model="comment"
                cols="30"
                rows="5"
                placeholder="内容を入力してください。"
              />
            </td>
          </tr>
        </table>
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
        <div class="warning">
          <br><p>※スコアは-100から100の範囲で指定してください。</p>
          <p>※コメントは250文字以内で入力してください。</p><br>
        </div>
        <div v-if="ageCheck">
          年齢が不正です
        </div>
        <div v-if="scoreCheck">
          スコアが不正です
        </div>
        <div v-if="editError">
          更新に失敗しました。
        </div>
        <div class="listInfo">
          <table>
            <thead>
              <tr>
                <th class="th2">
                  年齢
                </th>
                <th class="th2">
                  満足度
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
                <td>
                  {{ content.age }}
                </td>
                <td>
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
          <button
            class="button, reload"
            @click="update()"
          >
            更新
          </button>
        </div>
      </div>
    </div>
    <d class="editGraph">
      <div
        v-if="loaded"
        id="chart"
      >
        <Chart />
      </div>
    </d><br><br>
  </div>
</template>

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
  },
  mounted () {
    this.setContents()
  },
  methods: {
    setContents () {
      this.contents = this.$store.state.chart.contents.map((content) => {
        return {
          age: content.age,
          score: content.score,
          comment: content.comment
        }
      })
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
      // console.log(this.contents)
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
      const currentParentId = this.$store.state.chart.contents[0].parentId
      const currentUserId = this.$store.state.auth.userId
      const apiContents = {
        parentId: currentParentId,
        userId: currentUserId,
        children: this.$store.state.chart.contents
      }
      this.$store.dispatch('chart/editContent', apiContents)
      // console.log(apiContents)
      // const userId = this.$store.state.auth.userId
      // this.$store.dispatch('chart/addContent', userId)
    }
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
  text-align: right;
  line-height: 60%;
}

#input {
  float: left;
  background:#FFF;
  border-radius: 20px;
  color: #565452;
  width: 550px;
  height: 500px;
  font-size: 12pt;
  word-break: break-all;
  margin:0 2px 0 70px;
  padding: 20px;
  text-align: center;
  filter: drop-shadow(10px 10px 10px rgba(0,0,0,0.2))
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
  margin: 0 70px 0 -2px;
  padding: 20px;
  text-align: center;
  filter: drop-shadow(10px 10px 10px rgba(0,0,0,0.2))
}

.field {
  margin: 0 auto;
}

.th1 {
  text-align: left;
  padding: 0 2px 8px 0;
  width: 80px
}

.th2 {
  text-align: left;
  padding: 0 10px;
  width: 70px;
  background: #000;
}

td {
  text-align: left;
  width: 30px;
}

h1 {
  color: #565452;
  font-size: 40px;
  padding-top: 80px;
  text-align: left;
  margin: 10px 0 30px 40px;
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
  bottom: 360px;
  position: absolute;
  padding: 4px 8px;
  font-size: 12pt;
  cursor: pointer;
}

.button {
  width: 100px;
  height: 40px;
  border: none;
  outline: none;
  background:#FE5F52;
  color: #FFF;
  border-radius: 30px;
  right: 30px;
  bottom: 360px;
  position: absolute;
  padding: 4px 8px;
  font-size: 12pt;
  cursor: pointer;
}

.button.disabled {
  background: #ffbab3;
  cursor: pointer;
}

.deleteButton {
  position: absolute;
  right: 108px;
  width: 70px;
  border: none;
  outline: none;
  background:rgb(204,204,204);
  color: #FFF;
  border-radius: 30px;
  padding: 4px 8px;
  font-size: 12pt;
  cursor: pointer;
}

.editButton {
  position : absolute;
  right : 30px;
  width: 70px;
  border: none;
  outline: none;
  background:#FE5F52;
  color: #FFF;
  border-radius: 30px;
  padding: 4px 8px;
  font-size: 12pt;
  cursor: pointer;
}

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
}

.commentTable {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
  }

.reload {
  position : absolute;
  bottom : 30px;
  right : 30px;
}

#chart {
  padding: 8px 12px 8px 6px;
}
</style>
